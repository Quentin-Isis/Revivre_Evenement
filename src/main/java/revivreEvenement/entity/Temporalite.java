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
public class Temporalite {
    
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer idTemporalite;
    
    @Column(unique=true)
    @NonNull
    private LocalDate heureDebut;
    
    @Column(unique=true)
    @NonNull
    private LocalDate heureFin;
    
    @Column(unique=true)
    @NonNull
    private LocalDate date;
    
    @OneToOne
    private Evenement event;
    
    @OneToOne
    private Item item;
}

