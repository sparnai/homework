package com.tieto.weather.service.repository;

import java.util.Set;

/**
 * @author AL
 * Interface for keeping information on supported and popular cities.
 * 
 */
public interface WeatherCities {
	
	public static final String[] DEFAULT_SUPPORTED_CITIES = new String[] { "VILNIUS", "HELSINKI", "TALLINN", "RYGA" };
	public static final int THIS_IS_POPULAR = 3;
	
	public boolean isCitySupported(String city);

	public Set<String> getSupportedCities();
	
	public void addSupportedCity (final String city);
	
	public void countPopularity (final String city, int counter);
	
	public int getCityPopularity(String city);
}
