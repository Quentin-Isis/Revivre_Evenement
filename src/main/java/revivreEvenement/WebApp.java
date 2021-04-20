/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package revivreEvenement;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import revivreEvenement.storageservice.StorageProperties;
import revivreEvenement.storageservice.StorageService;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class WebApp {
    
    public static void main(String[] args) {
        

        SpringApplication.run(WebApp.class, args);
    }
    
    @Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
			storageService.deleteAll();
			storageService.init();
		};
	}
}
