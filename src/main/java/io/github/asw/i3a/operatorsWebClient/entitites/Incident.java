package io.github.asw.i3a.operatorsWebClient.entitites;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;

import lombok.Data;

@Data
public class Incident {

	private String title;
	private String description;
	private String status;
	private String location;
	private String[] tags;
	private String[] multimedia;
	private Map<String, String> propertyVal;
	private List<Comment> comments;
	private String agentId;
	private String operatorId;
	private String incidentId = "";

	public Incident() {
	}

	public Incident(String title, String description, String status, String location, String[] tags,
			String[] multimedia, Map<String, String> property_value, List<Comment> comments, String agentId,
			String operatorId) {
		super();
		this.title = title;
		this.description = description;
		this.status = status;
		this.location = location;
		this.tags = tags;
		this.multimedia = multimedia;
		this.propertyVal = property_value;
		this.comments = comments;
		this.agentId = agentId;
		this.operatorId = operatorId;
	}

	public void addComment(Comment comment) {
		this.comments.add(comment);
	}

	@Override
	public String toString() {
		String aux = "Incident [id=" + incidentId + ", title=" + title + ", description=" + description + ", status="
				+ status + ", location=" + location + ", tags=" + Arrays.toString(tags) + ", multimedia="
				+ Arrays.toString(multimedia) + ", property_value=" + propertyVal + ", comments=";
		for (Comment c : comments)
			aux += c.getComment() + ", ";
		aux += ", agentId=" + agentId + ", operatorId=" + operatorId + "]";

		return aux;
	}

	public String getDate() {
		return new ObjectId(this.incidentId).getDate().toString();
	}

}
