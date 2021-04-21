/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package revivreEvenement.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import revivreEvenement.dao.EvenementRepository;
import revivreEvenement.entity.Evenement;

/**
 *
 * @author mathieu
 */

@Controller
@RequestMapping(path="/formulaireEvenement")
public class FormEventController {
    /**
     *Appellé dans contribuer.html 
     * 
     * @attribute evenementRepository Le dao d'evenement
     * 
     * @method enregistrerNouvelEvenement
     */
    
    @Autowired
    private EvenementRepository evenementRepository;
    
    
     /**
     * Appelé par 'contribuer.html', méthode POST
     * @param evenement Un evenement initialisé avec les valeurs saisies dans le formulaire
     * @param model
     * @param redirectInfo pour transmettre des paramètres lors de la redirection
     * @return une redirection vers contribuer.html
     */
    @PostMapping("save")
    public String enregistrerNouvelEvenement(Evenement evenement, Model model, RedirectAttributes redirectInfo /* ESSAI 2 et 3 ,@RequestParam(name="id") Evenement evenementPrincipal*/){
       String message;
        try {
            // cf. https://www.baeldung.com/spring-data-crud-repository-save
            evenementRepository.save(evenement);
            // Génération du message de succès
            message = "L'évènement '" + evenement.getNomEvenement() + "' a été correctement enregistré.";
        } catch (DataIntegrityViolationException e) {
            // Les noms sont définis comme 'UNIQUE' 
            // En cas de doublon, JPA lève une exception de violation de contrainte d'intégrité
            message = "Erreur : L'évènement '" + evenement.getNomEvenement() + "' existe déjà";
        }
        
        evenement.getEvenementPrincipal().getListeSousEvenements().add(evenement); // ESSAI 1: Cannot invoke "revivreEvenement.entity.Evenement.getListeSousEvenements()" because the return value of "revivreEvenement.entity.Evenement.getEvenementPrincipal()" is null
        //evenement.setEvenementPrincipal(evenementPrincipal); //ESSAI 2:  java.lang.StackOverflowError: null
        //evenementPrincipal.getListeSousEvenements().add(evenement); ESSAI 3: Cette ligne de rnvoie pas d'erreur. Seulement, il n'y a plus aucun evenement dans le dao (????????)
        
        //System.out.println("Liste de ss-event de l'event principal: "+ evenement.getEvenementPrincipal().getListeSousEvenements());
        // RedirectAttributes permet de transmettre des informations lors d'une redirection,
        // Ici on transmet un message de succès ou d'erreur
        // Ce message est accessible et affiché dans la vue 'contribuer.html'
        redirectInfo.addFlashAttribute("message", message);
        
        return "redirect:/contribuer"; // On renvoie à  contribuer POUR ESSAI 3, remplacer par "redirect:/"
    }
}   
