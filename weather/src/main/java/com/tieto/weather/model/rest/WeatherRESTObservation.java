/**
 * 
 */
package com.tieto.weather.model.rest;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.tieto.weather.model.WeatherObservation;

/**
 * @author AL
 * DTO class for weather observation for REST service.
 * 
 */
@JsonRootName(value = "observation")
@JsonIgnoreProperties("empty")
public class WeatherRESTObservation extends WeatherObservation {

	@JsonProperty(value = "location")
	protected String location;
	@JsonProperty(value = "temperature")
    protected String temperature;
	@JsonProperty(value = "humidity")
    protected String humidity;
	@JsonProperty(value = "weather")
    protected String weather;
	@JsonProperty(value = "windDirection")
    protected String windDirection;
	@JsonProperty(value = "wind")
    protected String wind;
	@JsonProperty(value = "time")
    protected String time;
	
	public WeatherRESTObservation() {
	}
	
	public WeatherRESTObservation(WeatherObservation observation) {
		
		this.humidity = observation.getHumidity();
		this.location = observation.getLocation();
		this.temperature = observation.getTemperature();
		this.time = observation.getTime();
		this.weather = observation.getWeather();
		this.wind = observation.getWind();
		this.windDirection = observation.getWindDirection();
	}
	
	@Override
    public String getLocation() {
        return location;
    }

	@Override
    public void setLocation(String location) {
        this.location = location;
    }
    
    @Override
    public String getTemperature() {
        return temperature;
    }

    @Override
    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    @Override
    public String getHumidity() {
        return humidity;
    }

    @Override
    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    @Override
    public String getWeather() {
        return weather;
    }

    @Override
    public void setWeather(String weather) {
        this.weather = weather;
    }

    @Override
    public String getWindDirection() {
        return windDirection;
    }

    @Override
    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    @Override
    public String getWind() {
        return wind;
    }

    @Override
    public void setWind(String wind) {
        this.wind = wind;
    }
    
    @Override
    public String getTime() {
        return time;
    }
    
    @Override
    public void setTime(String time) {
        this.time = time;
    } 
    
}