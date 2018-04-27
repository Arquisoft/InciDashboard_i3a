package com.uniovi.entitites;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "agents")
public class Agent {

	@Id
	private ObjectId id;

	private String username;
	private int kind;

	public Agent(String username, int kind) {
		super();
		this.username = username;
		this.kind = kind;
	}

}
