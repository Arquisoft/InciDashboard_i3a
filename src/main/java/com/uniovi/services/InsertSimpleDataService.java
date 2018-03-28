package com.uniovi.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entitites.Agent;
import com.uniovi.entitites.Incident;
import com.uniovi.entitites.IncidentStates;
import com.uniovi.entitites.Notification;
import com.uniovi.entitites.Operator;

@Service
public class InsertSimpleDataService {

	@Autowired
	private OperatorService operatorServ;

	@Autowired
	private IncidentsService incidentServ;

	@Autowired
	private AgentsService agentsServ;

	@Autowired
	private NotificationService notificationServ;

	@PostConstruct
	public void init() {

		operatorServ.deleteAll();
		incidentServ.deleteAll();
		agentsServ.deleteAll();
		notificationServ.deleteAll();
		
		// OPERADORES
		Operator op1 = new Operator("pepe", "123456", new HashSet<>());
		Operator op2 = new Operator("juan", "asdfgh", new HashSet<>());

		operatorServ.add(op1);
		operatorServ.add(op2);

		// INCIDENTES
		Map<String, String> properties = new HashMap<>();
		properties.put("wounded", "5");
		Incident i1 = new Incident("Incidente en carretera", "Multiples heridos", IncidentStates.OPEN, "56N89W",
				new ArrayList<>(), new ArrayList<>(), properties);

		i1.setOperator(op1);
		
		properties.remove("wounded");
		properties.put("temperature", "41.3");
		Incident i2 = new Incident("Sensor de temperatura 1234", "Temperatura elevada", IncidentStates.OPEN,
				"65,89N4685,5E", new ArrayList<>(), new ArrayList<>(), properties);

		incidentServ.addIncident(i1);
		incidentServ.addIncident(i2);

		// AGENTS
		Agent a1 = new Agent("pepito", 1); // person
		Agent a2 = new Agent("sensor1234", 3);

		agentsServ.addAgent(a1);
		agentsServ.addAgent(a2);

		Notification n1 = new Notification(i1, op1);
		notificationServ.add(n1);

	}
}
