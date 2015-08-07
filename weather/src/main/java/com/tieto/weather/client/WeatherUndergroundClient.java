package com.tieto.weather.client;

import com.tieto.weather.model.external.WeatherExternalError;
import com.tieto.weather.model.external.WeatherExternalResult;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class WeatherUndergroundClient implements WeatherProviderClient {

	private static final Logger log = LoggerFactory.getLogger(WeatherUndergroundClient.class);
	
	@Resource
	private RestTemplate restTemplate;
	
    /**
     * {@inheritDoc}
     */
    @Override
    public WeatherExternalResult getWeather (final String city) {
		WeatherExternalResult result = null;
		
		try {
			log.info("Trying to get data from external source for city - " + city);
			result = restTemplate.getForObject(WUNDERGROUND_URL + city + ".json", WeatherExternalResult.class);
		} catch (RestClientException e) {
			log.error(e.toString());
			result = new WeatherExternalResult();
			result.setError(new WeatherExternalError ("error", e.toString()));
			return result;
		}
		
		if (result == null) {
			log.info("External response was empty");
			result = new WeatherExternalResult();
			result.setError(new WeatherExternalError ("error", "Response from external client returned no data"));
		}	
		
		if (result.hasErrors())
			log.error ("Houston, errors found. Wunderground service error description='" + result.getError().getDescription() + "'");
		else if (result.getObservation().isEmpty()) {
			log.error("External response has no observation data");
			result.setError(new WeatherExternalError ("error", "Response from external client returned no weather observation data"));
		}
		
		return result;
    
    }
    
}
