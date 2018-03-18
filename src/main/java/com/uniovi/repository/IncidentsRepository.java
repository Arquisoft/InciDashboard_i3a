package com.uniovi.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.uniovi.entitites.Incident;

public interface IncidentsRepository extends CrudRepository<Incident, Long> {

	public List<Incident> findAll();

}
