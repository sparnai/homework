package com.tieto.weather.model;

/**
 * @author AL
 * Abstract class for error message.
 * 
 */

public abstract class WeatherErrorAbstraction {
	
	protected String type;
	protected String description;
	
	// List of getters and setters - for clarity
	public abstract String getType ();	
	public abstract void setType (String type);
	public abstract String getDescription();
	public abstract void setDescription (String description);

}
