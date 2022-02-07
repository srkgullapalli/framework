#Author: sivaramakrishna.gullapalli@botnotch.com
#Summary : Sample Feature File to test services
@API
Feature: IDAM Application Test Event Related Services Post, Update, Delete and GetAll events

  Background: User generates authorisation token
    Given Generate the Auth Token with following request "src\test\resources\testdata\AuthToken.json"
      | endPoint   | /api/authorize |
      | httpMethod | Post           |

  @API
  Scenario: To verify Authorized user is able to fetch GetEvents data
    Then Trigger the API with authToken and request-body ""
      | endPoint         | /api/events |
      | httpMethod       | Get         |
      | restrictedAccess | False       |
    Then Verify API Response for the following service
      | service | GetAllEvents |

  Scenario: To verify Authorized user is able to Post an event
    Then Trigger the API with authToken and request-body "src\test\resources\testdata\PostingEvent.json"
      | endPoint   | /api/events |
      | httpMethod | Post        |
    Then Verify API Response for the following service
      | service | PostingEvent |

    #If you pass resourceID it will update else you need to run Post Event it will fetch from that API Call
  Scenario: To verify Authorized user is able to Update an event
    Then Trigger the API with authToken and request-body "src\test\resources\testdata\updateEvent.json"
      | endPoint         | /api/events |
      | httpMethod       | Put         |
      | restrictedAccess | False       |
      | resourceID       |             |
    Then Verify API Response for the following service
      | service | PostingEvent |

  #If you pass resourceID it will delete else you need to run Post Event it will fetch from that API Call
  Scenario: To verify Authorized user is able to Delete an event
    Then Delete The Resource
      | endPoint         | /api/events |
      | httpMethod       | Delete      |
      | restrictedAccess | False       |
      | resourceID       |             |
    Then Verify API Response for the following service
      | service | DeleteEvent |
