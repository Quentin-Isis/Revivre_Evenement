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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import revivreEvenement.dao.EvenementRepository;
import revivreEvenement.entity.Evenement;

/**
 *
 * @author mathi
 */

@Controller
@RequestMapping(path="/formulaireEvenement")
public class FormEventController {
    
    @Autowired
    private EvenementRepository evenementRepository;
    
    @PostMapping("save")
    public String enregistrerNouvelEvenement(Evenement evenement, Model model, RedirectAttributes redirectInfo){
        
       String message;
        try {
            // cf. https://www.baeldung.com/spring-data-crud-repository-save
            evenementRepository.save(evenement);
            // Le code de la catégorie a été initialisé par la BD au moment de l'insertion
            message = "L'évènement '" + evenement.getNomEvenement() + "' a été correctement enregistré.";
        } catch (DataIntegrityViolationException e) {
            // Les noms sont définis comme 'UNIQUE' 
            // En cas de doublon, JPA lève une exception de violation de contrainte d'intégrité
            message = "Erreur : L'évènement '" + evenement.getNomEvenement() + "' existe déjà";
        }
        // RedirectAttributes permet de transmettre des informations lors d'une redirection,
        // Ici on transmet un message de succès ou d'erreur
        // Ce message est accessible et affiché dans la vue 'contribuer.html'
        redirectInfo.addFlashAttribute("message", message);
        return "redirect:/contribuer";
    }
}
