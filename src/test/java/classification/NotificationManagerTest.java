package classification;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

import com.uniovi.clasification.NotificationManager;
import com.uniovi.entitites.Incident;
import com.uniovi.entitites.IncidentStates;
import com.uniovi.entitites.Notification;
import com.uniovi.entitites.Operator;

public class NotificationManagerTest {

	/*@Test
	public void testInserting() {
		Operator o = new Operator("pepe", "123456");
		Incident i = new Incident("Incidente de prueba", "Pruebaaa", IncidentStates.OPEN, "41N56E", new ArrayList<>(),
				new ArrayList<>(), new HashMap<>());
		Notification n = new Notification(i, o);

		NotificationManager manager = NotificationManager.getInstance();
		assertTrue(manager.getNotifications().isEmpty());
		manager.addNotification(n);
		assertTrue(manager.getNotifications().size() == 1);
	}*/
}
