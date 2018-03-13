package com.uniovi.entitites;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
@TypeAlias("incident")
public class Incident {

	@Id
	private long id;

	
	@Field("name") private String name;
	@Field("description") private String description;
	@Field("state") private IncidentStates state;
	@Field("StateDescription") private String decription;
	@Field("location") private String location;
	@Field("tags") private String[] tags;

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
