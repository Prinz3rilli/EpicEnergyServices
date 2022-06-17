package it.epicode.crm.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import it.epicode.crm.dto.FatturaDTO;
import it.epicode.crm.dto.ProvinciaDTO;
import it.epicode.crm.exceptions.ElementAlreadyPresentException;
import it.epicode.crm.exceptions.ElementNotFoundException;
import it.epicode.crm.model.Cliente;
import it.epicode.crm.model.Comune;
import it.epicode.crm.model.Fattura;
import it.epicode.crm.model.Provincia;
import it.epicode.crm.repository.ComuneRepository;
import it.epicode.crm.repository.ProvinciaRepository;
import it.epicode.crm.requests.InserisciProvinciaRequest;
import it.epicode.crm.requests.ModificaProvinciaRequest;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProvinciaService {
	
	@Autowired
	ProvinciaRepository provinciaRepo;
	ComuneRepository comuneRepo;
	
	public List<Provincia> elencoProvince(){
		return (List<Provincia>) provinciaRepo.findAll();
	}

	public Provincia getProvinciaBySigla(String sigla) throws ElementNotFoundException{
		if(!provinciaRepo.existsById(sigla)) {
			throw new ElementNotFoundException("Provincia non trovata");
		}
		return provinciaRepo.findById(sigla).get();		
			
	}


	public void inserisciProvincia(InserisciProvinciaRequest request) throws ElementAlreadyPresentException {
		if(!provinciaRepo.existsById(request.getSigla())) {
			Provincia p = new Provincia();
			BeanUtils.copyProperties(request, p);
			provinciaRepo.save(p);
		} else {
	log.info("Provincia già presente");
	throw new ElementAlreadyPresentException("Provincia già presente");

	}

	}

	public void eliminaProvinciaBySigla(String sigla) throws ElementNotFoundException {
		if(provinciaRepo.existsById(sigla)) {
			provinciaRepo.deleteById(sigla);
		} else {
			log.info("Provincia non eliminata");
			throw new ElementNotFoundException("Provincia non trovata");
		}
	}

	public boolean modificaProvincia(ModificaProvinciaRequest request) throws ElementNotFoundException {
		if(provinciaRepo.existsById(request.getSigla())) {
			Provincia p = provinciaRepo.findById(request.getSigla()).get();
			Comune c = new Comune();
			p.getComuni().add(c);
			BeanUtils.copyProperties(request, p);
			provinciaRepo.save(p);
			
		} else {
			log.info("Provincia non modificata");
			throw new ElementNotFoundException("Provincia non trovata");
		}
		return false;
	}
	}


