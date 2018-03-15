package com.uniovi.services;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.repository.OperatorRepository;

@Service
public class OperatorService {

	@Autowired
	private OperatorRepository operatorRepository;

	@PostConstruct
	public void init() {
	}
}
