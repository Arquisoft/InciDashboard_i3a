package entities;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
	}

	@Test
	public void testEmpty() {
		Agent a = new Agent();
		assertTrue(a.getUsername() == null);
		assertTrue(a.getKind() == 0);
		assertTrue(a.getId() == null);
	}

	@Test
	public void testId() {
		Agent a = new Agent("hola", 2);
		Agent a2 = new Agent("pepito", 1);
		a.setId(new ObjectId());

		assertFalse(a.equals(a2));
		assertFalse(a.hashCode() == a2.hashCode());
	}
}
