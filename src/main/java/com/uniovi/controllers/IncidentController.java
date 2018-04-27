package com.uniovi.controllers;

import java.util.List;

import org.springframework.lang.Nullable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uniovi.client.IncidentService;
import com.uniovi.entitites.Incident;

@Controller
@EnableScheduling
public class IncidentController {

	@RequestMapping(value = "/incidents", method = RequestMethod.GET)
	public String getDashboard(Model model, @Nullable @CookieValue("operatorId") String opId) {
		if (opId == null)
			return "redirect:/login";
		List<Incident> incidents = IncidentService.getAllOpenIncidents();
		model.addAttribute("incidentsList", incidents);
		return "incidents";
	}
}
