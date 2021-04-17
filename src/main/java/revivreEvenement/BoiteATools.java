/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package revivreEvenement;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import revivreEvenement.dao.EvenementRepository;
import revivreEvenement.entity.Evenement;

/**
 *
 * @author mathi
 * 
 * M'en voulez pas pour le nom, j'avais pas d'idée :(
 * 
 * L'idée, c'est de mettre ici toutes les fonctions qui ne servent pas à controller nos templates mais qui sont utilisées un peu partout
 */
public class BoiteATools {
    
    @Autowired
    EvenementRepository evenementRepository;
    
    
    public void fillSsEventListOf(Evenement event){
        /**
         * Pour un évènement donné, remplit la liste de sous-évènements
         */
        
        
        
        List<Evenement> listeEvenement = evenementRepository.findAll();
        
        for (Evenement e:listeEvenement){
            if ((event.getEvenementPrincipal() != null) && (e.getEvenementPrincipal() != null)) {
                if ((e.getEvenementPrincipal().getId() == event.getId()) && (e.getId()!=event.getId())){
                    if (!event.getListeSousEvenements().contains(e)){
                        event.getListeSousEvenements().add(e);
                    }
                }
            }
        }
    }
    
    public boolean motCleIsValide(String motCle){
        /**
         * Prend un argument un mot clé et vérifie s'il est valide.
         * Un mot clé invalide est un mot clé qui ne contient aucune lettre de l'alphabet latin
         * 
         * Utilisée dans SearchController
         */
        boolean motCleIsValide = false;
        
        char[] charSearch = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't','u', 'v', 'w', 'x', 'y', 'z'} ;
        for(int i=0; i<motCle.length(); i++) {
            char chr = motCle.charAt(i);
            for(int j=0; j<charSearch.length; j++){
                if(charSearch[j] == chr)
                {
                    motCleIsValide = true;      
                }
            }  
        }
        return motCleIsValide;
    }
    
    public boolean eventHasSsEvent(Evenement evenement){
        /**
         * Renvoie un boolean indiquant si un evenement donné à une liste de sous evenement vide.
         * 
         * True si la liste n'est pas vide
         * False si la liste est vide
         */
        
        boolean eventHasSsEvent = false;
        
        if (!evenement.getListeSousEvenements().isEmpty()){
            eventHasSsEvent = true;
        }
        return eventHasSsEvent;
    }
    
    
    public void suppressionEventRecursive(Evenement evenement){
        
        fillSsEventListOf(evenement);
        
        while (eventHasSsEvent(evenement)){
            for (Evenement ssEvent: evenement.getListeSousEvenements()){
                suppressionEventRecursive(ssEvent);
            }
        }
        evenementRepository.delete(evenement);
    }
}
