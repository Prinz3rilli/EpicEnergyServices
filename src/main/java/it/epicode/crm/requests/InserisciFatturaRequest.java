package it.epicode.crm.requests;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotBlank;

import it.epicode.crm.model.Cliente;
import it.epicode.crm.model.StatoFattura;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class InserisciFatturaRequest {
	
	@NotBlank
	private Integer numero;
	@NotBlank
	private Integer anno;
	@NotBlank
	private Date data;
	@NotBlank
	private BigDecimal importo;
	@NotBlank
	private StatoFattura statoFattura;
	@NotBlank
	private Cliente cliente;

}
