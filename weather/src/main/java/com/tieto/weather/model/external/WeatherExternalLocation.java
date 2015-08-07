package com.tieto.weather.model.external;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * @author AL
 * Dummy class for deserializing external service (Wunderground) response's location data.
 * 
 */
@JsonRootName(value = "display_location")
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherExternalLocation {
	@JsonProperty(value = "full")
	private String location;
	
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}