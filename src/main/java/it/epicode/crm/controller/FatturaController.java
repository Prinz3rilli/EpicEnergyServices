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
import it.epicode.crm.model.Fattura;
import it.epicode.crm.repository.FatturaRepository;
import it.epicode.crm.requests.InserisciFatturaRequest;
import it.epicode.crm.requests.ModificaFatturaRequest;
import it.epicode.crm.services.FatturaService;


@RestController
@RequestMapping("/fatture")
@Tag(name = "Fatture rest Services", description = "implementazioni delle api rest delle fatture")
public class FatturaController {
	
	@Autowired
	FatturaService fatturaServ;
	@Autowired
	FatturaRepository fatturaRepo;
	
	@Operation(summary = "inserisce una fattura nel DB", description = "Inserimento di una fattura")
	@ApiResponse(responseCode = "200", description = "fattura inserita correttamente")
	@ApiResponse(responseCode = "500", description = "Errore nell'inserimento")
	@PostMapping("/inserisci")
	@SecurityRequirement(name = "bearerAuth")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity inserisciFattura(@RequestBody InserisciFatturaRequest request) throws ElementAlreadyPresentException {
		fatturaServ.inserisciFattura(request);
		return ResponseEntity.ok("Fattura inserito con successo");
	}
	
	@Operation(summary = "Ricerca una fattura nel DB", description = "Ricerca di una fattura")
	@ApiResponse(responseCode = "200", description = "fattura trovata correttamente")
	@ApiResponse(responseCode = "500", description = "Errore nella ricerca")
	@GetMapping("/cerca/{id}")
	@SecurityRequirement(name = "bearerAuth")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity getFatturaByNumero(@PathVariable int numero) throws ElementNotFoundException {
		Fattura fattura = fatturaServ.getFatturaByNumero(numero);
		if(fattura == null) {
			return new ResponseEntity("Errore di ricerca", HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(fattura);
	}
	
	@Operation(summary = "Ricerca una lista di fatture nel DB", description = "Ricerca di una lista di fatture")
	@ApiResponse(responseCode = "200", description = "Lista fatture trovata correttamente")
	@ApiResponse(responseCode = "500", description = "Errore nella ricerca")
	@GetMapping("/cerca")
	@SecurityRequirement(name = "bearerAuth")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity elencoFatturePaginato() {
		return ResponseEntity.ok(fatturaServ.elencoFatturePaginato(null));
	}
	
	@Operation(summary = "Modifica una fattura nel DB", description = "Modifica di una fattura")
	@ApiResponse(responseCode = "200", description = "fattura modificato correttamente")
	@ApiResponse(responseCode = "500", description = "Errore nell'inserimento")
	@PutMapping("/modifica/{id}")
	@SecurityRequirement(name = "bearerAuth")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity modificaFattura(@PathVariable int numero, ModificaFatturaRequest request) throws ElementNotFoundException {
		if(fatturaServ.modificaFattura(request)) {
			return ResponseEntity.ok("fattura modificata");
		}
		return new ResponseEntity("Errore nella modifica", HttpStatus.BAD_REQUEST);
	}
	
	@Operation(summary = "Elimina una fattura nel DB", description = "Eliminazione di una fattura per numero")
	@ApiResponse(responseCode = "200", description = "fattura eliminata correttamente")
	@ApiResponse(responseCode = "500", description = "Errore nell'eliminazione")
	@DeleteMapping("/cancella/{id}")
	@SecurityRequirement(name = "bearerAuth")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity eliminaFatturaByNumero(@PathVariable int numero) throws ElementNotFoundException {
		fatturaServ.eliminaFatturaByNumero(numero);
		return ResponseEntity.ok("Fattura eliminata");
}
}
	
	


