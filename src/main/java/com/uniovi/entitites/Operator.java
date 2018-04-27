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
}
