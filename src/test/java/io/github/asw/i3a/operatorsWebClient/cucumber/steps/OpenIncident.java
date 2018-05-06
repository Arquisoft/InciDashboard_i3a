package io.github.asw.i3a.operatorsWebClient.cucumber.steps;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.asw.i3a.operatorsWebClient.client.IncidentService;
import io.github.asw.i3a.operatorsWebClient.client.OperatorService;
import io.github.asw.i3a.operatorsWebClient.entitites.Incident;
import io.github.asw.i3a.operatorsWebClient.entitites.Operator;

public class OpenIncident {
	private Operator operator;
	private Incident incident;
	
	@Given("^an operator$")
	public void operator() throws Throwable {
		operator = OperatorService.getAllOperators().get(1);
	}

	@Given("^an assigned incident$")
	public void an_assigned_incident() throws Throwable {
		List<Incident> incidents = IncidentService.getInProcessIncidentsOfOperator(operator.getOperatorId().toString());
		if (!incidents.isEmpty())
			incident = incidents.get(0);
	}

	@When("^he modifies the incident to open$")
	public void he_modifies_the_incidence() throws Throwable {
		incident.setStatus("OPEN");
	}

	@Then("^the incident is opened$")
	public void the_incidence_is_opened() throws Throwable {
		assertThat(incident.getStatus(), is("OPEN"));

	}
}
