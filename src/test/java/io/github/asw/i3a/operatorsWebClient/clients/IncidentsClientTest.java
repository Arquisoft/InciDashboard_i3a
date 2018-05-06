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
import io.github.asw.i3a.operatorsWebClient.entitites.Incident;

@SpringBootTest(classes = { Application.class })
@RunWith(SpringJUnit4ClassRunner.class)
@Category(IntegrationTest.class)
public class IncidentsClientTest {
    
    
    @Test
    public void getIncidentsTest() {
	assertNotNull(IncidentService.getAllIncidents());
    }
    
    @Test
    public void getIncidentTest() {
	Incident incident = IncidentService.getIncident("5ae842968b84c20001f8d5a4");
	assertNotNull(incident);
	System.out.println(incident);
    }
}
