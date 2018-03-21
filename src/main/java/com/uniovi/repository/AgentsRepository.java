package com.uniovi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.uniovi.entitites.Agent;

@Repository
public interface AgentsRepository extends CrudRepository<Agent, Long> {

}
