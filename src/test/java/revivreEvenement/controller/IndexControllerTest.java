/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package revivreEvenement.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import revivreEvenement.dao.EvenementRepository;

import revivreEvenement.entity.Evenement;
import revivreEvenement.storageservice.StorageService;

/**
 *
 * @author mathi
 */
@WebMvcTest(controllers = IndexController.class)
public class IndexControllerTest {
    
        
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private Evenement evenement;
    
    @MockBean
    private EvenementRepository evenementRepository;
    
    @MockBean
    private StorageService storageService;
    
    /*
    @Test
    public void testShowEvenementRandom() throws Exception{
        mockMvc.perform(get("/eventRandom")).andExpect(status().isOk());
    }*/
    
    @Test
    public void testShowContribuer() throws Exception{
         mockMvc.perform(get("/contribuer")).andExpect(status().isOk());
    }
    
    @Test
    public void testShowShearchPage() throws Exception{
        mockMvc.perform(get("/search")).andExpect(status().isOk());
    }
    
    @Test
    public void testShowListEventPage() throws Exception{
        mockMvc.perform(get("/")).andExpect(status().isOk());
    }
}
