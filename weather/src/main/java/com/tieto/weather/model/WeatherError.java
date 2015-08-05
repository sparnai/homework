package com.tieto.weather.model;

public class WeatherError extends WeatherErrorAbstraction {
	
	// We need an additional parameter here
	protected String params;
	
	public WeatherError (String params, String type, String description) {
		this.params = params;
		this.type = type;
		this.description = description;
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
	
	public String getType () {
		return type;
	}
	
	public void setType (String type) {
		this.type = type;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription (String description) {
		this.description = description;
	}
	
}
