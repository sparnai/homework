package com.tieto.weather.client;

import static org.junit.Assert.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withNoContent;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withServerError;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.ResponseActions;
import org.springframework.web.client.RestTemplate;

import com.tieto.weather.WeatherBaseTest;
import com.tieto.weather.model.WeatherObservation;
import com.tieto.weather.model.external.WeatherExternalResult;

public class WeatherUndergroundClientTest extends WeatherBaseTest {


	private MockRestServiceServer mockServer;
	
	@Resource
	private RestTemplate restTemplate;
	
	@Resource
	WeatherProviderClient client;

	private ResponseActions mockServer(String city) {
		return mockServer.expect(requestTo(WeatherProviderClient.WUNDERGROUND_URL + city + ".json")).andExpect(method(HttpMethod.GET));
	}

	@Before
	public void setUp() {
		//create a mock Server instance for RestTemplate
		mockServer = MockRestServiceServer.createServer(restTemplate);
	}	

	@Test
	// Normal response
	public void testGetWeatherNormal()  throws Exception {
		String testCity = "Vilnius";
		
		mockServer(testCity).andRespond(withSuccess(new ClassPathResource("WeatherUndergroundResponse.json"), MediaType.APPLICATION_JSON));
		
		WeatherExternalResult result = client.getWeather(testCity);
		
		assertNotNull(result);
		assertNotNull(result.getObservation());
		
		WeatherObservation observation = result.getObservation();
		assertEquals("Vilnius, Lithuania",  observation.getLocation());
		assertEquals("26", observation.getTemperature());
		assertEquals("54%", observation.getHumidity());
		assertEquals("Clear", observation.getWeather());
		assertEquals("From the South at 5 MPH", observation.getWind());
		assertEquals("South", observation.getWindDirection());
		assertEquals("Wed, 05 Aug 2015 10:35:59 +0300", observation.getTime());
		
		mockServer.verify();
	}
	
	@Test
	// Normal response, but bad city name
	public void testGetWeatherBadCity()  throws Exception {
		String testCity = "Bliamba";
		
		mockServer(testCity).andRespond(withSuccess(new ClassPathResource("WeatherUndergroundBadCityResponse.json"), MediaType.APPLICATION_JSON));
		
		WeatherExternalResult result = client.getWeather(testCity);
		
		// Result still has to be returned from external client, but with error messages. 
		assertNotNull(result);

		assertEquals("querynotfound", result.getError().getType());
		assertEquals("No cities match your search query", result.getError().getDescription());
		
		mockServer.verify();
	}
	
	@Test
	// Normal response, but strange city name - no observation data in the response
	public void testGetWeatherStrangeCity()  throws Exception {
		String testCity = "London";
		
		mockServer(testCity).andRespond(withSuccess(new ClassPathResource("WeatherUndergroundStrangeCityResponse.json"), MediaType.APPLICATION_JSON));
		
		WeatherExternalResult result = client.getWeather(testCity);
		
		// Result still has to be returned from external client, but with error messages. 
		assertNotNull(result);

		assertEquals("error", result.getError().getType());
		assertEquals("Response from external client returned no weather observation data", result.getError().getDescription());
		
		mockServer.verify();
	}
	
	
	@Test
	// Now, what if server returned... nothing at all
	public void testGetWeatherNoContent() throws Exception {
		String testCity = "Vilnius";

		Collection<String> validErrorMessages = new ArrayList<String>();
		validErrorMessages.add("Response from external client returned no weather observation data");
		validErrorMessages.add("Response from external client returned no data");
		
		mockServer(testCity).andRespond(withNoContent());

		WeatherExternalResult result = client.getWeather(testCity);
		
		// Result still has to be returned from external client, but with error messages. 
		assertNotNull(result);

		assertTrue(validErrorMessages.contains(result.getError().getDescription()) );
		
		mockServer.verify();
	}
	
	@Test
	// What if server returns an error?
	public void testGetWeatherServerError() throws Exception {
		String testCity = "Vilnius";

		Collection<String> validErrorMessages = new ArrayList<String>();
		validErrorMessages.add("Response from external client returned no weather observation data");
		validErrorMessages.add("Response from external client returned no data");
		
		mockServer(testCity).andRespond(withServerError());

		WeatherExternalResult result = client.getWeather(testCity);
		
		// Result still has to be returned from external client, but with error messages. 
		assertNotNull(result);

		assertTrue(result.hasErrors());
		
		mockServer.verify();
	}

}
