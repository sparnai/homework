package com.tieto.weather.rest.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tieto.weather.model.rest.WeatherRESTResponse;
import com.tieto.weather.service.WeatherTube;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author AL
 * Weather REST controller
 *
 */
@RestController
@RequestMapping("/weather")
public class WeatherController {

    private static final String HEADER = "Accept=application/json";
    private static final Logger log = LoggerFactory.getLogger(WeatherController.class);
    
    @Resource
    private WeatherTube tube;
	//private RepositoryService repository;
	
    /**
     * Endpoint for fetching supported locations weather data.
     *
     * @return {@link WeatherResponse}
     */
    @RequestMapping(method = RequestMethod.GET, headers = HEADER)
    public WeatherRESTResponse getWeather() {
    	log.info("REST Controller process all supported cities");
    	
    	return tube.getWeather(new WeatherRESTResponse());
    	
    }

    /*
    @ResponseBody
    @RequestMapping(value = "/{city}", method = RequestMethod.GET, headers = HEADER)
    public WeatherResponse getWeather(@PathVariable final String city) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    */

    /**
     * Endpoint for fetching single location weather data.
     *
     * @return {@link WeatherResponse}
     */
    @ResponseBody
	@RequestMapping(value = "/rest/{city}", method = RequestMethod.GET)
	public WeatherRESTResponse getWeather (@PathVariable final String city) {

		log.info("REST Controller: Got city name - " + city);
		
		return tube.getWeather(city, new WeatherRESTResponse());
		
		/*
    	WeatherObservation observation = new WeatherRESTObservation();
    	
    	if (city != null) {
    		observation = new WeatherUndergroundClient().getWeather(city).getObservation();
    		//observation = RepositoryServiceImpl.getWeather(city);
    		//return repository.getWeather(city);
    	}
    	/*
    	else
    		return new WeatherObservation();
    	*/
	}

}