package io.github.asw.i3a.operatorsWebClient.entities;

import static org.junit.Assert.assertTrue;
import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import TestKit.UnitTest;
import io.github.asw.i3a.operatorsWebClient.entitites.Operator;
import pl.pojo.tester.api.assertion.Method;

@Category(UnitTest.class)
public class OperatorTest {

	@Test
	public void allPropertiesTest() {
		assertPojoMethodsFor(Operator.class).testing(Method.GETTER, Method.SETTER, Method.EQUALS, Method.HASH_CODE,
				Method.TO_STRING, Method.CONSTRUCTOR).areWellImplemented();
	}

	@Test
	public void extraTest() {
		Operator op = new Operator("Pepe@gmail.com", "123456");
		assertTrue(op.getEmail().equals("Pepe@gmail.com"));
		assertTrue(op.getPassword().equals("123456"));

		Operator op2 = new Operator();
		assertTrue(op.canEquals(op2));
	}

}