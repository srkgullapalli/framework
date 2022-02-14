Feature: IDAM Application User Creation Flows
  Description: The purpose of this feature is to test User Creation

  @Users
  Scenario: To test Create user is working with Role Approval process
    Given Launch Application with URL
      | launchMode | Normal |
    Then User is in LoginPage and performs login with below fields
      | UserName | admin@botnotch.com |
      | Password | Botnotch#560040    |
    Then Select the service from the below menu options
      | service | IDAM |
    Then Select sub service for main service selection
      | subService | Users |
    Then Create new user with the below fields
    Then Verify Approval Status in User Details Screen
      | approvalStatus | User Approval Pending |
      | IDAMID         | ####                  |
      | userStatus     | Valid                 |
    Then Logout of the Application
    Given Launch Application with URL
      | launchMode | Incognito |
    Then User is in LoginPage and performs login with below fields
      | UserName | vasanth12@yopmail.com |
      | Password | Admin@1234            |
    Then Select the service from the below menu options
      | service | IDAM |
    Then Select sub service for main service selection
      | subService | Pending Approvals |
    Then navigate to view details screen for the user
    Then Approve or Reject RequestAccess for the user
    Then Select sub service for main service selection
      | subService | Users |
    Then Verify Approval Status in User Details Screen
      | approvalStatus | Roles Approved |
    Then Logout of the Application
