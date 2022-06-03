## POC Based on mock service simulating a Subscription Service
#
# Transaction creation
Feature: POC Example - Create actions

  # Create a transaction based on a String - Request body
  Scenario: Create a new random client transactions based on body request
    When I create new transactions 11
    Then I get the response code equals to 201

  # Create a user based on resources by key (JSON file)
  Scenario: Create a new user based on JSON file (resource)
    When I create a new user using resources with key "userCreation"
    Then I get the response code equals to 201