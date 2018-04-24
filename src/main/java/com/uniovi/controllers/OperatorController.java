package com.uniovi.controllers;

import java.io.IOException;

import org.bson.types.ObjectId;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.uniovi.client.IncidentService;
import com.uniovi.client.OperatorService;
import com.uniovi.entitites.Incident;
import com.uniovi.entitites.Operator;

@Controller
public class OperatorController {

	@RequestMapping("/login")
	public String getLogin() {
		return "login";
	}

	@RequestMapping("/operator/details/{id}")
	public String getDetail(Model model, @PathVariable ObjectId id)
			throws JsonParseException, JsonMappingException, IOException, UnirestException {
		model.addAttribute("incident", IncidentService.getIncident(id));
		return "operator/details";
	}

	@RequestMapping("/operator/list")
	public String getIncidentsList(Model model)
			throws JsonParseException, JsonMappingException, IOException, UnirestException {
		if (getActiveUser() != null) {
			model.addAttribute("indicentsList",
					IncidentService.getIncidentsOfOperator(getActiveUser().getId().toString()));
			return "operator/list";
		} else {
			return "login";
		}
	}

	@RequestMapping(value = "/operator/edit/{id}")
	public String getEdit(Model model, @PathVariable ObjectId id)
			throws JsonParseException, JsonMappingException, IOException, UnirestException {
		Incident incident = IncidentService.getIncident(id);
		model.addAttribute("incident", incident);
		return "operator/edit";
	}

	@RequestMapping(value = "/operator/edit/{id}", method = RequestMethod.POST)
	public String setEdit(Model model, @PathVariable ObjectId id, @ModelAttribute Incident incident)
			throws JsonParseException, JsonMappingException, IOException, UnirestException {
		Incident original = IncidentService.getIncident(id);
		original.setState(incident.getState());
		original.addComment(incident.getComments().get(0));
		IncidentService.addIncident(original);
		return "redirect:/operator/details/" + id;
	}

	private Operator getActiveUser() throws JsonParseException, JsonMappingException, IOException, UnirestException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			String id = auth.getName();
			return OperatorService.getOperator(id);
		}
		return null;
	}

}
