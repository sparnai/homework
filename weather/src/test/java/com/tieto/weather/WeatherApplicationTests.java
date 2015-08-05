package com.tieto.weather;

import static org.junit.Assert.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withNoContent;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.ResponseActions;
import org.springframework.web.client.RestTemplate;

import com.tieto.weather.client.WeatherProviderClient;
import com.tieto.weather.model.WeatherObservation;
import com.tieto.weather.model.external.WeatherExternalResult;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {WeatherApplication.class})
//@WebIntegrationTest({"server.port=0", "management.port=0"})
public class WeatherApplicationTests {

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
	public void contextLoads() {
	}

	@Test
	public void testGetWeatherNormal()  throws Exception {
		String testCity = "Vilnius";
		
		mockServer(testCity).andRespond(withSuccess(new ClassPathResource("WeatherUndergroundResponse.json"), MediaType.APPLICATION_JSON));
		
		WeatherExternalResult result = client.getWeather(testCity);
		
		assertNotNull(result);
		assertNotNull(result.getObservation());
		
		WeatherObservation observation = result.getObservation();
		assertEquals("Vilnius, Lithuania",  observation.getLocation());
		assertEquals("26", observation.getTemperature());
		assertEquals("Wed, 05 Aug 2015 10:35:59 +0300", observation.getTime());
		mockServer.verify();
	}
	
	@Test
	@Ignore
	// Now, what if server returned... nothing at all
	public void testGetWeatherNoContent() throws Exception {
		String testCity = "Vilnius";
		
		mockServer(testCity).andRespond(withNoContent());

		WeatherExternalResult result = client.getWeather(testCity);
		assertNull(result);

		mockServer.verify();
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
