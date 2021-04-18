/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package revivreEvenement.config;


import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.stereotype.Component;
import revivreEvenement.entity.Evenement;
import revivreEvenement.entity.Item;
import revivreEvenement.entity.Position;

@Component
/**
 * @author: Mathieu
 * Configuration pour que les clés primaires soient automatiquement exposées
 * par l'API REST (pas obligatoire, mais commode).
 * Par défaut, l'API expose seulement le lien 'self'
 */
public class RestConfiguration implements RepositoryRestConfigurer{
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        // Lister les classes dont on veut exposer les clés dans l'API REST
        config.exposeIdsFor(Evenement.class, Item.class, Position.class);
    }
}
