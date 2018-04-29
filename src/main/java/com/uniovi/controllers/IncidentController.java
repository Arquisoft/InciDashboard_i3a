package com.uniovi.controllers;

import org.springframework.lang.Nullable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uniovi.client.IncidentService;
import com.uniovi.entitites.Incident;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@EnableScheduling
public class IncidentController {

	@RequestMapping(value = "/incidents", method = RequestMethod.GET)
	public String getListOpen(Model model, @Nullable @CookieValue("operatorId") String opId) {
		if (opId == null)
			return "redirect:/login";
		Incident[] incidents = IncidentService.getAllOpenIncidents();
		model.addAttribute("incidents", incidents);
		log.info("Incidents sent to the model: " + incidents.length);
		return "incident/list";
	}

	@RequestMapping("/incident/details/{id}")
	public String getDetail(Model model, @PathVariable String id, @Nullable @CookieValue("operatorId") String opId) {
		if (opId == null)
			return "redirect:/login";
		model.addAttribute("incident", IncidentService.getIncident(id));
		return "incident/details";
	}

	@RequestMapping(value = "/incident/edit/{id}")
	public String getEdit(Model model, @PathVariable String id, @Nullable @CookieValue("operatorId") String opId) {
		if (opId == null)
			return "redirect:/login";
		Incident incident = IncidentService.getIncident(id);
		model.addAttribute("incident", incident);
		return "incident/edit";
	}

	@RequestMapping(value = "/incident/edit/{id}", method = RequestMethod.POST)
	public String setEdit(Model model, @PathVariable String id, @ModelAttribute Incident incident,
			@Nullable @CookieValue("operatorId") String opId) {
		if (opId == null)
			return "redirect:/login";
		if (!id.equals(incident.getIncidentId()))
			return "redirect:/incident/details/" + incident.getIncidentId();

		IncidentService.saveIncident(incident);
		return "redirect:/incident/details/" + id;
	}

}
