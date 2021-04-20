// @Author: mathieu
//const url = "http://localhost:8080/api/evenements";// A chager après le déploiement
const url = "https://lit-plains-26980.herokuapp.com/api/evenements"
console.log(url);
//document.addEventListener("DOMContentLoaded", generation_button_ID)

var btns_modif = document.getElementsByClassName("btn_modif");
var btns_retour = document.getElementsByClassName("btn_retour");
var btns_save = document.getElementsByClassName("btn_save");
  
var liste_id = [];  

var liste_nomEvenementCells = document.getElementsByClassName("nomEvenementCells");
var liste_nomEvenements = [];

var liste_descriptionCells = document.getElementsByClassName("descriptionCells");
var liste_descriptions = [];

var liste_lieuCells = document.getElementsByClassName("lieuCells");
var liste_lieux = [];

var liste_dateDebutCells = document.getElementsByClassName("dateDebutCells");
var liste_dateDebut = []

var liste_dateFinCells = document.getElementsByClassName("dateFinCells");
var liste_dateFin = []

var liste_espaces_modif = document.getElementsByClassName("espace_de_modification");

console.log("SALUT")


for(let i=0; i<btns_modif.length; i++){
  btns_modif[i].addEventListener("click", afficher_modificateur);
  btns_modif[i].style.display = "block";
}
for(let i=0; i<btns_retour.length;i++){
  btns_retour[i].addEventListener("click", afficher_modificateur);
}
for(let i=0; i<btns_save.length;i++){
  btns_save[i].addEventListener("click", afficher_modificateur);
}   

for (let i=0; i<liste_nomEvenementCells.length; i++){
    liste_nomEvenements.push(liste_nomEvenementCells[i].textContent);
    liste_id.push(liste_nomEvenementCells[i].id);
}

for (let i=0; i<liste_lieuCells.length; i++){
    liste_lieux.push(liste_lieuCells[i].textContent);
}

for (let i=0; i<liste_descriptionCells.length; i++){
    liste_descriptions.push(liste_descriptionCells[i].textContent);
}

for (let i=0; i<liste_dateDebutCells.length; i++){
    liste_dateDebut.push(liste_dateDebutCells[i].textContent);
}

for (let i=0; i<liste_dateFinCells.length; i++){
    liste_dateFin.push(liste_dateFinCells[i].textContent);
}

function afficher_modificateur(event){
    console.log("clickme")
    index_btn = searchIndexOf(event.target);
    console.log(index_btn);
    
    if (event.target.className == "btn_modif"){
        console.log("on est là");
        let btnCible = event.target;
        btnCible.style.display = "none";
        btns_retour[index_btn].style.display = "block";
        btns_save[index_btn].style.display = "block";
        liste_espaces_modif[index_btn].style.display = "block";
        
        console.log(liste_dateDebut[index_btn])
        console.log(manipulationDate(liste_dateDebut[index_btn]))
        nomEvenementToInput(liste_nomEvenementCells[index_btn], liste_nomEvenements[index_btn]);
        lieuToInput(liste_lieuCells[index_btn], liste_lieux[index_btn]);
        descriptionToTextArea(liste_descriptionCells[index_btn], liste_descriptions[index_btn]);
        dateDebutToInput(liste_dateDebutCells[index_btn], liste_dateDebut[index_btn]);
        dateFinToInput(liste_dateFinCells[index_btn], liste_dateFin[index_btn]);
    }
    else if(event.target.className == "btn_retour"){
        window.location.reload();
        let btnCible = event.target;
        btnCible.style.display = "none";
        btns_save[index_btn].style.display = "none";
        liste_espaces_modif[index_btn].style.display = "none";
        btns_modif[index_btn].style.display = "block";
        
        inputToNomEvenement(liste_nomEvenementCells[index_btn], liste_nomEvenements[index_btn]);
        inputToLieu(liste_lieuCells[index_btn], liste_lieux[index_btn]);
        textAreaToDescription(liste_descriptionCells[index_btn], liste_descriptions[index_btn]);
        inputToDateDateDebut(liste_dateDebutCells[index_btn], liste_dateDebut[index_btn]);
        inputToDateFin(liste_dateFinCells[index_btn], liste_dateFin[index_btn]);
    }
    else if(event.target.className == "btn_save"){
        
        let btnCible = event.target;
        btnCible.style.display = "none";
        btns_modif[index_btn].style.display = "block";
        btns_retour[index_btn].style.display = "none";
        
        liste_nomEvenements[index_btn] = liste_nomEvenementCells[index_btn].firstChild.value;
        liste_lieux[index_btn] = liste_lieuCells[index_btn].firstChild.value;
        liste_descriptions[index_btn] = liste_descriptionCells[index_btn].firstChild.value;
        liste_dateDebut[index_btn] = liste_dateDebutCells[index_btn].firstChild.value;
        liste_dateFin[index_btn] = liste_dateFinCells[index_btn].firstChild.value;
        
        let evenement = {
            id: liste_id[index_btn],
            nomEvenement: liste_nomEvenements[index_btn],
            description: liste_descriptions[index_btn],
            lieu: liste_lieux[index_btn],            
            dateDebut: liste_dateDebut[index_btn],
            dateFin: liste_dateFin[index_btn]
        }
        console.log(evenement);
        $.ajax({
          type: 'PUT',
          url: url+ "/"+evenement.id,
          contentType: 'application/json',
          data: JSON.stringify(evenement), // access in body
      }).done(function () {
          console.log('SUCCESS');
          window.alert("L'évènement a été modifié!");
          window.location.reload();
      }).fail(function (msg) {
          console.log('FAIL');
      }).always(function (msg) {
          console.log('ALWAYS');
      });
    }

}

function searchIndexOf(btnCible){
    let index = 0;
    if (btnCible.className == "btn_modif"){
        for (let i=0; i<btns_modif.length;i++){
            if (btnCible == btns_modif[i]){
                return index;
            }
            else{
                index+=1;
            }
        }
    }
    else if (btnCible.className == "btn_retour"){
        for (let i=0; i<btns_retour.length;i++){
            if (btnCible == btns_retour[i]){
                return index;
            }
            else{
                index+=1;
            }
        }
    }
    else if (btnCible.className == "btn_save"){
        for (let i=0; i<btns_save.length;i++){
            if (btnCible == btns_save[i]){
                return index;
            }
            else{
                index+=1;
            }
        }
    } 
    else if (btnCible.className == "btn-suppr"){
        for (let i=0; i<btns_save.length;i++){
            if (btnCible == btns_suppr[i]){
                return index;
            }
            else{
                index+=1;
            }
        }
    }
}
/// MODIFICATION EVENEMENT

function nomEvenementToInput(nomEvenementCell, nomEvenement){
    
    nomEvenementCell.textContent = "";
    nomEvenementCell.innerHTML = "<input type=\"text\" value=\"" + nomEvenement +"\"></input>";
}

function inputToNomEvenement(nomEvenementCell, nomEvenement){
    
    nomEvenementCell.innerHTML = "";
    nomEvenementCell.textContent = nomEvenement;
    
}

function lieuToInput(lieuCell, lieu){
    
    lieuCell.textContent = "";
    lieuCell.innerHTML = "<input type=\"text\" value=\"" + lieu +"\"></input>";
}

function inputToLieu(lieuCell, lieu){
    
    lieuCell.innerHTML = "";
    lieuCell.textContent = lieu;
}

function descriptionToTextArea(descriptionCell, description){
    descriptionCell.textContent = "";
    descriptionCell.innerHTML = "<textarea rows=7 cols=50>" + description +"</textarea>";
}

function textAreaToDescription(descriptionCell, description){
    descriptionCell.innerHTML = "";
    descriptionCell.textContent = description;
}

function dateDebutToInput(dateDebutCell, dateDebut){
    dateDebutCell.textContent=""
    dateDebutCell.innerHTML = "<input type=\"date\" required>"+dateDebut+"</input>";
}

function inputToDateDebut(dateDebutCell, dateDebut){
    dateDebutCell.innerHTML="";
    dateDebutCell.textContent = dateDebut;
}

function dateFinToInput(dateFinCell, dateFin){
    dateFinCell.textContent="";
    dateFinCell.innerHTML = "<input type=\"date\" required>"+dateFin+"</input>";
}

function inputToDateFin(dateFinCell, dateFin){
    dateFinCell.innerHTML="";
    dateFinCell.textContent = dateFin;
}

function manipulationDate(date){
    /**
     * 
     * Retpurne une date au format yyyy-mm-dd
     */ 
    let day_month_year = date.split("/");
    
    return day_month_year[2]+"-"+day_month_year[1]+"-"+day_month_year[0];
}

// Modification effective

function findIdFromBtn(btnCible){
    let index = searchIndexOf(btnCible);
    return liste_id[index];
}

function findNomFromBtn(btnCible){
    let index = searchIndexOf(btnCible);
    return liste_nomEvenements[index];
}

function findLieuFromBtn(btnCible){
    let index = searchIndexOf(btnCible);
    return liste_lieux[index];
}

function findDateDebutFromBtn(btnCible){
    let index = searchIndexOf(btnCible);
    return liste_dateDebut[index];
}

function findDateFinFromBtn(btnCible){
    let index = searchIndexOf(btnCible);
    return liste_dateFin[index];
}
    
//Confirmation suppression
var btns_suppr = document.getElementsByClassName("btn-suppr");

var liste_suppr = document.getElementsByClassName("suppr");

for(let i=0; i<btns_suppr.length; i++){
  btns_suppr[i].addEventListener("click", confirmation_suppr);
  }
  



function confirmation_suppr(event){
    console.log(liste_suppr);
    let index_btn = searchIndexOf(event.target);
    event.target.style.display = "none";
    console.log(index_btn);
    window.alert("Veuillez cliquer une deuxième fois!");
    liste_suppr[index_btn].style.display = "block";
}




