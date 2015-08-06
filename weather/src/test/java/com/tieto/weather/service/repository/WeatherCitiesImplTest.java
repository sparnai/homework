package com.tieto.weather.service.repository;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;

import com.tieto.weather.WeatherBaseTest;

public class WeatherCitiesImplTest extends WeatherBaseTest {
	
	@Resource
	WeatherCities cities;
	
	@Test 
	public void testIsCitySupported() {
		// Cities from DEFAULT_SUPPORTED_CITIES must return true
		// Cities from DEFAULT_SUPPORTED_CITIES in lowercase should return false
		for (String city : WeatherCities.DEFAULT_SUPPORTED_CITIES) {
			assertTrue (cities.isCitySupported(city));	
			assertFalse (cities.isCitySupported(city.toLowerCase()));
 		}
	}
	
	@Test
	public void testCountPopularity () {
		
		cities.countPopularity("MY_CITY", 5);
		cities.countPopularity("DREAM_CITY", 0);
		cities.countPopularity("DREAM_CITY", -1);
		assertEquals (-1, cities.getCityPopularity("DREAM_CITY"));
		assertEquals (5, cities.getCityPopularity("MY_CITY"));

	}

}
