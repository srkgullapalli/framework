#Author: sivaramakrishna.gullapalli@botnotch.com
#Summary : Sample Feature File to test services
@API
Feature: IDAM Application REST Services Test
  I want to use this template for my feature file

  Background: User generates authorisation token
    Given Generate the Auth Token with following request "src\test\resources\testdata\AuthToken.json"
      | endPoint   | /api/authorize |
      | httpMethod | Post           |

  @API
  Scenario: Authorized user is able to fetch GetEvents data
    Then Trigger the API with authToken and requestbody ""
      | endPoint         | /api/events |
      | httpMethod       | Get         |
      | restrictedAccess | False       |
