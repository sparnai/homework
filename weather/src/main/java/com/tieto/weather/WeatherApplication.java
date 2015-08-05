package com.tieto.weather;

//import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

//import com.tieto.weather.model.*;

/**
 * @Author AL 
 */

@SpringBootApplication
public class WeatherApplication {
//implements CommandLineRunner  {

    public static void main(String[] args) {
        SpringApplication.run(WeatherApplication.class, args);
        
        System.out.println("Ready to weather...");
    }
    
    /*
    @Override
    public void run(String... strings) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        Wunderground w = restTemplate.getForObject("http://api.wunderground.com/api/8580cf5381f32048/conditions/q/CA/Vilnius.json", 
        		Wunderground.class);
        log.info(w.toString());
        System.out.println(w.toString());
    }
    */
    
    
}
