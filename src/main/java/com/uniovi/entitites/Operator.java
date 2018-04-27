package com.uniovi.entitites;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "operators")
public class Operator {
	@Id
	private ObjectId id;

	private String email;

	private String password;

	public Operator(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	@Override
	public String toString() {
		return "Operator [email=" + email + ", password=" + password + "]";
	}

}
