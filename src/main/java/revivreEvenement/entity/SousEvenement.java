/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package revivreEvenement.entity;

import java.time.LocalDate;
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
@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor @ToString
@Entity
public class SousEvenement {
    
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer idSsEvenement;
    
    @Column(unique=true)
    @NonNull
    private String nomSsEvenement;
    
    @Column(unique=true)
    @NonNull
    private String description;
    
    @ManyToOne
    private Evenement evenement;
    
    @ManyToOne
    private Item items; 
    
    @OneToOne(mappedBy = "ssEvenement")
    private Position idPosition;
}