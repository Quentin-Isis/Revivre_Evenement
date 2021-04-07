/**
 * Apparition des menus et des formulaires de recherche.
 */

document.getElementById("lieu").addEventListener("click", afficheRechercheLieu);
document.getElementById("date").addEventListener("click", afficheRechercheDate);

function afficheRechercheLieu(event){
    let texteHTML = "<div id=espace class=col-md-6 col-12 >"+
                        "<div class=card>"+
                            "<div class=card-body>"+
                                "<h5 class=card-title> Rechercher un évènement par son lieu"+
                                "</h5>\n\
                                <div class=emplacementRecherche card-text > "+
                                        "<form id=formulaire >"+
                                            "<label for=pays>Pays : </label><input id=recherchePays type=text /><br/>"+
                                            "<label for=ville>Ville : </label><input id=rechercheVille type=text /><br/>"+
                                            "<label for=adresse>Adresse : </label><input id=rechercheAdresse type=text /><br/>\n\
                                             <input type=submit value=Recherche /> \n\
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
                                        "<input type=submit value=Recherche /> \n\
                                    </form> \n\
                                </div> \n\
                            </div> \n\
                        </div> \n\
                    </div>";          
    document.getElementById("rechercher").innerHTML = texteHTML;
}

function afficheRechercheDate(event){
    let texteHTML = "<div id=espace class=col-12 >"+
                        "<div class=card>"+
                            "<div class=card-body>"+
                                "<h5 class=card-title> Rechercher un évènement par sa date"+
                                "</h5>\n\
                                <div class=emplacementRecherche card-text > "+
                                        "<form id=formulaire >"+
                                            "<label for=annee>Année : </label><input id=rechercheAnnée type=text /><br/>"+
                                            "<label for=mois>Mois : </label><input id=rechercheMois type=text /><br/>"+
                                            "<label for=jour>Jour : </label><input id=rechercheJour type=text /><br/>\n\
                                             <input type=submit value=Recherche /> \n\
                                        </form> \n\
                                </div> \n\
                            </div> \n\
                        </div> \n\
                    </div>";          
    document.getElementById("rechercher").innerHTML = texteHTML;
}
