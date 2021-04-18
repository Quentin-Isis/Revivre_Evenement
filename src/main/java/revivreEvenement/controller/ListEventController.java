/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package revivreEvenement.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import revivreEvenement.BoiteATools;
import revivreEvenement.dao.EvenementRepository;
import revivreEvenement.dao.ItemRepository;
import revivreEvenement.dao.PositionRepository;
import revivreEvenement.entity.Evenement;
import revivreEvenement.entity.Item;
import revivreEvenement.entity.Position;

/**
 *
 * @author Mathieu
 */

@Controller
@RequestMapping(path="/liste_events")
public class ListEventController {
    
    @Autowired
    EvenementRepository evenementRepository;
     
    @Autowired
    ItemRepository itemRepository;
             
    @Autowired
    PositionRepository positionRepository;
     
     BoiteATools outil = new BoiteATools();
     
    @GetMapping(path = "delete")
    public String supprimeUnEvenementPuisMontreLaListe(@RequestParam("id") Evenement evenement, RedirectAttributes redirectInfo) {
        /**
         * Fait appel à la fonction supprssionEventRecursive() de BoiteATools
         * 
         * 
         * 
         */
        
        
        evenementRepository.delete(evenement);
        String message = "L'évènement " + evenement.getNomEvenement() + "' a bien été supprimé";
        
        // RedirectAttributes permet de transmettre des informations lors d'une redirection,
        // Ici on transmet un message de succès ou d'erreur
        // Ce message est accessible et affiché dans la vue 'liste_evenements.html'
        redirectInfo.addFlashAttribute("message", message);
        
        
        return "redirect:/wiki/liste_evenements"; // on se redirige vers l'affichage de la liste
    }     
    
    @GetMapping(path = "showWikiPage")
    public String showEventWikiPage(Model model, @RequestParam(name="id") Evenement evenement){
        
        fillSsEventListOf(evenement);
        // Vérifiaction de la possibilité d'afficher la liste de sous évènement
        boolean hasSsEvent = false;
        if (!evenement.getListeSousEvenements().isEmpty()){
            hasSsEvent = true;
        }
        model.addAttribute("hasSsEvent", hasSsEvent);   
        
        model.addAttribute("evenement", evenement);
        
        return "wiki";
    }
    
    public void fillSsEventListOf(Evenement event){
        /**
         * Pour un évènement donné, remplit la liste de sous-évènements
         */
        List<Evenement> listeEvenement = evenementRepository.findAll();
        
        //continuer à réfléchir là-dessus
        
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
}
