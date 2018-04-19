package entities;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.bson.types.ObjectId;
import org.junit.Test;

import com.uniovi.entitites.Comment;
import com.uniovi.entitites.Incident;
import com.uniovi.entitites.IncidentStates;

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

	@Test
	public void extraTest() {
		Incident i = new Incident("Accidente", "Pruebaaa", IncidentStates.OPEN, "41N56E", new ArrayList<>(),
				new ArrayList<>(), new HashMap<>(), new ArrayList<>());
		assertTrue(i.getTags().isEmpty());
		assertTrue(i.getMultimedia().isEmpty());
		i.getMultimedia().add("foto.png");
		assertFalse(i.getMultimedia().isEmpty());
		i.setMultimedia(new ArrayList<>());
		assertTrue(i.getMultimedia().isEmpty());
		assertTrue(i.getAgent_id() == null);

		i.getComments().add(new Comment("hello", new Date(), "pepe"));
		assertFalse(i.getComments().isEmpty());
		i.setComments(new ArrayList<>());
		assertTrue(i.getComments().isEmpty());
		assertTrue(i.getProperties() == null);
	}

	@Test
	public void propertiesTest() {
		Incident i = new Incident("Incidente de prueba", "Pruebaaa", IncidentStates.OPEN, "41N56E", new ArrayList<>(),
				new ArrayList<>(), new HashMap<>());
		try {
			i.getProperty_value().put("temperature", "notADouble");
			i.hasNormalValues();
		} catch (NumberFormatException e) {
			assertTrue(e.getMessage().equals("Wrong value for temperature"));
		}

		i.getProperty_value().clear();
		try {
			i.getProperty_value().put("humidity", "notADouble");
			i.hasNormalValues();
		} catch (NumberFormatException e) {
			assertTrue(e.getMessage().equals("Wrong value for humidity"));
		}
		i.getProperty_value().clear();

		try {
			i.getProperty_value().put("wind", "notADouble");
			i.hasNormalValues();
		} catch (NumberFormatException e) {
			assertTrue(e.getMessage().equals("Wrong value for wind"));
		}

		i.getProperty_value().clear();
		try {
			i.getProperty_value().put("wounded", "notANumber");
			i.hasNormalValues();
		} catch (NumberFormatException e) {
			assertTrue(e.getMessage().equals("Wrong value for number of wounded people"));
		}
		i.getProperty_value().clear();

		try {
			i.getProperty_value().put("dead", "notANumber");
			i.hasNormalValues();
		} catch (NumberFormatException e) {
			assertTrue(e.getMessage().equals("Wrong value for dead people"));
		}

		i.getProperty_value().clear();

		i.getProperty_value().put("attack", "true");
		assertFalse(i.hasNormalValues());
		i.getProperty_value().put("attack", "false");
		assertTrue(i.hasNormalValues());
		i.getProperty_value().put("robbery", "true");
		assertFalse(i.hasNormalValues());

		i.getProperty_value().clear();
		i.getProperty_value().put("dead", "2");
		assertFalse(i.hasNormalValues());

		i.getProperty_value().clear();
		i.getProperty_value().put("wounded", "3");
		assertFalse(i.hasNormalValues());

		i.getProperty_value().clear();
		i.getProperty_value().put("humidity", "37.5");
		assertTrue(i.hasNormalValues());

		i.getProperty_value().clear();
		i.getProperty_value().put("wind", "20.9");
		assertTrue(i.hasNormalValues());
	}

	@Test
	public void testEquals() {
		Incident in = new Incident();
		Incident in2 = null;

		assertFalse(in.equals(in2));
		assertFalse(in.equals(new Object()));

		in2 = new Incident();
		in2.setId(new ObjectId());
		assertFalse(in.equals(in2));

		in.setId(new ObjectId(new Date()));
		assertFalse(in.equals(in2));

		in2.setLocation("41N56W");
		assertFalse(in.equals(in2));

		in2.setName("Prueba");
		assertFalse(in.equals(in2));

		in.setName("No son iguales");
		assertFalse(in.equals(in2));

	}
}
