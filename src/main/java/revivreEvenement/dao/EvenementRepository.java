/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package revivreEvenement.dao;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import revivreEvenement.entity.Evenement;

/**
 *
 * @author Mathieu
 */
public interface EvenementRepository extends JpaRepository<Evenement, Integer>{
    
    // Fonction qui cherche un mot clé dans le nom d'un évènement
    // Return un liste d'évènement. Si on se rend compte qu'il y a trop d'évènement affichés, on pourra faire un système de pagination
    public List<Evenement> findByNomEvenementContains(String motCle);
    public List<Evenement> findByLieuContains(String motCle);
    
    
}
