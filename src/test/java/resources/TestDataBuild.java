package resources;

import java.util.ArrayList;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {
	
	public AddPlace addPlacePayLoad(String name,String Language, String Address)
	{
		AddPlace addplace = new AddPlace();
		addplace.setAccuracy(50);
		addplace.setName(name);
		addplace.setPhone_number("(+91) 983 893 3937");
		addplace.setAddress(Address);
		addplace.setWebsite("http://google.com");
		addplace.setLanguage(Language);
		ArrayList<String> typeList = new ArrayList<String>();
		typeList.add("shoe park");
		typeList.add("shop");
		addplace.setTypes(typeList);

		Location location = new Location();
		location.setLat(-38.383494);
		location.setLng(33.427362);

		addplace.setLocation(location);
		return addplace;
	}
	
	public String deletePlacePayload(String place_id)
	{
		return "{\r\n"
				+ "    \"place_id\":\""+place_id+"\"\r\n"
				+ "}";
	}

}
