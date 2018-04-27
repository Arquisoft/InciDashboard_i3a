package entities;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.bson.types.ObjectId;
import org.junit.Test;

import com.uniovi.entitites.Agent;

public class AgentTest {

	private Agent agent;

	@Test
	public void testAll() {
		assertNull(agent);
		agent = new Agent("pepe", 1);

		assertTrue(agent.getUsername().equals("pepe"));
		assertTrue(agent.getKind() == 1);

		Agent aux = new Agent("pepe", 1);
		assertTrue(aux.equals(agent));
		assertTrue(aux.hashCode() == agent.hashCode());

		agent.setUsername("paco");
		assertTrue(agent.getUsername().equals("paco"));
		agent.setKind(2);
		assertTrue(agent.getKind() == 2);

		assertTrue(agent.getId() == null);
		agent.setId(new ObjectId(new Date()));
		assertTrue(agent.getId() != null);
	}

}
