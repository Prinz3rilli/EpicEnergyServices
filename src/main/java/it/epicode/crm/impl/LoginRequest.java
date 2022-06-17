package it.epicode.crm.impl;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginRequest {
	
	private String userName;
	private String password;
	private String eMail;
	private String nome;
	private String cognome;
	
	

}
