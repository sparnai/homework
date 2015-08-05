package com.tieto.weather.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.client.RestTemplate;

import com.tieto.weather.tracker.WeatherTrackerAspect;

@Configuration
//@Import(WeatherCacheConfiguration.class)
@EnableAspectJAutoProxy
public class WeatherConfiguration {
	public static final String  WUNDERGROUND_URL = "http://api.wunderground.com/api/8580cf5381f32048/conditions/q/CA/";
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	/*
	@Bean	
	public WeatherProviderClient weatherUndergroundClient(){
		return new WeatherUndergroundClient();
	}
	*/
	/*
	@Bean	
	public WeatherTrackerAspect weatherTrackerAspect(){
		return new WeatherTrackerAspect();
	}
	*/
}
