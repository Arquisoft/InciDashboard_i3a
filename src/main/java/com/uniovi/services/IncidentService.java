package com.uniovi.services;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.repository.IncidentRepository;

@Service
public class IncidentService {

	@Autowired
	private IncidentRepository incidentRepository;

	@PostConstruct
	public void init() {
	}

}
