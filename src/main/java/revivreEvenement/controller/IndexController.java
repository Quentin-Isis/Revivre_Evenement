
package revivreEvenement.controller;


import java.time.LocalDate;
import java.util.*;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    
 /*   @GetMapping("/")
    public String showIndexPage(Model model){
        return "index";
    }
   */ 
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
        
       // fillSsEventListOf(evenementRandom);
        
      /*  // Vérifiaction de la possibilité d'afficher la liste de sous évènement
        boolean hasSsEvent = false;
        if (!evenementRandom.getListeSousEvenements().isEmpty()){
            hasSsEvent = true;
        }
        model.addAttribute("hasSsEvent", hasSsEvent);
        */model.addAttribute("evenement", evenementRandom);
     
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
    
    @GetMapping("/")
    public String showListEventPage(Model model){
        /**
         * Affiche la liste des évènements dans le template "liste_evenements.html"
         * 10 évènements par pages
         * paramètre page: indice de la page que l'on affiche
         */
        
        List<Evenement> events = evenementRepository.findAll(Sort.by(Sort.Direction.ASC, "dateDebut"));
        
        ArrayList<Integer> listeDatesDebutAnnee = new ArrayList<>();
        for (Evenement e : events) {
            listeDatesDebutAnnee.add(e.getDateDebut().getYear());
        }
        int i=0;
        while (i<listeDatesDebutAnnee.size()-1) {
            if (!listeDatesDebutAnnee.get(i).equals(listeDatesDebutAnnee.get(i+1))) {
                i+=1;
            } else {
                listeDatesDebutAnnee.remove(listeDatesDebutAnnee.get(i+1));
            }
        }
   
        HashMap<Integer, List<String>> da = new HashMap<Integer, List<String>>();     
        
        for (Integer d: listeDatesDebutAnnee) {
            List<String> eventForD = new ArrayList<>();
            for (Evenement e : events) {
                if (d==e.getDateDebut().getYear()) {
                    eventForD.add(e.getNomEvenement());
                }
                da.put(d, eventForD);
            }
        }
        Map sortedMap = new TreeMap(da);
        System.out.println(sortedMap);
        
        model.addAttribute("evenements", evenementRepository.findAll());
        model.addAttribute("dates", listeDatesDebutAnnee); //On ajoute la liste au modèle qui permet l'affichage
        model.addAttribute("da",sortedMap);
      //  model.addAttribute("eventForDates",eventForD);
        return "index";
    }
    
  /*  public void fillSsEventListOf(Evenement event){
        /**
         * Pour un évènement donné, remplit la liste de sous-évènements
         */
  /*      List<Evenement> listeEvenement = evenementRepository.findAll();

        for (Evenement e:listeEvenement){
            if (e.getEvenementPrincipal().getId() == event.getId() && !(e.getId()==event.getId())){
                if (!event.getListeSousEvenements().contains(e)){
                    event.getListeSousEvenements().add(e);
                } 
            }
        }
    }*/
}