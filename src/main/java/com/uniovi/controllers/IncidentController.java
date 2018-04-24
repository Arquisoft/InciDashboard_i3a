package com.uniovi.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.uniovi.client.IncidentService;
import com.uniovi.entitites.Incident;

@Controller
@EnableScheduling
public class IncidentController {

	@RequestMapping(value = "/incidents", method = RequestMethod.GET)
	public String getDashboard(Model model)
			throws JsonParseException, JsonMappingException, IOException, UnirestException {
		List<Incident> incidents = IncidentService.getAllIncidents();
		model.addAttribute("incidentsList", incidents);
		return "incidents";
	}

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String getDash(Model model) throws JsonParseException, JsonMappingException, IOException, UnirestException {
		List<Incident> incidents = IncidentService.getAllIncidents();
		model.addAttribute("incidents", incidents);
		return "operator/incidentsSocket";
	}
}
