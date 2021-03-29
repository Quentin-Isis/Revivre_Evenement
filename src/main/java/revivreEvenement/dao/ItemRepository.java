/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package revivreEvenement.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import revivreEvenement.entity.Item;

/**
 *
 * @author Mathieu
 */
public interface ItemRepository extends JpaRepository<Item, Integer>{
    
}
