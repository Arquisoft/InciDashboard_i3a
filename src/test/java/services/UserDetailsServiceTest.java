package services;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.uniovi.Application;
import com.uniovi.entitites.Operator;
import com.uniovi.services.OperatorService;
import com.uniovi.services.UserDetailsServiceImpl;

@SpringBootTest(classes = { Application.class })
@RunWith(SpringJUnit4ClassRunner.class)
public class UserDetailsServiceTest {

	@Autowired
	private UserDetailsServiceImpl userDetails;

	@Autowired
	private OperatorService operatorService;

	private Operator op;

	@Before
	public void setUp() {
		op = new Operator("pruebaUser@gmail.com", "asdfgh");
		operatorService.add(op);
	}

	@After
	public void clean() {
		operatorService.delete(op);
	}

	@Test
	public void testLoad() {
		String email = "noexiste@gmail.com";
		assertTrue(userDetails.loadUserByUsername(email) == null);
		email = "pruebaUser@gmail.com";
		UserDetails u = userDetails.loadUserByUsername(email);
		assertTrue(u.getUsername().equals(op.getEmail()));
		assertTrue(u.getPassword().equals(op.getPassword()));
	}

}
