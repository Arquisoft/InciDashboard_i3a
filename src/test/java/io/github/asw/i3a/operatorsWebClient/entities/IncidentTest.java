package io.github.asw.i3a.operatorsWebClient.entities;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import io.github.asw.i3a.operatorsWebClient.entitites.Comment;
import io.github.asw.i3a.operatorsWebClient.entitites.Incident;

public class IncidentTest {

	private Incident incident;

	@Test
	public void test() {
		assertNull(incident);
		incident = new Incident("Prueba", "desc", "OPEN", "", new String[1], new String[1], new HashMap<>(),
				new ArrayList<>(), "456", "132");
		assertNotNull(incident);

		assertTrue(incident.getTitle().equals("Prueba"));
		incident.setTitle("Cambio");
		assertTrue(incident.getTitle().equals("Cambio"));

		assertTrue(incident.getDescription().equals("desc"));
		incident.setDescription("description");
		assertTrue(incident.getDescription().equals("description"));

		assertTrue(incident.getStatus().equals("OPEN"));
		incident.setStatus("CLOSED");
		assertTrue(incident.getStatus().equals("CLOSED"));

		assertTrue(incident.getLocation().equals(""));
		incident.setLocation("36N34W");
		assertTrue(incident.getLocation().equals("36N34W"));

		assertTrue(incident.getTags().length == 1);
		incident.setTags(new String[2]);
		assertTrue(incident.getTags().length == 2);

		assertTrue(incident.getMultimedia().length == 1);
		incident.setMultimedia(new String[2]);
		assertTrue(incident.getMultimedia().length == 2);

		assertTrue(incident.getComments().size() == 0);
		List<Comment> comms = new ArrayList<>();
		comms.add(new Comment("aaa", "123"));
		incident.setComments(comms);
		assertTrue(incident.getComments().size() == 1);

		assertTrue(incident.getAgentId().equals("456"));
		incident.setAgentId("789");
		assertTrue(incident.getAgentId().equals("789"));

		assertTrue(incident.getOperatorId().equals("132"));
		incident.setOperatorId("321");
		assertTrue(incident.getOperatorId().equals("321"));

		assertTrue(incident.getPropertyVal().size() == 0);
		Map<String, String> props = new HashMap<>();
		props.put("accidente", "true");
		incident.setPropertyVal(props);
		assertTrue(incident.getPropertyVal().size() == 1);

		Incident aux = new Incident(incident.getTitle(), incident.getDescription(), incident.getStatus(),
				incident.getLocation(), incident.getTags(), incident.getMultimedia(), props, incident.getComments(),
				incident.getAgentId(), incident.getOperatorId());

		assertTrue(aux.equals(incident));
		assertTrue(aux.hashCode() == incident.hashCode());
		assertTrue(incident.toString()
				.equals("Incident [id=, title=Cambio, description=description, status=CLOSED, location=36N34W, "
						+ "tags=[null, null], multimedia=[null, null], property_value={accidente=true}, comments=aaa, , "
						+ "agentId=789, operatorId=321]"));

		aux = new Incident();
		assertFalse(aux.equals(incident));
		assertFalse(aux.hashCode() == incident.hashCode());
	}

}
