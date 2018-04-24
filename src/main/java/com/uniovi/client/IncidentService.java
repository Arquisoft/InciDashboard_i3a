package com.uniovi.client;

import java.io.IOException;
import java.util.List;

import org.bson.types.ObjectId;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.uniovi.entitites.Incident;

public class IncidentService {

	static String API_GATEWAY = "http://asw-i3a-zuul-eu-west-1.guill.io/incidents_service";

	public static Incident getIncident(ObjectId id)
			throws JsonParseException, JsonMappingException, IOException, UnirestException {
		HttpResponse<JsonNode> response = Unirest.post(API_GATEWAY + "/incidents/" + id).asJson();
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(response.getBody().toString(), Incident.class);
	}

	public static void addIncident(Incident original) {
		// TODO Auto-generated method stub

	}

	public static List<Incident> getIncidentsOfOperator(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public static List<Incident> getAllIncidents()
			throws JsonParseException, JsonMappingException, IOException, UnirestException {
		HttpResponse<JsonNode> response = Unirest.post(API_GATEWAY + "/incidents").asJson();
		ObjectMapper mapper = new ObjectMapper();
		List<Incident> items = mapper.readValue(response.getBody().toString(),
				mapper.getTypeFactory().constructParametricType(List.class, Incident.class));

		return items;
	}

}
