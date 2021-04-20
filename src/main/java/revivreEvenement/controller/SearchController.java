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

import revivreEvenement.dao.EvenementRepository;
import revivreEvenement.entity.Evenement;

/**
 *
 * @author Mathieu & Lea
 */

@Controller
@RequestMapping(path="/recherches")
public class SearchController {
    
    @Autowired
    private EvenementRepository evenementRepository;
    
    @GetMapping("fctSearch")
    private String fctSearchByMotCle(Model model, @RequestParam(name="motCle", defaultValue ="") String motCle){
        /**
         * chemin -> th:href="@{/recherches/fctSearch}"
         * 
         * Cette méthode, utilisée sur la page search.html, prend en argument un mot clé et recherche un évènement dans le dao qui contient ce mot clé
         * On vérifie si le mot clé est contenu dans le nom d'un évènement ou dans son lieu
         * 
         * TODO: faire en sorte que la recherche ne soit pas sensible à la casse (majuscule/minuscule)
         * On aura des résultats pour "Russie" mais pas pour "russie"
         * 
         * @param model le model pour afficher les données dans le template search.html avec thymeleaf
         * @param motCle le mot clé en question. S'il n'y en a pas dans l'input, la valeur par défaut est une chaine de caractère vide ""
         * 
         * @return le template search.html avec l'affichage des résultats en utilisant thymeleaf et le script javascript search.js
         */
        
        boolean recherche_en_cours = true;  // Pour indiquer à thymeleaf d'afficher la div des résultats dans le template
        boolean recherche_par_mot_cle = true; // Pour indiquer à thymeleaf d'afficher la div des résultats d'une recherche par mot clé
        boolean motCleIsValide = motCleIsValide(motCle); // Verification de la validité du mot clé. Cf. la méthode motCleIsValide(). true si le mot clé est valide
        boolean resultats_trouves = false; // Indiquer à thymeleaf si des résultats ont été trouvés. Affichage adapté en fonction de la valeur du boolean.
        
        // On ajoute tous les booleans au model afin de pouvoir les utiliser
        model.addAttribute("recherche_en_cours", recherche_en_cours);
        model.addAttribute("recherche_par_mot_cle", recherche_par_mot_cle);
        model.addAttribute("isValide", motCleIsValide);
        model.addAttribute("motCle", motCle);
        
        
        if (motCleIsValide){
            // cf. EvenementRepository.java Ce sont deux méthodes du dao
            List<Evenement> listEvenementSearched = evenementRepository.findByNomEvenementContains(motCle);
            //On vérifie aussi si le lieu contient le mot clé
            List<Evenement> listEvenementSearchedByLieu = evenementRepository.findByLieuContains(motCle);
            
            if (!listEvenementSearchedByLieu.isEmpty()){
                for (int i=0; i<listEvenementSearchedByLieu.size(); i++){
                    if (!listEvenementSearched.contains(listEvenementSearchedByLieu.get(i))){
                        //On ajoute les résultats de la recherche par lieu à la liste des évènements déjà trouvés
                        listEvenementSearched.add(listEvenementSearchedByLieu.get(i));
                    }
                }
            }
            //Si la liste de résultat n'est pas vide, c'est qu'on a trouvé des résultats
            if (!listEvenementSearched.isEmpty()){
                resultats_trouves = true;
            }
            //Ajout du boolean au model
            model.addAttribute("results", listEvenementSearched);
        }
        //Ajout de la liste de résultat
        model.addAttribute("trouves", resultats_trouves);
        
        //renvoie vers search.html
        return "search";
    }
    
    @GetMapping("eventWikiPage")
    private String showEventWikiPage(Model model, @RequestParam("id") Evenement event){
        
        /**
         * Méthode qui permet, lorsqu'on clique sur un résultat, d'accèder à la page wiki de cet évènement
         * 
         * @param model le model utilisé par thymeleaf
         * @param event l'évènement pour lequel on veut la page wiki        * 
         * 
         * @return wiki.html, avec les bonnes infos dans le model
         */
        
        // On remplit la liste de sous evenement de l'evenement. Cf fillSsEventListOf()
        fillSsEventListOf(event);
        // Vérifiaction de la possibilité d'afficher la liste de sous évènement. bollean utilisé par thymeleaf
        boolean hasSsEvent = false;
        if (!event.getListeSousEvenements().isEmpty()){
            hasSsEvent = true;
             
            ArrayList<LocalDate> days = new ArrayList<>();
            ArrayList<LocalDate> month = new ArrayList<>();
            ArrayList<LocalDate> year = new ArrayList<>();
            LocalDate dDebut;
            LocalDate dFin;
            for (Evenement e : event.getListeSousEvenements()) {
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
            
            for (Evenement e : event.getListeSousEvenements()) {
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
        
        
        model.addAttribute("evenement", event);
        
        return "wiki";
    }
    
    private boolean motCleIsValide(String motCle){
        /**
         * Vérifie si un mot cle est valide. Un mot clé est valide s'il contient AU MOINS un caractère de l'alphabet latin
         * 
         * @param motCle Le mot clé à vérifier
         * 
         * @return motCleIsValide, boolean true si le mot cle est valide, false sinon
         */
        // On l'initialise à false
        boolean motCleIsValide = false;
        
        // Tableau des caractères latin
        char[] charSearch = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't','u', 'v', 'w', 'x', 'y', 'z'} ;
        // On parcourt les caractères du mot clé
        for(int i=0; i<motCle.length(); i++) 
        {
            // On récupère le char à l'index i
            char chr = motCle.charAt(i);
            // On parcourt le tableau de caractère
            for(int j=0; j<charSearch.length; j++)
            {   
                // Si on trouve un caractère dabs le mot cle, celui ci est valide
                if(charSearch[j] == chr)
                {
                    motCleIsValide = true;      
                }
            }  
        }
        
        return motCleIsValide;
    }
    
    //FONCTIONS POUR RECHERCHER PAR DATE
    
    private String fctSearchByDate(Model model, 
            @RequestParam(name="month", defaultValue = "") String month, 
            @RequestParam(name="year", defaultValue = "") String year, 
            @RequestParam(name="day", defaultValue = "") String day){
        
        /**
         * Methode pour la recherche par date
         * 
         * TODO
         */
        
        boolean recherche_par_date = true;
        model.addAttribute("recherche_par_date", recherche_par_date);
        model.addAttribute("year", year);
        
        return "search";
    }
    
    public void fillSsEventListOf(Evenement event){
        /**
         * Pour un évènement donné, remplit la liste de sous-évènements
         * 
         * @param event l'evenement pour lequel on veut remplir la liste de sous evenements
         * 
         */
        // Constitution d'une liste avec tous les evenements. On va tous les vérifier
        List<Evenement> listeEvenement = evenementRepository.findAll();
        
        //continuer à réfléchir là-dessus
        
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
