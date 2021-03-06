package revivreEvenement.controller;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import revivreEvenement.dao.EvenementRepository;
import revivreEvenement.entity.Evenement;

@Controller
@RequestMapping(path="/")
public class IndexController{
    
    @Autowired
    private EvenementRepository evenementRepository;
   
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
        
       fillSsEventListOf(evenementRandom);
        
        // Vérifiaction de la possibilité d'afficher la liste de sous évènement
        boolean hasSsEvent = false;
        if (!evenementRandom.getListeSousEvenements().isEmpty()){
            hasSsEvent = true;
            
            /*
             * Conception de la frise d'un événement en particulier
            */
            
            ArrayList<LocalDate> days = new ArrayList<>();
            LocalDate dDebut;
            LocalDate dFin;
            // création de la liste de date pour les affichages sur la frise
            for (Evenement e : evenementRandom.getListeSousEvenements()) {
                dDebut = e.getDateDebut();
                dFin = e.getDateFin();
                
                if (ChronoUnit.DAYS.between(dDebut, dFin) < 32) {
                    days.add(dDebut);
                    int i=0;
                    while (i<days.size()-1) {
                        if (!days.get(i).equals(days.get(i+1))) {
                            i+=1;
                        } else {
                            days.remove(days.get(i+1));
                        }
                    }
                }
            }
            // tri de la liste de date
            Collections.sort(days);
            
            HashMap<LocalDate, List<String>> dateForDays = new HashMap<LocalDate, List<String>>();
            // vérification des événements
            // si la date dDays correspond à une date de début d'un des sous-événements, ajouter le nom de l'événenement à la hashmap
            for (LocalDate dDays : days) {
                List<String> eventForDays = new ArrayList<>();
                for (Evenement e : evenementRandom.getListeSousEvenements()) {
                    if (dDays.equals(e.getDateDebut())) {
                        eventForDays.add(e.getNomEvenement());
                    }
                    dateForDays.put(dDays, eventForDays);
                }
            }
            Map sortedDaysMap = new TreeMap(dateForDays);
            model.addAttribute("days", days); //On ajoute la liste au modèle qui permet l'affichage
            model.addAttribute("sortedDays",sortedDaysMap); // liste de noms des événements triés par leurs dates
        }
        model.addAttribute("evenements", evenementRepository.findAll());
        model.addAttribute("hasSsEvent", hasSsEvent);
        model.addAttribute("evenement", evenementRandom);
     
        return "wiki";
    }
    
    @GetMapping("contribuer")
    public String showContribuer(Model model){
        /**
         * Montre la page pour ajouter un évènement
         */
        boolean fromWiki = false;
        model.addAttribute("fromWiki", fromWiki);
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
         * conception de la frise
         */
        
        List<Evenement> events = evenementRepository.findAll(Sort.by(Sort.Direction.ASC, "dateDebut"));
        // création de la liste de date pour les affichages sur la frise
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
        // vérification des événements
        // si la date dDays correspond à une date de début d'un des sous-événements, ajouter le nom de l'événenement à la hashmap
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
        
        model.addAttribute("evenements", evenementRepository.findAll());
        model.addAttribute("dates", listeDatesDebutAnnee); //On ajoute la liste au modèle qui permet l'affichage
        model.addAttribute("da",sortedMap);
        return "index";
    }
    
  public void fillSsEventListOf(Evenement event){
        /**
         * Pour un évènement donné, remplit la liste de sous-évènements
         */
    List<Evenement> listeEvenement = evenementRepository.findAll();

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