## POC Based on mock service simulating a Subscription Service
#
## User creation
Feature: POC Example - Put actions

# Create a user based on resources by key (JSON file)
  Scenario: Create a new user based on JSON file (resource)
    When I create a new user using resources with key "userCreation"
    Then I get the response code equals to 201

    #
 # Get request scenario using endpoint by parameter
  Scenario: Testing an Endpoint - Get action using string parameter by resource
    Given I get the endpoint by resource "bank_endpoint"
    Then I get the response code equals to 200