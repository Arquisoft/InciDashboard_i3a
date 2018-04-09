package com.uniovi.listeners;

import java.util.concurrent.CountDownLatch;

import javax.annotation.ManagedBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import com.uniovi.entitites.Incident;
import com.uniovi.services.IncidentsService;

@ManagedBean
public class Receiver {

	@Autowired
    private SimpMessagingTemplate template;
	
	@Autowired
	private IncidentsService inciService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

	private CountDownLatch latch = new CountDownLatch(1);

	public CountDownLatch getLatch() {
		return latch;
	}

	@KafkaListener(topics = "${kafka.topic}")
	public void listen(Incident incident) {
		LOGGER.info("received incident='{}'", incident);
		this.template.convertAndSend("/topic/incidents", incident);
		inciService.addIncident(incident);
		latch.countDown();
	}
}
