# POC Based on mock service simulating a Subscription Service

# Update information from API
Feature: POC Example - Update actions

  # UPDATE account number of client by data table
  @updateScenario
  Scenario: Update client transaction by id
   Given I get the endpoint by resource "bank_endpoint"
    When I want update data with response code equals to 200
    Then I UPDATE client by id with valid account number "1502-1702-2022-03" and id 1

# If sure to have data in API, uncomment and Execute this Scenario

#  Scenario: Update client transaction by id
#    Given I get the endpoint by resource "bank_endpoint"
#    When I get the response code equals to 200
#    Then I UPDATE client by id with valid account number "1502-1702-2022-03" and id 1

