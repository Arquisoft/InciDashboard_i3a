package com.uniovi.entitites;

import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "incidents")
public class Incident {

	@Id
	private ObjectId id;

	private String title;

	private String description;

	private String status;

	private String location;

	private String[] tags;

	private String[] multimedia;

	private Map<String, String> property_value;

	private List<Comment> comments;

	private String agentId;

	private String operatorId;

	public void addComment(Comment comment) {
		this.comments.add(comment);
	}

}
