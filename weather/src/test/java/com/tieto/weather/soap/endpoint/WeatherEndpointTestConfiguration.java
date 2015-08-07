package com.tieto.weather.soap.endpoint;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;

@Configuration
public class WeatherEndpointTestConfiguration {

	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("com.tieto.weather.model.soap");
		return marshaller;
	}
	
	@Bean
	public Jaxb2Marshaller unmarshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("com.tieto.weather.model.soap");
		return marshaller;
	}

	@Bean
	public WebServiceTemplate webServiceTemplate() {
		WebServiceTemplate w = new WebServiceTemplate();
		return w;
	}
	
}
