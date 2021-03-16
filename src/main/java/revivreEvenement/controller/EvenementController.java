
package revivreEvenement.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 *
 * @author Mathieu
 */

@Controller
@RequestMapping(path="/evenement")
public class EvenementController{
    
    @GetMapping("eventRandom")
    public String showEvenementRandom(Model model){
        /**
         * Return la page wiki d'un évènement choisi au hasard
         * pour l'instant, renvoie juste la page wiki
         */
        return "wiki"; /// Provisoire
    }
    
    @GetMapping("contribuer")
    public String showContribuer(Model model){
        return "contribuer";
    }
    
    @GetMapping("listeRessourcesEvent")
    public String showListeRessourcesEvent(Model model){
        return "liste_ressources_event";
    }
    
    @GetMapping("search")
    public String showSearchPage(Model model){
        return "search";
    }
}
    
    

