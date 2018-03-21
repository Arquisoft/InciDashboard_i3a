package com.uniovi.clasification;

import java.util.List;

import com.uniovi.entitites.Incident;

public class IncidentsClassifier {
	
	private List<Incident> incidents;

	public IncidentsClassifier(List<Incident> incidents) {
		this.incidents = incidents;
	}
	
	public IncidentsClassifier() {}
	
	public void classify() {
		for (Incident i : incidents) {
			if (! i.hasNormalValues()) {
				NotificationManager.getInstance().addNotification(i.createNotification());
			}
		}
	}
	
	public void classify(Incident i) {
		if (! i.hasNormalValues()) {
			NotificationManager.getInstance().addNotification(i.createNotification());
		}
	}
}
