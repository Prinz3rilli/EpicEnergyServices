package it.epicode.crm.cliente;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import it.epicode.crm.impl.LoginRequest;
import it.epicode.crm.model.TipoCliente;
import it.epicode.crm.requests.InserisciClienteRequest;
import it.epicode.crm.requests.ModificaClienteRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ClienteControllerTest {
	
	@Autowired TestRestTemplate restTemplate;
	@LocalServerPort int port;
	
	@Test
	
	void elencoClientiPaginato() {
		//primo test
				String url = "http://localhost:" + port + "/clienti/cerca";
				log.info("----------------elencoClientiPaginato " + url + "------GET non autorizzato" );
				ResponseEntity<String> r = restTemplate.getForEntity(url, String.class);
				assertThat(r.getStatusCode()).isEqualByComparingTo(HttpStatus.UNAUTHORIZED);
				
				//secondo test con admin
				HttpEntity<String> adminEntity = new HttpEntity<String>(getAdminHeader()); // uguale al token di swagger
				log.info("----------------getAllClienti" + url + "------GET autorizzato ad admin");
				r = restTemplate.exchange(url, HttpMethod.GET, adminEntity, String.class); // chiamata usando il token
				assertThat(r.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
				
				//terzo test con user
				HttpEntity<String> userEntity = new HttpEntity<String>(getUserHeader()); // uguale al token di swagger
				log.info("----------------getAllClienti" + url + "------GET autorizzato user");
				r = restTemplate.exchange(url, HttpMethod.GET, userEntity, String.class); // chiamata usando il token
				assertThat(r.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
	}
	
	@Test
	void inserisciCliente() {
		String url = "http://localhost:" + port + "/clienti/inserisci"; 
		InserisciClienteRequest clienteDTO = new InserisciClienteRequest();
		
		clienteDTO.setCognomeContatto("Rossi");
		clienteDTO.setDataInserimento("01 febbraio 2022");
		clienteDTO.setDataUltimoContatto("03 febbraio 2022");
		clienteDTO.setEmail("aziendaUno@miamail.com");
		clienteDTO.setEmailContatto("mrossi@miamail.com");
		clienteDTO.setFatturatoAnnuale(150000);
		clienteDTO.setNomeContatto("Mario");
		clienteDTO.setPartitaIva("IT23485950");
		clienteDTO.setPec("aziendaUno@pecmail.com");
		clienteDTO.setRagioneSociale("Societa a Resposabilita Limitata");
		clienteDTO.setTelefono("338992xxx");
		clienteDTO.setTelefonoContatto("334786xxx");
		clienteDTO.setTipoCliente(TipoCliente.SRL);
		
		//test non autorizzato
		HttpEntity<InserisciClienteRequest> clienteEntity = new HttpEntity<InserisciClienteRequest>(clienteDTO);
		log.info("----------------inserisciCliente" + url + "------POST");
		ResponseEntity<String> r = restTemplate.exchange(url, HttpMethod.POST, clienteEntity, String.class);
		assertThat(r.getStatusCode()).isEqualByComparingTo(HttpStatus.UNAUTHORIZED);
		
		//test con autorizzazione admin
		HttpEntity<InserisciClienteRequest> adminEntity = new HttpEntity<InserisciClienteRequest>(clienteDTO, getAdminHeader());
		log.info("----------------inserisciCliente" + url + "------POST");
		r = restTemplate.exchange(url, HttpMethod.POST,adminEntity , String.class);
		assertThat(r.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
		
		//test con user non autorizzato
		HttpEntity<InserisciClienteRequest> userEntity = new HttpEntity<InserisciClienteRequest>(clienteDTO, getUserHeader());
		log.info("----------------inserisciCliente" + url + "------POST");
		r = restTemplate.exchange(url, HttpMethod.POST,userEntity , String.class);
		assertThat(r.getStatusCode()).isEqualByComparingTo(HttpStatus.FORBIDDEN);
	}
	
	@Test
	void modificaCliente() {
		String url = "http://localhost:" + port + "/clienti/modifica/1"; 
		
		ModificaClienteRequest clienteDTO = new ModificaClienteRequest();
		clienteDTO.setCognomeContatto("Bianchi");
		clienteDTO.setDataInserimento("01 febbraio 2022");
		clienteDTO.setDataUltimoContatto("03 febbraio 2022");
		clienteDTO.setNomeContatto("Carlo");
		clienteDTO.setPartitaIva("IT23485932");
		clienteDTO.setRagioneSociale("Societa a Resposabilita Limitata");
		clienteDTO.setTipoCliente(TipoCliente.SRL);
		
		HttpEntity<ModificaClienteRequest> clienteEntity = new HttpEntity<ModificaClienteRequest>(clienteDTO);
		log.info("----------------modificaCliente" + url + "------PUT non autorizzato");
		ResponseEntity<String> r = restTemplate.exchange(url, HttpMethod.PUT, clienteEntity, String.class);
		assertThat(r.getStatusCode()).isEqualByComparingTo(HttpStatus.UNAUTHORIZED);
		
		HttpEntity<ModificaClienteRequest> adminEntity = new HttpEntity<ModificaClienteRequest>(clienteDTO, getAdminHeader());
		log.info("----------------modificaCliente" + url + "------PUT autorizzato con Admin");
		r = restTemplate.exchange(url, HttpMethod.PUT, adminEntity , String.class);
		assertThat(r.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
		
		HttpEntity<ModificaClienteRequest> userEntity = new HttpEntity<ModificaClienteRequest>(clienteDTO, getUserHeader());
		log.info("----------------modificaCliente" + url + "------PUT non autorizzato con User");
		r = restTemplate.exchange(url, HttpMethod.PUT,userEntity , String.class);
		assertThat(r.getStatusCode()).isEqualByComparingTo(HttpStatus.FORBIDDEN);
}
	@Test
	void eliminaClienteByPartitaIva() {
String url = "http://localhost:" + port + "/clienti/1"; 
		
		log.info("----------------cancellaCliente" + url + "------DELETE");
		ResponseEntity<String> r = restTemplate.exchange(url, HttpMethod.DELETE, HttpEntity.EMPTY, String.class);
		assertThat(r.getStatusCode()).isEqualByComparingTo(HttpStatus.UNAUTHORIZED);
		
		HttpEntity<String> adminEntity = new HttpEntity<String>(getAdminHeader());
		log.info("----------------cancellaCliente" + url + "------DELETE");
		r = restTemplate.exchange(url, HttpMethod.DELETE, adminEntity, String.class);
		assertThat(r.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
		
		HttpEntity<String> userEntity = new HttpEntity<String>(getUserHeader());
		log.info("----------------cancellaCliente" + url + "------DELETE");
		r = restTemplate.exchange(url, HttpMethod.DELETE, userEntity, String.class);
		assertThat(r.getStatusCode()).isEqualByComparingTo(HttpStatus.FORBIDDEN);

}

	protected String getAdminToken() {
		String url = "http://localhost:" + port + "/api/auth/login/jwt";
		LoginRequest login = new LoginRequest();
		login.setUserName("priscilla");
		login.setPassword("prinzerilli");
		HttpEntity<LoginRequest> loginRequest = new HttpEntity<LoginRequest>(login); //RequestBody
		String jwt = restTemplate.postForObject(url, loginRequest, String.class);
		log.info("---------" + jwt);
		return jwt;
	}
	
	protected String getUserToken() {
		String url = "http://localhost:" + port + "/api/auth/login/jwt";
		LoginRequest login = new LoginRequest();
		login.setUserName("prisci");
		login.setPassword("prinze");
		HttpEntity<LoginRequest> loginRequest = new HttpEntity<LoginRequest>(login); //RequestBody
		String jwt = restTemplate.postForObject(url, loginRequest, String.class);
		return jwt;
	}
	
	protected HttpHeaders getAdminHeader() {
		HttpHeaders header = new HttpHeaders();
		String jwt = getAdminToken();
		header.set("Authorization", "Bearer " + jwt);
		log.info(jwt);
		return header;
	}
	
	protected HttpHeaders getUserHeader() {
		HttpHeaders header = new HttpHeaders();
		String jwt = getUserToken();
		header.set("Authorization", "Bearer " + jwt);
		return header;
	}
	

}
