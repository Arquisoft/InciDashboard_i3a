package com.uniovi.listeners;

import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;

import com.uniovi.entitites.Incident;

public class Receiver {

	private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

	private CountDownLatch latch = new CountDownLatch(1);

	public CountDownLatch getLatch() {
		return latch;
	}

	@KafkaListener(topics = "${kakfa.topic.json}")
	public void receive(Incident incident) {
		LOGGER.info("received incident='{}'", incident.toString());
		latch.countDown();
	}
}
