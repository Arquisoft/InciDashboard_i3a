package com.uniovi.entitites;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "agents")
public class Agent {

	@Id
	private ObjectId id;

	private String username;
	private int kind;

	public Agent() {
	}

	public Agent(String username, int kind) {
		this.username = username;
		this.kind = kind;
		checkValues();
	}

	private void checkValues() {
		if (this.username == null || this.username.isEmpty()) {
			throw new IllegalArgumentException("The username cannot be empty nor null");
		}

		if (this.kind < 0) {
			throw new IllegalArgumentException("The kind should be a valid positive value");
		}
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getKind() {
		return kind;
	}

	public void setKind(int kind) {
		this.kind = kind;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Agent other = (Agent) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
