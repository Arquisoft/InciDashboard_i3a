package io.github.asw.i3a.operatorsWebClient.entitites;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserInfo {

	private String login;
	private String password;
}
