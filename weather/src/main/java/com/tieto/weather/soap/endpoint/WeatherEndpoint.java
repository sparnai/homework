package com.tieto.weather.soap.endpoint;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.tieto.weather.model.soap.WeatherRequest;
import com.tieto.weather.model.soap.WeatherSOAPResponse;
import com.tieto.weather.service.WeatherTube;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author AL
 * Weather SOAP endpoint.
 */
@Endpoint
public class WeatherEndpoint {

    private static final String NAMESPACE_URI = "http://tieto.com/weather/schemas";
    private static final Logger log = LoggerFactory.getLogger(WeatherEndpoint.class);

    @Resource
    private WeatherTube tube;
    
    /**
     * Endpoint for fetching location weather data.
     *
     * @param {@link WeatherRequest}
     * @return {@link WeatherResponse}
     */
    @PayloadRoot(namespace=NAMESPACE_URI, localPart = "WeatherRequest")
    @ResponsePayload
    public WeatherSOAPResponse getWeather(@RequestPayload final WeatherRequest request) {
		
    	WeatherSOAPResponse response = new WeatherSOAPResponse();

    	/*
    	WeatherObservationType observation = new WeatherObservationType();

    	observation.setLocation("Vilnius");
    	observation.setHumidity("12%");
    	observation.setTemperature("15C");
    	observation.setWeather("Clear");
    	observation.setWindDirection("South");
    	observation.setWind("Strong wind");
    	
    	response.getObservations().add(observation);
    	*/
		List<String> cities = request.getCity();

    	log.info("SOAP Endpoint - got request");
		
		if (cities.size() == 0) {
			log.info("SOAP Endpoint: Display supported cities ");
			response = tube.getWeather(response);
		} else {
			log.info("SOAP Endpoint: Got " + cities.size() + " cities.");
			response = tube.getWeather(cities, response);
		}
		return response;
    }
}
