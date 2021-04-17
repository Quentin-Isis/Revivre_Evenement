/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package revivreEvenement.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;


/**
 *
 * @author QUENTIN
 */
@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor @ToString
@Entity
public class Evenement {
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer id;

    @Column(unique=true)
    @NonNull
    private String nomEvenement;
    
    @NonNull
    @Column(length=2048)
    private String description;
    
    @NonNull
    private String lieu;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NonNull
    private LocalDate dateDebut;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NonNull
    private LocalDate dateFin;
    
    @OneToMany(mappedBy = "evenement", cascade = CascadeType.ALL, orphanRemoval = true)
    List <Item> items = new LinkedList<>();
    
    @ManyToOne 
    private Evenement evenementPrincipal;
    
    @OneToMany (mappedBy = "evenementPrincipal", cascade = CascadeType.ALL, orphanRemoval = true)
    List <Evenement> listeSousEvenements = new ArrayList<>();
    
}





