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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import revivreEvenement.dao.EvenementRepository;
import revivreEvenement.dao.ItemRepository;
import revivreEvenement.dao.PositionRepository;
import revivreEvenement.entity.Evenement;

/**
 *
 * @author Mathieu
 */

@Controller
@RequestMapping(path="/liste_events")
public class ListEventController {
    
    @Autowired
    EvenementRepository evenementRepository;
     
     /**Supprimer un évènement supprime en cascade tous ses sous-évènements, ses items, les positions liés à ces items, les items liés aux sous-évènements, etc...
      * Dangereux de supprimer un évènement à la racine de l'arborescence. C'est pour cela qu'il y a une sécurité implémentée
      * dans le script liste_events.js
      * 
      * @param evenement l'évènement à supprimer
      * @param redirectInfo les infis de redirection
      * @return 
      */
    @GetMapping(path = "delete")
    public String supprimeUnEvenementPuisMontreLaListe(@RequestParam("id") Evenement evenement, RedirectAttributes redirectInfo) {       
        evenementRepository.delete(evenement);
        String message = "L'évènement " + evenement.getNomEvenement() + "' a bien été supprimé";
        
        // RedirectAttributes permet de transmettre des informations lors d'une redirection,
        // Ici on transmet un message de succès ou d'erreur
        // Ce message est accessible et affiché dans la vue 'liste_evenements.html'
        redirectInfo.addFlashAttribute("message", message);        
        
        return "redirect:/wiki/liste_evenements"; // on se redirige vers l'affichage de la liste
    }    
    
    /**Affiche la page wiki de l'évènement en paramètre, ainsi que sa frise chronologique
     * 
     * @param model
     * @param evenement
     * @return 
     */
    @GetMapping(path = "showWikiPage")
    public String showEventWikiPage(Model model, @RequestParam(name="id") Evenement evenement){
        
        // On remplit la liste de sous-évènement de l'évènement
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
    
 /**
     * Pour un évènement donné, remplit la liste de sous-évènements
     * 
     * @param event l'evenement pour lequel on veut remplir la liste de sous evenements
     * 
     */
    public void fillSsEventListOf(Evenement event){

        // Constitution d'une liste avec tous les evenements. On va tous les vérifier
        List<Evenement> listeEvenement = evenementRepository.findAll();
        
        // On parcourt la liste de tous les evenements
        for (Evenement e:listeEvenement){
            // Si l'evenement en paramètre possède un evenement principal (sinon ça plante) et si l'evenement atteint possède un evenement principal
            if ((event.getEvenementPrincipal() != null) && (e.getEvenementPrincipal() != null)) {
                // Si l'evenement paramètre possède le même id que l'evenement principal de l'evenement atteint et si ce n'est pas lui-même 
                //(on ne veut pas ajouter l'evenement parametre à sa propre liste de ss-evenement
                if ((e.getEvenementPrincipal().getId() == event.getId()) && (e.getId()!=event.getId())){
                    // On vérifie si on a déjà ajouter l'evenement atteint à la liste pour ne pas l'ajouter en doublon
                    if (!event.getListeSousEvenements().contains(e)){
                        // On l'ajoute
                        event.getListeSousEvenements().add(e);
                    }
                }
            }
        }
    } 
}