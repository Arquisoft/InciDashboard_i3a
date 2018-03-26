package com.uniovi.services;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entitites.Operator;
import com.uniovi.repository.OperatorRepository;

@Service
public class OperatorService {

	@Autowired
	private OperatorRepository operatorRepository;

	public List<Operator> getAll() {
		return operatorRepository.findAll();

	}

	public Operator getOperatorByEmail(String email) {
		return operatorRepository.findByEmail(email);
	}

	public void add(Operator op) {
		operatorRepository.save(op);
	}

	/**
	 * This method selects a random operator from all the existing ones
	 * 
	 * @return the operator
	 */
	public Operator getRandomOperator() {
		List<Operator> ops = getAll();
		if (ops.size() >= 1) {
			Random rand = new Random();
			int randomNum = rand.nextInt(ops.size());
			return ops.get(randomNum);
		}
		return null;
	}
}
