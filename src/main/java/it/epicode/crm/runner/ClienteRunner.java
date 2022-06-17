package it.epicode.crm.runner;



import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import it.epicode.crm.model.Cliente;
import it.epicode.crm.model.Fattura;
import it.epicode.crm.model.Sede;
import it.epicode.crm.model.StatoFattura;
import it.epicode.crm.model.TipoSede;
import it.epicode.crm.model.Utente;

import it.epicode.crm.repository.FatturaRepository;

import it.epicode.crm.repository.SedeRepository;
import it.epicode.crm.repository.UtenteRepository;

@Component
public class ClienteRunner implements CommandLineRunner {
	
	@Autowired
	FatturaRepository fatturaRepo;
	@Autowired
	SedeRepository sedeRepo;
	@Autowired
	UtenteRepository utenteRepo;
	

	@Override
	public void run(String... args) throws Exception {
		String hash = BCrypt.hashpw("ciao", BCrypt.gensalt());
		Date dataToSet = new Date();
		Cliente cliente1 = new Cliente();
		cliente1.setPartitaIva("12374IY");
		cliente1.setCognomeContatto("Rossi");
		cliente1.setRagioneSociale("Telegram");
		
		utenteRepo.save(Utente.builder().nome("priscilla").cognome("inzerilli").username("prinzerilli").eMail("p.inzerilli@miamail.com").password(hash).build());
		utenteRepo.save(Utente.builder().nome("mario").cognome("verdi").username("mverdi").eMail("m.verdi@miamail.com").password(BCrypt.hashpw("1234", BCrypt.gensalt())).build());
		
		fatturaRepo.save(Fattura.builder().numero(1).anno(2022).data(dataToSet).importo(new BigDecimal("500.000")).statoFattura(StatoFattura.Emessa).cliente(cliente1).build());
		
		sedeRepo.save(Sede.builder().id(01).via("via Roma").civico("32").località("tuscia").cap("00164").comune("Viterbo").tipoSede(TipoSede.SedeLegale).cliente(cliente1).build());
		
		
		
	}
	
}
