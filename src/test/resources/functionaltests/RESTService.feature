#Author: sivaramakrishna.gullapalli@botnotch.com
#Summary : Sample Feature File to test services
@API
Feature: IDAM Application REST Services Test
  I want to use this template for my feature file

  @API
  Scenario: To test GetALL Events API
    Given Generate the Auth Token with following request "src\test\resources\testdata\AuthToken.json"
      | Service Protocol | REST                                               |
      | EndPoint         | https://identity-test.hostcountry.qa/api/authorize |
      | Content Type     | application/json                                   |
      | HTTP Method      | Post                                               |