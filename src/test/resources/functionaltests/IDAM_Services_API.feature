#Author: sivaramakrishna.gullapalli@botnotch.com
#Summary : Sample Feature File to test services
@API
Feature: IDAM Application Test Services API Endpoints

  Background: User generates authorisation token
    Given Generate the Auth Token with following request "src\test\resources\testdata\AuthToken.json"
      | endPoint   | /api/authorize |
      | httpMethod | Post           |

  @API
  Scenario: To verify Authorized user is able to fetch GetServices data
    Then Trigger the API with authToken and request-body ""
      | endPoint         | /api/sns_service_types |
      | httpMethod       | Get                    |
      | restrictedAccess | False                  |
    Then Verify API Response for the following service
      | service | GetAllServices |

  Scenario: To verify Authorized user is able to Post a service
    Then Trigger the API with authToken and request-body "src\test\resources\testdata\CreateService.json"
      | endPoint   | /api/sns_service_types |
      | httpMethod | Post                   |
    Then Verify API Response for the following service
      | service | PostingService |