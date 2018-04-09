package entities;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
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
		Operator o = new Operator("pepe", "123456");
		Incident i = new Incident("Incidente de prueba", "Pruebaaa", IncidentStates.OPEN, "41N56E", new ArrayList<>(),
				new ArrayList<>(), new HashMap<>());
		n.setIncident(i);
		n.setOperator(o);
		n.setId(new ObjectId());

		assertTrue(n.getIncident().equals(i));
		assertTrue(n.getOperator().equals(o));

		Notification n2 = new Notification(i, o);
		n2.setId(new ObjectId());

		assertFalse(n.equals(n2));
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
}
