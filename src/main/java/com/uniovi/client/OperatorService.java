package com.uniovi.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.http.HttpStatus;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.uniovi.entitites.Operator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OperatorService {

	static String API_GATEWAY = "http://asw-i3a-zuul-eu-west-1.guill.io/operators_service";

	public static List<Operator> getAllOperators() {
		try {
			HttpResponse<JsonNode> response = Unirest.post(API_GATEWAY + "/operators").asJson();
			ObjectMapper mapper = new ObjectMapper();
			List<Operator> items = mapper.readValue(response.getBody().toString(),
					mapper.getTypeFactory().constructParametricType(List.class, Operator.class));

			return items;
		} catch (IOException | UnirestException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Operator getOperator(String id) {
		try {
			HttpResponse<JsonNode> response = Unirest.post(API_GATEWAY + "/operators/" + id).asJson();
			ObjectMapper mapper = new ObjectMapper();
			return mapper.readValue(response.getBody().toString(), Operator.class);
		} catch (IOException | UnirestException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String getRandomOperator() {
		List<Operator> ops = getAllOperators();
		Random rn = new Random();
		return ops.get(rn.nextInt(ops.size())).getId().toString();
	}

	public static HttpResponse<JsonNode> authenticate(String login, String password) {
		Map<String, String> map = new HashMap<>();
		map.put("email", login);
		map.put("password", password);
		try {
			HttpResponse<JsonNode> response = Unirest.post(API_GATEWAY + "/auth")
					.header("Content-Type", "application/json").body(new JSONObject(map)).asJson();
			if (response.getStatus() == HttpStatus.SC_OK) {
				log.debug("Login succeded");
			} else {
				log.debug("The login didn't succeded");
			}
		} catch (UnirestException e) {
			log.debug("The login didn't succeded");
		}
		return null;
	}

}
