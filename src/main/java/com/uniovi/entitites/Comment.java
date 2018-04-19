package com.uniovi.entitites;

import java.util.Date;

import lombok.Data;

@Data
public class Comment {

	private String comment;
	private Date date;
	private String idOperator;

	public Comment(String com, Date d, String idOp) {
		this.comment = com;
		this.date = d;
		this.idOperator = idOp;
	}

}
