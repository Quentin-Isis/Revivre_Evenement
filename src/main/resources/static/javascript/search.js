/**
 * Apparition des menus et des formulaires de recherche.
 */

document.getElementById("lieu").addEventListener("click", afficheRechercheLieu);
document.getElementById("date").addEventListener("click", afficheRechercheDate);
document.getElementById("rechercheMotCle").addEventListener("click",afficheResultats);
document.getElementById("btn-retour").addEventListener("click", retour);
/**let bouttons_rechercher = document.getElementsByClassName("btn-rechercher")

for(let i = 0; i < bouttons_rechercher.length; i++) {
  bouttons_rechercher[i].addEventListener("click", affichageResultats)
}
*/

function afficheRechercheLieu(event){
    /**let texteHTML = "<div id=espace class=col-md-6 col-12 >"+
                        "<div class=card>"+
                            "<div class=card-body>"+
                                "<h5 class=card-title> Rechercher un évènement par son lieu"+
                                "</h5>\n\
                                <div class=emplacementRecherche card-text > "+
                                        "<form id=formulaire >"+
                                            "<label for=pays>Pays : </label><input id=recherchePays type=text /><br/>"+
                                            "<label for=ville>Ville : </label><input id=rechercheVille type=text /><br/>"+
                                            "<label for=adresse>Adresse : </label><input id=rechercheAdresse type=text /><br/>\n\
                                             <input class=\"btn-rechercher\" type=submit value=Recherche /> \n\
                                        </form> \n\
                                </div> \n\
                            </div> \n\
                        </div> \n\
                    </div>"+
                    "<div id=espace1 class=col-md-6 col-12 >"+
                        "<div class=card>"+
                            "<div class=card-body>"+
                                "<h5 class=card-title> Rechercher un évènement par ses coordonnées"+
                                "</h5>\n\
                                <div class=emplacementRecherche card-text >"+
                                    "<form id=formulaire>"+
                                        "<label for=latitude>Latitude : </label><input id=rechercheLatitude type=text /><br/>"+
                                        "<label for=longitude>Longitude : </label><input id=rechercheLongitude type=text /><br/>"+
                                        "<input class=\"btn-rechercher\" type=submit value=Recherche /> \n\
                                    </form> \n\
                                </div> \n\
                            </div> \n\
                        </div> \n\
                    </div>"+
                    "<button id=\"btn-retour\" >Retour</button>";   */       
    
    //document.getElementById("rechercher").innerHTML = texteHTML;
    
    /**let bouttons_rechercher = document.getElementsByClassName("btn-rechercher")

    for(let i = 0; i < bouttons_rechercher.length; i++) {
      bouttons_rechercher[i].addEventListener("click", affichageResultats)
    }*/
    
    document.getElementById("espace_mot_cle").style.display = "none";
    document.getElementById("espace_lieu_1").style.display = "block";
    document.getElementById("espace_lieu_2").style.display = "block";
    document.getElementById("btn-retour").style.display = "block";
}

function afficheRechercheDate(event){
    /**let texteHTML = "<div id=espace class=col-12 >"+
                        "<div class=card>"+
                            "<div class=card-body>"+
                                "<h5 class=card-title> Rechercher un évènement par sa date"+
                                "</h5>\n\
                                <div class=emplacementRecherche card-text > "+
                                        "<form id=formulaire >"+
                                            "<label for=annee>Année : </label><input id=rechercheAnnée type=number min=\"2000\" max=\"2021\"/><br/>"+
                                            "<label for=mois>Mois : </label><select name=\"mois\" id=\"mois\">"+
                                            "<option value=\"\">--Choissiez un mois</option>"+
                                            "<option value=\"01\"> Janvier </option>"+
                                            "<option value=\"02\"> Février </option>"+
                                            "<option value=\"03\"> Mars </option>"+
                                            "<option value=\"04\"> Avril</option>"+
                                            "<option value=\"05\"> Mai </option>"+
                                            "<option value=\"06\"> Juin </option>"+
                                            "<option value=\"07\"> Juillet </option>"+
                                            "<option value=\"08\"> Août </option>"+
                                            "<option value=\"09\"> Septembre </option>"+
                                            "<option value=\"10\"> Octobre </option>"+
                                            "<option value=\"11\"> Novembre </option>"+
                                            "<option value=\"12\"> Décembre </option></select><br/>"+
                                            "<label for=jour>Jour : </label><input id=rechercheJour type=number min=\"1\" max=\"31\"/><br/>\n\
                                             <input class=\"btn-rechercher\" type=submit value=Recherche /> \n\
                                        </form> \n\
                                </div> \n\
                            </div> \n\
                        </div> \n\
                    </div>"+
                    "<button id=\"btn-retour\">Retour</button>";          
    document.getElementById("rechercher").innerHTML = texteHTML;*/
    //document.getElementById("btn-retour").addEventListener("click", retour);
    /**let bouttons_rechercher = document.getElementsByClassName("btn-rechercher")

    for(let i = 0; i < bouttons_rechercher.length; i++) {
      bouttons_rechercher[i].addEventListener("click", affichageResultats)
    }
*/
    document.getElementById("espace_mot_cle").style.display = "none";
    document.getElementById("espace_date").style.display = "block";
    document.getElementById("btn-retour").style.display = "block";
}


//BOUTON RETOUR

function retour(event){
    console.log("click me")
    /**
    let textHTML = "<div id=\"espace\" class=\"col\">"+
                            "<div class=\"card\">"+
                                "<div class=\"card-body\">"+
                                    "<div class=\"row \" >"+
                                        "<div class=\"col-12 col-md-6\">"+
                                            "<h5 class=\"card-title \"> Rechercher un évènement"+
                                            "</h5>"+
                                            "<div >"+
                                                "<form th:action=\"@{/recherches/fctSearch}\" method=\"get\">"+
                                                    "<label for=motCle>Mot-clé : </label>"+
                                                    "<input id=rechercheMot type=text name=\"motCle\" th:value=\"${motCle}\"/><br/>"+
                                                    "<input class=\"btn-rechercher\" type=submit value=\"Recherche par mot-clé\" />"+
                                                "</form>"+
                                            "</div>"+
                                        "</div>"+
                                        "<div class=\"emplacementRecherche card-text col-12 col-md-6\">" +
                                            "<div class=\"row\">"+
                                                "<div class=\"col-6\">"+
                                                    "<input  id=\"lieu\" type=\"button\" value=\"Par lieu\"/>"+
                                                "</div>"+
                                                "<div class=\"col-6\">"+
                                                    "<input id=\"date\" type=\"button\" value=\"Par date\"/>"+
                                                "</div>"+
                                            "</div>"+
                                        "</div>"+
                                    "</div>"+
                                "</div>"+
                            "</div>"+
                        "</div>";
    document.getElementById("rechercher").innerHTML = textHTML;*/
    
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

function afficheResultats(event){
    let affichageResultat=false;
    let footer =document.getElementsByTagName("footer");
    while(affichageResultat === false){
        document.getElementById("resultats").style.display="block"     
        for (let ft of footer){
            ft.style.position = "relative";
            ft.style.bottom = "0px";
            ft.style.alignItems = "center";
        }
        wait(5000);
        affichageResultat=true;
    }
}