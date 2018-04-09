package com.uniovi.clasification;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.uniovi.entitites.Incident;
import com.uniovi.entitites.Notification;
import com.uniovi.services.OperatorService;

public class IncidentsClassifier {

	private List<Incident> incidents;

	@Autowired
	private OperatorService operatorService;

	public IncidentsClassifier(List<Incident> incidents) {
		this.incidents = incidents;
	}

	public IncidentsClassifier() {
	}

	public void classify() {
		for (Incident i : incidents) {
			addNotifications(i);
		}
	}

	public void classify(Incident i) {
		addNotifications(i);
	}

	private void addNotifications(Incident i) {
		if (!i.hasNormalValues()) {
			Notification not = i.createNotification();
			if (i.getOperator() == null)
				not.setOperator(operatorService.getRandomOperator());
			else
				not.setOperator(i.getOperator());
			NotificationManager.getInstance().addNotification(i.createNotification());
		}
	}
}
