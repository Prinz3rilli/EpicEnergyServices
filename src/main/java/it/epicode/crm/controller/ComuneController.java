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
import it.epicode.crm.model.Comune;
import it.epicode.crm.repository.ComuneRepository;
import it.epicode.crm.requests.InserisciComuneRequest;
import it.epicode.crm.requests.ModificaComuneRequest;
import it.epicode.crm.services.ComuneService;


@RestController
@RequestMapping("/comuni")
@Tag(name = "Comuni rest Services", description = "implementazioni delle api rest dei comuni")
public class ComuneController {
	
	@Autowired
	ComuneRepository comuneRepo;
	@Autowired
	ComuneService comuneServ;
	
	@Operation(summary = "inserisce un comune nel DB", description = "Inserimento di un comune")
	@ApiResponse(responseCode = "200", description = "comune inserito correttamente")
	@ApiResponse(responseCode = "500", description = "Errore nell'inserimento")
	@PostMapping("/inserisci")
	@SecurityRequirement(name = "bearerAuth")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity inserisciComune(@RequestBody InserisciComuneRequest request) throws ElementAlreadyPresentException {
		comuneServ.inserisciComune(request);
		return ResponseEntity.ok("Comune inserito con successo");
	}
	
	@Operation(summary = "Ricerca un comune nel DB", description = "Ricerca di un comune")
	@ApiResponse(responseCode = "200", description = "comune trovato correttamente")
	@ApiResponse(responseCode = "500", description = "Errore nella ricerca")
	@GetMapping("/cerca/{id}")
	@SecurityRequirement(name = "bearerAuth")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity getComuneById(@PathVariable int id) throws ElementNotFoundException {
		Comune comune = comuneServ.getComuneById(id);
		if(comune == null) {
			return new ResponseEntity("Errore di ricerca", HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(comune);
	}
	
	@Operation(summary = "Ricerca una lista di comuni nel DB", description = "Ricerca di una lista di comuni")
	@ApiResponse(responseCode = "200", description = "Lista comuni trovata correttamente")
	@ApiResponse(responseCode = "500", description = "Errore nella ricerca")
	@GetMapping("/cerca")
	@SecurityRequirement(name = "bearerAuth")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity elencoComuni() {
		return ResponseEntity.ok(comuneServ.elencoComuni());
	}
	
	@Operation(summary = "Modifica un comune nel DB", description = "Modifica di un comune")
	@ApiResponse(responseCode = "200", description = "comune modificato correttamente")
	@ApiResponse(responseCode = "500", description = "Errore nell'inserimento")
	@PutMapping("/modifica/{id}")
	@SecurityRequirement(name = "bearerAuth")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity modificaComune(@PathVariable int id, ModificaComuneRequest request) throws ElementNotFoundException {
		if(comuneServ.modificaComune(request)) {
			return ResponseEntity.ok("comune modificato");
		}
		return new ResponseEntity("Errore nella modifica", HttpStatus.BAD_REQUEST);
	}
	
	@Operation(summary = "Elimina un comune nel DB", description = "Eliminazione di un comune per id")
	@ApiResponse(responseCode = "200", description = "fattura eliminata correttamente")
	@ApiResponse(responseCode = "500", description = "Errore nell'eliminazione")
	@DeleteMapping("/cancella/{id}")
	@SecurityRequirement(name = "bearerAuth")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity eliminaComuneById(@PathVariable int id) throws ElementNotFoundException {
		comuneServ.eliminaComuneById(id);
		return ResponseEntity.ok("Comune eliminato");
}

}
