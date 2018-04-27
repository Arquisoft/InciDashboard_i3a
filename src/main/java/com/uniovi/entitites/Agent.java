package com.uniovi.entitites;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.Getter;

@Data
@Document(collection = "agents")
public class Agent {

	@Id
	private ObjectId id;

	private String email;
	private int kindCode;
	@Getter
	private String location;

	public Agent(String username, int kind, String loc) {
		super();
		this.email = username;
		this.kindCode = kind;
		this.location = loc;
	}

	@Override
	public String toString() {
		return "Agent [username=" + email + ", kind=" + kindCode + "]";
	}

}
