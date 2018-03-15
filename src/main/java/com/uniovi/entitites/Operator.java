package com.uniovi.entitites;

import java.util.ArrayList;
import java.util.List;

public class Operator {

	private String username;
	private String password;
	private List<Incident> incidents = new ArrayList<>();

	public Operator() {

	}

	public Operator(String username, String password, List<Incident> incidents) {
		super();
		this.username = username;
		this.password = password;
		this.incidents = incidents;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Incident> getIncidents() {
		return incidents;
	}

	public void setIncidents(List<Incident> incidents) {
		this.incidents = incidents;
	}

}
