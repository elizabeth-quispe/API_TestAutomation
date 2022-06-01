## POC Based on mock service simulating a Subscription Service
#
# Transaction creation
Feature: POC Example - Create actions

  # Create a transaction based on a String - Request body
  Scenario: Create a new random client transactions based on body request
    When I create new transactions 1
    Then I get the response code equals to 201
