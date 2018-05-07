package io.github.asw.i3a.operatorsWebClient.entities;

import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import TestKit.UnitTest;
import io.github.asw.i3a.operatorsWebClient.entitites.Operator;

@Category(UnitTest.class)
public class OperatorTest {

	@Test
	public void allPropertiesTest() {
		assertPojoMethodsFor(Operator.class).areWellImplemented();
	}

}