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
import it.epicode.crm.model.Sede;
import it.epicode.crm.repository.SedeRepository;
import it.epicode.crm.requests.InserisciSedeRequest;
import it.epicode.crm.requests.ModificaSedeRequest;
import it.epicode.crm.services.SedeService;

@RestController
@RequestMapping("/sedi")
@Tag(name = "Sedi rest Services", description = "implementazioni delle api rest delle sedi") 
public class SedeController {
	
	@Autowired
	SedeRepository sedeRepo;
	@Autowired
	SedeService sedeServ;
	
	@Operation(summary = "inserisce una sede nel DB", description = "Inserimento di una sede")
	@ApiResponse(responseCode = "200", description = "sede inserita correttamente")
	@ApiResponse(responseCode = "500", description = "Errore nell'inserimento")
	@PostMapping("/inserisci")
	@SecurityRequirement(name = "bearerAuth")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity inserisciSede(@RequestBody InserisciSedeRequest request) throws ElementAlreadyPresentException {
		sedeServ.inserisciSede(request);
		return ResponseEntity.ok("Sede inserita con successo");
	}
	
	@Operation(summary = "Ricerca una sede nel DB", description = "Ricerca di una sede")
	@ApiResponse(responseCode = "200", description = "sede trovata correttamente")
	@ApiResponse(responseCode = "500", description = "Errore nella ricerca")
	@GetMapping("/cerca/{id}")
	@SecurityRequirement(name = "bearerAuth")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity getSedeById(@PathVariable int id) throws ElementNotFoundException {
		Sede sede = sedeServ.getSedeById(id);
		if(sede == null) {
			return new ResponseEntity("Errore di ricerca", HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(sede);
	}
	
	@Operation(summary = "Ricerca una lista di sedi nel DB", description = "Ricerca di una lista di sedi")
	@ApiResponse(responseCode = "200", description = "Lista sedi trovata correttamente")
	@ApiResponse(responseCode = "500", description = "Errore nella ricerca")
	@GetMapping("/cerca")
	@SecurityRequirement(name = "bearerAuth")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity elencoSedi() {
		return ResponseEntity.ok(sedeServ.elencoSedi());
	}
	
	@Operation(summary = "Modifica una sede nel DB", description = "Modifica di una sede")
	@ApiResponse(responseCode = "200", description = "sede modificata correttamente")
	@ApiResponse(responseCode = "500", description = "Errore nell'inserimento")
	@PutMapping("/modifica/{id}")
	@SecurityRequirement(name = "bearerAuth")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity modificaSede(@PathVariable int id, ModificaSedeRequest request) throws ElementNotFoundException {
		if(sedeServ.modificaSede(request)) {
			return ResponseEntity.ok("Sede modificata");
		}
		return new ResponseEntity("Errore nella modifica", HttpStatus.BAD_REQUEST);
	}
	
	@Operation(summary = "Elimina uan sede nel DB", description = "Eliminazione di una sede per id")
	@ApiResponse(responseCode = "200", description = "sede eliminata correttamente")
	@ApiResponse(responseCode = "500", description = "Errore nell'eliminazione")
	@DeleteMapping("/cancella/{id}")
	@SecurityRequirement(name = "bearerAuth")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity eliminaSedeById(@PathVariable int id) throws ElementNotFoundException {
		sedeServ.eliminaSedeById(id);
		return ResponseEntity.ok("sede eliminata");
}

}
