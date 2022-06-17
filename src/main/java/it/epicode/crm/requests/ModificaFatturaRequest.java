package it.epicode.crm.requests;

import java.math.BigDecimal;
import java.util.Date;



import it.epicode.crm.model.Cliente;
import it.epicode.crm.model.StatoFattura;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ModificaFatturaRequest {
	

	private Integer numero;
	private Integer anno;
	private Date data;
	private BigDecimal importo;
	private StatoFattura statoFattura;
	private Cliente cliente;

}
