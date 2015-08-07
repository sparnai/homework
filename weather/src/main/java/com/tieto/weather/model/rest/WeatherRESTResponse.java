package com.tieto.weather.model.rest;

import java.util.ArrayList;
import java.util.Collection;

import com.tieto.weather.model.WeatherError;
import com.tieto.weather.model.WeatherErrorAbstraction;
import com.tieto.weather.model.WeatherObservation;
import com.tieto.weather.model.WeatherResponse;
import com.tieto.weather.model.soap.WeatherObservationType;

/**
 * {@link WeatherObservation} container,
 */
public class WeatherRESTResponse extends WeatherResponse<WeatherRESTObservation, WeatherError> {

	protected Collection<WeatherRESTObservation> observations;
    protected Collection<WeatherError> errors;

    /*
    public WeatherRESTResponse() {
    	observations = new ArrayList<WeatherRESTObservation>();
    	errors = new ArrayList<WeatherError>();
    }
    */
    
    public Collection<WeatherError> getErrors() {
        return errors;
    }

	@Override
	public void addObservation(WeatherObservation observation) {
		if (observations == null)
			 observations = new ArrayList<WeatherRESTObservation>();
		observations.add(new WeatherRESTObservation(observation));
	}

	@Override
	public Collection<WeatherRESTObservation> getObservations() {
		if (observations == null)
			 observations = new ArrayList<WeatherRESTObservation>();
		return observations;
	}
	
	@Override
	public void addError(WeatherErrorAbstraction error) {
		if (errors == null)
			errors  = new ArrayList<WeatherError>();
		errors.add((WeatherError)error);
	}
    
	@Override
	public void addError(String params, WeatherErrorAbstraction error) {
		if (errors == null)
			errors  = new ArrayList<WeatherError>();
		errors.add(new WeatherError (params, error));
	}


	
}
