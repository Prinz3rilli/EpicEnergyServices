package it.epicode.crm.controller;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import it.epicode.crm.exceptions.ElementAlreadyPresentException;
import it.epicode.crm.exceptions.ElementNotFoundException;
import it.epicode.crm.model.Cliente;
import it.epicode.crm.repository.ClienteRepository;
import it.epicode.crm.requests.InserisciClienteRequest;
import it.epicode.crm.requests.ModificaClienteRequest;
import it.epicode.crm.services.ClienteService;

@RestController
@RequestMapping("/clienti")
@Tag(name = "Clienti rest Services", description = "implementazioni delle api rest dei clienti")
public class ClienteController {
	@Autowired
	ClienteService clienteServ;
	@Autowired
	ClienteRepository clienteRepo;
	
	@Operation(summary = "inserisce un cliente nel DB", description = "Inserimento di un cliente con rispettivi attributi")
	@ApiResponse(responseCode = "200", description = "cliente inserito correttamente")
	@ApiResponse(responseCode = "500", description = "Errore nell'inserimento")
	@PostMapping("/inserisci")
	@SecurityRequirement(name = "bearerAuth")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity inserisciCliente(@RequestBody InserisciClienteRequest request) throws ElementAlreadyPresentException {
		clienteServ.inserisciCliente(request);
		return ResponseEntity.ok("Cliente inserito con successo");
	}
	
	@Operation(summary = "Ricerca un cliente nel DB", description = "Ricerca di un cliente con relativi attributi")
	@ApiResponse(responseCode = "200", description = "cliente trovato correttamente")
	@ApiResponse(responseCode = "500", description = "Errore nella ricerca")
	@GetMapping("/cerca/{id}")
	@SecurityRequirement(name = "bearerAuth")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity getClienteById(@PathVariable String partitaIva) throws ElementNotFoundException {
		Cliente cliente = clienteServ.getClienteById(partitaIva);
		if(cliente == null) {
			return new ResponseEntity("Errore di ricerca", HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(cliente);
	}
	
	@Operation(summary = "Ricerca una lista di clienti nel DB", description = "Ricerca di una lista di clienti con relativi attributi")
	@ApiResponse(responseCode = "200", description = "Lista clienti trovata correttamente")
	@ApiResponse(responseCode = "500", description = "Errore nella ricerca")
	@GetMapping("/cerca")
	@SecurityRequirement(name = "bearerAuth")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity elencoClientiPaginato() {
		return ResponseEntity.ok(clienteServ.elencoClientiPaginato(null));
	}
	
	@Operation(summary = "Modifica un cliente nel DB", description = "Modifica di un cliente")
	@ApiResponse(responseCode = "200", description = "clientemodificato correttamente")
	@ApiResponse(responseCode = "500", description = "Errore nell'inserimento")
	@PutMapping("/modifica/{id}")
	@SecurityRequirement(name = "bearerAuth")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity modificaCliente(@PathVariable String partitaIva, ModificaClienteRequest request) throws ElementNotFoundException {
		if(clienteServ.modificaCliente(request)) {
			return ResponseEntity.ok("Cliente modificato");
		}
		return new ResponseEntity("Errore nella modifica", HttpStatus.BAD_REQUEST);
	}
	
	@Operation(summary = "Elimina un cliente nel DB", description = "Eliminazione di un cliente per partita iva")
	@ApiResponse(responseCode = "200", description = "cliente eliminato correttamente")
	@ApiResponse(responseCode = "500", description = "Errore nell'eliminazione")
	@DeleteMapping("/cancella/{id}")
	@SecurityRequirement(name = "bearerAuth")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity eliminaClienteByPartitaIva(@PathVariable String partitaIva) throws ElementNotFoundException {
		clienteServ.eliminaClienteByPartitaIva(partitaIva);
		return ResponseEntity.ok("Cliente eliminato");
}
}