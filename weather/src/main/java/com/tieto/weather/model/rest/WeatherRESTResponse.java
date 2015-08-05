package com.tieto.weather.model.rest;

import java.util.ArrayList;
import java.util.Collection;

import com.tieto.weather.model.WeatherError;
import com.tieto.weather.model.WeatherErrorAbstraction;
import com.tieto.weather.model.WeatherObservation;
import com.tieto.weather.model.WeatherResponse;

/**
 * {@link WeatherObservation} container,
 */
public class WeatherRESTResponse extends WeatherResponse<WeatherRESTObservation, WeatherError> {

    public WeatherRESTResponse() {
    	observations = new ArrayList<WeatherRESTObservation>();
    	errors = new ArrayList<WeatherError>();
    }
    
    public Collection<WeatherError> getErrors() {
        return errors;
    }

	@Override
	public void addObservation(WeatherObservation observation) {
		observations.add(new WeatherRESTObservation(observation));
	}

	@Override
	public void addError(WeatherError error) {
		errors.add(error);
	}
	
	public void addError(String params, WeatherErrorAbstraction error) {
		errors.add(new WeatherError (params, error));
	}
	

}
