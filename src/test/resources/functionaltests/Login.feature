Feature: Automated End2End Tests
Description: The purpose of this feature is to test End 2 End IDAM Application.
 
@Login
Scenario: Verify Whether the Dashboard is displayed when users logs in with valid credentials
	Given User is in LoginPage and performs login with below fields
	|UserName|admin@botnotch.com|
	|Password|Botnotch#560040   |
	Then Logout of the Application