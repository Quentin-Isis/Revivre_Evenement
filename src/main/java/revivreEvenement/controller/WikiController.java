/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package revivreEvenement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import revivreEvenement.dao.EvenementRepository;
import revivreEvenement.entity.Evenement;

/**
 *
 * @author Léa
 */
@Controller
@RequestMapping(path="/wiki")
public class WikiController {
    
    @Autowired
    private EvenementRepository evenementRepository;
    
    
    @GetMapping("listeRessourcesEvent")
    public String showListeRessourcesEvent(Model model){
        // Accessible depuis 
        return "liste_ressources_event";
    }
    
    @GetMapping("show")
    public String showMap(Model model){
        return "map";
    }
    
    @GetMapping("liste_evenements")
    public String showListEventPage(Model model, @RequestParam(name="page", defaultValue="0") int page){
        /**
         * Affiche la liste des évènements dans le template "liste_evenements.html"
         * 10 évènements par pages
         * paramètre page: indice de la page que l'on affiche
         */
        
        Page<Evenement> pageEvenements = evenementRepository.findAll(PageRequest.of(page,10)); // On récupère tous les evenements dans le dao (on en récupère 10)
        
        model.addAttribute("listEvenements", pageEvenements.getContent()); //On ajoute la liste au modèle qui permet l'affichage
        model.addAttribute("pages", new int[pageEvenements.getTotalPages()]); // Pour crée la pagination
        model.addAttribute("currentPage", page); // La page courrante (POUR LE CSS)
        return "liste_evenements";
    }
}