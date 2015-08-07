package com.tieto.weather;

//import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//import com.tieto.weather.model.*;

/**
 * @Author AL 
 */

@SpringBootApplication
public class WeatherApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeatherApplication.class, args);
        
        System.out.println("Ready to weather...");
    }
    
}
