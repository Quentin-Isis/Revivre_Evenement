/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package revivreEvenement.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import revivreEvenement.dao.EvenementRepository;
import revivreEvenement.entity.Evenement;

/**
 *
 * @author Mathieu
 */

@Controller
@RequestMapping(path="/recherches")
public class SearchController {
    
    @Autowired
    private EvenementRepository evenementRepository;
    
    @GetMapping("fctSearch")
    private String fctSearch(Model model, @RequestParam(name="motCle", defaultValue ="") String motCle){
        
        boolean recherche_par_mot_cle = true;
        boolean motCleIsValide = motCleIsValide(motCle);
        boolean resultats_trouves = false;
       
        model.addAttribute("recherche_par_mot_cle", recherche_par_mot_cle);
        model.addAttribute("isValide", motCleIsValide);
        model.addAttribute("motCle", motCle);
        // cf. EvenementRepository.java
        if (motCleIsValide){
            List<Evenement> listEvenementSearched = evenementRepository.findByNomEvenementContains(motCle);
            List<Evenement> listEvenementSearchedByLieu = evenementRepository.findByLieuContains(motCle);
            
            if (!listEvenementSearchedByLieu.isEmpty()){
                for (int i=0; i<listEvenementSearchedByLieu.size(); i++){
                    if (!listEvenementSearched.contains(listEvenementSearchedByLieu.get(i))){
                        listEvenementSearched.add(listEvenementSearchedByLieu.get(i));
                    }
                }
            }
            if (!listEvenementSearched.isEmpty()){
                resultats_trouves = true;
            }
            model.addAttribute("results", listEvenementSearched);
        }
        model.addAttribute("trouves", resultats_trouves);
        
        return "search";
    }
    
    @GetMapping("eventWikiPage")
    private String showEventWikiPage(Model model, @RequestParam("id") Evenement event){
        
        model.addAttribute("evenement", event);
        
        return "wiki";
    }
    
    private boolean motCleIsValide(String motCle){
        boolean motCleIsValide = false;
        
        char[] charSearch = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't','u', 'v', 'w', 'x', 'y', 'z'} ;
        for(int i=0; i<motCle.length(); i++) 
        {
            char chr = motCle.charAt(i);
            for(int j=0; j<charSearch.length; j++)
            {
                if(charSearch[j] == chr)
                {
                    motCleIsValide = true;      
                }
            }  
        }
        
        return motCleIsValide;
    }
    
    //FONCTIONS POUR RECHERCHER PAR DATE
    
    private String fctSearchByDate(Model model, 
            @RequestParam(name="month", defaultValue = "") String month, 
            @RequestParam(name="year", defaultValue = "") String year, 
            @RequestParam(name="day", defaultValue = "") String day){
        
        boolean recherche_par_date = true;
        model.addAttribute("recherche_par_date", recherche_par_date);
        model.addAttribute("year", year);
        
        return "search";
    }
    
}
