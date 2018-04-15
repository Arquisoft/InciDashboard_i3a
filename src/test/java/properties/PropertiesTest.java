package properties;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.uniovi.properties.Attack;
import com.uniovi.properties.Dead;
import com.uniovi.properties.Fire;
import com.uniovi.properties.Flood;
import com.uniovi.properties.Humidity;
import com.uniovi.properties.Robbery;
import com.uniovi.properties.Temperature;
import com.uniovi.properties.Wind;
import com.uniovi.properties.Wounded;

public class PropertiesTest {

	@Test
	public void testAttack() {
		Attack a = new Attack(false);
		assertTrue(a.hasNormalValue());

		a = new Attack(true);
		assertFalse(a.hasNormalValue());
	}

	@Test
	public void testDead() {
		Dead d;

		try {
			d = new Dead(-1);
		} catch (IllegalArgumentException e) {
			assertTrue(e.getMessage().equals("The number of dead should be 0 or more"));
		}

		d = new Dead(0);
		assertTrue(d.hasNormalValue());

		d = new Dead(1);
		assertFalse(d.hasNormalValue());
		d = new Dead(100);
		assertFalse(d.hasNormalValue());

	}

	@Test
	public void testFire() {
		Fire a = new Fire(false);
		assertTrue(a.hasNormalValue());

		a = new Fire(true);
		assertFalse(a.hasNormalValue());
	}

	@Test
	public void testFlood() {
		Flood a = new Flood(false);
		assertTrue(a.hasNormalValue());

		a = new Flood(true);
		assertFalse(a.hasNormalValue());
	}

	@Test
	public void testHumidity() {
		Humidity h;
		try {
			h = new Humidity(-0.1);
		} catch (IllegalArgumentException e) {
			assertTrue(e.getMessage().equals("The value of humidity should be between 0% and 100%"));
		}

		h = new Humidity(5.6);
		assertFalse(h.hasNormalValue());

		h = new Humidity(30.8);
		assertTrue(h.hasNormalValue());

		h = new Humidity(70);
		assertFalse(h.hasNormalValue());

		try {
			h = new Humidity(100.1);
		} catch (IllegalArgumentException e) {
			assertTrue(e.getMessage().equals("The value of humidity should be between 0% and 100%"));
		}
	}

	@Test
	public void testRobbery() {
		Robbery a = new Robbery(false);
		assertTrue(a.hasNormalValue());

		a = new Robbery(true);
		assertFalse(a.hasNormalValue());
	}

	@Test
	public void testTemperature() {
		Temperature t = new Temperature(-1.5);
		assertFalse(t.hasNormalValue());

		t = new Temperature(30.8);
		assertTrue(t.hasNormalValue());

		t = new Temperature(50.3);
		assertFalse(t.hasNormalValue());
	}

	@Test
	public void testWind() {
		Wind w;
		try {
			w = new Wind(-1.0);
		} catch (IllegalArgumentException e) {
			assertTrue(e.getMessage().equals("The wind's speed should have a value greater or equal to 0"));
		}

		w = new Wind(10.0);
		assertTrue(w.hasNormalValue());

		w = new Wind(30.1);
		assertFalse(w.hasNormalValue());
	}

	@Test
	public void testWounded() {
		Wounded w;

		try {
			w = new Wounded(-1);
		} catch (IllegalArgumentException e) {
			assertTrue(e.getMessage().equals("The number of wounded should be 0, or more"));
		}

		w = new Wounded(0);
		assertTrue(w.hasNormalValue());

		w = new Wounded(1);
		assertFalse(w.hasNormalValue());
	}

}
