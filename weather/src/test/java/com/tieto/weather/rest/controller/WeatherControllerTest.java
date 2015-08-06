package com.tieto.weather.rest.controller;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withNoContent;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withServerError;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.*;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.ResponseActions;
import org.springframework.web.client.RestTemplate;

import com.tieto.weather.WeatherBaseTest;
import com.tieto.weather.client.WeatherProviderClient;
import com.tieto.weather.model.WeatherObservation;
import com.tieto.weather.model.external.WeatherExternalResult;
import com.tieto.weather.model.rest.WeatherRESTResponse;

@WebAppConfiguration   
@IntegrationTest({"server.port=9999"})
public class WeatherControllerTest extends WeatherBaseTest {

    private static final String BASE_URL = "http://localhost:9999/";
    private static final String GET_SUPPORTED_CITIES_URL = "weather";
    private static final String GET_CITY_URL = "weather/rest/";

	private MockRestServiceServer mockServer;
	
	//private RestAssured ra = new RestAssured();
	
	@Resource
	private RestTemplate restTemplate;
	
	@Resource
	WeatherController controller;

	private ResponseActions mockServer(String city) {
		return mockServer.expect(requestTo(WeatherProviderClient.WUNDERGROUND_URL + city.toUpperCase() + ".json")).andExpect(method(HttpMethod.GET));
	}

	@Before
	public void setUp() {
		//create a mock Server instance for RestTemplate
		mockServer = MockRestServiceServer.createServer(restTemplate);
		
		//RestAssured.port = port;
	}	
	
	@Test
	@Ignore()
	public void testUserFetchesSuccess() {
		String testCity = "Vilnius";
		
		// Mocking external client
		mockServer(testCity).andRespond(withSuccess(new ClassPathResource("WeatherUndergroundResponse.json"), MediaType.APPLICATION_JSON));
/*
		expect().
			body("id", equalTo("12")).
			body("firstName", equalTo("Tim")).
			body("lastName", equalTo("Tester")).
			body("birthday", equalTo("1970-01-16T07:56:49.871+01:00")).
		when().
			get("/weather/rest/vilnius");
	*/
	}
	
	private HttpHeaders createHeaders(final String username, final String password ){
	    HttpHeaders headers = new HttpHeaders();
	    
	    if ((username != null) && (username.length() > 0)) {
	    	String plainCredentials = username + ":" + password;
	    	byte[] plainCredentialsInBytes = plainCredentials.getBytes();
	    	byte[] base64CredentialsInBytes = Base64.getEncoder().encode(plainCredentialsInBytes);
	    	String base64Credentials = new String(base64CredentialsInBytes);
		    headers.add("Authorization", "Basic " + base64Credentials);
		}
	    
	    headers.add("Content-Type", "application/json");
	    headers.add("Accept", "application/json");
	    
	    return headers;
	}
	
	@Test
	// Normal response
	public void testGetWeatherNormal()  throws Exception {
		String testCity = "Vilnius";
		String expectedResponse = "[[time=Wed, 05 Aug 2015 10:35:59 +0300 temperature=26, weather=Clear, humidity=54%, windDirection=South, wind=From the South at 5 MPH]]";
		
		// Mocking external client
		mockServer(testCity).andRespond(withSuccess(new ClassPathResource("WeatherUndergroundResponse.json"), MediaType.APPLICATION_JSON));
		    
		RestTemplate testREST = new TestRestTemplate();
		HttpHeaders httpHeaders = createHeaders("weather", "w34th3r");
		HttpEntity<String> httpRequest = new HttpEntity<String>(httpHeaders);
		ResponseEntity<WeatherRESTResponse> httpResponse = testREST.exchange(BASE_URL + GET_CITY_URL + testCity, HttpMethod.GET, httpRequest, WeatherRESTResponse.class);
		WeatherRESTResponse weatherResponse = httpResponse.getBody();
		
		assertNotNull(weatherResponse);
	    assertEquals (expectedResponse, weatherResponse.getObservations().toString());
	    
	    mockServer.verify();
	}
	
	@Test
    @Ignore("Can be enabled once WeatherController is implemented.")
    public void getWeaterAllCities() {
    }
	
}
