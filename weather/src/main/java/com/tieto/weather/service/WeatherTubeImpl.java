package com.tieto.weather.service;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tieto.weather.client.WeatherProviderClient;
import com.tieto.weather.model.WeatherObservation;
import com.tieto.weather.model.WeatherResponse;
import com.tieto.weather.model.external.WeatherExternalResult;
import com.tieto.weather.service.repository.RepositoryService;
import com.tieto.weather.service.repository.WeatherCities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author AL
 * Main Weather Tube class. Gets weather information from external sources and cache and passes onto REST or SOAP endpoints.
 * 
 */
@Service
public class WeatherTubeImpl implements WeatherTube{
	
	private static final Logger log = LoggerFactory.getLogger(WeatherTube.class);
	
	@Resource
	private WeatherProviderClient client;
	
	@Resource
	WeatherCities cities;
	
	@Resource
	RepositoryService repository;
	
	public <T extends WeatherResponse> void processCity (final String city, T responseObject) {
	
		WeatherObservation observation = null;
		WeatherExternalResult externalResponse = new WeatherExternalResult();
		String trimmedCity = city.trim().toUpperCase();
		boolean citySupported = cities.isCitySupported(trimmedCity);
		
		if (citySupported) {
			log.info("WeatherTube found that " + trimmedCity + " is supported. Trying to get observation from cache.");
			observation = repository.getWeather(trimmedCity);
		}

		// City is not supported/popular or not cached - get observations from external client
		if (observation == null) {
			log.info("WeatherTube found that " + trimmedCity + " is not supported or not cached. Trying to get observation external client.");
			
			externalResponse = client.getWeather(trimmedCity);
			if (externalResponse.hasErrors()) {
				log.error("WeatherTube got error from external client: " + externalResponse.getError().getDescription());
				responseObject.addError(city, externalResponse.getError());
			}
			else { 
				log.info("WeatherTube got weather observation from external client - weather - " + externalResponse.getObservation().getWeather() );
				observation = externalResponse.getObservation();
				
				// Save city weather observation
				if (citySupported)
					observation = repository.setWeather(trimmedCity, observation);
				
				responseObject.addObservation(observation);
			}
		} else {
			log.info("WeatherTube succeded to get " + trimmedCity + " weather observation from cache.");
			responseObject.addObservation(observation);
		}
	}

    /**
     * {@inheritDoc}
     */
    public <T extends WeatherResponse> T getWeather(final String city, T responseObject) {
    	
    	processCity (city, responseObject);
    	
    	return responseObject;
    }
    
    public <T extends WeatherResponse> T getWeather(final Collection<String> cities, T responseObject) {
    	
    	for (String city : cities) {
    		processCity (city, responseObject);
		}
    	
    	return responseObject;
    }    
    
    /**
     * Gets weather information for all supported cities.
     * 
     */
    public <T extends WeatherResponse> T getWeather(T responseObject) {

    	log.info("WeatherTube process all supported cities");
    	
    	for (String city : cities.getSupportedCities()) {
    		processCity (city, responseObject);
		}
    	
    	return responseObject;
    }       

}
