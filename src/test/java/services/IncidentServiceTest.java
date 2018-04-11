package services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bson.types.ObjectId;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.uniovi.Application;
import com.uniovi.entitites.Agent;
import com.uniovi.entitites.Incident;
import com.uniovi.entitites.IncidentStates;
import com.uniovi.entitites.Notification;
import com.uniovi.entitites.Operator;
import com.uniovi.services.AgentsService;
import com.uniovi.services.IncidentsService;
import com.uniovi.services.NotificationService;

@SpringBootTest(classes = { Application.class })
@RunWith(SpringJUnit4ClassRunner.class)
public class IncidentServiceTest {

	private Incident incident1;
	private Incident incident2;

	private Agent agent1;
	private Agent agent2;

	@Autowired
	private IncidentsService incidentsService;

	@Autowired
	private AgentsService agentsService;

	@Autowired
	private NotificationService notificationsService;

	@Before
	public void setUp() {
		agent1 = new Agent("pepe", 1);
		agent2 = new Agent("sensor5684651JS", 2);

		incident1 = new Incident("Accidente de trafico", "Multiples heridos", IncidentStates.OPEN, "556N26E",
				new ArrayList<>(), new ArrayList<>(), new HashMap<>());
		incident1.getProperty_value().put("wounded", "10");

		incident2 = new Incident("Sensor de temperatura 4645", "Temperatura elevada", IncidentStates.OPEN, "",
				new ArrayList<>(), new ArrayList<>(), new HashMap<>());
		incident2.getProperty_value().put("temperature", "42.3");

		agentsService.addAgent(agent1);
		agentsService.addAgent(agent2);

		incidentsService.addIncident(incident1);
		incidentsService.addIncident(incident2);
	}

	@After
	public void clean() {
		incidentsService.deleteIncident(incident1);
		incidentsService.deleteIncident(incident2);
		if (agent1 != null) {
			agentsService.deleteAgent(agent1);
		}
		if (agent2 != null) {
			agentsService.deleteAgent(agent2);
		}
	}

	@Test
	public void testChangeStateIncident() {
		ObjectId id = incident1.getId();
		incidentsService.changeIncidentState(incident1.getId(), IncidentStates.OPEN.toString());
		assertEquals(IncidentStates.OPEN, incidentsService.getIncident(id).getState());

		incidentsService.changeIncidentState(null, IncidentStates.CLOSED.toString());
		assertNotEquals(IncidentStates.CLOSED, incidentsService.getIncident(id).getState());

		incidentsService.changeIncidentState(incident1.getId(), "whatever");
		assertEquals(IncidentStates.OPEN, incidentsService.getIncident(id).getState());
	}

	@Test
	public void testInsertionsDeletions() {
		Incident aux = new Incident("Pruebaaa", "Pruebaaa", IncidentStates.OPEN, "", new ArrayList<>(),
				new ArrayList<>(), new HashMap<>());
		assertEquals(incidentsService.getIncident(aux.getId()), null);
		incidentsService.addIncident(aux);
		assertEquals(incidentsService.getIncident(aux.getId()), aux);

		assertEquals(incidentsService.getIncident(incident1.getId()), incident1);
		assertEquals(incidentsService.getIncident(incident2.getId()), incident2);

		incidentsService.deleteIncident(aux);
		assertEquals(incidentsService.getIncident(aux.getId()), null);
	}

	@Test
	public void testIncidentsOperator() {
		Operator op = new Operator("juan", "123456");

		List<Incident> incidents = incidentsService.getIncidentsOfOperator(op.getEmail());
		assertEquals(0, incidents.size());

		Notification n1 = new Notification(incident1, op);
		Notification n2 = new Notification(incident2, op);

		notificationsService.add(n1);
		notificationsService.add(n2);

		incidents = incidentsService.getIncidentsOfOperator(op.getEmail());
		assertEquals(2, incidents.size());
		assertTrue(incidents.contains(incident1));
		assertTrue(incidents.contains(incident2));
	}

	@Test
	public void testAddInfo() {
		assertEquals(0, incidentsService.getIncident(incident1.getId()).getComments().size());
		incident1.addComment("This incident is too difficult to be solved");
		// incidentsService.addIncident(inciTest4);
		assertEquals(1, incidentsService.getIncident(incident1.getId()).getComments().size());
	}
}
