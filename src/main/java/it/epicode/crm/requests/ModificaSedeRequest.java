package it.epicode.crm.requests;



import it.epicode.crm.model.Cliente;
import it.epicode.crm.model.TipoSede;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ModificaSedeRequest {
	
	private int id;
	private String via;
	private String civico;
	private String località;
	private String cap;
	private String comune;
	private TipoSede tipoSede;
	private Cliente cliente;

}
