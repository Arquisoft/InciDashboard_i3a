package entities;

import static org.junit.Assert.assertTrue;

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
}
