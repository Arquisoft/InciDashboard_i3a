package controllers;

import java.net.URL;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;

import com.uniovi.Application;
import com.uniovi.controllers.OperatorController;

@SpringBootTest(classes = { Application.class })
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
public class OperatorControllerTest {

	private URL base;
	private RestTemplate template;

	private MockMvc mockMvc;

	@Autowired
	private OperatorController context;

	@Before
	public void setUp() throws Exception {
		this.base = new URL("http://localhost:8080/");
		template = new RestTemplate();
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(context).build();
	}

	@Test
	public void testLoginPage() throws Exception {
		// template.getForEntity(base.toString(), String.class);
		// String message =
		// mockMvc.perform(get("/login")).andExpect(status().isOk())
		// .andExpect(content().string(containsString("Email:")))
		// .andExpect(content().string(containsString("Password:"))).andReturn().toString();
		//
		// assertNotNull(message);
	}
}
