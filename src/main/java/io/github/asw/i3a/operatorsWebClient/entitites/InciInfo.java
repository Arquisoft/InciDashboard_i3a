package io.github.asw.i3a.operatorsWebClient.entitites;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InciInfo {
	private String status;
	private String comment;

	public InciInfo() {

	}

	public boolean canEquals(Object o) {
		return this.canEqual(o);
	}
}
