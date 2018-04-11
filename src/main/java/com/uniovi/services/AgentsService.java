package com.uniovi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entitites.Agent;
import com.uniovi.repository.AgentsRepository;

@Service
public class AgentsService {

	@Autowired
	private AgentsRepository agentsRepository;

	public void addAgent(Agent agent) {
		this.agentsRepository.save(agent);
	}

	public void deleteAll() {
		agentsRepository.deleteAll();
	}

	public void deleteAgent(Agent agent) {
		this.agentsRepository.delete(agent);
	}

}
