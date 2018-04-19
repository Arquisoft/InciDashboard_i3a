package com.uniovi.entitites;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "incidents")
public class Incident {

	@Id
	private ObjectId id;

	private String name;

	private String description;

	private String date;

	private IncidentStates state;

	private String location;

	private List<String> tags;

	private List<String> multimedia;

	private Map<String, String> property_value;

	private List<Property> properties;

	private List<Comment> comments;

	private String agent_id;

	private String operator_id;

	public Incident() {
		comments = new ArrayList<Comment>();
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
			List<String> multimedia, Map<String, String> property_value, List<Comment> comments) {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
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

	public void setProperties() {
		this.properties = new ArrayList<Property>();
		if (property_value.containsKey("temperature")) {
			try {
				properties.add(new Temperature(Double.parseDouble(property_value.get("temperature"))));
			} catch (NumberFormatException e) {
				throw new NumberFormatException("Wrong value for temperature");
			}
		}
		if (property_value.containsKey("humidity")) {
			try {
				properties.add(new Humidity(Double.parseDouble(property_value.get("humidity"))));
			} catch (NumberFormatException e) {
				throw new NumberFormatException("Wrong value for humidity");
			}
		}
		if (property_value.containsKey("wind")) {
			try {
				properties.add(new Wind(Double.parseDouble(property_value.get("wind"))));
			} catch (NumberFormatException e) {
				throw new NumberFormatException("Wrong value for wind");
			}
		}
		if (property_value.containsKey("wounded")) {
			try {
				properties.add(new Wounded(Integer.parseInt(property_value.get("wounded"))));
			} catch (NumberFormatException e) {
				throw new NumberFormatException("Wrong value for number of wounded people");
			}
		}
		if (property_value.containsKey("dead")) {
			try {
				properties.add(new Dead(Integer.parseInt(property_value.get("dead"))));
			} catch (NumberFormatException e) {
				throw new NumberFormatException("Wrong value for dead people");
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

	public void addComment(Comment comment) {
		this.comments.add(comment);
	}

}
