## POC Based on mock service simulating a Subscription Service

## Delete information from API
  Feature: POC Example - Delete actions

  # DELETE Request based on a key and using JSON resource
  @deleteScenario
  Scenario: Delete all client transactions from service
    Given I clean all transactions from the endpoint
    When  I get the response code equals to 200
    Then I check the list is empty