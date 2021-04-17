/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package revivreEvenement.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
@Getter @Setter @NoArgsConstructor @ToString
@Entity
public class Position {
    
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer id;
    
   // @Column(unique=true)
    private double longitude;
    
   // @Column(unique=true)
    private double latitude;
    
   // @Column(unique=true)
    private double orientation;
    
    @OneToOne(mappedBy = "position")
    private Item item;
    
}