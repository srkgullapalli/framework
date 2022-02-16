Feature: IDAM Application Import User Feature
  Description: The purpose of this feature is to test Import functionality

  @Users
  Scenario: To test Create user is working with Imports
    Given Launch Application with URL
      | launchMode | Normal |
    Then User is in LoginPage and performs login with below fields
      | UserName | admin@botnotch.com |
      | Password | Botnotch#560040    |
    Then Select the service from the below menu options
      | service | IDAM |
    Then Select sub service for main service selection
      | subService | Imports |
    Then Inject data into testContext
      | phoneNo | 9000448221 |
    Then Perform User import using the file "\src\test\resources\testdata\User_Automation.xlsx"
    Then Select sub service for main service selection
      | subService | Users |
    Then perform search for user in Users screen with the following
      | searchCriteria | phoneNo |
    Then Verify Approval Status in User Details Screen
      | approvalStatus | Role Approval Pending |
      | userStatus     | Valid                 |
    Then Logout of the Application