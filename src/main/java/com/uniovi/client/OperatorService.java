package com.uniovi.client;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.uniovi.entitites.Operator;

public class OperatorService {

	static String API_GATEWAY = "http://asw-i3a-zuul-eu-west-1.guill.io/operators_service";

	public static List<Operator> getAllOperators()
			throws UnirestException, JsonParseException, JsonMappingException, IOException {
		HttpResponse<JsonNode> response = Unirest.post(API_GATEWAY + "/operators").asJson();
		ObjectMapper mapper = new ObjectMapper();
		List<Operator> items = mapper.readValue(response.getBody().toString(),
				mapper.getTypeFactory().constructParametricType(List.class, Operator.class));

		return items;
	}

	public static Operator getOperator(String id)
			throws JsonParseException, JsonMappingException, IOException, UnirestException {
		HttpResponse<JsonNode> response = Unirest.post(API_GATEWAY + "/operators/" + id).asJson();
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(response.getBody().toString(), Operator.class);
	}

	public static String getRandomOperator()
			throws JsonParseException, JsonMappingException, UnirestException, IOException {
		List<Operator> ops = getAllOperators();
		Random rn = new Random();
		return ops.get(rn.nextInt(ops.size())).getId().toString();
	}

}
