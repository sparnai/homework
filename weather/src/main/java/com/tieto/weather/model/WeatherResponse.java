package com.tieto.weather.model;

import java.util.Collection;

/**
 * @author AL
 * Response abstraction. Used by SOAP and REST endpoints.
 * {@link WeatherObservation}. {@link WeatherErrorAbstraction}.
 * 
 */
public abstract class WeatherResponse<O extends WeatherObservation, E extends WeatherErrorAbstraction> {

	public abstract Collection<O> getObservations();
	public abstract Collection<E> getErrors();
	
	public abstract void addObservation (WeatherObservation observation);
	public abstract void addError (WeatherErrorAbstraction error);	
	public abstract void addError (String params, WeatherErrorAbstraction error);
	
}
