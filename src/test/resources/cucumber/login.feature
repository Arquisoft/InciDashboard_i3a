Feature: Login as an operator in the web site
Scenario: Correct login
Given the operator is correct
When the username exists
And the password corresponds to the username
Then the operator should access the dashboard page