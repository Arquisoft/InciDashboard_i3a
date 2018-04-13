package com.uniovi.controllers;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uniovi.entitites.Incident;
import com.uniovi.entitites.Operator;
import com.uniovi.services.IncidentsService;
import com.uniovi.services.OperatorService;

@Controller
public class OperatorController {

	@Autowired
	private OperatorService operatorService;

	@Autowired
	private IncidentsService incidentsService;

	@RequestMapping("/login")
	public String getLogin() {
		return "login";
	}

	@RequestMapping("/operator/details/{id}")
	public String getDetail(Model model, @PathVariable ObjectId id) {
		model.addAttribute("incident", incidentsService.getIncident(id));
		return "operator/details";
	}

	@RequestMapping("/operator/list")
	public String getIncidentsList(Model model) {
		model.addAttribute("indicentsList", operatorService.getIncidents(getActiveUser()));
		return "operator/list";
	}

	@RequestMapping(value = "/operator/edit/{id}")
	public String getEdit(Model model, @PathVariable ObjectId id) {
		Incident incident = incidentsService.getIncident(id);
		model.addAttribute("incident", incident);
		return "operator/edit";
	}

	@RequestMapping(value = "/operator/edit/{id}", method = RequestMethod.POST)
	public String setEdit(Model model, @PathVariable ObjectId id, @ModelAttribute Incident incident) {
		Incident original = incidentsService.getIncident(id);
		original.setState(incident.getState());
		original.addComment(incident.getComments().get(0));
		incidentsService.addIncident(original);
		return "redirect:/operator/details/" + id;
	}

	private Operator getActiveUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		return operatorService.getOperatorByEmail(username);
	}

}
