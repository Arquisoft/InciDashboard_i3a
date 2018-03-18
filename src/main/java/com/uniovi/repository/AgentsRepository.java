package com.uniovi.repository;

import org.springframework.data.repository.CrudRepository;

import com.uniovi.entitites.Agent;

public interface AgentsRepository extends CrudRepository<Agent, Long> {

}
