# POC Based on mock service simulating a Subscription Service

# Update information from API
Feature: POC Example - Update actions


  # UPDATE User by data table
  Scenario: Update User based by id with data table information
    Given I get the endpoint by resource "bank_endpoint"
    When I get the response code equals to 200
    Then I UPDATE client by id with valid account number
      |id | accountNumber          | name |
      | 1 | 1502-5003-1502-9491-48 | Eli  |