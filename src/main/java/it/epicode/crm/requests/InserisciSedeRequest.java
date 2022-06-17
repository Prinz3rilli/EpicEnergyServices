package it.epicode.crm.requests;

import javax.validation.constraints.NotBlank;

import it.epicode.crm.model.Cliente;
import it.epicode.crm.model.TipoSede;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class InserisciSedeRequest {
	@NotBlank
	private int id;
	@NotBlank
	private String via;
	@NotBlank
	private String civico;
	@NotBlank
	private String località;
	@NotBlank
	private String cap;
	@NotBlank
	private String comune;
	@NotBlank
	private TipoSede tipoSede;
	@NotBlank
	private Cliente cliente;

}
