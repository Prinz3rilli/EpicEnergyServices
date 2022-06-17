package it.epicode.crm.impl;

import java.util.List;

import lombok.Data;

@Data
public class JwtResponse {

	private String token;
	private String type = "Bearer";
	private Long id;
	private String username;
	private String password;
	private String nome;
	private String cognome;
	private String eMail;
	private List<String> roles;

	public JwtResponse(String accessToken, Long id, String username, String password, String nome, String cognome, String eMail, List<String> roles) {
		this.token = accessToken;
		this.id = id;
		this.username = username;
		this.password = password;
		this.nome = nome;
		this.cognome = cognome;
		this.eMail = eMail;
		this.roles = roles;
	}
}


