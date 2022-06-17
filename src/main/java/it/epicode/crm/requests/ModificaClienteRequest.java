package it.epicode.crm.requests;

import java.util.List;

import it.epicode.crm.model.Fattura;
import it.epicode.crm.model.Sede;
import it.epicode.crm.model.TipoCliente;
import it.epicode.crm.model.TipoSede;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ModificaClienteRequest {
	
	private String partitaIva;
	private String ragioneSociale;
	private String dataInserimento;
	private String dataUltimoContatto;
	private String nomeContatto;
	private String cognomeContatto;
	private TipoCliente tipoCliente;
	private TipoSede tipoSede;
	private List <Fattura> fatture;
	private List <Sede> sedi;
	private Integer numero;

}
