package it.epicode.crm.requests;

import java.util.List;

import javax.validation.constraints.NotBlank;

import it.epicode.crm.model.Comune;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class InserisciProvinciaRequest {
	
	@NotBlank
	private int id;
	@NotBlank
	private String sigla;
	@NotBlank
	private String nomeProvincia;
	@NotBlank
	private String regione;
	@NotBlank
	private List <Comune> comuni;

}
