package com.uniovi.entitites;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

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

@Entity
public class Incident {

	@Id
	@GeneratedValue
	private Long id;

	private String name;
	private String description;

	@Enumerated(EnumType.STRING)
	private IncidentStates state;
	private String location;
	@ElementCollection(targetClass = String.class)
	private List<String> tags;
	@ElementCollection(targetClass = String.class)
	private List<String> multimedia;
	@ElementCollection
	private Map<String, String> property_value;
	@Transient
	private List<Property> properties;
	@ElementCollection(targetClass = String.class)
	private List<String> comments;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "agent_id")
	private Agent agent;

	@OneToOne
	@JoinColumn(name = "notification_id")
	private Notification notification;

	private static String[] keyWordsDanger = { "temperature", "fire", "flood", "windy", "wonded", "attack", "robbery",
			"dead" };

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
	}

	public Incident(Long id, String name, String description, IncidentStates state, String location, List<String> tags,
			List<String> multimedia, Map<String, String> property_value) {
		this(name, description, state, location, tags, multimedia, property_value);
	}

	public Incident(Long id, String name, String description, IncidentStates state, String location, List<String> tags,
			List<String> multimedia, Map<String, String> property_value, List<String> comments) {
		this(id, name, description, state, location, tags, multimedia, property_value);
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

	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	public Notification getNotification() {
		return notification;
	}

	public void setNotification(Notification notification) {
		this.notification = notification;
	}

	public static String[] getKeyWordsDanger() {
		return keyWordsDanger;
	}

	public static void setKeyWordsDanger(String[] keyWordsDanger) {
		Incident.keyWordsDanger = keyWordsDanger;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
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
		return new Notification(this, new Operator());
	}

}
