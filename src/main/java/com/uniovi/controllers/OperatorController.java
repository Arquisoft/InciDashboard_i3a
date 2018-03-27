package com.uniovi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uniovi.entitites.Operator;
import com.uniovi.services.IncidentsService;
import com.uniovi.services.OperatorService;

public class OperatorController {

	@Autowired
	private OperatorService operatorService;
	
	@Autowired
	private IncidentsService incidentsService;

	@RequestMapping("/login")
	public String getLogin() {
		return "login";
	}

	@RequestMapping("/operator/details/{id}" )
	public String getDetail(Model model, @PathVariable Long id){
		model.addAttribute("incident", incidentsService.getIncident(id));
		return "operator/details";
	}

	@RequestMapping("/operator/list")
	public String getIncidentsList(Model model) {
		model.addAttribute("indicentsList", operatorService.getIncidents(getActiveUser()));
		return "operator/list";
	}
	
	private Operator getActiveUser() {
		org.springframework.security.core.Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		return operatorService.getOperatorByEmail(username);
	}

}
