package it.epicode.crm.impl;

import javax.persistence.EnumType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends JpaRepository<Role, EnumType> {

	@Query(value= "SELECT * FROM Roles WHERE Role_name = ?1", nativeQuery = true)
	public Role findByRuoloNome(ERole nomeRuolo);
}
