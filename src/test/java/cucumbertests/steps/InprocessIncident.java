package cucumbertests.steps;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.uniovi.client.IncidentService;
import com.uniovi.client.OperatorService;
import com.uniovi.entitites.Incident;
import com.uniovi.entitites.Operator;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class InprocessIncident {
	private Operator operator;
	private Incident incident;

	@Autowired
	OperatorService operatorService;
	
	@Autowired
	IncidentService incidentService;
	
	@Given("^an operator of the application$")
	public void anoperator() throws Throwable {
		operator = operatorService.getAllOperators().get(1);
	}

	@Given("^one assigned incident$")
	public void assigned_incident() throws Throwable {
		List<Incident> incidents = incidentService.getInProcessIncidentsOfOperator(operator.getOperatorId().toString());
		if (!incidents.isEmpty())
			incident = incidents.get(0);
	}

	@When("^he modifies the incident to in process$")
	public void modifies_the_incidence() throws Throwable {
		incident.setStatus("IN_PROCESS");
	}

	@Then("^the incident is in process$")
	public void incidence_is_in_process() throws Throwable {
		assertThat(incident.getStatus(), is("IN_PROCESS"));

	}
}

