package entities;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.bson.types.ObjectId;
import org.junit.Test;

import com.uniovi.entitites.Incident;
import com.uniovi.entitites.IncidentStates;
import com.uniovi.entitites.Notification;
import com.uniovi.entitites.Operator;

public class NotificationTest {

	@Test
	public void testCreation() {
		Notification n = new Notification();
		Operator o = new Operator("pepe@gmail.com", "123456");
		Incident i = new Incident("Incidente de prueba", "Pruebaaa", IncidentStates.OPEN, "41N56E", new ArrayList<>(),
				new ArrayList<>(), new HashMap<>());
		n.setIncident(i);
		n.setOperator(o);

		assertTrue(n.getIncident().equals(i));
		assertTrue(n.getOperator().equals(o));
	}

	@Test
	public void testErrors() {
		try {
			new Notification(null);
			fail("An exception should have been raised [incident null]");
		} catch (IllegalArgumentException e) {
			assertTrue(e.getMessage().equals("The incident to be assigned cannot be null"));
		}

		try {
			new Notification(new Incident(), null);
			fail("An exception should have been raised [operator null]");
		} catch (IllegalArgumentException e) {
			assertTrue(e.getMessage().equals("The operator to be assigned cannot be null"));
		}
	}

	@Test
	public void testEquals() {
		Notification n = new Notification();
		Notification n2 = null;
		assertFalse(n.equals(n2));
		assertFalse(n.equals(new Object()));

		n2 = new Notification();
		n2.setId(new ObjectId(new Date()));
		assertFalse(n.equals(n2));
		n.setId(new ObjectId());
		assertFalse(n.equals(n2));

		n2.setIncident(new Incident());
		assertFalse(n.equals(n2));
		n.setIncident(new Incident("Incidente de prueba", "Pruebaaa", IncidentStates.OPEN, "41N56E", new ArrayList<>(),
				new ArrayList<>(), new HashMap<>()));
		assertFalse(n.equals(n2));

		n2.setOperator(new Operator());
		assertFalse(n.equals(n2));
		n.setOperator(new Operator("pepe@gmail.com", "123456"));
		assertFalse(n.equals(n2));

		n = new Notification();
		n.setId(new ObjectId());
		n2 = n;
		assertTrue(n.equals(n2));

	}
}
