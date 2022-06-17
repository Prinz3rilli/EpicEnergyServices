package it.epicode.crm.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import it.epicode.crm.exceptions.ElementAlreadyPresentException;
import it.epicode.crm.exceptions.ElementNotFoundException;
import it.epicode.crm.model.Cliente;
import it.epicode.crm.repository.ClienteRepository;
import it.epicode.crm.requests.InserisciClienteRequest;
import it.epicode.crm.requests.ModificaClienteRequest;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ClienteService {

		
		@Autowired
		ClienteRepository clienteRepo;
		
		
		
		public Page elencoClientiPaginato(Pageable page){
			return clienteRepo.findAll(page);
		}
		
		public Cliente getClienteById(String partitaIva) throws ElementNotFoundException{
			if(!clienteRepo.existsById(partitaIva)) {
				throw new ElementNotFoundException("Cliente non trovato");
			}
			return clienteRepo.findById(partitaIva).get();		
				
}
		
		public void inserisciCliente(InserisciClienteRequest request) throws ElementAlreadyPresentException {
			if(!clienteRepo.existsById(request.getPartitaIva())) {
				Cliente cl = new Cliente();
				BeanUtils.copyProperties(request, cl);
				clienteRepo.save(cl);
			} else {
		log.info("Cliente già presente");
		throw new ElementAlreadyPresentException("Cliente già presente");
			}
		}
		
		public void eliminaClienteByPartitaIva(String partitaIva) throws ElementNotFoundException{
			if(clienteRepo.existsById(partitaIva)) {
				clienteRepo.deleteById(partitaIva);
			} else {
				log.info("Cliente non eliminato");
				throw new ElementNotFoundException("Cliente non trovato");
			}
		}

		public boolean modificaCliente(ModificaClienteRequest request) throws ElementNotFoundException {
			if(clienteRepo.existsById(request.getPartitaIva())) {
				Cliente cl = clienteRepo.findById(request.getPartitaIva()).get();
				BeanUtils.copyProperties(request, cl);
				clienteRepo.save(cl);
				
			} else {
				log.info("Cliente non modificato");
				throw new ElementNotFoundException("Cliente non trovato");
			}
			return false;
		}
		
	}

