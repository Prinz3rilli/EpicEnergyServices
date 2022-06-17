package it.epicode.crm.requests;

import java.util.List;

import it.epicode.crm.model.Comune;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ModificaProvinciaRequest {
	
	private int id;
	private String sigla;
	private String nomeProvincia;
	private String regione;
	private List <Comune> comuni;

}
