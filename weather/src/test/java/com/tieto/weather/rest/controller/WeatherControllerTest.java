package com.tieto.weather.rest.controller;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withNoContent;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withServerError;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import static org.junit.Assert.*;

import java.util.Optional;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.ResponseActions;
import org.springframework.web.client.RestTemplate;

import com.tieto.weather.WeatherBaseTest;
import com.tieto.weather.client.WeatherProviderClient;
import com.tieto.weather.model.WeatherError;
import com.tieto.weather.model.rest.WeatherRESTObservation;
import com.tieto.weather.model.rest.WeatherRESTResponse;

@WebAppConfiguration   
@IntegrationTest({"server.port=9999"})
public class WeatherControllerTest extends WeatherBaseTest {

    private static final String BASE_URL = "http://localhost:9999/";
    private static final String GET_CITY_URL = "weather/rest/";

	private MockRestServiceServer mockServer;
	
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
	}	
	
	private HttpHeaders createHeaders(final String username, final String password ){
	    HttpHeaders headers = new HttpHeaders();
	    
	    if ((username != null) && (password != null)) {
		    headers.add("Authorization", "Basic " + encodeCredentials(username, password));
		}
	    
	    headers.add("Content-Type", "application/json");
	    headers.add("Accept", "application/json");
	    
	    return headers;
	}
	
	WeatherRESTResponse getResponse (String testCity) {
		RestTemplate testREST = new TestRestTemplate();
		HttpHeaders httpHeaders = createHeaders(USER_NAME, PASS_WORD);
		HttpEntity<String> httpRequest = new HttpEntity<String>(httpHeaders);
		ResponseEntity<WeatherRESTResponse> httpResponse = testREST.exchange(BASE_URL + GET_CITY_URL + testCity, HttpMethod.GET, httpRequest, WeatherRESTResponse.class);
		return httpResponse.getBody();
	}
	
	@Test
	// Normal response
	public void testGetWeatherNormal()  throws Exception {
		String testCity = "Vilnius";

		// Mocking external client
		mockServer(testCity).andRespond(withSuccess(new ClassPathResource(MOCK_CLIENT_RESPONSE_VILNIUS), MediaType.APPLICATION_JSON));
		    
		WeatherRESTResponse response = getResponse (testCity);
		
		assertNotNull(response);
		Optional<WeatherRESTObservation> firstElement = response.getObservations().stream().findFirst();
		
		checkMockVilniusObservation (firstElement.get());
		
	    //assertEquals (expectedResponse, weatherResponse.getObservations().toString());
	    
	    mockServer.verify();
	}
	
	@Test
	// Normal response, but bad city name
	public void testGetWeatherBadCity()  throws Exception {
		String testCity = "Bliamba";
		
		// Mocking external client
		mockServer(testCity).andRespond(withSuccess(new ClassPathResource(MOCK_CLIENT_RESPONSE_BAD_CITY), MediaType.APPLICATION_JSON));
		
		WeatherRESTResponse response = getResponse (testCity);
		
		assertNotNull(response);
		assertNotNull(response.getErrors());
		assertEquals(1, response.getErrors().size());

		Optional<WeatherError> firstElement = response.getErrors().stream().findFirst();
		
		checkMockBadCityErrors (firstElement.get());

		mockServer.verify();
	}	
	
	@Test
	// Normal response, but strange city name - no observation data in the response
	public void testGetWeatherStrangeCity()  throws Exception {
		String testCity = "London";
		
		mockServer(testCity).andRespond(withSuccess(new ClassPathResource(MOCK_CLIENT_RESPONSE_STRANGE_CITY), MediaType.APPLICATION_JSON));
		
		WeatherRESTResponse response = getResponse (testCity);

		assertNotNull(response);
		assertNotNull(response.getErrors());
		assertEquals(1, response.getErrors().size());

		Optional<WeatherError> firstElement = response.getErrors().stream().findFirst();
		
		checkMockStrangeCityErrors (firstElement.get());
		
		mockServer.verify();
	}	

	@Test
	// Now, what if server returned... nothing at all
	public void testGetWeatherNoContent() throws Exception {
		String testCity = "Vilnius";

		mockServer(testCity).andRespond(withNoContent());

		WeatherRESTResponse response = getResponse (testCity);

		assertNotNull(response);
		assertNotNull(response.getErrors());
		assertEquals(1, response.getErrors().size());
		
		Optional<WeatherError> firstElement = response.getErrors().stream().findFirst();
		
		checkMockNoContentErrors (firstElement.get());
		
		mockServer.verify();
	}
	
}
