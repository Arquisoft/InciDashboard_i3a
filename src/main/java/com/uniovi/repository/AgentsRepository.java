package com.uniovi.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.uniovi.entitites.Agent;

@Repository
public interface AgentsRepository extends MongoRepository<Agent, ObjectId> {

}
