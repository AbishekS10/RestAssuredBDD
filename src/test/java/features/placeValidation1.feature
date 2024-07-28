Feature: Validating Place API's

@Addplace
Scenario Outline: Verify if Place is being Succesfully added using AddPlaceAPI

Given Add Place Payload with "<name>" "<language>" "<address>"
When user calls "AddPlaceAPI" with "post" http request
Then the API call got success with status code 200
And "status" in response body is "OK"
And "scope" in response body is "APP"
And verify place_id created maps to "<name>" using "GetPlaceAPI"

Examples:
|name  |  language     | address       | 
|Abishek | Tamil  | Chennai |
#|Shruthi | Malayalam  | Kochi |

@Deleteplace
Scenario: Verify id user able to delete place

Given delete Payload
When user calls "DeletePlaceAPI" with "post" http request
Then the API call got success with status code 200
And "status" in response body is "OK"



