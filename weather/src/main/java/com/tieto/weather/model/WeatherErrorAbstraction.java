package com.tieto.weather.model;

/**
 * @author AL
 * Error message abstraction.
 * 
 */
public abstract class WeatherErrorAbstraction {
	
	public abstract String getType ();	
	public abstract void setType (String type);
	public abstract String getDescription();
	public abstract void setDescription (String description);

}
