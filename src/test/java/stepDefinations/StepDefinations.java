package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefinations extends Utils {

	RequestSpecification res;
	ResponseSpecification resspec;
	Response response;
	String place_ID;
	JsonPath jp;
	static String place_id;
	
	TestDataBuild data = new TestDataBuild();

	@Given("Add place payload {string} {string} {string}")
	public void add_place_payload(String name, String Language, String Address) throws IOException {
		// Write code here that turns the phrase above into concrete actions

		res = given().spec(requestSpecification()).body(data.addPlacePayLoad(name, Language, Address));

	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {

		// Write code here that turns the phrase above into concrete actions

		resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

		APIResources resourceAPI = APIResources.valueOf(resource);
		System.out.println(resourceAPI.getResource());

		if (method.equalsIgnoreCase("POST"))
			response = res.when().post(resourceAPI.getResource());
		else if (method.equalsIgnoreCase("GET"))
			response = res.when().get(resourceAPI.getResource());

	}

	@Then("the API call got success with {string} code {int}")
	public void the_api_call_got_success_with_code(String string, Integer int1) {
		// Write code here that turns the phrase above into concrete actions
		assertEquals(response.getStatusCode(), 200);
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String value) {
		// Write code here that turns the phrase above into concrete actions

		assertEquals(getJsonPath(response, key), value);
		System.out.println(res);

	}

	@Then("Verify place_ID created map to {string} using {string}")
	public void verify_place_id_created_map_to_using(String expectedName, String resource) throws IOException {
	    
		place_id=getJsonPath(response, "place_id");
		res = given().spec(requestSpecification()).queryParam("place_id", place_id);
		user_calls_with_http_request(resource, "GET");
		String actualName=getJsonPath(response, "name");
		assertEquals(actualName,expectedName);
		
	}
	
	@Given("delete place payload")
	public void delete_place_payload() throws IOException {


		res=given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
		
	}

}
