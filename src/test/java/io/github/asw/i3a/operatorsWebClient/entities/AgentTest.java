package io.github.asw.i3a.operatorsWebClient.entities;

import static org.junit.Assert.assertTrue;
import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import TestKit.UnitTest;
import io.github.asw.i3a.operatorsWebClient.entitites.Agent;
import pl.pojo.tester.api.assertion.Method;

@Category(UnitTest.class)
public class AgentTest {

	@Test
	public void allPropertiesTest() {
		assertPojoMethodsFor(Agent.class).testing(Method.GETTER, Method.SETTER, Method.EQUALS, Method.HASH_CODE,
				Method.TO_STRING, Method.CONSTRUCTOR).areWellImplemented();
	}

	@Test
	public void extraTest() {
		Agent a = new Agent();
		Agent a2 = new Agent();
		assertTrue(a.canEquals(a2));
	}
}
