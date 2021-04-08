
package revivreEvenement.controller;


import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import revivreEvenement.dao.EvenementRepository;
import revivreEvenement.entity.Evenement;


/**
 *
 * @author Mathieu
 */

@Controller
@RequestMapping(path="/index")
public class IndexController{
    
    @Autowired
    private EvenementRepository evenementRepository;
    
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
        /**
         * Montre la page pour ajouter un évènement
         */
        model.addAttribute("evenement", new Evenement());
        
        return "contribuer";
    }
    
    @GetMapping("search")
    public String showSearchPage(Model model){
        return "search";
    }
    
    
}
    
    

