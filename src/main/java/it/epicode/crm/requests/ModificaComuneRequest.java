package it.epicode.crm.requests;

import it.epicode.crm.model.Provincia;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ModificaComuneRequest {
	
	
	private int id;
	private String nomeComune;
	private String cap;
	private String sigla;

}
