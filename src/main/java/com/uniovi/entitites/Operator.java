package com.uniovi.entitites;

import java.util.HashSet;
import java.util.Set;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "operators")
// @Entity
public class Operator {

	@Id
	private ObjectId id;

	private String email;

	private Set<Notification> notifications = new HashSet<Notification>();

	private String password;

	public Operator() {

	}

	public Operator(String username, String password) {
		this.email = username;
		this.password = password;
		checkValues();
	}

	private void checkValues() {
		if (this.email == null || this.email.isEmpty()) {
			throw new IllegalArgumentException("The email cannot be empty nor null");
		}
		if (this.password == null || this.password.isEmpty()) {
			throw new IllegalArgumentException("The password cannot be empty nor null");
		}

	}

	public Operator(String username, String password, Set<Notification> n) {
		this(username, password);
		this.notifications = n;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Notification> getNotifications() {
		return notifications;
	}

	public void setNotifications(Set<Notification> notifications) {
		this.notifications = notifications;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
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
		Operator other = (Operator) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
