package com.tieto.weather.model.external;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tieto.weather.model.WeatherErrorAbstraction;
import com.tieto.weather.model.WeatherObservation;

/**
 * @author AL
 * DTO class for deserializing external service (Wunderground) response data.
 * 
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherExternalResult {
	
	@JsonProperty(value = "current_observation")
	private WeatherExternalObservation observation;

	@JsonProperty(value = "response")
	private WeatherExternalResponse response;
	
	public WeatherExternalResult () {
		response = new WeatherExternalResponse ();
	}
	
    public WeatherObservation getObservation () {
    	return (WeatherObservation) observation;
    }

    public void setObservation (WeatherExternalObservation observation) {
    	this.observation = observation;
    }

    public WeatherExternalResponse getResponse() {
    	return response;
    }

    public void setResponse(WeatherExternalResponse response) {
    	this.response = response;
    }
    
    public boolean hasErrors() {
    	return (response==null)||(response.hasErrors()) || (observation == null);
    }
    
    public WeatherErrorAbstraction getError() {
    	return response.getError();
    }
    
    public void setError(WeatherExternalError error) {
    	response.setError(error);
    }
    
    @Override
    public String toString () {
    	if (observation == null)
    		return "Observation is null";
    	else 
    		return observation.toString();
    }
    
}

