package io.github.asw.i3a.operatorsWebClient.entities;

import static org.junit.Assert.assertTrue;
import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

import java.util.Date;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import TestKit.UnitTest;
import io.github.asw.i3a.operatorsWebClient.entitites.Comment;
import pl.pojo.tester.api.assertion.Method;

@Category(UnitTest.class)
public class CommentTest {

	@Test
	public void allPropertiesTest() {
		assertPojoMethodsFor(Comment.class).testing(Method.GETTER, Method.SETTER, Method.EQUALS, Method.HASH_CODE,
				Method.TO_STRING, Method.CONSTRUCTOR).areWellImplemented();
	}

	@Test
	public void extraTest() {
		Comment c = new Comment("hola", "idOp");
		Date d = new Date();
		assertTrue(c.getDate().getTime() <= d.getTime());
	}

}
