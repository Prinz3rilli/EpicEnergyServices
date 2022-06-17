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
import it.epicode.crm.model.Provincia;
import it.epicode.crm.repository.ProvinciaRepository;
import it.epicode.crm.requests.InserisciProvinciaRequest;
import it.epicode.crm.requests.ModificaProvinciaRequest;
import it.epicode.crm.services.ProvinciaService;


@RestController
@RequestMapping("/province")
@Tag(name = "Province rest Services", description = "implementazioni delle api rest delle province")
public class ProvinciaController {
	
	@Autowired
	ProvinciaRepository provinciaRepo;
	@Autowired
	ProvinciaService provinciaServ;
	
	@Operation(summary = "inserisce una provincia nel DB", description = "Inserimento di una provincia")
	@ApiResponse(responseCode = "200", description = "provincia inserita correttamente")
	@ApiResponse(responseCode = "500", description = "Errore nell'inserimento")
	@PostMapping("/inserisci")
	@SecurityRequirement(name = "bearerAuth")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity inserisciProvincia(@RequestBody InserisciProvinciaRequest request) throws ElementAlreadyPresentException {
		provinciaServ.inserisciProvincia(request);
		return ResponseEntity.ok("Provincia inserito con successo");
	}
	
	@Operation(summary = "Ricerca una provincia nel DB", description = "Ricerca di una provincia")
	@ApiResponse(responseCode = "200", description = "provincia trovata correttamente")
	@ApiResponse(responseCode = "500", description = "Errore nella ricerca")
	@GetMapping("/cerca/{id}")
	@SecurityRequirement(name = "bearerAuth")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity getProvinciaBySigla(@PathVariable String sigla) throws ElementNotFoundException {
		Provincia provincia = provinciaServ.getProvinciaBySigla(sigla);
		if(provincia == null) {
			return new ResponseEntity("Errore di ricerca", HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(provincia);
	}
	
	@Operation(summary = "Ricerca una lista di province nel DB", description = "Ricerca di una lista di province")
	@ApiResponse(responseCode = "200", description = "Lista province trovata correttamente")
	@ApiResponse(responseCode = "500", description = "Errore nella ricerca")
	@GetMapping("/cerca")
	@SecurityRequirement(name = "bearerAuth")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity elencoProvince() {
		return ResponseEntity.ok(provinciaServ.elencoProvince());
	}
	
	@Operation(summary = "Modifica una provincia nel DB", description = "Modifica di una provincia")
	@ApiResponse(responseCode = "200", description = "provincia modificata correttamente")
	@ApiResponse(responseCode = "500", description = "Errore nell'inserimento")
	@PutMapping("/modifica/{id}")
	@SecurityRequirement(name = "bearerAuth")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity modificaProvincia(@PathVariable String sigla, ModificaProvinciaRequest request) throws ElementNotFoundException {
		if(provinciaServ.modificaProvincia(request)) {
			return ResponseEntity.ok("Provincia modificata");
		}
		return new ResponseEntity("Errore nella modifica", HttpStatus.BAD_REQUEST);
	}
	
	@Operation(summary = "Elimina una provincia nel DB", description = "Eliminazione di una provincia per sigla")
	@ApiResponse(responseCode = "200", description = "provincia eliminata correttamente")
	@ApiResponse(responseCode = "500", description = "Errore nell'eliminazione")
	@DeleteMapping("/cancella/{id}")
	@SecurityRequirement(name = "bearerAuth")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity eliminaProvinciaBySigla(@PathVariable String sigla) throws ElementNotFoundException {
		provinciaServ.eliminaProvinciaBySigla(sigla);
		return ResponseEntity.ok("Provincia eliminata");
}

}
