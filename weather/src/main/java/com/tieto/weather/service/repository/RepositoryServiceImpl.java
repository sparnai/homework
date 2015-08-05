package com.tieto.weather.service.repository;

import java.util.Collection;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.tieto.weather.model.WeatherObservation;

@Repository
public class RepositoryServiceImpl implements RepositoryService {

    /**
     * {@inheritDoc}
     */
    @Override
    @Cacheable(value = "citiesCache", key = "#city", unless = "#result == null")
    public WeatherObservation getWeather(final String city) {
    	return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @CachePut(value = "citiesCache", key = "#city", unless = "#result == null")
    public WeatherObservation setWeather(final String city, WeatherObservation observation) {
    	return observation;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<WeatherObservation> getWeather() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
