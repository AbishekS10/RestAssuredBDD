package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@Deleteplace")
	public void beforeScenario() throws IOException
	{
		
	  
		StepDefination a = new StepDefination();
		if(StepDefination.place_id == null)
		{
		a.add_place_payload_with("Pooja", "Spanish", "Dubai");
		a.user_calls_with_http_request("AddPlaceAPI", "post");
		a.verify_place_id_created_maps_to_using("Pooja", "GetPlaceAPI");
		}
		
	}

}
