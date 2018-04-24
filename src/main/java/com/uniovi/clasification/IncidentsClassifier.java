package com.uniovi.clasification;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.uniovi.client.OperatorService;
import com.uniovi.entitites.Incident;
import com.uniovi.entitites.IncidentStates;

public class IncidentsClassifier {

	private List<Incident> incidents;

	public IncidentsClassifier(List<Incident> incidents) {
		this.incidents = incidents;
	}

	public IncidentsClassifier() {
	}

	public void classify() throws JsonParseException, JsonMappingException, UnirestException, IOException {
		for (Incident i : incidents) {
			addNotification(i);
		}
	}

	public List<Incident> getIncidents() {
		return incidents;
	}

	private void addNotification(Incident i)
			throws JsonParseException, JsonMappingException, UnirestException, IOException {
		if (!i.hasNormalValues()) {
			i.setOperator_id(OperatorService.getRandomOperator());
			i.setState(IncidentStates.IN_PROCESS);
		}
	}
}
