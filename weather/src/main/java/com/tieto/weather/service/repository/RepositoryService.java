package com.tieto.weather.service.repository;

import java.util.Collection;

import com.tieto.weather.client.WeatherProviderClient;
import com.tieto.weather.model.WeatherObservation;

/**
 * Repository service.
 *
 * Returns cached {@link WeatherObservation}s retrieved from {@link WeatherProviderClient}.
 */
public interface RepositoryService {

    /**
     * Returns cached {@link WeatherObservation} by city.
     * If city not cached, returns null
     *
     * @param city
     * @return {@link WeatherObservation}
     */
    public WeatherObservation getWeather(final String city);
    
    /**
     * Puts city information to cache.
     *
     * @param observation
     * @return {@link WeatherObservation}
     */
    public WeatherObservation setWeather(String city, WeatherObservation observation);

    /**
     * Returns cached {@link WeatherObservation}s by city.
     *
     * @return Collection<{@link WeatherObservation}>
     */
    public Collection<WeatherObservation> getWeather();
}
