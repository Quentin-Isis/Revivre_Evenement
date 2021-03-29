/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package revivreEvenement.entity;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author QUENTIN
 */
@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor @ToString
@Entity
public class Item {
    
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer idItem;
    
    @Column(unique=true)
    @NonNull
    private String type;
    
    @OneToMany (mappedBy = "items")
    List<SousEvenement> ssEvenement = new LinkedList<>();
    
    @OneToOne(mappedBy = "item")
    private Temporalite idTemporalite;
    
    @OneToOne(mappedBy = "item")
    private Position idPosition;
    
}
