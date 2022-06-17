package it.epicode.crm.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import it.epicode.crm.dto.ComuneDTO;
import it.epicode.crm.exceptions.ElementAlreadyPresentException;
import it.epicode.crm.exceptions.ElementNotFoundException;
import it.epicode.crm.model.Cliente;
import it.epicode.crm.model.Comune;
import it.epicode.crm.model.Provincia;
import it.epicode.crm.repository.ClienteRepository;
import it.epicode.crm.repository.ComuneRepository;
import it.epicode.crm.repository.ProvinciaRepository;
import it.epicode.crm.requests.InserisciComuneRequest;
import it.epicode.crm.requests.ModificaComuneRequest;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ComuneService {


@Autowired
ComuneRepository comuneRepo;
ProvinciaRepository provinciaRepo;

public List<Comune> elencoComuni(){
	return (List<Comune>) comuneRepo.findAll();
}

public Comune getComuneById(int id) throws ElementNotFoundException{
	if(!comuneRepo.existsById(id)) {
		throw new ElementNotFoundException("Comune non trovato");
	}
	return comuneRepo.findById(id).get();		
		
}


public void inserisciComune(InserisciComuneRequest request) throws ElementAlreadyPresentException {
	if(!comuneRepo.existsById(request.getId())) {
		Comune com = new Comune();
		BeanUtils.copyProperties(request, com);
		comuneRepo.save(com);
	} else {
log.info("Comune già presente");
throw new ElementAlreadyPresentException("Comune già presente");

}

}

public void eliminaComuneById(int id) throws ElementNotFoundException {
	if(comuneRepo.existsById(id)) {
		comuneRepo.deleteById(id);
	} else {
		log.info("Comune non eliminato");
		throw new ElementNotFoundException("Comune non trovato");
	}
}

public boolean modificaComune(ModificaComuneRequest request) throws ElementNotFoundException {
	if(comuneRepo.existsById(request.getId())) {
		Comune com = comuneRepo.findById(request.getId()).get();
		Provincia p = new Provincia();
		com.getProvincia().setSigla(null);
		BeanUtils.copyProperties(request, com);
		comuneRepo.save(com);
		
	} else {
		log.info("Comune non modificato");
		throw new ElementNotFoundException("Comune non trovato");
	}
	return false;
}
}


