package services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.uniovi.Application;
import com.uniovi.entitites.Operator;
import com.uniovi.services.OperatorService;

@SpringBootTest(classes = { Application.class })
@RunWith(SpringJUnit4ClassRunner.class)
public class OperatorServiceTest {

	@Autowired
	private OperatorService operatorService;

	private Operator op1;
	private Operator op2;

	@Before
	public void setUp() {
		op1 = new Operator("paco@gmail.com", "123456");
		op2 = new Operator("pepe@gmail.com", "passwd");

		operatorService.add(op1);
		operatorService.add(op2);
	}

	@After
	public void clean() {
		operatorService.delete(op1);
		operatorService.delete(op2);
	}

	@Test
	public void testOperator() {
		assertEquals(op1, operatorService.getOperatorByEmail("paco@gmail.com"));
		assertEquals(op2, operatorService.getOperatorByEmail("pepe@gmail.com"));

		Operator auxOp = new Operator("prueba@prueba.org", "asdfgh");
		assertEquals(null, operatorService.getOperatorByEmail("prueba@prueba.org"));
		operatorService.add(auxOp);
		assertEquals(auxOp, operatorService.getOperatorByEmail("prueba@prueba.org"));

		operatorService.delete(auxOp);
		assertEquals(null, operatorService.getOperatorByEmail("prueba@prueba.org"));
	}

	@Test
	public void testExtras() {
		Operator op = operatorService.getRandomOperator();
		List<Operator> operators = operatorService.getAll();
		assertTrue(operators.contains(op));
	}
}
