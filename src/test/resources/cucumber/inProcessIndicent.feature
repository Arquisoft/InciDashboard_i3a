Feature: An incident is in process
Scenario: An operator modifies an assigned incident
Given operator with username "pepe"
And an assigned incident		
When he modifies the incident
Then the incident is in process