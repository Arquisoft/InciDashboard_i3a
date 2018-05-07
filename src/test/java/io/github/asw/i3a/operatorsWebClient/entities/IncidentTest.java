package io.github.asw.i3a.operatorsWebClient.entities;

import static org.junit.Assert.assertNotNull;
import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import TestKit.UnitTest;
import io.github.asw.i3a.operatorsWebClient.entitites.Incident;
import pl.pojo.tester.api.assertion.Method;

@Category(UnitTest.class)
public class IncidentTest {

	@Test
	public void allPropertiesTest() {
		assertPojoMethodsFor(Incident.class).testing(Method.GETTER, Method.SETTER, Method.EQUALS, Method.HASH_CODE,
				Method.TO_STRING, Method.CONSTRUCTOR).areWellImplemented();
	}

	@Test
	public void extraTest() {
		Incident i = new Incident();
		i.setIncidentId("5aef2acdb1f3911fd0bdb41c");
		assertNotNull(i.getDate().equals("Sun May 06 18:18:21 CEST 2018"));
	}

}