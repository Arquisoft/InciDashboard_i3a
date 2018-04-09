package com.uniovi.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uniovi.entitites.Incident;
import com.uniovi.entitites.IncidentStates;
import com.uniovi.services.IncidentsService;

@Controller
@EnableScheduling
public class IncidentController {
	@Autowired
    private SimpMessagingTemplate template;
	
	@Autowired
	private IncidentsService incidentsService;

	@RequestMapping(value = "/incidents", method = RequestMethod.GET)
	public String getDashboard(Model model) {
		List<Incident> incidents = incidentsService.getAllIncidents();
		model.addAttribute("incidentsList", incidents);
		return "incidents";
	}
	
	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String getDash(Model model) {
		List<Incident> incidents = incidentsService.getAllIncidents();
		System.out.println( incidents );
		model.addAttribute("incidents", incidents);
		return "operator/incidentsSocket";
	}    
}
