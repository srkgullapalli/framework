Feature: Dry Run
  Description: Smoke Test

  @Login
  Scenario: Verify Whether a new user can be created in IDAM Application with valid credentials
    Given Launch Application with URL
    Then User is in LoginPage and performs login with below fields
      | UserName | admin@botnotch.com |
      | Password | Botnotch#560040    |
    Then Select the service from the below menu options
      | service    | IDAM  |
      | subService | Users |
    Then Inject data into testContext
    Then verify user created is displayed in search results for user
    Then Logout of the Application

