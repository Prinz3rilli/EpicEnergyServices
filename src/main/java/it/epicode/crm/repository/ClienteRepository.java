package it.epicode.crm.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import it.epicode.crm.model.Cliente;
import it.epicode.crm.model.TipoSede;

public interface ClienteRepository extends PagingAndSortingRepository <Cliente, String> {
	
	public Page <Cliente> findByFatturatoAnnualeLike (Pageable page, String fatturatoAnnuale);
	public Page <Cliente> findByDataInserimentoLike (Pageable page, String dataInserimento);
	public List <Cliente> findByDataUltimoContattoLike (Pageable page, String dataUltimoContatto);
	@Query ("select c from Cliente c join c.sedi s where s.tipoSede = 'SedeLegale' and s.provincia = ?1") 
	public Page <Cliente> findByProvinciaSedeLegale (Pageable page, String Sigla);
	public Page <Cliente> findByRagioneSocialeContaining (Pageable page, String ragioneSociale);

}
