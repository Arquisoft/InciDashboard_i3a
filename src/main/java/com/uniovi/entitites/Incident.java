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
	private String location;
	private String[] tags;

	public Incident() {

	}

	public Incident(String name, String description, IncidentStates state, String location, String[] tags) {
		super();
		this.name = name;
		this.description = description;
		this.state = state;
		this.location = location;
		this.tags = tags;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public IncidentStates getState() {
		return state;
	}

	public void setState(IncidentStates state) {
		this.state = state;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String[] getTags() {
		return tags;
	}

	public void setTags(String[] tags) {
		this.tags = tags;
	}

}
