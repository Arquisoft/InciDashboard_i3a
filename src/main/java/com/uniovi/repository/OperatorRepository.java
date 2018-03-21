package com.uniovi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.uniovi.entitites.Incident;
import com.uniovi.entitites.Operator;

public interface OperatorRepository extends CrudRepository<Operator, Long> {

	Operator findByEmail(String username);

	@Query("SELECT inci from Incident inci where inci.agent = ?1")
	List<Incident> findIncidentsByOp(Operator op);
}
