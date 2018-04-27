package com.uniovi.client;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.uniovi.entitites.Incident;

public class IncidentService {

	static String API_GATEWAY = "http://asw-i3a-zuul-eu-west-1.guill.io/incidents_service";

	public static Incident getIncident(String id) {
		try {
			HttpResponse<JsonNode> response = Unirest.post(API_GATEWAY + "/incidents/" + id).asJson();
			ObjectMapper mapper = new ObjectMapper();
			return mapper.readValue(response.getBody().toString(), Incident.class);
		} catch (IOException | UnirestException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static List<Incident> getInProcessIncidentsOfOperator(String id) {
		try {
			HttpResponse<JsonNode> response = Unirest
					.post(API_GATEWAY + "/incidents?operatorId=" + id + "&status=IN_PROCESS").asJson();
			ObjectMapper mapper = new ObjectMapper();
			List<Incident> items = mapper.readValue(response.getBody().toString(),
					mapper.getTypeFactory().constructParametricType(List.class, Incident.class));

			return items;
		} catch (IOException | UnirestException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static List<Incident> getAllIncidents() {
		try {
			HttpResponse<JsonNode> response = Unirest.post(API_GATEWAY + "/incidents").asJson();
			ObjectMapper mapper = new ObjectMapper();
			List<Incident> items = mapper.readValue(response.getBody().toString(),
					mapper.getTypeFactory().constructParametricType(List.class, Incident.class));

			return items;
		} catch (IOException | UnirestException e) {
			e.printStackTrace();
			return null;
		}

	}

	public static List<Incident> getAllOpenIncidents() {
		try {
			HttpResponse<JsonNode> response = Unirest.post(API_GATEWAY + "/incidents?status=OPEN").asJson();
			ObjectMapper mapper = new ObjectMapper();
			List<Incident> items = mapper.readValue(response.getBody().toString(),
					mapper.getTypeFactory().constructParametricType(List.class, Incident.class));

			return items;
		} catch (IOException | UnirestException e) {
			e.printStackTrace();
			return null;
		}

	}

	public static void saveIncident(Incident inci) {

	}

}
