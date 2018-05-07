package io.github.asw.i3a.operatorsWebClient.entitites;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "operators")
public class Operator {
	@Id
	private ObjectId id;
	private String name;
	private String email;
	private String password;
	private String operatorId;

	public Operator() {
	}

	public Operator(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public boolean canEquals(Object o) {
		return this.canEqual(o);
	}
}
