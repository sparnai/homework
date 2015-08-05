/**
 * 
 */
package com.tieto.weather.model;

/**
 * @author AL
 * DTO class for weather observation.
 * 
 */

public abstract class WeatherObservation {

	protected String location;
	
    protected String temperature;

    protected String humidity;

    protected String weather;

    protected String windDirection;

    protected String wind;
    
    protected String time;
    
    // List of getters and setters - for clarity 
    public abstract String getLocation();
    public abstract void setLocation(String location);
    public abstract String getTemperature();
    public abstract void setTemperature(String temperature);
    public abstract String getHumidity();
    public abstract void setHumidity(String humidity);
    public abstract String getWeather();
    public abstract void setWeather(String weather);
    public abstract String getWindDirection();
    public abstract void setWindDirection(String windDirection);
    public abstract String getWind();
    public abstract void setWind(String wind);
    public abstract String getTime();
    public abstract void setTime(String time);
    
    public abstract String toString ();
    
}