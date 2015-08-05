package com.tieto.weather.service.repository;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author AL
 * Weather tracker class. Keeps information on supported and popular cities.
 * 
 */

@Service
public class WeatherCitiesImpl implements WeatherCities {
	
	private static final Logger log = LoggerFactory.getLogger(WeatherCitiesImpl.class);
	
	private Map<String, Integer> cityPopularity = new HashMap<String, Integer>();
	
	private Set<String> supportedCities;
	
	public WeatherCitiesImpl () {
		this(new HashSet<String>(Arrays.asList(DEFAULT_SUPPORTED_CITIES)));
		log.info("Initialize supported cities");
	}
	
	/**
	 * 
	 * @param supportedCities
	 *            initialize supported cities
	 */
	public WeatherCitiesImpl (Set<String> supportedCities) {
		this.supportedCities = supportedCities;
	}
	
	public boolean isCitySupported(String city) {
		return supportedCities.contains(city);
	}

	public Set<String> getSupportedCities() {
		return supportedCities;
	}	
	
	public void addSupportedCity (final String city) {
		supportedCities.add(city);
	}
	
	public void countPopularity (final String city, int counter) {
		cityPopularity.put(city, counter);
	}
	
	public int getCityPopularity(String city) {
		return cityPopularity.containsKey(city) ? cityPopularity.get(city) : 0;
	}
}
