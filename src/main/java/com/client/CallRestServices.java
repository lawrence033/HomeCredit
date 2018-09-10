package com.client;

import org.json.simple.JSONObject;
import org.springframework.boot.CommandLineRunner;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;


@Component
public class CallRestServices implements CommandLineRunner{
	
	private static void callRestService(String country){
	
		String openweatherUrl = "http://api.openweathermap.org/data/2.5/weather?q=";
		String appid = "8a1a5bc640bd6f81e65963bd682fe874";
		
		System.out.println("Weather information in " + country);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.getForEntity(openweatherUrl + country + "&appid=" + appid, String.class);
		
		JSONParser parser = new JSONParser();
		
		try {
			Object obj = parser.parse((String) response.getBody());
			JSONObject json = (JSONObject) obj;
			JSONObject jCoord = (JSONObject) obj;
			JSONObject jMain  = (JSONObject) obj;
			
			jCoord = (JSONObject) json.get("coord");
			jMain = (JSONObject) json.get("main");
		
	        System.out.println("Location: " + "Longitude: "  + jCoord.get("lon") + " Latitude: " + jCoord.get("lat"));
	        System.out.println("Weather: " + json.get("weather"));
	        System.out.println("Temperature: " + jMain.get("temp"));
	        System.out.println();
	        
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		callRestService("London");
		callRestService("Prague");
		callRestService("San Francisco");
	}
}
