package com.uniovi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uniovi.entitites.Incident;
import com.uniovi.services.IncidentsService;

@Controller
public class IncidentController {
	@Autowired
	private IncidentsService incidentsService;

	@RequestMapping(value = "/incidents", method = RequestMethod.GET)
	public String getDashboard(Model model) {
		List<Incident> incidents = incidentsService.getAllIncidents();
		model.addAttribute("incidentsList", incidents);
		return "incidents";
	}
}
