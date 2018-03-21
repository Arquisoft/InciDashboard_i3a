package classification;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uniovi.clasification.IncidentsClassifier;
import com.uniovi.clasification.NotificationManager;
import com.uniovi.entitites.Incident;

public class IncidentClassificationTest {

	@Test
	public void test() {
		String json = "{\"name\":\"fuego en uniovi\",\"description\":\"se ha producido un incendio en la Escuela de Ingeniería Informática\",\"location\":\"41.5N35.99W\",\"tags\":[\"fuego\",\"informática\"],\"property_value\":{\"fire\":true,\"temperature\":40}}";
		Incident i = json2object(json);
		assertEquals(i.getDescription(), "se ha producido un incendio en la Escuela de Ingeniería Informática");
		System.out.println(i.toString());
		
		IncidentsClassifier classifier = new IncidentsClassifier();
		classifier.classify(i);
		
		assertEquals(NotificationManager.getInstance().getNotifications().size(), 1);
	}

	public Incident json2object(String json) {		

		ObjectMapper mapper = new ObjectMapper();

		try {
			// Convert JSON string to Object
			Incident incident = mapper.readValue(json, Incident.class);

			//Pretty print
			String prettyStaff1 = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(incident);
			System.out.println(prettyStaff1);

			return incident;

		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}