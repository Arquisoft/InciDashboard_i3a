package com.uniovi.entitites;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import org.springframework.data.annotation.Id;

@Entity
public class Incident {

	@Id
	@GeneratedValue
	private long id;

	private String name;
	private String description;
	private IncidentStates state;
	private String decription;
	private String location;
	private String[] tags;

	public Incident() {

	}

	public Incident(String name, String description, IncidentStates state, String decription, String location,
			String[] tags) {
		super();
		this.name = name;
		this.description = description;
		this.state = state;
		this.decription = decription;
		this.location = location;
		this.tags = tags;
	}

	public IncidentStates getState() {
		return state;
	}

	public void setState(IncidentStates state) {
		this.state = state;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getDecription() {
		return decription;
	}

	public String getLocation() {
		return location;
	}

	public String[] getTags() {
		return tags;
	}
}
