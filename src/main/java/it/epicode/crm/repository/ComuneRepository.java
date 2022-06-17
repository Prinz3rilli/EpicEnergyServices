package it.epicode.crm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.epicode.crm.model.Cliente;
import it.epicode.crm.model.Comune;

public interface ComuneRepository extends JpaRepository<Comune, Integer> {


}
