package com.tieto.weather;

import static org.junit.Assert.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withNoContent;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.ResponseActions;
import org.springframework.web.client.RestTemplate;

import com.tieto.weather.client.WeatherProviderClient;
import com.tieto.weather.model.external.WeatherExternalResult;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WeatherApplication.class)
@WebIntegrationTest({"server.port=0", "management.port=0"})
public class WeatherApplicationTests {

	private MockRestServiceServer mockServer;
	
	RestTemplate restTemplate = new TestRestTemplate();

	@Mock
	WeatherProviderClient client;

	private ResponseActions mockServer(String city) {
		return mockServer.expect(requestTo(WeatherProviderClient.WUNDERGROUND_URL + city + ".json")).andExpect(method(HttpMethod.GET));
	}

	@Before
	public void setUp() {
		//create a mock Server instance for RestTemplate
		MockitoAnnotations.initMocks(this);
		mockServer = MockRestServiceServer.createServer(restTemplate);
	}	

	@Test
	public void contextLoads() {
	}

	@Test
	@Ignore()
	public void testGetWeatherNormal()  throws Exception {
		String testCity = "Vilnius";
		
		//mockServer(testCity).andRespond(withSuccess(new ClassPathResource("src/test/resources/WeatherUndergroundResponse.json"), MediaType.APPLICATION_JSON));
		mockServer(testCity).andRespond(withSuccess("{ \"id\" : \"42\", \"name\" : \"Holiday Inn\"}", MediaType.APPLICATION_JSON));
		
		//WeatherObservation observation = client.getWeather(testCity).getObservation();
		WeatherExternalResult result = client.getWeather(testCity);
		
		assertNull(result);
		//assertNull(observation);
		//assertEquals("Vilnius, Lithuania",  observation.getLocation());
		//mockServer.verify();
	}
	
	/*
	private MockRestServiceServer mockServer;
	
	RestTemplate restTemplate = new TestRestTemplate();
	
	//@Autowired
	@Mock
	WeatherProviderClient client;
	
	private ResponseActions mockServer(String city) {
		return mockServer.expect(requestTo(WeatherProviderClient.WUNDERGROUND_URL + city + ".json")).andExpect(method(HttpMethod.GET));
	}

	@Before
	public void setUp() {
		//create a mock Server instance for RestTemplate
		MockitoAnnotations.initMocks(this);
		mockServer = MockRestServiceServer.createServer(restTemplate);
	}

	@Test
	@Ignore()
	public void contextLoads() {
	}
	
	@Test
	public void testGetWeatherNormal()  throws Exception {
		String testCity = "Vilnius";
			
		
		//mockServer(testCity).andRespond(withSuccess(new ClassPathResource("src/test/resources/WeatherUndergroundResponse.json"), MediaType.APPLICATION_JSON));
		mockServer(testCity).andRespond(withSuccess("{ \"id\" : \"42\", \"name\" : \"Holiday Inn\"}", MediaType.APPLICATION_JSON));
		
		//WeatherObservation observation = client.getWeather(testCity).getObservation();
		WeatherExternalResult result = client.getWeather(testCity);
		
		assertNull(result);
		//assertNull(observation);
		//assertEquals("Vilnius, Lithuania",  observation.getLocation());
		mockServer.verify();
	}
*/
}
