package io.github.asw.i3a.operatorsWebClient.entities;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import io.github.asw.i3a.operatorsWebClient.entitites.Agent;

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
		agent.setAgentId("asdfgh");
		assertTrue(agent.getAgentId() != null);

		assertFalse(aux.equals(agent));
		assertFalse(aux.hashCode() == agent.hashCode());

		aux.setLocation("39N56W");
		assertTrue(aux.getLocation().equals("39N56W"));

		assertTrue(agent.toString()
				.equals("Agent(name=pepe, email=paco, id=null, location=null, kindCode=2, kind=null, agentId=asdfgh)"));
	}

}
