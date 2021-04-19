/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package revivreEvenement.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import revivreEvenement.dao.EvenementRepository;
import revivreEvenement.dao.ItemRepository;
import revivreEvenement.entity.Evenement;
import revivreEvenement.entity.Item;

import revivreEvenement.storageservice.StorageFileNotFoundException;
import revivreEvenement.storageservice.StorageService;

@Slf4j
@Controller
@RequestMapping(path="/formulaireItem")
public class FileUploadController {

	private final StorageService storageService;
        @Autowired
        private ItemRepository itemRepository;
        
        @Autowired
        private EvenementRepository evenementRepository;

	@Autowired
	public FileUploadController(StorageService storageService) {
		this.storageService = storageService;
	}
        
        @GetMapping("upload1")
	public String listUploadedFiles(Model model) throws IOException {

		model.addAttribute("files", storageService.loadAll().map(
				path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
						"serveFile", path.getFileName().toString()).build().toUri().toString())
				.collect(Collectors.toList()));

		return "contribuer";
	}

	@GetMapping("/files/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

		Resource file = storageService.loadAsResource(filename);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
				"attachment; filename=\"" + file.getFilename() + "\"").body(file);
	}

        @PostMapping("upload2")
	public String handleFileUpload(@RequestParam(name="file") MultipartFile file, @RequestParam(name="id") Evenement event,
			RedirectAttributes redirectAttributes, RedirectAttributes redirectInfo) {
		
            log.info("Téléchargement du fichier {}", file.getOriginalFilename());
            storageService.store(file);
            redirectAttributes.addFlashAttribute("message",
                            "You successfully uploaded " + file.getOriginalFilename() + "!");

            Item newFile = new Item();
            newFile.nomItem = file.getOriginalFilename();
            newFile.typeItem = file.getContentType();
            List<Evenement> listEvenements = evenementRepository.findAll();
            
            for (Evenement e : listEvenements) {
                if (e.id == event.id){
                    newFile.setEvenement(e);
                }
            }
            
            String message;
            try {
                itemRepository.save(newFile);
            } catch (DataIntegrityViolationException e) {
                message = "Erreur : L'évènement '" + newFile.nomItem + "' existe déjà";
            }
            redirectInfo.addFlashAttribute("message", "You successfully created " + newFile.nomItem);
                      
            
            return "redirect:/";
	}
	
        

	@ExceptionHandler(StorageFileNotFoundException.class)
	public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
		return ResponseEntity.notFound().build();
	}

}