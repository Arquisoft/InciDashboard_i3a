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
		model.addAttribute("incidentsList", incidents);
		return "operator/incidentsSocket";
	}
	
	/*
	 * Only use when kafka is not working for testing purposes.
	 * 
	private static int counter = 0;
	@Scheduled(fixedRate = 2500)
    public void addToDash() {
        System.out.println("scheduled");
        Incident i = new Incident("Incidente de prueba_" + counter++ , "Pruebaaa", IncidentStates.OPEN, "41N56E", new ArrayList<>(),
				new ArrayList<>(), new HashMap<>());
        this.template.convertAndSend("/topic/incidents", i);
    }
    */
}
