Feature: Change incident state

	Scenario: An operator modifies an incident status to open
		Given an operator
		And an assigned incident
		When he modifies the incident to open
		Then the incident is opened
		
	Scenario: An operator modifies an incident status to in process
		Given an operator of the application
		And one assigned incident
		When he modifies the incident to in process
		Then the incident is in process
		
	Scenario: An operator modifies an incident status to cancel
		Given one operator
		And one incident of the operator
		When he modifies the incident to cancel
		Then the incident is cancel
	
	Scenario: An operator modifies an incident status to closed
		Given one operator of the application
		And an operator's incident
		When he modifies the incident to closed
		Then the incident is closed