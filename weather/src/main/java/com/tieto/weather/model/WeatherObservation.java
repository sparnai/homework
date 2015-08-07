/**
 * 
 */
package com.tieto.weather.model;

/**
 * @author AL
 * Weather observation data class abstraction.
 * 
 */
public abstract class WeatherObservation {

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
    
    public boolean isEmpty () {
    	return 
    		(getLocation() == null) &&
    		(getWindDirection() == null) &&
    		(getWind() == null) &&
    		(getWeather() == null) &&
    		(getHumidity() == null) &&
    		(getTemperature() == null) &&
    		(getTime() == null)
    	;
    }
    
    @Override
    public String toString () {
    	return 
    			"[time=" + getTime() + 
    			", location=" + getLocation() +
    			", temperature=" + getTemperature() + 
    			", weather=" + getWeather() + 
    			", humidity=" + getHumidity() + 
    			", windDirection=" + getWindDirection() +
    			", wind=" + getWind() + 
    			"]"; 
    }
    
    
    
}