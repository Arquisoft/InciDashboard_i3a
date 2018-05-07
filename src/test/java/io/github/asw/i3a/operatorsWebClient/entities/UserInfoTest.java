package io.github.asw.i3a.operatorsWebClient.entities;

import static org.junit.Assert.assertTrue;
import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import TestKit.UnitTest;
import io.github.asw.i3a.operatorsWebClient.entitites.UserInfo;
import pl.pojo.tester.api.assertion.Method;

@Category(UnitTest.class)
public class UserInfoTest {

	@Test
	public void allPropertiesTest() {
		assertPojoMethodsFor(UserInfo.class).testing(Method.GETTER, Method.SETTER, Method.EQUALS, Method.HASH_CODE,
				Method.TO_STRING, Method.CONSTRUCTOR).areWellImplemented();
	}

	@Test
	public void textExtra() {
		UserInfo u = new UserInfo("hola", "holi");
		assertTrue(u.getLogin().equals("hola"));
		assertTrue(u.getPassword().equals("holi"));
		UserInfo u2 = new UserInfo();
		assertTrue(u.canEquals(u2));
	}

}
