package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException
	{
		
		
		StepDefinations m=new StepDefinations();
		
		if(StepDefinations.place_id==null)
		{
		m.add_place_payload("Abdul", "Hindi", "Agra");
		m.user_calls_with_http_request("AddPlaceAPI", "POST");
		m.verify_place_id_created_map_to_using("Abdul", "GetPlaceAPI");
		}
	}

}
