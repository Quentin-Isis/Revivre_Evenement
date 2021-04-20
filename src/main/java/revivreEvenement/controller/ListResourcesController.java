/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package revivreEvenement.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import revivreEvenement.dao.ItemRepository;
import revivreEvenement.entity.Evenement;
import revivreEvenement.entity.Item;
import revivreEvenement.storageservice.StorageService;

/**
 *
 * @author Mathieu
 */
@Controller
@RequestMapping(path="/ressources")
public class ListResourcesController {        

    private final StorageService storageService;

    @Autowired
    public ListResourcesController(StorageService storageService) {
            this.storageService = storageService;
    }

    @Autowired
    ItemRepository itemRepository;
    
    /**
     * Remplit la liste d'items d'un évènement donné
     * @param evenement 
     */
    public void fillItemListOf(Evenement evenement){
    
        // On va chercher tous les items
        List<Item> liste_item = itemRepository.findAll();
        
        // On parcourt cette liste
        for (Item i: liste_item){
            // On récupère l'évènement correspondant à l'item et on vérifie si c'est l'évènement en paramètre. 
            //Si oui et s'il n'est pas déjà dans la liste, on l'ajoute
            if(i.getEvenement()==evenement){
                if(!evenement.getItems().contains(i)){
                    evenement.getItems().add(i);
                }
            }
        }
    }
    
    /**On affiche la liste d'item d'un évènement donné grâce à thymeleaf
     * 
     * @param model
     * @param evenement
     * @return
     * @throws IOException 
     */
    @GetMapping("liste_item")
    public String showListItemOf(Model model, @RequestParam(name="id") Evenement evenement) throws IOException{        
        
        // On ajoute la liste de fichier au model
        model.addAttribute("files", storageService.loadAll().map(
				path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
						"serveFile", path.getFileName().toString()).build().toUri().toString())
				.collect(Collectors.toList()));
        
        // On remplit la liste d'item de l'évènement et on récupère cette liste
        fillItemListOf(evenement);
        List<Item> liste_item_of_event = evenement.getItems();
        
        // On vérifie si l'évènement a des items pour faciliter l'utilisation de thymeleaf
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
