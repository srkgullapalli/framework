#Author: sivaramakrishna.gullapalli@botnotch.com
#Summary : Sample Feature File to test services

@tag
Feature: IDAM Application REST Services Test
  I want to use this template for my feature file

  @tag1
  Scenario: To test GetALL Events API
    Given Generate the Auth Token with following request ""
    And some other precondition
    When I complete action
    And some other action
    And yet another action
    Then I validate the outcomes
    And check more outcomes

  @tag2
  Scenario Outline: Title of your scenario outline
    Given I want to write a step with <name>
    When I check for the <value> in step
    Then I verify the <status> in step

    Examples: 
      | name  | value | status  |
      | name1 |     5 | success |
      | name2 |     7 | Fail    |