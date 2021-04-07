/**
 * Integration de la carte sur la page
 */

let map;

function initMap() {
  map = new google.maps.Map(document.getElementById("map"), {
    center: { lat: 43.624227611324216,  lng: 2.268580304110914 },
    zoom: 15,
  });
}