Feature: Validating Place API

@AddPlace
Scenario Outline: Verify if Place is being successfully added using AddPlaceApi
Given Add place payload "<name>" "<Language>" "<Address>"
When user calls "AddPlaceAPI" with "Post" http request
Then the API call got success with "status" code 200
And "status" in response body is "OK"
And "scope" in response body is "APP"
And Verify place_ID created map to "<name>" using "GetPlaceAPI"

Examples:

       |name	|Language|Address|
       |Rahaman|Hindi|Mainpuri|
       
@DeletePlace @Regression
Scenario: Verify if Place is being successfully deleted using DeletePlaceApi  

Given delete place payload 
When user calls "DeletePlaceAPI" with "Post" http request
Then the API call got success with "status" code 200
And "status" in response body is "OK"     
