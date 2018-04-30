package entities;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.uniovi.entitites.InciInfo;

public class InciInfoTest {

	private InciInfo inci;

	@Test
	public void test() {
		assertNull(inci);
		inci = new InciInfo();
		assertNotNull(inci);
		assertTrue(inci.getComment() == null);
		assertTrue(inci.getStatus() == null);
		inci.setComment("comentario");
		inci.setStatus("OPEN");
		assertTrue(inci.getComment() == "comentario");
		assertTrue(inci.getStatus() == "OPEN");

		InciInfo aux = new InciInfo();
		assertFalse(aux.equals(inci));
		aux.setComment(inci.getComment());
		assertFalse(aux.equals(inci));
		aux.setStatus(inci.getStatus());
		assertTrue(aux.equals(inci));
		assertTrue(aux.hashCode() == inci.hashCode());

		assertTrue(inci.toString().equals("InciInfo(status=OPEN, comment=comentario)"));
	}

}
