package com.uniovi.services;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entitites.Incident;
import com.uniovi.entitites.IncidentStates;
import com.uniovi.entitites.Notification;
import com.uniovi.repository.IncidentsRepository;
import com.uniovi.repository.NotificationsRepository;

@Service
public class IncidentsService {

	@Autowired
	private IncidentsRepository incidentsRepository;

	@Autowired
	private NotificationsRepository notificationsRepository;

	public void addIncident(Incident incident) {

		incidentsRepository.save(incident);
	}

	public void deleteIncident(Incident inci) {
		incidentsRepository.delete(inci);
	}

	public List<Incident> getAllIncidents() {
		return incidentsRepository.findAll();
	}

	public Incident getIncident(ObjectId id) {
		return incidentsRepository.findByIncidentId(id.toString());
	}

	public List<Incident> getIncidentsOfOperator(String email) {
		List<Notification> nots = notificationsRepository.findIncidentsOf(email);
		List<Incident> incs = new ArrayList<>();
		for (Notification notification : nots) {
			incs.add(notification.getIncident());
		}
		return incs;
	}

	public void deleteAll() {
		incidentsRepository.deleteAll();
	}

	/**
	 * This method changes the state of the incident defined by its id
	 * 
	 * @param id
	 *            the id of the incident
	 * @param string
	 *            the new state
	 */
	public void changeIncidentState(ObjectId id, String stateIn) {
		Incident incident = incidentsRepository.findByIncidentId(id.toString());
		if (incident != null) {
			try {
				IncidentStates state = IncidentStates.valueOf(stateIn);
				incident.setState(state);
				incidentsRepository.save(incident);
			} catch (Exception e) {
				return;
			}
		} else {
			return;
		}
	}

}
