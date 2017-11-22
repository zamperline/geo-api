package br.com.geo.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import br.com.geo.api.config.property.GeoApiProperty;

@SpringBootApplication
@EnableConfigurationProperties(GeoApiProperty.class)
public class GeoApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GeoApiApplication.class, args);
	}
}
