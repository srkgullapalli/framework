Feature: IDAM Application Block and Unblock Users
  Description: The purpose of this feature is to test Block and Unblock users

  @Users
  Scenario: To Test Block User Functionality for a Approved User
    Given Launch Application with URL
      | launchMode | Normal |
    Then User is in LoginPage and performs login with below fields
      | UserName | admin@botnotch.com |
      | Password | Botnotch#560040    |
    Then Select the service from the below menu options
      | service | IDAM |
    Then Select sub service for main service selection
      | subService | Users |
    Then Inject data into testContext
      | phoneNo | 9605773707 |
    Then perform search for user in Users screen with the following
      | searchCriteria | phoneNo |
    Then navigate to view details screen for the user
    Then Perform Block and UnBlock Access for user
      | action        | Block        |
      | blockComments | BlockingUser |
    Then perform search for user in Users screen with the following
      | searchCriteria | phoneNo |
    Then Verify Approval Status in User Details Screen
      | approvalStatus | Block Access Pending |
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
    Then perform search for user in Users screen with the following
      | searchCriteria | phoneNo |
    Then navigate to view details screen for the user
    Then Approve or Reject Block Access For User
      | action        | approveBlock  |
      | blockComments | Approve Block |
    Then Select sub service for main service selection
      | subService | Users |
    Then perform search for user in Users screen with the following
      | searchCriteria | phoneNo |
    Then Verify Approval Status in User Details Screen
      | approvalStatus | Block Access Approved |
      | userStatus     | Blocked               |
    Then Logout of the Application

  @Users
  Scenario: To Test Unblock User Functionality for a Blocked User
    Given Launch Application with URL
      | launchMode | Normal |
    Then User is in LoginPage and performs login with below fields
      | UserName | admin@botnotch.com |
      | Password | Botnotch#560040    |
    Then Select the service from the below menu options
      | service | IDAM |
    Then Select sub service for main service selection
      | subService | Users |
    Then Inject data into testContext
      | phoneNo | 9605773707 |
    Then perform search for user in Users screen with the following
      | searchCriteria | phoneNo |
    Then navigate to view details screen for the user
    Then Perform Block and UnBlock Access for user
      | action        | UnBlock     |
      | blockComments | UnblockUser |
    Then perform search for user in Users screen with the following
      | searchCriteria | phoneNo |
    Then Verify Approval Status in User Details Screen
      | approvalStatus | Unblock Access Pending |
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
    Then perform search for user in Users screen with the following
      | searchCriteria | phoneNo |
    Then navigate to view details screen for the user
    Then Approve or Reject Block Access For User
      | action        | approveUnBlock  |
      | blockComments | Approve Unblock |
    Then Select sub service for main service selection
      | subService | Users |
    Then perform search for user in Users screen with the following
      | searchCriteria | phoneNo |
    Then Verify Approval Status in User Details Screen
      | approvalStatus | Unblock Access Approved |
      | userStatus     | Valid                   |
    Then Logout of the Application