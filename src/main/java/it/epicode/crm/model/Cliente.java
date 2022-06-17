package it.epicode.crm.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="clienti")
public class Cliente {
	
	@Id
	private String partitaIva;
	private String ragioneSociale;
	private String email;
	private String dataInserimento;
	private String dataUltimoContatto;
	private double fatturatoAnnuale;
	private String pec;
	private String telefono;
	private String emailContatto;
	private String nomeContatto;
	private String cognomeContatto;
	private String telefonoContatto;
	@Enumerated(EnumType.STRING)
	private TipoCliente tipoCliente;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonIgnoreProperties({"clienti"})
	private List <Sede> sedi;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonIgnoreProperties({"clienti"})
	private List <Fattura> fatture;
	

}
