package it.epicode.crm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SedeDTO {
	private int id;
	private String via;
	private String civico;
	private String località;
	private String cap;
	private String comune;

}
