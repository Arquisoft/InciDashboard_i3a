package entities;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Date;

import org.bson.types.ObjectId;
import org.junit.Test;

import com.uniovi.entitites.Agent;

public class AgentTest {

	@Test
	public void testCreation() {
		Agent a = new Agent("pepito", 1);
		assertTrue(a.getKind() == 1);
		assertTrue(a.getUsername() == "pepito");
		assertTrue(a.getId() == null);
		a.setUsername("hola");
		assertTrue(a.getUsername() == "hola");
		a.setKind(2);
		assertTrue(a.getKind() == 2);
		Agent a2 = new Agent("hola", 2);
		assertTrue(a.equals(a2));
	}

	@Test
	public void testEmpty() {
		Agent a = new Agent();
		assertTrue(a.getUsername() == null);
		assertTrue(a.getKind() == 0);
		assertTrue(a.getId() == null);
	}

	@Test
	public void testErrors() {
		try {
			new Agent(null, 0);
			fail("An exception should have been raised [username null]");
		} catch (IllegalArgumentException e) {
			assertTrue(e.getMessage().equals("The username cannot be empty nor null"));
		}

		try {
			new Agent("", 0);
			fail("An exception should have been raised [username empty]");
		} catch (IllegalArgumentException e) {
			assertTrue(e.getMessage().equals("The username cannot be empty nor null"));
		}

		try {
			new Agent("hola", -1);
			fail("An exception should have been raised [kind < 0]");
		} catch (IllegalArgumentException e) {
			assertTrue(e.getMessage().equals("The kind should be a valid positive value"));
		}
	}

	@Test
	public void testEquals() {
		Agent a = new Agent();
		Agent a2 = a;
		assertTrue(a.equals(a2));
		a2 = null;
		assertFalse(a.equals(a2));
		assertFalse(a.equals(new Object()));

		a2 = new Agent();
		a2.setId(new ObjectId());
		assertFalse(a.equals(a2));

		a.setId(new ObjectId(new Date()));
		assertFalse(a.equals(a2));

		assertFalse(a.hashCode() == a2.hashCode());
	}
}
