package com.tieto.weather.model.external;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.tieto.weather.model.WeatherErrorAbstraction;

@JsonRootName(value = "error")
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherExternalError extends WeatherErrorAbstraction {
	
	public WeatherExternalError () {
	}
	
	public WeatherExternalError (String type, String description) {
		this.type = type;
		this.description = description;
	}
	
	@Override
    public String getType() {
        return type;
    }
	
	@Override
    public void setType(String type) {
        this.type = type;
    }
	
	@Override
    public String getDescription() {
        return description;
    }

	@Override
    public void setDescription(String description) {
        this.description = description;
    }
	
	@Override 
	public String toString () {
		return "[type=" + type + ", description=" + description + "]";
	}
	
}
