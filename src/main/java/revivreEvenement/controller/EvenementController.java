
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
@RequestMapping(path="/evenement")
public class EvenementController{
    
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
    
    @PostMapping("save")
    public String enregistrerNouvelEvenement(Model model, @Valid Evenement evenement, BindingResult bindingResult){
        
        // vérification des erreurs
        if (bindingResult.hasErrors()) {
            return "contribuer";
        }
        evenementRepository.save(evenement);
        return "liste_evenements";
    }
    
    @GetMapping("listeRessourcesEvent")
    public String showListeRessourcesEvent(Model model){
        return "liste_ressources_event";
    }
    
    @GetMapping("search")
    public String showSearchPage(Model model){
        return "search";
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
    
    

