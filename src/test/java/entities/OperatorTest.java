package entities;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.uniovi.entitites.Incident;
import com.uniovi.entitites.IncidentStates;
import com.uniovi.entitites.Notification;
import com.uniovi.entitites.Operator;

public class OperatorTest {

	@Test
	public void testCreation() {
		Operator op = new Operator("pepe", "123456");
		Notification n = new Notification();
		Incident i = new Incident("Incidente de prueba", "Pruebaaa", IncidentStates.OPEN, "41N56E", new ArrayList<>(),
				new ArrayList<>(), new HashMap<>());
		n.setIncident(i);
		n.setOperator(op);

		Set<Notification> nots = new HashSet<>();
		nots.add(n);
		op.setNotifications(nots);

		assertTrue(op.getNotifications().size() == 1);
		assertTrue(n.getOperator().equals(op));
	}

	@Test
	public void testGetters() {
		Operator op = new Operator("pepe", "123456");
		assertTrue(op.getEmail().equals("pepe"));
		assertTrue(op.getPassword().equals("123456"));
		assertTrue(op.getNotifications().isEmpty());
	}

	@Test
	public void testErrors() {
		try {
			new Operator(null, "124");
			fail("An exception should have been raised [email null]");
		} catch (IllegalArgumentException e) {
			assertTrue(e.getMessage().equals("The email cannot be empty nor null"));
		}

		try {
			new Operator("", "124");
			fail("An exception should have been raised [email empty]");
		} catch (IllegalArgumentException e) {
			assertTrue(e.getMessage().equals("The email cannot be empty nor null"));
		}

		try {
			new Operator("hola", "");
			fail("An exception should have been raised [password empty]");
		} catch (IllegalArgumentException e) {
			assertTrue(e.getMessage().equals("The password cannot be empty nor null"));
		}

		try {
			new Operator("hola", null);
			fail("An exception should have been raised [password null]");
		} catch (IllegalArgumentException e) {
			assertTrue(e.getMessage().equals("The password cannot be empty nor null"));
		}
	}
}
