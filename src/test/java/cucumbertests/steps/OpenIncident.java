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

public class OpenIncident {
	private Operator operator;
	private Incident incident;

	@Autowired
	OperatorService operatorService;
	
	@Autowired
	IncidentService incidentService;
	
	@Given("^an operator$")
	public void operator() throws Throwable {
		operator = operatorService.getAllOperators().get(1);
	}

	@Given("^an assigned incident$")
	public void an_assigned_incident() throws Throwable {
		List<Incident> incidents = incidentService.getInProcessIncidentsOfOperator(operator.getOperatorId().toString());
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
