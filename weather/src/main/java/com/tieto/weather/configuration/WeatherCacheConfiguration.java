package com.tieto.weather.configuration;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
//@Import(WeatherSOAPConfiguration.class)
@EnableCaching
public class WeatherCacheConfiguration {
}
