package io.github.asw.i3a.operatorsWebClient.entitites;

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

	public String getDate() {
		return new ObjectId(this.incidentId).getDate().toString();
	}

}
