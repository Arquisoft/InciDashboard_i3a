package entities;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Date;

import org.bson.types.ObjectId;
import org.junit.Test;

import com.uniovi.entitites.Operator;

public class OperatorTest {

	@Test
	public void testGetters() {
		Operator op = new Operator("pepe@gmail.com", "123456");
		assertTrue(op.getEmail().equals("pepe@gmail.com"));
		op.setEmail("pepe2@gmail.com");
		assertTrue(op.getEmail().equals("pepe2@gmail.com"));
		assertTrue(op.getPassword().equals("123456"));
	}

	@Test
	public void testErrors() {
		try {
			new Operator(null, "124");
			fail("An exception should have been raised [email null]");
		} catch (IllegalArgumentException e) {
			assertTrue(e.getMessage().equals("The email cannot be empty nor null"));
		}

		try {
			new Operator("", "124");
			fail("An exception should have been raised [email empty]");
		} catch (IllegalArgumentException e) {
			assertTrue(e.getMessage().equals("The email cannot be empty nor null"));
		}

		try {
			new Operator("hola@gmail.com", "");
			fail("An exception should have been raised [password empty]");
		} catch (IllegalArgumentException e) {
			assertTrue(e.getMessage().equals("The password cannot be empty nor null"));
		}

		try {
			new Operator("hola@gmail.com", null);
			fail("An exception should have been raised [password null]");
		} catch (IllegalArgumentException e) {
			assertTrue(e.getMessage().equals("The password cannot be empty nor null"));
		}
	}

	@Test
	public void testEquals() {
		Operator op = new Operator("pepe@gmail.com", "123456");
		Operator op2 = null;
		assertFalse(op.equals(op2));
		assertFalse(op.equals(new Object()));
		op.setEmail(null);
		op2 = new Operator("paco@gmail.com", "12356");
		assertFalse(op.equals(op2));

		op.setId(null);
		op2.setId(new ObjectId());
		assertFalse(op.equals(op2));

		op.setId(new ObjectId(new Date()));
		assertFalse(op.equals(op2));
	}
}
