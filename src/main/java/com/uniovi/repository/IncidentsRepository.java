package com.uniovi.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.uniovi.entitites.Incident;

@Repository
public interface IncidentsRepository extends CrudRepository<Incident, Long> {

	public List<Incident> findAll();

}
