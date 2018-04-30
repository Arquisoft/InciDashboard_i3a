package com.uniovi.listeners;

import javax.annotation.ManagedBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import com.uniovi.client.IncidentService;
import com.uniovi.client.OperatorService;
import com.uniovi.entitites.Incident;

@ManagedBean
public class Receiver {

	@Autowired
	private SimpMessagingTemplate template;

	private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

	@KafkaListener(topics = "${kafka.topic}")
	public void listen(String id) {
		LOGGER.info("received incident='{}'", id);
		Incident in = assignOperator(IncidentService.getIncident(id));
		this.template.convertAndSend("/topic/incidents", in);
	}

	private Incident assignOperator(Incident in) {
		if (in.getOperatorId() == "") {
			in.setOperatorId(OperatorService.getOperatorToAssign());
			in.setStatus("IN_PROCESS");
			IncidentService.saveIncident(in);
		}
		return in;
	}
}
