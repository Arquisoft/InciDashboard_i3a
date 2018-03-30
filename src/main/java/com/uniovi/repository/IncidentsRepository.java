package com.uniovi.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.uniovi.entitites.Incident;
import com.uniovi.entitites.Operator;

@Repository
public interface IncidentsRepository extends MongoRepository<Incident, ObjectId> {

	public List<Incident> findAll();

	public List<Incident> findByOperator(Operator activeUser);

}
