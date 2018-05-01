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
import com.uniovi.entitites.Comment;
import com.uniovi.entitites.InciInfo;
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
		Incident inci = IncidentService.getIncident(id);
		model.addAttribute("incident", inci);
		try {
		    if(!inci.getLocation().isEmpty() && inci.getLocation().contains(", "))
			model.addAttribute("lat", Double.parseDouble(inci.getLocation().split(", ")[0]));
			model.addAttribute("lng", Double.parseDouble(inci.getLocation().split(", ")[1]));
		} catch (Exception e ) {
		    log.error("Error parsin the location of the incident no map will be displayed.");
		}
		log.info("Seeing incent: " + id + " details");
		return "incident/details";
	}

	@RequestMapping(value = "/incident/edit/{id}", method = RequestMethod.GET)
	public String getEdit(Model model, @Nullable @CookieValue("operatorId") String opId, @PathVariable String id) {
		if (opId == null)
			return "redirect:/login";
		Incident incident = IncidentService.getIncident(id);
		model.addAttribute("incident", incident);
		log.info("Page for editing incident: " + id);
		return "incident/edit";
	}

	@RequestMapping(value = "/incident/edit/{id}", method = RequestMethod.POST)
	public String setEdit(Model model, @ModelAttribute("inciInfo") InciInfo newInciData,
			@Nullable @CookieValue("operatorId") String opId, @PathVariable String id) {
		if (opId == null)
			return "redirect:/login";
		Incident incident = IncidentService.getIncident(id);
		giveNewDataToIncident(newInciData, incident, opId);
		assignOp(opId, incident);
		incident.setIncidentId(id);
		log.info("Operator assigned to the incident");
		
		IncidentService.saveIncident(incident);
		log.info("Incident updated in the database");
		return "redirect:/incidents";
	}

	private void giveNewDataToIncident(InciInfo newInciData, Incident incident, String opId) {
		log.info("The old status of the incident is: " + incident.getStatus());
		log.info("The NEW status: " + newInciData.getStatus());
		incident.setStatus(newInciData.getStatus());
		log.info("There are " + incident.getComments().size() + " comments in the incident");
		log.info("Content of the new comment: " + newInciData.getComment());
		incident.getComments().add(new Comment(newInciData.getComment(), opId));
		log.info("UPDATE  " + incident.getComments().size() + " comments in the incident");
	}

	private void assignOp(String opId, Incident incident) {
		switch (incident.getStatus()) {
		case "OPEN":
			incident.setOperatorId("");
			log.info("The operator has been desassigned");
			break;
		default:
			incident.setOperatorId(opId);
			log.info("The new operator " + opId + " has been assigned");
			break;
		}
	}

}
