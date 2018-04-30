package com.uniovi.controllers;

import org.springframework.lang.Nullable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uniovi.client.AgentService;
import com.uniovi.entitites.Agent;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@EnableScheduling
public class AgentController {

	@RequestMapping(value = "/agent/listSensors", method = RequestMethod.GET)
	public String getListOpen(Model model, @Nullable @CookieValue("operatorId") String opId) {
		if (opId == null)
			return "redirect:/login";
		Agent[] sensors = AgentService.getAllSensors();
		model.addAttribute("sensors", sensors);
		log.info("List of sensors showned");
		return "agent/listSensors";
	}

	@RequestMapping("/agent/sensorDetails/{id}")
	public String getDetail(Model model, @PathVariable String id, @Nullable @CookieValue("operatorId") String opId) {
		if (opId == null)
			return "redirect:/login";
		Agent sen = AgentService.getAgent(id);
		log.info("Deatils of sensor: " + id);
		model.addAttribute("sensor", sen);
		if (sen.getLocation() != "") {
			model.addAttribute("lat", Double.parseDouble(sen.getLocation().split(", ")[0]));
			model.addAttribute("lng", Double.parseDouble(sen.getLocation().split(", ")[1]));
		}
		log.info("Location: " + sen.getLocation());
		return "agent/detailsSensor";
	}
}
