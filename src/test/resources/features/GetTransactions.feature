# POC Based on mock service simulating a Subscription Service

# Get information from API
Feature: POC Example - Get actions

  # Get request scenario using a implicit endpoint
  @myScenario
  Scenario: Testing an Endpoint - Get action using JSON resource
    Given I get the response from the endpoint
    Then I get the response code equals to 200

  # Get request scenario using endpoint by parameter
  Scenario: Testing an Endpoint - Get action using string parameter
    Given I get the response from the endpoint "https://628c1544a3fd714fd02c7a3e.mockapi.io/api/v1/client"
    Then I get the response code equals to 200

  # Get request scenario using endpoint by parameter
  Scenario: Testing an Endpoint - Get action using string parameter by resource
    Given I get the endpoint by resource "bank_endpoint"
    Then I get the response code equals to 200

  # Get request scenario using data table by parameter
  Scenario Outline: Testing an Endpoint - Get action using example table
    Given I get the response from the endpoint <Endpoint>
    Then I get the response code equals to <Status>

    Examples:
      | Endpoint                                                    | Status |
      | "https://628c1544a3fd714fd02c7a3e.mockapi.io/api/v1/client" | 200    |
      | "https://628c1544a3fd714fd02c7a3e.mockapi.io/api/v1/client" | 200    |
      | "https://628c1544a3fd714fd02c7a3e.mockapi.io/api/v1/client" | 200    |


  # Get request scenario using data table by parameter with key
  Scenario Outline: Testing an Endpoint - Get action using example table with key
    Given I get the response from the endpoint file with key <Key>
    Then I get the response code equals to <Status>

    Examples:
      | Key             | Status |
      | "bank_endpoint" | 200    |
      | "bank_endpoint" | 200    |
      | "bank_endpoint" | 200    |


  # Get request scenario using data table comparing against service response body
#  Scenario: Testing an Endpoint - Get action using example table comparing values
 #   Given I get the response from the endpoint "https://628c1544a3fd714fd02c7a3e.mockapi.io/api/v1/client"
 #   When I get the response code equals to 200
 #   Then I compare following data against client
 #     | name   | lastName  | amount |
#      | name 1 | lastName 1| 106.81 |
#      | name 2 | lastName 2| 203.36 |
#      | name 3 | lastName 3| 503.53 |

  # Check if List is empty
 # Scenario: Testing an Endpoint - Get action using string parameter by resource
  #  Given I get the endpoint by resource "bank_endpoint"
   # Then I check the list of Transactions are available
