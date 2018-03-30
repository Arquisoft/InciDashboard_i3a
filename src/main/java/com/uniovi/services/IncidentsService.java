package com.uniovi.services;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entitites.Incident;
import com.uniovi.repository.IncidentsRepository;

@Service
public class IncidentsService {

	@Autowired
	private IncidentsRepository incidentsRepository;

	public void addIncident(Incident incident) {
		incidentsRepository.save(incident);
	}

	public void deleteIncident(Incident inci) {
		incidentsRepository.delete(inci);
	}

	public List<Incident> getAllIncidents() {
		return incidentsRepository.findAll();
	}

	public Optional<Incident> getIncident(ObjectId id) {
		return incidentsRepository.findById(id);
	}

	public void deleteAll() {
		incidentsRepository.deleteAll();
	}

}
