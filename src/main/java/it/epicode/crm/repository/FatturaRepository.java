package it.epicode.crm.repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import it.epicode.crm.model.Fattura;
import it.epicode.crm.model.StatoFattura;

public interface FatturaRepository extends PagingAndSortingRepository<Fattura, Integer> {
	List<Fattura> findByNumero (int numero);
	public boolean existsByNumero (int numero);
	
	public Page <Fattura> findByClienteLike (Pageable page, String cliente);
	public Page <Fattura> findByStatoFatturaLike (Pageable page, StatoFattura statoFattura);
	public Page <Fattura> findByDataLike (Pageable page, Date data);
	public Page <Fattura> findByAnnoLike (Pageable page, Integer anno);
	public Page <Fattura> findByImportoBetween (Pageable page, BigDecimal importoDa, BigDecimal importoA);

}
