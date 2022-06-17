package it.epicode.crm.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import it.epicode.crm.exceptions.ElementAlreadyPresentException;
import it.epicode.crm.exceptions.ElementNotFoundException;
import it.epicode.crm.requests.UtenteRequest;
import it.epicode.crm.services.UtenteService;

@RestController
@RequestMapping("/utenti")
@Tag(name = "Utenti rest servicies", description = "implementazioni delle api rest degli utenti")
public class UtenteController {

	@Autowired 
	UtenteService utenteService;
	
	@Operation(summary = "Inserisce un utente nel DB",  description = "Inserimento di un utente")
	@ApiResponse(responseCode = "200", description = "Utente registrato correttamente")
	@ApiResponse(responseCode = "500", description = "ERRORE nell'inserimento")
	@PostMapping("/inserisciUtente")
	public ResponseEntity registraUtente(@Valid @RequestBody UtenteRequest request) throws ElementAlreadyPresentException, ElementNotFoundException {
		utenteService.registraUtente(request);
		return ResponseEntity.ok("Utente inserito");
	}
	
	
	@Operation(summary = "Trova Utenti nel DB", description = "ritorna la lista di tutti gli utenti presenti")
	@ApiResponse(responseCode = "200", description = "Utenti trovati")
	@ApiResponse(responseCode = "400", description = "Utenti non trovati")
	@GetMapping("/getAllUtenti")
	@SecurityRequirement(name = "bearerAuth")
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	public ResponseEntity elencoUtenti() {
		return ResponseEntity.ok(utenteService.elencoUtenti());
	}

}
