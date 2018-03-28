package classification;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.uniovi.clasification.IncidentsClassifier;
import com.uniovi.clasification.NotificationManager;
import com.uniovi.entitites.Incident;
import com.uniovi.entitites.Operator;
import com.uniovi.repository.IncidentsRepository;
import com.uniovi.repository.OperatorRepository;
import com.uniovi.serializer.InciDeserializer;

public class IncidentClassificationTest {

	@Autowired
	OperatorRepository operatorRepository;
	
	@Autowired
	IncidentsRepository incidentRepository;
	
	@SuppressWarnings("resource")
	@Test
	public void test() {
		 String json = "{\"name\":\"fuego en uniovi\",\"description\":\"se ha producido un incendio en la Escuela de Ingeniería Informática\",\"location\":\"41.5N35.99W\",\"tags\":[\"fuego\",\"informática\"],\"property_value\":{\"fire\":true,\"temperature\":40}}";
		 InciDeserializer deserializer = new InciDeserializer();
		 Incident i = deserializer.deserialize("", json.getBytes());
		 assertEquals(i.getDescription(), "se ha producido un incendio en la Escuela de Ingeniería Informática");
		 System.out.println(i.toString());
		
		 i.setOperator(new Operator("operator1", "123456"));
		 
		 IncidentsClassifier classifier = new IncidentsClassifier();
		 classifier.classify(i);
		 assertEquals(NotificationManager.getInstance().getNotifications().size(), 1);
	}

}