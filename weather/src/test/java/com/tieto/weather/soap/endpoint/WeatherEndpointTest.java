package com.tieto.weather.soap.endpoint;

import static org.junit.Assert.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withNoContent;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Optional;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.ResponseActions;
import org.springframework.web.client.RestTemplate;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.client.core.SoapActionCallback;
import org.springframework.ws.transport.http.HttpUrlConnectionMessageSender;

import com.tieto.weather.WeatherBaseTest;
import com.tieto.weather.client.WeatherProviderClient;
import com.tieto.weather.model.soap.*;

@WebAppConfiguration   
@IntegrationTest({"server.port=9999"})

public class WeatherEndpointTest extends WeatherBaseTest {

	private static final String BASE_URL = "http://localhost:9999/ws";

	private MockRestServiceServer mockServer;
	
	@Resource
	private RestTemplate restTemplate;
	
	@Resource
	WebServiceTemplate webServiceTemplate;
	
	@Resource
	Jaxb2Marshaller marshaller;
	
	@Resource
	Jaxb2Marshaller unmarshaller;
	
	private ResponseActions mockServer(String city) {
		return mockServer.expect(requestTo(WeatherProviderClient.WUNDERGROUND_URL + city.toUpperCase() + ".json")).andExpect(method(HttpMethod.GET));
	}

	@Before
	public void setUp() {
		//create a mock Server instance for RestTemplate
		mockServer = MockRestServiceServer.createServer(restTemplate);
	}	
	
	private WeatherSOAPResponse getWebServiceTemplate (String testCity) {
		WeatherRequest request = new WeatherRequest();
		if (testCity != null)
			request.getCity().add(testCity);
		else
			request.getCity();
		
		webServiceTemplate.setDefaultUri(BASE_URL);
		webServiceTemplate.setMarshaller(marshaller);
		webServiceTemplate.setUnmarshaller(unmarshaller);
		
		webServiceTemplate.setMessageSender(new HttpUrlConnectionMessageSender() {
			@Override
			protected void prepareConnection(HttpURLConnection connection) throws IOException {
				connection.setRequestProperty("Authorization", "Basic " + encodeCredentials (USER_NAME, PASS_WORD));
				super.prepareConnection(connection);
			}			
		});
		
		return (WeatherSOAPResponse) webServiceTemplate.marshalSendAndReceive(request, new SoapActionCallback(BASE_URL));
	}
	
	@Test
	// Normal response
	public void testGetWeatherNormal()  throws Exception {
		String testCity = "Vilnius";
		
		// Mocking external client
		mockServer(testCity).andRespond(withSuccess(new ClassPathResource(MOCK_CLIENT_RESPONSE_VILNIUS), MediaType.APPLICATION_JSON));
		    
		WeatherSOAPResponse response = getWebServiceTemplate (testCity);

		assertNotNull(response);
		assertNotNull(response.getObservations());
		assertEquals(1, response.getObservations().size());

		Optional<WeatherObservationType> firstElement = response.getObservations().stream().findFirst();
		
		checkMockVilniusObservation (firstElement.get());

		mockServer.verify();
	}
	
	@Test
	// Normal response, but bad city name
	public void testGetWeatherBadCity()  throws Exception {
		String testCity = "Bliamba";
		
		// Mocking external client
		mockServer(testCity).andRespond(withSuccess(new ClassPathResource(MOCK_CLIENT_RESPONSE_BAD_CITY), MediaType.APPLICATION_JSON));
		
		WeatherSOAPResponse response = getWebServiceTemplate (testCity);
		
		assertNotNull(response);
		assertNotNull(response.getErrors());
		assertEquals(1, response.getErrors().size());

		Optional<WeatherErrorType> firstElement = response.getErrors().stream().findFirst();
		
		checkMockBadCityErrors (firstElement.get());

		mockServer.verify();
	}	
	
	@Test
	// Normal response, but strange city name - no observation data in the response
	public void testGetWeatherStrangeCity()  throws Exception {
		String testCity = "London";
		
		mockServer(testCity).andRespond(withSuccess(new ClassPathResource(MOCK_CLIENT_RESPONSE_STRANGE_CITY), MediaType.APPLICATION_JSON));
		
		WeatherSOAPResponse response = getWebServiceTemplate (testCity);

		assertNotNull(response);
		assertNotNull(response.getErrors());
		assertEquals(1, response.getErrors().size());

		Optional<WeatherErrorType> firstElement = response.getErrors().stream().findFirst();
		
		checkMockStrangeCityErrors (firstElement.get());
		
		mockServer.verify();
	}	

	@Test
	// Now, what if server returned... nothing at all
	public void testGetWeatherNoContent() throws Exception {
		String testCity = "Vilnius";

		mockServer(testCity).andRespond(withNoContent());

		WeatherSOAPResponse response = getWebServiceTemplate (testCity);

		assertNotNull(response);
		assertNotNull(response.getErrors());
		assertEquals(1, response.getErrors().size());
		
		Optional<WeatherErrorType> firstElement = response.getErrors().stream().findFirst();
		
		checkMockNoContentErrors (firstElement.get());
		
		mockServer.verify();
	}
	

}
