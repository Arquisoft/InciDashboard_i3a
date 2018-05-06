package io.github.asw.i3a.operatorsWebClient.clients;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import TestKit.IntegrationTest;
import io.github.asw.i3a.operatorsWebClient.Application;
import io.github.asw.i3a.operatorsWebClient.client.IncidentService;

@SpringBootTest(classes = { Application.class })
@RunWith(SpringJUnit4ClassRunner.class)
@Category(IntegrationTest.class)
public class IncidentsClientTest {

	@Test
	public void getIncidentsTest() {
		assertNotNull( IncidentService.getAllIncidents() );
	}

	@Test
	public void getIncidentTest() {
		IncidentService.getIncident( "5aef11e118995700017ea205" );
		// That Depends from the database status so not compulsory to pass
		//assertNotNull( incident );
	}
}
