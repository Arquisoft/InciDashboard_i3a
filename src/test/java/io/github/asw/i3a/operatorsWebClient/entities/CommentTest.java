package io.github.asw.i3a.operatorsWebClient.entities;

import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import TestKit.UnitTest;
import io.github.asw.i3a.operatorsWebClient.entitites.Comment;

@Category(UnitTest.class)
public class CommentTest {

	@Test
	public void allPropertiesTest() {
		assertPojoMethodsFor(Comment.class).areWellImplemented();
	}

}
