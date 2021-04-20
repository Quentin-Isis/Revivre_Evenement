/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package revivreEvenement.controller;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import revivreEvenement.BoiteATools;
import revivreEvenement.dao.EvenementRepository;
import revivreEvenement.dao.ItemRepository;
import revivreEvenement.dao.PositionRepository;
import revivreEvenement.entity.Evenement;
import revivreEvenement.entity.Item;
import revivreEvenement.entity.Position;

/**
 *
 * @author Mathieu
 */

@Controller
@RequestMapping(path="/liste_events")
public class ListEventController {
    
    @Autowired
    EvenementRepository evenementRepository;
     
    @Autowired
    ItemRepository itemRepository;
             
    @Autowired
    PositionRepository positionRepository;
     
     
    @GetMapping(path = "delete")
    public String supprimeUnEvenementPuisMontreLaListe(@RequestParam("id") Evenement evenement, RedirectAttributes redirectInfo) {
        /**
         * Fait appel à la fonction supprssionEventRecursive() de BoiteATools
         * 
         * 
         * 
         */
       
        evenementRepository.delete(evenement);
        String message = "L'évènement " + evenement.getNomEvenement() + "' a bien été supprimé";
        
        // RedirectAttributes permet de transmettre des informations lors d'une redirection,
        // Ici on transmet un message de succès ou d'erreur
        // Ce message est accessible et affiché dans la vue 'liste_evenements.html'
        redirectInfo.addFlashAttribute("message", message);
        
        
        return "redirect:/wiki/liste_evenements"; // on se redirige vers l'affichage de la liste
    }     
    
    @GetMapping(path = "showWikiPage")
    public String showEventWikiPage(Model model, @RequestParam(name="id") Evenement evenement){
        
        fillSsEventListOf(evenement);
        // Vérifiaction de la possibilité d'afficher la liste de sous évènement
        boolean hasSsEvent = false;
        if (!evenement.getListeSousEvenements().isEmpty()){
            hasSsEvent = true;
             
            ArrayList<LocalDate> days = new ArrayList<>();
            ArrayList<LocalDate> month = new ArrayList<>();
            ArrayList<LocalDate> year = new ArrayList<>();
            LocalDate dDebut;
            LocalDate dFin;
            for (Evenement e : evenement.getListeSousEvenements()) {
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
            
            for (Evenement e : evenement.getListeSousEvenements()) {
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
        
        model.addAttribute("evenement", evenement);
        
        return "wiki";
    }
    
    public void fillSsEventListOf(Evenement event){
        /**
         * Pour un évènement donné, remplit la liste de sous-évènements
         */
        List<Evenement> listeEvenement = evenementRepository.findAll();
        
        //continuer à réfléchir là-dessus
        
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
