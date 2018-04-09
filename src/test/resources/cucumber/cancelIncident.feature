Feature: An incident is cancelled
Scenario: An operator modifies an assigned incident
Given operator with username "pepe"
And an assigned incident		
When he modifies the incident
Then the incident is cancelled