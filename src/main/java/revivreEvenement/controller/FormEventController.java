/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package revivreEvenement.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public String enregistrerNouvelEvenement(Model model, @Valid Evenement evenement, BindingResult bindingResult){
        
        // vérification des erreurs
        if (bindingResult.hasErrors()) {
            return "contribuer";
        }
        evenementRepository.save(evenement);
        return "liste_evenements";
    }
}
