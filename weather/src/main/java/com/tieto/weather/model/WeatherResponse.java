package com.tieto.weather.model;

import java.util.Collection;

/**
 * {@link WeatherObservation} container,
 * Abstract response class.
 */
public abstract class WeatherResponse<O extends WeatherObservation, E extends WeatherError> {

    protected Collection<O> observations;
    protected Collection<E> errors;
    
    /*
    public Collection<WeatherObservation> getResult() {
        return result;
    }

    public void setResult(final Collection<WeatherObservation> result) {
        this.result = result;
    }
    */
	
    public Collection<O> getObservations() {
    	return observations;
    }
    
    public Collection<E> getErrors () {
    	return errors;
    }

	public abstract void addObservation (WeatherObservation observation);
	public abstract void addError (WeatherError error);	
	public abstract void addError (String params, WeatherErrorAbstraction error);
	
}
