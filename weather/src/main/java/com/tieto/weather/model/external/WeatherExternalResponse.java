package com.tieto.weather.model.external;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * @author AL
 * A dummy DTO class for deserializing external service (Wunderground) response data.
 * 
 */

@JsonRootName(value = "response")
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherExternalResponse {
	
	private String version;
	
	@JsonProperty(value = "error")
	private WeatherExternalError error;
	
    public WeatherExternalError getError() {
    	return error;
    }

    public void setError(WeatherExternalError error) {
    	this.error = error;
    }

    public boolean hasErrors() {
    	return (error != null);
    }
    
    public String getVersion () {
    	return version;
    }
    
    public void setVersion (String version) {
    	this.version = version;
    }
    
}

