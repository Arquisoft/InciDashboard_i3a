package controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.uniovi.Application;

@SpringBootTest(classes = { Application.class })
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
public class AgentControllerTest {

	@Autowired
	private WebApplicationContext context;

	private MockMvc mockMvc;
	private MockHttpSession session;

	@Before
	public void setUp() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
		session = new MockHttpSession();
	}

	@Test
	public void testIncis() throws Exception {
		MockHttpServletRequestBuilder request = get("/agent/listSensors").session(session);
		mockMvc.perform(request).andExpect(view().name("redirect:/login"));
	}
}
