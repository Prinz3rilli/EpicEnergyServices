package it.epicode.crm.requests;

import javax.validation.constraints.NotBlank;

import it.epicode.crm.model.Provincia;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class InserisciComuneRequest {
	
	@NotBlank
	private int id;
	@NotBlank
	private String nomeComune;
	@NotBlank
	private String cap;
	@NotBlank
	private String sigla;

}
