package it.epicode.crm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComuneDTO {
	private int id;
	private String nomeComune;
	private String cap;

}
