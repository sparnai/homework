package com.tieto.weather.model.external;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.tieto.weather.model.WeatherObservation;

/**
 * @author AL
 * DTO class for deserializing external service (Wunderground) response's location data.
 * 
 */
@JsonRootName(value = "current_observation")
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherExternalObservation extends WeatherObservation {

	protected String location;
    protected String temperature;
    protected String humidity;
    protected String weather;
    protected String windDirection;
    protected String wind;
    protected String time;
	
    @JsonProperty(value = "display_location")
    private WeatherExternalLocation displayLocation;
    
    public WeatherExternalObservation () {
    	displayLocation = new WeatherExternalLocation();
    }
    
    @Override
    public String getLocation() {
        return displayLocation.getLocation();
    }

    @Override
    public void setLocation(String location) {
        this.location = displayLocation.getLocation();
    }
    
    @Override
    @JsonProperty(value = "temp_c")
    public String getTemperature() {
        return temperature;
    }

    @Override
    @JsonProperty(value = "temp_c")
    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    @Override
    @JsonProperty(value = "relative_humidity")
    public String getHumidity() {
        return humidity;
    }

    @Override
    @JsonProperty(value = "relative_humidity")
    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    @Override
    @JsonProperty(value = "weather")
    public String getWeather() {
        return weather;
    }

    @Override
    @JsonProperty(value = "weather")
    public void setWeather(String weather) {
        this.weather = weather;
    }

    @Override
    @JsonProperty(value = "wind_dir")
    public String getWindDirection() {
        return windDirection;
    }

    @Override
    @JsonProperty(value = "wind_dir")
    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    @Override
    @JsonProperty(value = "wind_string")
    public String getWind() {
        return wind;
    }

    @Override
    @JsonProperty(value = "wind_string")
    public void setWind(String wind) {
        this.wind = wind;
    }
    
    @Override
    @JsonProperty(value = "local_time_rfc822")
    public String getTime() {
        return time;
    }
    
    @Override
    @JsonProperty(value = "local_time_rfc822")
    public void setTime(String time) {
        this.time = time;
    } 
    
}