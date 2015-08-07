package com.tieto.weather.model;

/**
 * @author AL
 * Error message, used by SOAP and REST endpoints.
 * 
 */
public class WeatherError extends WeatherErrorAbstraction {
	
	protected String type;
	protected String description;
	
	// We need an additional parameter here
	protected String params;
	
	public WeatherError () {
	}
	
	public WeatherError (String params, WeatherErrorAbstraction error) {
		this.params = params;
		this.type = error.getType();
		this.description = error.getDescription();
	}
	
	public String getParams () {
		return params;
	}
	
	public void setParams (String params) {
		this.params = params;
	}
	
	@Override
	public String getType () {
		return type;
	}
	
	@Override
	public void setType (String type) {
		this.type = type;
	}
	
	@Override
	public String getDescription() {
		return description;
	}
	
	@Override
	public void setDescription (String description) {
		this.description = description;
	}
	
}
