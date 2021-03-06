//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7-b41 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.08.06 at 11:22:05 PM EEST 
//


package com.tieto.weather.model.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.tieto.weather.model.WeatherObservation;


/**
 * <p>Java class for WeatherObservationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WeatherObservationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="location" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="relative_humidity" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="temp_c" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="weather" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="wind_dir" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="wind_string" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WeatherObservationType", propOrder = {
    "location",
    "humidity",
    "temperature",
    "weather",
    "windDirection",
    "wind",
    "time"
})
public class WeatherObservationType extends WeatherObservation {

    @XmlElement(required = true)
    protected String location;
    @XmlElement(name = "relative_humidity", required = true)
    protected String humidity;
    @XmlElement(name = "temp_c", required = true)
    protected String temperature;
    @XmlElement(required = true)
    protected String weather;
    @XmlElement(name = "wind_dir", required = true)
    protected String windDirection;
    @XmlElement(name = "wind_string", required = true)
    protected String wind;
    @XmlElement(name = "time", required = true)
    protected String time;
	
    public WeatherObservationType () {
    }
    
    public WeatherObservationType (WeatherObservation observation) {
    	this.location = observation.getLocation();
    	this.humidity = observation.getHumidity();
    	this.temperature = observation.getTemperature();
    	this.weather = observation.getWeather();
    	this.windDirection = observation.getWindDirection();
    	this.wind = observation.getWind();
    	this.time = observation.getTime();
    }
    
    /**
     * Gets the value of the location property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the value of the location property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocation(String value) {
        this.location = value;
    }

    /**
     * Gets the value of the relativeHumidity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHumidity() {
        return humidity;
    }

    /**
     * Sets the value of the relativeHumidity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHumidity(String value) {
        this.humidity = value;
    }

    /**
     * Gets the value of the tempC property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTemperature() {
        return temperature;
    }

    /**
     * Sets the value of the tempC property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTemperature(String value) {
        this.temperature = value;
    }

    /**
     * Gets the value of the weather property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWeather() {
        return weather;
    }

    /**
     * Sets the value of the weather property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWeather(String value) {
        this.weather = value;
    }

    /**
     * Gets the value of the windDir property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWindDirection() {
        return windDirection;
    }

    /**
     * Sets the value of the windDir property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWindDirection(String value) {
        this.windDirection = value;
    }

    /**
     * Gets the value of the windString property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWind() {
        return wind;
    }

    /**
     * Sets the value of the windString property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWind(String value) {
        this.wind = value;
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
