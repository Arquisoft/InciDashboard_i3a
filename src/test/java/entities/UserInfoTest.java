package entities;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.uniovi.entitites.UserInfo;

public class UserInfoTest {

	private UserInfo user;

	@Test
	public void test() {
		assertNull(user);
		user = new UserInfo("paco", "1234");
		assertNotNull(user);
		assertTrue(user.getLogin().equals("paco"));
		assertTrue(user.getPassword().equals("1234"));

		UserInfo aux = new UserInfo("pepe", "asdfg");
		assertFalse(aux.equals(user));
		aux.setLogin(user.getLogin());
		assertFalse(aux.equals(user));
		aux.setPassword(user.getPassword());
		assertTrue(aux.equals(user));
		assertTrue(aux.hashCode() == user.hashCode());

		assertTrue(user.toString().equals("UserInfo(login=paco, password=1234)"));
	}

}
