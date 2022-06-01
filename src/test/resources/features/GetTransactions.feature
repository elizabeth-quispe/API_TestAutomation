# POC Based on mock service simulating a Subscription Service

# Get information from API
Feature: POC Example - Get actions

  Scenario: Testing an Endpoint - Get action using string parameter by resource
    Given I get the endpoint by resource "bank_endpoint"
    Then I get the response code equals to 200

  # Get request scenario using a implicit endpoint
  @myScenario
  Scenario: Testing an Endpoint - Get action using JSON resource
    Given I get the response from the endpoint "https://628c1544a3fd714fd02c7a3e.mockapi.io/api/v1/client"
    When I get the response code equals to 200
    Then I get the list of transactions

  @emailScenario
  Scenario: Testing an email duplicated - Get action using string parameter by resource
  #Given I Verify email
  When I Verify email
  Then I get the response code equals to 200


  # Check if List is empty
 # Scenario: Testing an Endpoint - Get action using string parameter by resource
  #  Given I get the endpoint by resource "bank_endpoint"
   # Then I check the list of Transactions are available
