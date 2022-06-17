package it.epicode.crm.requests;

import java.util.List;

import javax.validation.constraints.NotBlank;

import it.epicode.crm.model.Fattura;
import it.epicode.crm.model.Sede;
import it.epicode.crm.model.TipoCliente;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class InserisciClienteRequest {
	
	@NotBlank
	private String partitaIva;
	@NotBlank
	private String ragioneSociale;
	private String email;
	@NotBlank
	private String dataInserimento;
	@NotBlank
	private String dataUltimoContatto;
	private double fatturatoAnnuale;
	private String pec;
	private String telefono;
	private String emailContatto;
	@NotBlank
	private String nomeContatto;
	@NotBlank
	private String cognomeContatto;
	private String telefonoContatto;
	@NotBlank
	private TipoCliente tipoCliente;
	@NotBlank
	private List <Sede> sedi;
	@NotBlank
	private List <Fattura> fatture;
	

}
