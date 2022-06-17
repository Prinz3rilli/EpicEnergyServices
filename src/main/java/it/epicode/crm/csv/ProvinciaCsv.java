package it.epicode.crm.csv;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProvinciaCsv {
	
	@JsonProperty("Sigla")
	private String sigla;
	@JsonProperty("Provincia")
	private String nomeProvincia;
	@JsonProperty("Regione")
	private String regione;

}
