package com.uniovi.entitites;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.Transient;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.uniovi.properties.Attack;
import com.uniovi.properties.Dead;
import com.uniovi.properties.Fire;
import com.uniovi.properties.Flood;
import com.uniovi.properties.Humidity;
import com.uniovi.properties.Property;
import com.uniovi.properties.Robbery;
import com.uniovi.properties.Temperature;
import com.uniovi.properties.Wind;
import com.uniovi.properties.Wounded;

@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "incidents")
public class Incident {

	@Id
	private ObjectId id;

	private String name;

	private String description;

	private IncidentStates state;

	private String location;

	private List<String> tags;

	private List<String> multimedia;

	private Map<String, String> property_value;

	@Transient
	private List<Property> properties;

	private List<String> comments;

	private Agent agent;

	private Operator operator;

	private Notification notification;

	public Incident() {
		comments = new ArrayList<String>();
	}

	public Incident(String name, String description, IncidentStates state, String location, List<String> tags,
			List<String> multimedia, Map<String, String> property_value) {
		this();
		this.name = name;
		this.description = description;
		this.state = state;
		this.location = location;
		this.tags = tags;
		this.multimedia = multimedia;
		this.property_value = property_value;
		checkCorrectValues();
	}

	public Incident(String name, String description, IncidentStates state, String location, List<String> tags,
			List<String> multimedia, Map<String, String> property_value, List<String> comments) {
		this(name, description, state, location, tags, multimedia, property_value);
		this.comments = comments;
	}

	private void checkCorrectValues() {
		if (this.name == null || this.name.isEmpty()) {
			throw new IllegalArgumentException("The name of the incident cannot be null nor empty");
		}
		if (this.description == null || this.description.isEmpty()) {
			throw new IllegalArgumentException("The description of the incident cannot be null nor empty");
		}

		if (this.state == null) {
			throw new IllegalArgumentException("The state cannot be null");
		}
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

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public List<String> getComments() {
		return comments;
	}

	public void setComments(List<String> comments) {
		this.comments = comments;
	}

	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	public Notification getNotification() {
		return notification;
	}

	public void setNotification(Notification notification) {
		this.notification = notification;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Incident other = (Incident) obj;
		if (id != other.id)
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Incident [id=" + id + ", name=" + name + ", description=" + description + ", state=" + state
				+ ", location=" + location + ", tags=" + tags + ", multimedia=" + multimedia + ", property_value="
				+ property_value + ", comments=" + comments + ", agent=" + agent + ", notification=" + notification
				+ "]";
	}

	public void setProperties() {
		this.properties = new ArrayList<Property>();
		if (property_value.containsKey("temperature")) {
			try {
				properties.add(new Temperature(Double.parseDouble(property_value.get("temperature"))));
			} catch (NumberFormatException e) {
				System.out.println("Wrong value for temperature");
			}
		}
		if (property_value.containsKey("humidity")) {
			try {
				properties.add(new Humidity(Double.parseDouble(property_value.get("humidity"))));
			} catch (NumberFormatException e) {
				System.out.println("Wrong value for humidity");
			}
		}
		if (property_value.containsKey("wind")) {
			try {
				properties.add(new Wind(Double.parseDouble(property_value.get("wind"))));
			} catch (NumberFormatException e) {
				System.out.println("Wrong value for humidity");
			}
		}
		if (property_value.containsKey("wounded")) {
			try {
				properties.add(new Wounded(Integer.parseInt(property_value.get("wounded"))));
			} catch (NumberFormatException e) {
				System.out.println("Wrong value for number of wounded people");
			}
		}
		if (property_value.containsKey("dead")) {
			try {
				properties.add(new Dead(Integer.parseInt(property_value.get("dead"))));
			} catch (NumberFormatException e) {
				System.out.println("Wrong value for temperature");
			}
		}
		if (property_value.containsKey("fire")) {
			properties.add(new Fire(Boolean.parseBoolean(property_value.get("fire"))));
		}
		if (property_value.containsKey("flood")) {
			properties.add(new Flood(Boolean.parseBoolean(property_value.get("flood"))));
		}
		if (property_value.containsKey("attack")) {
			properties.add(new Attack(Boolean.parseBoolean(property_value.get("attack"))));
		}
		if (property_value.containsKey("robbery")) {
			properties.add(new Robbery(Boolean.parseBoolean(property_value.get("robbery"))));
		}
	}

	public boolean hasNormalValues() {
		setProperties();
		for (Property p : properties) {
			if (!p.hasNormalValue())
				return false;
		}
		return true;
	}

	public Notification createNotification() {
		setNotification(new Notification(this));
		return this.notification;
	}

	public void addComment(String comment) {
		this.comments.add(comment);
	}

}
