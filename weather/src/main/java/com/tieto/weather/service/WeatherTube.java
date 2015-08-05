package com.tieto.weather.service;

import java.util.Collection;

import com.tieto.weather.model.WeatherResponse;

/**
 * @author AL
 * Main Weather Tube service interface. Gets weather information from external sources and cache and passes onto REST or SOAP endpoints.
 * 
 */
public interface WeatherTube {
	
    /**
     * Processes city weather - gets data from cache or from external client if neccesary. Handles cache updates.
     */
	public <T extends WeatherResponse> void processCity (final String city, T responseObject);
	
    /**
     * Gets weather information for a city
     */
    public <T extends WeatherResponse> T getWeather(final String city, T responseObject);

    /**
     * Gets weather information for a collection of cities
     */
    public <T extends WeatherResponse> T getWeather(final Collection<String> cities, T responseObject);
    
    /**
     * Gets weather information for all supported cities.
     * 
     */
    public <T extends WeatherResponse> T getWeather(T responseObject);
    
    public void Test ();

}
