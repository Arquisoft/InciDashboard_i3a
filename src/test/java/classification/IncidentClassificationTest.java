package classification;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.uniovi.entitites.Incident;
import com.uniovi.serializer.InciDeserializer;

@RunWith(SpringJUnit4ClassRunner.class)
public class IncidentClassificationTest {

	@SuppressWarnings("resource")
	@Test
	public void test() {
		String json = "{\"name\":\"fuego en uniovi\",\"description\":\"se ha producido un incendio en la Escuela de Ingeniería Informática\",\"date\":\"2012-10-01T09:45:00.000UTC+00:00\",\"location\":\"41.5N35.99W\",\"tags\":[\"fuego\",\"informática\"],\"property_value\":{\"fire\":true,\"temperature\":40}}";
		InciDeserializer deserializer = new InciDeserializer();
		Incident i = deserializer.deserialize("", json.getBytes());
		assertEquals(i.getDescription(), "se ha producido un incendio en la Escuela de Ingeniería Informática");
	}
}