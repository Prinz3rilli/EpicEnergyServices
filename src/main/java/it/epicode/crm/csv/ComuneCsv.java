package it.epicode.crm.csv;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ComuneCsv {
	
	@JsonProperty("Comune")
	private String nomeComune;
	@JsonProperty("CAP")
	private String cap;
	@JsonProperty("Provincia")
	private String provincia;

}
