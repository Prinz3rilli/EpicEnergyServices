package it.epicode.crm.requests;

import java.util.List;

import it.epicode.crm.impl.ERole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UtenteRequest {
	
	private String username;
	private String password;
	private String nome;
	private String cognome;
	private String eMail;
	private List <ERole> elencoRuoli;
}


