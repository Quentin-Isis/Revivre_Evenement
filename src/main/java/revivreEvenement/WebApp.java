/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package revivreEvenement;

import java.time.LocalDate;
import java.time.Month;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import revivreEvenement.entity.Evenement;

/**
 *
 * @author QUENTIN
 */
@SpringBootApplication
public class WebApp {
    
    public static void main(String[] args) {
        

        SpringApplication.run(WebApp.class, args);
    }    
}
