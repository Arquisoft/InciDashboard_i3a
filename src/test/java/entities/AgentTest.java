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
		agent = new Agent();
		agent.setName("pepe");
		agent.setKindCode(1);
		agent.setEmail("pepe");

		assertTrue(agent.getEmail().equals("pepe"));
		assertTrue(agent.getKindCode() == 1);

		Agent aux = new Agent();
		aux.setName("pepe");
		aux.setKindCode(1);

		agent.setEmail("paco");
		assertTrue(agent.getEmail().equals("paco"));
		agent.setKindCode(2);
		assertTrue(agent.getKindCode() == 2);

		assertTrue(agent.getId() == null);
		agent.setAgentId(new ObjectId(new Date()).toString());
		assertTrue(agent.getAgentId() != null);

		aux.setLocation("39N56W");
		assertTrue(aux.getLocation().equals("39N56W"));
	}

}
