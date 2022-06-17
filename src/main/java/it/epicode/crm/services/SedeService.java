package it.epicode.crm.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import it.epicode.crm.dto.FatturaDTO;
import it.epicode.crm.dto.SedeDTO;
import it.epicode.crm.exceptions.ElementAlreadyPresentException;
import it.epicode.crm.exceptions.ElementNotFoundException;
import it.epicode.crm.model.Cliente;
import it.epicode.crm.model.Fattura;
import it.epicode.crm.model.Sede;
import it.epicode.crm.repository.ClienteRepository;
import it.epicode.crm.repository.SedeRepository;
import it.epicode.crm.requests.InserisciSedeRequest;
import it.epicode.crm.requests.ModificaSedeRequest;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SedeService {
	@Autowired
	SedeRepository sedeRepo;
	
	@Autowired
	ClienteRepository clienteRepo;
	
	public List<Sede> elencoSedi(){
		return (List<Sede>)sedeRepo.findAll();
	}

	public Sede getSedeById(int id) throws ElementNotFoundException{
		if(!sedeRepo.existsById(id)) {
			throw new ElementNotFoundException("Sede non trovata");
		}
		return sedeRepo.findById(id).get();		
			
	}


	public void inserisciSede(InserisciSedeRequest request) throws ElementAlreadyPresentException {
		if(!sedeRepo.existsById(request.getId())) {
			Sede s = new Sede();
			BeanUtils.copyProperties(request, s);
			sedeRepo.save(s);
		} else {
	log.info("Sede già presente");
	throw new ElementAlreadyPresentException("Sede già presente");

	}

	}

	public void eliminaSedeById(int id) throws ElementNotFoundException {
		if(sedeRepo.existsById(id)) {
			sedeRepo.deleteById(id);
		} else {
			log.info("Sede non eliminata");
			throw new ElementNotFoundException("Sede non trovata");
		}
	}

	public boolean modificaSede(ModificaSedeRequest request) throws ElementNotFoundException {
		if(sedeRepo.existsById(request.getId())) {
			Sede s = sedeRepo.findById(request.getId()).get();
			BeanUtils.copyProperties(request, s);
			s.setId(0);
			s.setTipoSede(null);
			sedeRepo.save(s);
			
		} else {
			log.info("Sede non modificata");
			throw new ElementNotFoundException("Sede non trovata");
		}
		return false;
	}
	}

