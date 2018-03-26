package com.uniovi.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.uniovi.entitites.Operator;

@Repository
public interface OperatorRepository extends MongoRepository<Operator, ObjectId> {

	Operator findByEmail(String username);

	List<Operator> findAll();

	// @Query("SELECT inci from Incident inci where inci.agent = ?1")
	// List<Incident> findIncidentsByOp(Operator op);
}
