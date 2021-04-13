/**
 * Integration de la carte sur la page
 */

let map;
let latitude=0;
let longitude=0;
let orientation="";
let adresse="";

// quand j'utilise les fonctions à partir d'une recherche, j'obtiens une erreur comme quoi initMap et goToPlace ne sont pas des fonctions
 
function initMap() {
  map = new google.maps.Map(document.getElementById("map"), {
    center: { lat: 43.624227611324216,  lng: 2.268580304110914 },
    zoom: 15,
  });
}

/*function goToPlace(){
    //if (latitude == 0 && longitude == 0){
        adresse= document.getElementById("adresse");
        map = new google.maps.Map(document.getElementById("map"))
        var geocoder = new google.maps.ClientGeocoder();
        geocoder.getLatLng(adresse, function (coord) {
            // Et centrage de la map sur les coordonnées renvoyées par Google :
            map.setCenter(coord, 15);
        });
    //}
}
*/
function goToPlace()
{
    // initialisation de la marque
    map = new google.maps.Map(document.getElementById("map"), {
    center: { lat: 43.624227611324216,  lng: 2.268580304110914 },
    zoom: 15,
  });
  //récup adresse qui correspond au lieu de l'évènement
    adresse = document.getElementById("adresse").attributes.value.textContent;
    
    // De ce que j'ai compris je crée un geocoder qui va permettre de transformer
    // le string du lieu en quelque chose de lisible pour Maps
    var geocoder = new google.maps.Geocoder();
    
    //Transformation de l'adresse obtenue en info utilisable par google Maps
    geocoder.geocode({'address':adresse}, function(results, status) {
      if (status == 'OK') {
          //Si pas d'erreurs, centre la map sur l'adresse utilisable par Google
          //Maps
        map.setCenter(results[0].geometry.location);
        //Création d'un marqueur au point visé.
        var marker = new google.maps.Marker({
            map: map,
            position: results[0].geometry.location
        });
      } else {
        alert('Geocode was not successful for the following reason: ' + status);
      }
    });
}