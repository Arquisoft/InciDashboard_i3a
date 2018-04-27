package entities;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.uniovi.entitites.Comment;

public class CommentTest {

	private Comment comment;

	@Test
	public void test() {
		assertNull(comment);
		comment = new Comment("Prueba", "235b");
		assertTrue(comment.getComment().equals("Prueba"));
		assertTrue(comment.getOperatorId().equals("235b"));

		Comment aux = new Comment("Segunda prueba", "468t");
		assertFalse(aux.equals(comment));
		aux.setComment(comment.getComment());
		aux.setOperatorId(comment.getOperatorId());
		assertTrue(aux.equals(comment));
		aux.setDate(comment.getDate());
		assertTrue(aux.equals(comment));

		assertTrue(aux.hashCode() == comment.hashCode());
	}

}
