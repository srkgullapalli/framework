Feature: Automated End2End Tests
  Description: The purpose of this feature is to test End 2 End IDAM Application.

  @Login
  Scenario: Verify Whether the Dashboard is displayed when users logs in with valid credentials
    Given Launch Application with URL
    Then User is in LoginPage and performs login with below fields
      | UserName | admin@botnotch.com |
      | Password | Botnotch#560040    |
    Then Select the service from the below menu options
      | service    | IDAM  |
      | subService | Users |
    Then Create new user with the below fields
      | firstName     | AutoUser          |
      | lastName      | Automation        |
      | userType      | staff             |
      | gender        | Male              |
      | documentType  | QatarID           |
      | countryCode   | India             |
      | mobileNo      |        9676098748 |
      | roleGroup     | AutomationManager |
      | jobTitle      | Steward           |
      | justification | Automation        |
    Then Logout of the Application
