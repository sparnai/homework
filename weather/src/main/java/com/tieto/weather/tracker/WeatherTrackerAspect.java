package com.tieto.weather.tracker;

import javax.annotation.Resource;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.tieto.weather.client.WeatherProviderClient;
import com.tieto.weather.model.external.WeatherExternalResult;
import com.tieto.weather.service.repository.RepositoryService;
import com.tieto.weather.service.repository.WeatherCities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
@Component
public class WeatherTrackerAspect {
	
	private static final Logger log = LoggerFactory.getLogger(WeatherTrackerAspect.class);
	
	@Resource
	WeatherProviderClient client;
	
	@Resource
	WeatherCities cities;
	
	@Resource 
	RepositoryService repository;
	
	/**
	 * Using external client checks if city name is suitable (returns weather observation from external client)
	 * 
	 * @param trimmedCity
	 * 
	 */
	boolean isCityNameSuitable (String trimmedCity) {
		WeatherExternalResult result = client.getWeather(trimmedCity);
		
		return !result.hasErrors();
	}
	
	@Pointcut("execution(public * com.tieto.weather.client.WeatherProviderClient.getWeather(..)) && args(city)")
		public void processCityPointcut(String city) {}
	
    @AfterReturning(pointcut = "processCityPointcut(city)", returning="result")
	public void countCityPopularityAfter (String city,  WeatherExternalResult result) { 
    	String trimmedCity = city.trim().toUpperCase();

    	log.info("'Advicing' " + trimmedCity + " city. Counting popularity.");

		// If not already supported/popular
		if (!cities.isCitySupported (trimmedCity)) {
			int nextCount = cities.getCityPopularity(trimmedCity) + 1;
			
			log.info("Counting " + city + " popularity. Current count: " + nextCount + ". Left to become popular (if name is suitable, of course): " + (WeatherCities.THIS_IS_POPULAR - nextCount));
			
			// Only if city name is suitable (no errors in result produced) it is transfered to supported/popular cities
			if ((nextCount >= WeatherCities.THIS_IS_POPULAR) && (!result.hasErrors())) {
				log.info("Tracker adding new city to supported cities: " + trimmedCity);
				cities.addSupportedCity(trimmedCity);
				
				// Cache new city information...
				repository.setWeather(trimmedCity, result.getObservation());
				
				// And no point for counting popularity anymore - from now on we know it is popular.
			}
			else {
				cities.countPopularity (city, nextCount);
			}
		}
	}
	
}
