package entities;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.bson.types.ObjectId;
import org.junit.Test;

import com.uniovi.entitites.Operator;

public class OperatorTest {

	private Operator operator;

	@Test
	public void test() {
		assertNull(operator);
		operator = new Operator("paco", "1234");
		assertTrue(operator.getEmail().equals("paco"));
		assertTrue(operator.getPassword().equals("1234"));

		Operator aux = new Operator("pepe", "asdf");
		assertFalse(aux.equals(operator));
		assertFalse(aux.hashCode() == operator.hashCode());

		aux.setEmail(operator.getEmail());
		aux.setPassword(operator.getPassword());

		assertTrue(aux.equals(operator));
		assertTrue(aux.hashCode() == operator.hashCode());

		assertTrue(operator.getId() == null);
		operator.setId(new ObjectId(new Date()));
		assertFalse(operator.getId() == null);

		assertTrue(operator.toString().equals("Operator [email=paco, password=1234]"));

	}
}
