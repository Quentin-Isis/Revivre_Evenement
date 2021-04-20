/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package revivreEvenement.controller;

import java.util.ArrayList;
import java.util.List;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import revivreEvenement.dao.EvenementRepository;
import revivreEvenement.dao.ItemRepository;
import revivreEvenement.entity.Evenement;
import revivreEvenement.entity.Item;

/**
 *
 * @author Léa
 */
@Controller
@RequestMapping(path="/wiki")
public class WikiController {
    
    @Autowired
    private EvenementRepository evenementRepository;
    
    @Autowired
    private ItemRepository itemRepository;
    
    
    @GetMapping("listeRessourcesEvent")
    public String showListeRessourcesEvent(Model model){
        // Accessible depuis 
        return "liste_ressources_event";
    }
    
    @GetMapping("showSsEventWikiPage")
    public String showSsEventWikiPage(Model model, @RequestParam(name="id") Evenement ssEvent){
        
        fillSsEventListOf(ssEvent, model);
       // System.out.println("gsggsegesgsegesgseges"+model);
        boolean hasSsEvent = false;
        if (!ssEvent.getListeSousEvenements().isEmpty()){
            hasSsEvent = true;
            
            ArrayList<LocalDate> days = new ArrayList<>();
            ArrayList<LocalDate> month = new ArrayList<>();
            ArrayList<LocalDate> year = new ArrayList<>();
            LocalDate dDebut;
            LocalDate dFin;
            for (Evenement e : ssEvent.getListeSousEvenements()) {
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
            
            for (Evenement e : ssEvent.getListeSousEvenements()) {
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
        model.addAttribute("evenement", ssEvent);
        
        return "wiki";
    }
    
    @GetMapping("show")
    public String showMap(Model model){
        return "map";
    }
    
    @GetMapping("ajoutSsEvent")
    public String showContribuerAddSsEventTo(Model model, @RequestParam(name="id") Evenement evenement){
        
        boolean fromWiki = true;
        model.addAttribute("fromWiki", fromWiki);
        model.addAttribute("evenement", new Evenement());
        model.addAttribute("evenementPrincipal", evenement);
        return "contribuer";
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
    
    public void fillSsEventListOf(Evenement event, Model model){
        /**
         * Pour un évènement donné, remplit la liste de sous-évènements
         */
        List<Evenement> listeEvenement = evenementRepository.findAll(Sort.by(Sort.Direction.ASC, "dateDebut"));
        
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
    
    public void fillItemListOf(Evenement evenement){
        /**
         * Pour un évènement donné, rempli sa liste d'item
         */
        
        List<Item> liste_item = itemRepository.findAll();
        
        for (Item i: liste_item){
            if(i.getEvenement()==evenement){
                if (! evenement.getItems().contains(i)) {
                    evenement.getItems().add(i);
                }   
            }
        }
    }
    
    @GetMapping("liste_item")
    public String showListItemOf(Model model, @RequestParam(name="id") Evenement evenement){
        
        fillItemListOf(evenement);
        List<Item> liste_item_of_event = evenement.getItems();
        boolean hasItems = false;
        if (!liste_item_of_event.isEmpty()){
            hasItems = true;
        }
        
        model.addAttribute("hasItems", hasItems);
        model.addAttribute("evenement", evenement);
        model.addAttribute("liste_item_of_event", liste_item_of_event);
        
        return "liste_ressources_event";
    }
}
