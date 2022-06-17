package it.epicode.crm.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import it.epicode.crm.impl.ERole;
import it.epicode.crm.impl.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="utenti")
public class Utente {
	@Id
	@NotBlank
	private String username;
	private String nome;
	private String cognome;
	@NotBlank
	private String password;
	private String eMail;
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany(mappedBy="utente",orphanRemoval = true, cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Role> elencoRuoli;
	//private List <Role> elencoRuoli= new ArrayList<>();

}
