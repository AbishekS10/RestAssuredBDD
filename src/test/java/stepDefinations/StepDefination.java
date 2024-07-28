package stepDefinations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import io.restassured.path.json.JsonPath;
import pojo.Addplace;
import pojo.Location;
import resources.APIresources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefination extends Utils {
	RequestSpecification res;
	ResponseSpecification resspec;
	Response response;
	static String place_id;
	TestDataBuild data = new TestDataBuild();
	


@Given("Add Place Payload with {string} {string} {string}")
public void add_place_payload_with(String name, String language, String address) throws IOException {
	
	

	res= given().spec(RequestSpecification())
	.body(data.addPlacepayload(name,language,address));

    
}
@When("user calls {string} with {string} http request")
public void user_calls_with_http_request(String resource, String method) {
    // Write code here that turns the phrase above into concrete actions
	
	APIresources APIres = APIresources.valueOf(resource);
	
	System.out.println(APIres.getAPIresource());
	
 resspec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
  if(method.equalsIgnoreCase("POST"))
    response =res.when().post(APIres.getAPIresource());
      else if(method.equalsIgnoreCase("GET"))
    	response =res.when().get(APIres.getAPIresource());

   System.out.println(response.toString());
    
}
@Then("the API call got success with status code {int}")
public void the_api_call_got_success_with_status_code(Integer int1) 
{
    assertEquals(response.getStatusCode(),200);
    
}
  
@Then("{string} in response body is {string}")
public void in_response_body_is(String keyValue, String ExpectedValue) {
//   String resp =  response.asString();
//   JsonPath js = new JsonPath(resp);
//   String io1 = js.get(keyValue).toString();
//		   System.out.println("Abishek1");
   assertEquals(getJsonpath(response,keyValue),ExpectedValue);
//   assertEquals(js.get(keyValue),ExpectedValue);
}

@Then("verify place_id created maps to {string} using {string}")
public void verify_place_id_created_maps_to_using(String name, String resource) throws IOException {
//    // Write code here that turns the phrase above into concrete actions
//    
	place_id = getJsonpath(response,"place_id");
	System.out.println(place_id);
	res = given().spec(RequestSpecification()).queryParam("place_id", place_id);
	user_calls_with_http_request(resource,"GET");
	String AN = getJsonpath(response,"name");
	assertEquals(AN,name);
}

@Given("delete Payload")
public void delete_payload() throws IOException {
    // Write code here that turns the phrase above into concrete actions
	res= given().spec(RequestSpecification())
			.body(data.deleteplacepayload(place_id));
}


}
