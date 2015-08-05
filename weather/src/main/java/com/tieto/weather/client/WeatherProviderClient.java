package com.tieto.weather.client;

import com.tieto.weather.model.external.WeatherExternalResult;

/**
 * External weather provider client.
 */
public interface WeatherProviderClient {

	public final String WUNDERGROUND_URL = "http://api.wunderground.com/api/8580cf5381f32048/conditions/q/CA/";
	
    /**
     * Returns {@link WeatherObservation} from weather provider.
     *
     * @param city
     * @return {@link WeatherObservation}
     */
    WeatherExternalResult getWeather(final String city);
}
