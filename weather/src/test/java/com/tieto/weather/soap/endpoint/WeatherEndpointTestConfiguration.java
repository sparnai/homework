package com.tieto.weather.soap.endpoint;

import java.io.IOException;
import java.net.HttpURLConnection;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.transport.http.HttpUrlConnectionMessageSender;

import com.tieto.weather.WeatherBaseTest;

@Configuration
public class WeatherEndpointTestConfiguration {

	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("com.tieto.weather.model.soap");
		return marshaller;
	}
	
	@Bean
	public WebServiceTemplate webServiceTemplate(@Qualifier("marshaller") Jaxb2Marshaller marshaller) {
		WebServiceTemplate webServiceTemplate = new WebServiceTemplate();
		
		webServiceTemplate.setDefaultUri(WeatherEndpointTest.BASE_URL);
		webServiceTemplate.setMarshaller(marshaller);
		webServiceTemplate.setUnmarshaller(marshaller);
		
		webServiceTemplate.setMessageSender(new HttpUrlConnectionMessageSender() {
			@Override
			protected void prepareConnection(HttpURLConnection connection) throws IOException {
				connection.setRequestProperty("Authorization", "Basic " + WeatherBaseTest.encodeCredentials (WeatherEndpointTest.USER_NAME, WeatherEndpointTest.PASS_WORD));
				super.prepareConnection(connection);
			}			
		});	
		
		return webServiceTemplate;
	}
	
}
