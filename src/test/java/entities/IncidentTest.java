package entities;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.uniovi.entitites.Incident;
import com.uniovi.entitites.IncidentStates;
import com.uniovi.entitites.Notification;

public class IncidentTest {

	@Test
	public void testCreation() {
		Incident i = new Incident("Incidente de prueba", "Pruebaaa", IncidentStates.OPEN, "41N56E", new ArrayList<>(),
				new ArrayList<>(), new HashMap<>());
		assertTrue(i.getState() == IncidentStates.OPEN);
		assertTrue(i.getDescription().equals("Pruebaaa"));
		assertTrue(i.getName().equals("Incidente de prueba"));
		assertTrue(i.getLocation().equals("41N56E"));
		assertTrue(i.getProperty_value().isEmpty());
	}

	@Test
	public void testNotificationCreation() {
		Incident i = new Incident("Incidente de prueba", "Pruebaaa", IncidentStates.OPEN, "41N56E", new ArrayList<>(),
				new ArrayList<>(), new HashMap<>());
		Notification created = i.createNotification();
		assertTrue(created.equals(i.getNotification()));
		assertTrue(created.getIncident().equals(i));
	}

	@Test
	public void testEquality() {
		Incident i = new Incident("Incidente de prueba", "Pruebaaa", IncidentStates.OPEN, "41N56E", new ArrayList<>(),
				new ArrayList<>(), new HashMap<>());
		Incident i2 = new Incident();

		assertFalse(i.equals(i2));
		assertFalse(i.hashCode() == i2.hashCode());
	}

	@Test
	public void testProperties() {
		Map<String, String> map = new HashMap<>();
		Incident i = new Incident("Incidente de prueba", "Pruebaaa", IncidentStates.OPEN, "41N56E", new ArrayList<>(),
				new ArrayList<>(), map);
		assertTrue(i.hasNormalValues());
		map.put("noexite", "hola");
		assertTrue(i.hasNormalValues());
		map.put("temperature", "39.3");
		assertTrue(i.hasNormalValues());
		map.put("flood", "true");
		assertFalse(i.hasNormalValues());
	}

	@Test
	public void testState() {
		Incident i = new Incident("Incidente de prueba", "Pruebaaa", IncidentStates.OPEN, "41N56E", new ArrayList<>(),
				new ArrayList<>(), new HashMap<>());
		assertTrue(i.getState() == IncidentStates.OPEN);
		assertTrue(i.getState().toString().equals("OPEN"));

		i.setState(IncidentStates.IN_PROCESS);
		assertTrue(i.getState() == IncidentStates.IN_PROCESS);
		assertTrue(i.getState().toString().equals("IN_PROCESS"));

		i.setState(IncidentStates.CANCELLED);
		assertTrue(i.getState() == IncidentStates.CANCELLED);
		assertTrue(i.getState().toString().equals("CANCELLED"));

		i.setState(IncidentStates.CLOSED);
		assertTrue(i.getState() == IncidentStates.CLOSED);
		assertTrue(i.getState().toString().equals("CLOSED"));
	}

	@Test
	public void testErrors() {
		try {
			new Incident("", "Pruebaaa", IncidentStates.OPEN, "41N56E", new ArrayList<>(), new ArrayList<>(),
					new HashMap<>());
			fail("An exception should have been thrown [name empty]");
		} catch (IllegalArgumentException e) {
			assertTrue(e.getMessage().equals("The name of the incident cannot be null nor empty"));
		}

		try {
			new Incident(null, "Pruebaaa", IncidentStates.OPEN, "41N56E", new ArrayList<>(), new ArrayList<>(),
					new HashMap<>());
			fail("An exception should have been thrown [name null]");
		} catch (IllegalArgumentException e) {
			assertTrue(e.getMessage().equals("The name of the incident cannot be null nor empty"));
		}

		try {
			new Incident("Accidente", "", IncidentStates.OPEN, "41N56E", new ArrayList<>(), new ArrayList<>(),
					new HashMap<>());
			fail("An exception should have been thrown [description empty]");
		} catch (IllegalArgumentException e) {
			assertTrue(e.getMessage().equals("The description of the incident cannot be null nor empty"));
		}

		try {
			new Incident("Accidente", null, IncidentStates.OPEN, "41N56E", new ArrayList<>(), new ArrayList<>(),
					new HashMap<>());
			fail("An exception should have been thrown [description null]");
		} catch (IllegalArgumentException e) {
			assertTrue(e.getMessage().equals("The description of the incident cannot be null nor empty"));
		}

		try {
			new Incident("Accidente", "Pruebaaa", null, "41N56E", new ArrayList<>(), new ArrayList<>(),
					new HashMap<>());
			fail("An exception should have been thrown [state null]");
		} catch (IllegalArgumentException e) {
			assertTrue(e.getMessage().equals("The state cannot be null"));
		}
	}
}
