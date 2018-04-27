package com.uniovi.controllers;

import java.util.List;

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

@Controller
@EnableScheduling
public class AgentController {

	@RequestMapping(value = "/agent/listSensors", method = RequestMethod.GET)
	public String getListOpen(Model model, @Nullable @CookieValue("operatorId") String opId) {
		if (opId == null)
			return "redirect:/login";
		List<Agent> sensors = AgentService.getAllSensors();
		model.addAttribute("sensors", sensors);
		return "agent/listSensors";
	}

	@RequestMapping("/agent/sensorDetails/{id}")
	public String getDetail(Model model, @PathVariable String id, @Nullable @CookieValue("operatorId") String opId) {
		if (opId == null)
			return "redirect:/login";
		model.addAttribute("sensor", AgentService.getAgent(id));
		return "agent/detailsSensor";
	}
}
