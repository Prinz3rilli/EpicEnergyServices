package it.epicode.crm.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import it.epicode.crm.exceptions.ElementAlreadyPresentException;
import it.epicode.crm.exceptions.ElementNotFoundException;
import it.epicode.crm.impl.ERole;
import it.epicode.crm.impl.Role;
import it.epicode.crm.impl.RoleRepository;
import it.epicode.crm.impl.User;
import it.epicode.crm.impl.UserRepository;
import it.epicode.crm.requests.UtenteRequest;

@Service
public class UtenteService {
	
	@Autowired UserRepository userRepository;
	@Autowired RoleRepository roleRepository;
	@Autowired PasswordEncoder encoder;
	
	public void registraUtente(UtenteRequest request) throws ElementAlreadyPresentException, ElementNotFoundException {
		
		User u = new User();
		
		u.setUsername(request.getUsername());
		u.setPassword(encoder.encode(request.getPassword()));
		List<ERole> elencoRuoli = request.getElencoRuoli();
		if(elencoRuoli.isEmpty()) {
			elencoRuoli.add(ERole.ROLE_USER);
		}
		Set<Role> ruoli = new HashSet();
		for(int i=0; i<elencoRuoli.size(); i++) {
			Role ruolo = roleRepository.findByRuoloNome(elencoRuoli.get(i));
			if(ruolo != null) {
				ruoli.add(ruolo);
			}
			else {
				throw new ElementNotFoundException("Nome Ruolo non trovato");
			}
			
		}
		u.setRoles(ruoli);
		
		userRepository.save(u);
	}
	
	public List<User> elencoUtenti() {
		return userRepository.findAll();
	}
}


