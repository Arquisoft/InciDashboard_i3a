package io.github.asw.i3a.operatorsWebClient.entities;

import static org.junit.Assert.assertTrue;
import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import TestKit.UnitTest;
import io.github.asw.i3a.operatorsWebClient.entitites.InciInfo;
import pl.pojo.tester.api.assertion.Method;

@Category(UnitTest.class)
public class InciInfoTest {

	@Test
	public void allPropertiesTest() {
		assertPojoMethodsFor(InciInfo.class).testing(Method.GETTER, Method.SETTER, Method.EQUALS, Method.HASH_CODE,
				Method.TO_STRING, Method.CONSTRUCTOR).areWellImplemented();
	}

	@Test
	public void extraTest() {
		InciInfo in = new InciInfo("OPEN", "pruebaa");
		assertTrue(in.getStatus().equals("OPEN"));
		assertTrue(in.getComment().equals("pruebaa"));

		InciInfo in2 = new InciInfo();
		assertTrue(in.canEquals(in2));
	}

}
