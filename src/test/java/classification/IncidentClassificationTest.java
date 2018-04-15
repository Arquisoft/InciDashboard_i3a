package classification;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.uniovi.clasification.IncidentsClassifier;
import com.uniovi.clasification.NotificationManager;
import com.uniovi.entitites.Incident;
import com.uniovi.entitites.IncidentStates;
import com.uniovi.entitites.Operator;
import com.uniovi.serializer.InciDeserializer;

@RunWith(SpringJUnit4ClassRunner.class)
public class IncidentClassificationTest {

	@SuppressWarnings("resource")
	@Test
	public void test() {
		int originalSize = NotificationManager.getInstance().getNotifications().size();
		String json = "{\"name\":\"fuego en uniovi\",\"description\":\"se ha producido un incendio en la Escuela de Ingeniería Informática\",\"location\":\"41.5N35.99W\",\"tags\":[\"fuego\",\"informática\"],\"property_value\":{\"fire\":true,\"temperature\":40}}";
		InciDeserializer deserializer = new InciDeserializer();
		Incident i = deserializer.deserialize("", json.getBytes());
		assertEquals(i.getDescription(), "se ha producido un incendio en la Escuela de Ingeniería Informática");

		i.setOperator(new Operator("operator1", "123456"));

		IncidentsClassifier classifier = new IncidentsClassifier();
		classifier.classify(i);
		assertEquals(originalSize + 1, NotificationManager.getInstance().getNotifications().size());
	}

	@Test
	public void testExtras() {
		List<Incident> incis = new ArrayList<>();
		Incident aux = new Incident("Atasco", "Atasco en la AS-6", IncidentStates.IN_PROCESS, "108N75E",
				new ArrayList<>(), new ArrayList<>(), new HashMap<>());
		aux.getProperty_value().put("wounded", "2");
		incis.add(new Incident("Incidente de prueba", "Pruebaaa", IncidentStates.OPEN, "41N56E", new ArrayList<>(),
				new ArrayList<>(), new HashMap<>()));
		incis.add(aux);
		int originalSize = NotificationManager.getInstance().getNotifications().size();
		IncidentsClassifier classifier = new IncidentsClassifier(incis);
		assertFalse(classifier.getIncidents().isEmpty());
		assertTrue(aux.getOperator() == null);
		aux.setOperator(new Operator("operator1", "123456"));
		classifier.classify();
		assertEquals(originalSize + 1, NotificationManager.getInstance().getNotifications().size());
	}

}