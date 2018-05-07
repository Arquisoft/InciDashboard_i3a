package io.github.asw.i3a.operatorsWebClient.entitites;

import java.util.Date;

import lombok.Data;

@Data
public class Comment {

	private String comment;
	private Date date;
	private String operatorId;

	public Comment() {
	}

	public Comment(String com, String idOp) {
		this.comment = com;
		this.date = new Date();
		this.operatorId = idOp;
	}

	public boolean canEquals(Object o) {
		return this.canEqual(o);
	}
}
