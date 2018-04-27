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
		agent = new Agent("pepe", 1, "");

		assertTrue(agent.getEmail().equals("pepe"));
		assertTrue(agent.getKindCode() == 1);

		Agent aux = new Agent("pepe", 1, "");
		assertTrue(aux.equals(agent));
		assertTrue(aux.hashCode() == agent.hashCode());

		agent.setEmail("paco");
		assertTrue(agent.getEmail().equals("paco"));
		agent.setKindCode(2);
		assertTrue(agent.getKindCode() == 2);

		assertTrue(agent.getId() == null);
		agent.setId(new ObjectId(new Date()));
		assertTrue(agent.getId() != null);

		aux.setLocation("39N56W");
		assertTrue(aux.getLocation().equals("39N56W"));

		assertTrue(agent.toString().equals("Agent [username=paco, kind=2]"));
	}

}
