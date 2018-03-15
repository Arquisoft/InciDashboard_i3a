package com.uniovi.entitites;

import java.util.List;
import java.util.Map;

public class Incident {

	private long id;
	private String name;
	private String description;
	private IncidentStates state;
	private String location;
	private List<String> tags;
	private List<String> multimedia;
	private Map<String, String> property_value;
	private List<String> comments;

	public Incident() {

	}

	public Incident(Long id, String name, String description, IncidentStates state, String location, List<String> tags,
			List<String> multimedia, Map<String, String> property_value, List<String> comments) {
		super();
		this.name = name;
		this.description = description;
		this.state = state;
		this.location = location;
		this.tags = tags;
		this.multimedia = multimedia;
		this.property_value = property_value;
		this.comments = comments;
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

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public List<String> getMultimedia() {
		return multimedia;
	}

	public void setMultimedia(List<String> multimedia) {
		this.multimedia = multimedia;
	}

	public Map<String, String> getProperty_value() {
		return property_value;
	}

	public void setProperty_value(Map<String, String> property_value) {
		this.property_value = property_value;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<String> getComments() {
		return comments;
	}

	public void setComments(List<String> comments) {
		this.comments = comments;
	}

}
