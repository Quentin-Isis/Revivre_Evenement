/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package revivreEvenement.controller;

import java.io.IOException;
import java.util.ArrayList;
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
 * @author Léa
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

        public void fillItemListOf(Evenement evenement){
        /**
         * Pour un évènement donné, rempli sa liste d'item
         */
        
        List<Item> liste_item = itemRepository.findAll();
        
        for (Item i: liste_item){
            if(i.getEvenement()==evenement){
                if(!evenement.getItems().contains(i)){
                    evenement.getItems().add(i);
                }
            }
        }
    }
    
    @GetMapping("liste_item")
    public String showListItemOf(Model model, @RequestParam(name="id") Evenement evenement) throws IOException{
        
        
        model.addAttribute("files", storageService.loadAll().map(
				path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
						"serveFile", path.getFileName().toString()).build().toUri().toString())
				.collect(Collectors.toList()));
        
        fillItemListOf(evenement);
        List<Item> liste_item_of_event = evenement.getItems();
        List<String> liste_extensions = new ArrayList<>();
        boolean hasItems = false;
        if (!liste_item_of_event.isEmpty()){
            hasItems = true;
            for (Item i: liste_item_of_event){
                if (i.getTypeItem().contains("/")){
                    liste_extensions.add(i.getTypeItem().split("/")[1]);
                } 
                else{
                    liste_extensions.add("undefined");
                }
            }
        }
        
        model.addAttribute("extensions", liste_extensions);
        model.addAttribute("hasItems", hasItems);
        model.addAttribute("evenement", evenement);
        model.addAttribute("liste_item_of_event", liste_item_of_event);
        
        return "liste_ressources_event";
    }
}
