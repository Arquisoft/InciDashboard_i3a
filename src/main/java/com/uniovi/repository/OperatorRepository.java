package com.uniovi.repository;

import org.springframework.data.repository.CrudRepository;

import com.uniovi.entitites.Incident;

public interface OperatorRepository extends CrudRepository<Incident, Long> {

}
