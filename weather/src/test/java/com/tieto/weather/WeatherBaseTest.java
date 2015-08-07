package com.tieto.weather;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;

import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tieto.weather.model.WeatherErrorAbstraction;
import com.tieto.weather.model.WeatherObservation;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {WeatherApplication.class})
@DirtiesContext
public abstract class WeatherBaseTest {
	
	protected final String USER_NAME = "weather";
	protected final String PASS_WORD = "w34th3r";
	
	protected final String MOCK_CLIENT_RESPONSE_VILNIUS = "WeatherUndergroundResponse.json";
	protected final String MOCK_CLIENT_RESPONSE_HELSINKI = "WeatherUndergroundResponseHelsinki.json";
	protected final String MOCK_CLIENT_RESPONSE_TALLINN = "WeatherUndergroundResponseTallinn.json";
	protected final String MOCK_CLIENT_RESPONSE_RYGA = "WeatherUndergroundResponseRyga.json";
	protected final String MOCK_CLIENT_RESPONSE_BAD_CITY = "WeatherUndergroundBadCityResponse.json";
	protected final String MOCK_CLIENT_RESPONSE_STRANGE_CITY = "WeatherUndergroundStrangeCityResponse.json";
	
	protected String encodeCredentials (final String username, final String password) {
		String plainCredentials = username + ":" + password;
    	byte[] plainCredentialsInBytes = plainCredentials.getBytes();
    	byte[] base64CredentialsInBytes = Base64.getEncoder().encode(plainCredentialsInBytes);
    	return new String(base64CredentialsInBytes);	
	}
	
	protected void checkMockVilniusObservation (WeatherObservation observation) {
		assertEquals("Vilnius, Lithuania",  observation.getLocation());
		assertEquals("26", observation.getTemperature());
		assertEquals("54%", observation.getHumidity());
		assertEquals("Clear", observation.getWeather());
		assertEquals("From the South at 5 MPH", observation.getWind());
		assertEquals("South", observation.getWindDirection());
		assertEquals("Wed, 05 Aug 2015 10:35:59 +0300", observation.getTime());
	}
	
	protected void checkMockBadCityErrors (WeatherErrorAbstraction error) {
		assertEquals("querynotfound", error.getType());
		assertEquals("No cities match your search query", error.getDescription());
	}
	
	protected void checkMockStrangeCityErrors (WeatherErrorAbstraction error) {
		assertEquals("error", error.getType());
		assertEquals("Response from external client returned no weather observation data", error.getDescription());
	}
	
	protected void checkMockNoContentErrors (WeatherErrorAbstraction error) {
		Collection<String> validErrorMessages = new ArrayList<String>();
		validErrorMessages.add("Response from external client returned no weather observation data");
		validErrorMessages.add("Response from external client returned no data");
		
		assertTrue(validErrorMessages.contains(error.getDescription()));
	}
	
	
}
