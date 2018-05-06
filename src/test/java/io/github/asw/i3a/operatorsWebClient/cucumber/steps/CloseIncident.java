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

public class CloseIncident {

	private Operator operator;
	private Incident incident;
	
	@Given("^one operator of the application$")
	public void anoperator() throws Throwable {
		operator = OperatorService.getAllOperators().get(1);
	}

	@Given("^an operator's incident$")
	public void assigned_incident() throws Throwable {
		List<Incident> incidents = IncidentService.getInProcessIncidentsOfOperator(operator.getOperatorId().toString());
		if (!incidents.isEmpty())
			incident = incidents.get(0);
	}

	@When("^he modifies the incident to closed$")
	public void modifies_the_incidence() throws Throwable {
		incident.setStatus("CLOSED");
	}

	@Then("^the incident is closed$")
	public void incidence_is_in_process() throws Throwable {
		assertThat(incident.getStatus(), is("CLOSED"));

	}
}
