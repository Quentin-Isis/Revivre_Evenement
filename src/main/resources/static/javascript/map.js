/**
 * Integration de la carte sur la page
 */

var lieu;
var lat;
var lon;
//let orientation="";
//let adresse="";

// quand j'utilise les fonctions à partir d'une recherche, j'obtiens une erreur comme quoi initMap et goToPlace ne sont pas des fonctions

document.getElementById("test").addEventListener("click", initMap);
 
function initMap(event) {
    
    let url = "http://localhost:8080/api/items";
    
    fetch(url, {method:"GET"})
        .then((response) => {
            return response.json();
        })
        .then((data) => {
            console.log(data.content[0].nomItem);
            fetch(url+"/"+data.content[0].id+"/position", {method:"GET"})
            .then((response) => {
                return response.json();
            })
            .then((dataJson) => {
               // console.log(dataJson);
                lat = parseFloat(dataJson.latitude);
                lon = parseFloat(dataJson.longitude);
                const lieu = {lat: lat,  lng: lon};
                const map = new google.maps.Map(document.getElementById("map"), {
                    center: { lat: lat,  lng: lon },
                    zoom: 15,
                });
                const marker = new google.maps.Marker({
                    position: lieu,
                    map: map,
                });
                
            })
        })
}

function goToPlace()
{
    // initialisation de la map
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