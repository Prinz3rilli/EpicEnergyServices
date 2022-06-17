package it.epicode.crm.dto;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FatturaDTO {
	private Integer numero;
	private Integer anno;
	private Date data;
	private BigDecimal importo;

}
