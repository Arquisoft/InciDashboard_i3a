package com.uniovi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entitites.Incident;
import com.uniovi.entitites.Operator;
import com.uniovi.repository.OperatorRepository;

@Service
public class OperatorService {

	@Autowired
	private OperatorRepository operatorRepository;

	public void getAll() {
		operatorRepository.findAll();

	}

	public Operator getOperatorByUsername(String username) {
		return operatorRepository.findByEmail(username);
	}

	public List<Incident> getIncidentsFromUser(Operator op) {
		return operatorRepository.findIncidentsByOp(op);
	}
}
