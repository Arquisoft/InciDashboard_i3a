package com.uniovi.services;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entitites.Incident;
import com.uniovi.repository.IncidentRepository;

@Service
public class IncidentService {

	@Autowired
	private IncidentRepository incidentRepository;

	@PostConstruct
	public void init() {
	}

	public void saveIncident(Incident i) {
		incidentRepository.save(i);
	}

}
