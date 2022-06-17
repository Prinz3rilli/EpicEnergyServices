package it.epicode.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.epicode.crm.model.Utente;

public interface UtenteRepository extends JpaRepository <Utente, String> {

}
