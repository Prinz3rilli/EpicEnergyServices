package it.epicode.crm.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import it.epicode.crm.dto.ComuneDTO;
import it.epicode.crm.dto.FatturaDTO;
import it.epicode.crm.exceptions.ElementAlreadyPresentException;
import it.epicode.crm.exceptions.ElementNotFoundException;
import it.epicode.crm.model.Cliente;
import it.epicode.crm.model.Comune;
import it.epicode.crm.model.Fattura;
import it.epicode.crm.model.Provincia;
import it.epicode.crm.repository.ClienteRepository;
import it.epicode.crm.repository.FatturaRepository;
import it.epicode.crm.requests.InserisciFatturaRequest;
import it.epicode.crm.requests.ModificaFatturaRequest;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FatturaService {
	
	@Autowired
	FatturaRepository fatturaRepo;
	ClienteRepository clienteRepo;

	public Page elencoFatturePaginato(Pageable page){
		return fatturaRepo.findAll(page);
	}

	public Fattura getFatturaByNumero(int numero) throws ElementNotFoundException{
		if(!fatturaRepo.existsById(numero)) {
			throw new ElementNotFoundException("Fattura non trovata");
		}
		return fatturaRepo.findById(numero).get();		
			
	}


	public void inserisciFattura(InserisciFatturaRequest request) throws ElementAlreadyPresentException {
		if(!fatturaRepo.existsById(request.getNumero())) {
			Fattura f = new Fattura();
			BeanUtils.copyProperties(request, f);
			fatturaRepo.save(f);
		} else {
	log.info("Fattura già presente");
	throw new ElementAlreadyPresentException("Fattura già presente");

	}

	}

	public void eliminaFatturaByNumero(int numero) throws ElementNotFoundException {
		if(fatturaRepo.existsById(numero)) {
			fatturaRepo.deleteById(numero);
		} else {
			log.info("Fattura non eliminata");
			throw new ElementNotFoundException("Fattura non trovata");
		}
	}

	public boolean modificaFattura(ModificaFatturaRequest request) throws ElementNotFoundException {
		if(fatturaRepo.existsById(request.getNumero())) {
			Fattura f = fatturaRepo.findById(request.getNumero()).get();
			BeanUtils.copyProperties(request, f);
			fatturaRepo.save(f);
			
		} else {
			log.info("Fattura non modificata");
			throw new ElementNotFoundException("Fattura non trovata");
		}
		return false;
	}
	}
