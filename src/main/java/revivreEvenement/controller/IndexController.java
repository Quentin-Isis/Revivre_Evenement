
package revivreEvenement.controller;


import java.time.LocalDate;
import java.util.*;
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
@RequestMapping(path="/")
public class IndexController{
    
    @Autowired
    private EvenementRepository evenementRepository;
    
    @GetMapping("index")
    public String showIndexPage(Model model){
        return "index";
    }
    
    @GetMapping("eventRandom")
    public String showEvenementRandom(Model model){
        /**
         * Return la page wiki d'un évènement choisi au hasard
         *
         */
        
        List<Evenement> listEvenements = evenementRepository.findAll();
        Random random = new Random();
        int indexRandom = random.nextInt(listEvenements.size());
        Evenement evenementRandom = listEvenements.get(indexRandom);
        
        model.addAttribute("evenement", evenementRandom);
     
        return "wiki";
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
    
/*    @GetMapping("/")
    public String showListEventPage(Model model){
        /**
         * Affiche la liste des évènements dans le template "liste_evenements.html"
         * 10 évènements par pages
         * paramètre page: indice de la page que l'on affiche
         */
        
 /*       List<Evenement> pageEvenements = evenementRepository.findAll(); // On récupère tous les evenements dans le dao (on en récupère 10)
        
        ArrayList<Integer> listeDatesDebut = new ArrayList<>();
        for (Evenement e : pageEvenements) {
            listeDatesDebut.add(e.getDateDebut().getYear());
        }
        model.addAttribute("dates", listeDatesDebut); //On ajoute la liste au modèle qui permet l'affichage
        return "index";
    }
 */   
}