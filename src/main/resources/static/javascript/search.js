/**
 * Apparition des menus et des formulaires de recherche.
 */

document.getElementById("lieu").addEventListener("click", afficheRechercheLieu);
document.getElementById("date").addEventListener("click", afficheRechercheDate);
document.getElementById("btn-retour").addEventListener("click", retour);
/**let bouttons_rechercher = document.getElementsByClassName("btn-rechercher")

for(let i = 0; i < bouttons_rechercher.length; i++) {
  bouttons_rechercher[i].addEventListener("click", affichageResultats)
}
*/

function afficheRechercheLieu(event){
    document.getElementById("espace_mot_cle").style.display = "none";
    document.getElementById("espace_lieu_1").style.display = "block";
    document.getElementById("espace_lieu_2").style.display = "block";
    document.getElementById("btn-retour").style.display = "block";
}

function afficheRechercheDate(event){
    document.getElementById("espace_mot_cle").style.display = "none";
    document.getElementById("espace_date").style.display = "block";
    document.getElementById("btn-retour").style.display = "block";
}


//BOUTON RETOUR

function retour(event){
    document.getElementById("espace_mot_cle").style.display = "block";
    document.getElementById("espace_lieu_1").style.display = "none";
    document.getElementById("espace_lieu_2").style.display = "none";
    document.getElementById("espace_date").style.display = "none";
    document.getElementById("btn-retour").style.display = "none";
}

// Formulaire recherche par date
// https://developer.mozilla.org/fr/docs/Web/HTML/Element/Input/date


var yearSelect = document.querySelector('#year');
var monthSelect = document.querySelector('#month');
var daySelect = document.querySelector('#day');

populateDays(monthSelect.value);
populateYears();

function populateDays(month) {
  // On supprime les éléments <option> pour l'élément
  // <select> des jours afin de pouvoir ajouter les prochains
  while(daySelect.firstChild){
    daySelect.removeChild(daySelect.firstChild);
  }

  // On crée une variable afin de contenir le nombre
  // de jours à afficher
  var dayNum;

  // 31 ou 30 jours ?
  if(month === 'Janvier' || month === 'Mars' || month === 'Mai' || month === 'Juillet' || month === 'Août' || month === 'Octobre' || month === 'Décembre') {
    dayNum = 31;
  } else if(month === 'Avril' || month === 'Juin' || month === 'Septembre' || month === 'Novembre') {
    dayNum = 30;
  } else {
  // Si le mois est février, on calcule si l'année est bissextile
    var year = yearSelect.value;
    var leap = new Date(year, 1, 29).getMonth() == 1;
    dayNum = leap ? 29 : 28;
  }

  // on ajoute le bon nombre de jours dans autant
  // d'éléments <option> pour l'élément <select>
  // pour la journée
  let no_choice_day = document.createElement('option');
  no_choice_day.textContent = "-- Jour --";
  no_choice_day.value = "";
  daySelect.appendChild(no_choice_day);
  
  for(i = 1; i <= dayNum; i++) {
    var option = document.createElement('option');
    option.textContent = i;
    option.name="day"
    daySelect.appendChild(option);
  }

  // Si le jour précédent a déjà été défini on utilise
  // la valeur de ce jour pour daySelect afin d'éviter de
  // réinitialiser le jour lorsqu'on change l'année
  if(previousDay) {
    daySelect.value = previousDay;

    // Si le jour précédent correspond au dernier jour d'un mois
    // et que le mois sélectionné possède moins de jours (par
    // exemple en février)
    if(daySelect.value === "") {
      daySelect.value = previousDay - 1;
    }

    if(daySelect.value === "") {
      daySelect.value = previousDay - 2;
    }

    if(daySelect.value === "") {
      daySelect.value = previousDay - 3;
    }
  }
}

function populateYears() {
  // On obtient l'année courante
  var date = new Date();
  var year = date.getFullYear();

  // On affiche l'année courante et les 100 années
  // précédentes pour l'élément <select> destiné à
  // stocker l'année
  for(var i = 0; i <= 100; i++) {
    var option = document.createElement('option');
    option.textContent = year-i;
    option.name="year"
    yearSelect.appendChild(option);
  }
}

// Lorsque la valeur du mois ou de l'année est modifiée
// on relance populateDays()
yearSelect.onchange = function() {
  populateDays(monthSelect.value);
}

monthSelect.onchange = function() {
  populateDays(monthSelect.value);
}

// On conserve le jour sélectionné
var previousDay;

// On met à jour la journée utilisé précédemment
// (voir la fin de populateDays() pour voir où
// est utilisée cette valeur)
daySelect.onchange = function() {
  previousDay = daySelect.value;
}