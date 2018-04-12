package controllers;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import org.springframework.web.context.WebApplicationContext;

import com.uniovi.Application;

@SpringBootTest(classes = { Application.class })
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
public class OperatorControllerTest {

	//@Value("${local.server.port}")
	//private int port;

	private URL base;
	private RestTemplate template;

	// @InjectMocks
	// private OperatorControllerTest operatorController;

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext context;

	@Before
	public void setUp() throws Exception {
		this.base = new URL("http://localhost:37445/");
		template = new RestTemplate();
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(context).build();
	}

	@Test
	public void testLoginPage() throws Exception {
		template.getForEntity(base.toString(), String.class);
		String message = mockMvc.perform(get("/login")).andExpect(status().isOk())
				.andExpect(content().string(containsString("Username:")))
				.andExpect(content().string(containsString("Password:")))
				.andExpect(content().string(containsString("Kind:"))).andReturn().toString();

		assertNotNull(message);
	}
}
