//
//This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7-b41 
//See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
//Any modifications to this file will be lost upon recompilation of the source schema. 
//Generated on: 2015.08.06 at 11:22:05 PM EEST 
//


package com.tieto.weather.model.soap;

import java.util.ArrayList;
import java.util.Collection;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.tieto.weather.model.WeatherErrorAbstraction;
import com.tieto.weather.model.WeatherObservation;
import com.tieto.weather.model.WeatherResponse;


/**
* <p>Java class for anonymous complex type.
* 
* <p>The following schema fragment specifies the expected content contained within this class.
* 
* <pre>
* &lt;complexType>
*   &lt;complexContent>
*     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
*       &lt;sequence>
*         &lt;sequence maxOccurs="unbounded">
*           &lt;element name="observations" type="{http://tieto.com/weather/schemas}WeatherObservationType"/>
*         &lt;/sequence>
*         &lt;sequence maxOccurs="unbounded">
*           &lt;element name="errors" type="{http://tieto.com/weather/schemas}WeatherErrorType"/>
*         &lt;/sequence>
*       &lt;/sequence>
*     &lt;/restriction>
*   &lt;/complexContent>
* &lt;/complexType>
* </pre>
* 
* 
*/
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
 "observations",
 "errors"
})
@XmlRootElement(name = "WeatherResponse")
public class WeatherSOAPResponse extends WeatherResponse<WeatherObservationType, WeatherErrorType>{

	@XmlElement(required = true)
	protected Collection<WeatherObservationType> observations;
	@XmlElement(required = true)
	protected Collection<WeatherErrorType> errors;
    
	 /**
	  * Gets the value of the observations property.
	  * 
	  * <p>
	  * This accessor method returns a reference to the live list,
	  * not a snapshot. Therefore any modification you make to the
	  * returned list will be present inside the JAXB object.
	  * This is why there is not a <CODE>set</CODE> method for the observations property.
	  * 
	  * <p>
	  * For example, to add a new item, do as follows:
	  * <pre>
	  *    getObservations().add(newItem);
	  * </pre>
	  * 
	  * 
	  * <p>
	  * Objects of the following type(s) are allowed in the list
	  * {@link WeatherObservationType }
	  * 
	  * 
	  */
	 public Collection<WeatherObservationType> getObservations() {
	     if (observations == null) {
	         observations = new ArrayList<WeatherObservationType>();
	     }
	     return this.observations;
	 }
	
	 /**
	  * Gets the value of the errors property.
	  * 
	  * <p>
	  * This accessor method returns a reference to the live list,
	  * not a snapshot. Therefore any modification you make to the
	  * returned list will be present inside the JAXB object.
	  * This is why there is not a <CODE>set</CODE> method for the errors property.
	  * 
	  * <p>
	  * For example, to add a new item, do as follows:
	  * <pre>
	  *    getErrors().add(newItem);
	  * </pre>
	  * 
	  * 
	  * <p>
	  * Objects of the following type(s) are allowed in the list
	  * {@link WeatherErrorType }
	  * 
	  * 
	  */
	 public Collection<WeatherErrorType> getErrors() {
	     if (errors == null) {
	         errors = new ArrayList<WeatherErrorType>();
	     }
	     return this.errors;
	 }
	 
	 //@Override
	 public void addObservation(WeatherObservation observation) {
		 if (observations == null)
			 observations = new ArrayList<WeatherObservationType>();
		observations.add(new WeatherObservationType(observation));
	 }
	
	 //@Override
	 public void addError(WeatherErrorAbstraction error) {
		 if (errors == null)
			 errors = new ArrayList<WeatherErrorType>();
		 errors.add((WeatherErrorType)error);
	 }
		
	 //@Override
	 public void addError(String params, WeatherErrorAbstraction error) {
		 if (errors == null)
			 errors = new ArrayList<WeatherErrorType>();
		 errors.add(new WeatherErrorType(params, error));
	 }

}
