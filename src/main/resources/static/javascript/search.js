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

// AFFICHAGE DES Résultats

//Recherche par mot clé:
/**
function affichageResultats(event){
    //console.log("click me");
    document.getElementById("resultats").innerHTML = "<h1>Salut</h1>"
}

*/