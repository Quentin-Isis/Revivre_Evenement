
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


/**
 *
 * @author Mathieu
 */

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
             
            ArrayList<LocalDate> days = new ArrayList<>();
            ArrayList<LocalDate> month = new ArrayList<>();
            ArrayList<LocalDate> year = new ArrayList<>();
            LocalDate dDebut;
            LocalDate dFin;
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
                } else if (ChronoUnit.MONTHS.between(dDebut, dFin) < 12) {
                    month.add(dDebut);
                    int j=0;
                    while (j<month.size()-1) {
                        if (!month.get(j).equals(month.get(j+1))) {
                            j+=1;
                        } else {
                            month.remove(month.get(j+1));
                        }
                    }
                } else if (ChronoUnit.YEARS.between(dDebut, dFin) < 1) {
                    year.add(dDebut);
                    int k=0;
                    while (k<year.size()-1) {
                        if (!year.get(k).equals(year.get(k+1))) {
                            k+=1;
                        } else {
                            year.remove(year.get(k+1));
                        }
                    }
                }
            }
            
            HashMap<LocalDate, List<String>> dateForDays = new HashMap<LocalDate, List<String>>();     
            HashMap<LocalDate, List<String>> dateForMonth = new HashMap<LocalDate, List<String>>();
            HashMap<LocalDate, List<String>> dateForYear = new HashMap<LocalDate, List<String>>();
            
            for (Evenement e : evenementRandom.getListeSousEvenements()) {
                List<String> eventForDays = new ArrayList<>();
                List<String> eventForMonth = new ArrayList<>();
                List<String> eventForYear = new ArrayList<>();

                for (LocalDate dDays : days) {
                    if (dDays == e.getDateDebut()) {
                        eventForDays.add(e.getNomEvenement());
                    }
                    dateForDays.put(dDays, eventForDays);
                }

                for (LocalDate dMonth : month) {
                    if (dMonth == e.getDateDebut()) {
                        eventForMonth.add(e.getNomEvenement());
                    }
                    dateForMonth.put(dMonth, eventForMonth);
                }

                for (LocalDate dYear : year) {
                    if (dYear == e.getDateDebut()) {
                        eventForYear.add(e.getNomEvenement());
                    }
                    dateForYear.put(dYear, eventForYear);
                }
            }
            Map sortedDaysMap = new TreeMap(dateForDays);
            Map sortedMonthMap = new TreeMap(dateForMonth);
            Map sortedYearMap = new TreeMap(dateForYear);
            
            model.addAttribute("evenements", evenementRepository.findAll());
            model.addAttribute("days", days); //On ajoute la liste au modèle qui permet l'affichage
            model.addAttribute("month", month);
            model.addAttribute("year", year);
            model.addAttribute("sortedDays",sortedDaysMap);
            model.addAttribute("sortedMonth",sortedMonthMap);
            model.addAttribute("sortedYear",sortedYearMap);
        }
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
        //model.addAttribute("evenementPrincipal", new Evenement()); ESSAI 2 et 3
        
        return "contribuer";
    }
    
    @GetMapping("search")
    public String showSearchPage(Model model){
        return "search";
    }
    
    @GetMapping("/")
    public String showListEventPage(Model model){
        /**
         * Author: léa
         * conception de la frise
         * 
         * 
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