package com.uniovi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uniovi.entitites.Operator;
import com.uniovi.services.OperatorService;

public class OperatorController {

	@Autowired
	private OperatorService operatorService;

	@RequestMapping("/login")
	public String getLogin() {
		return "login";
	}

	@RequestMapping("/operator/list")
	public String getIncidentsList(Model model) {
		model.addAttribute("indicentsList", operatorService.getIncidentsFromUser(getActiveUser()));
		return "operator/listIncidents";
	}

	private Operator getActiveUser() {
		org.springframework.security.core.Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		return operatorService.getOperatorByUsername(username);
	}

}
