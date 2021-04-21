/**
 * Integration de la carte sur la page
 */

var url = "https://lit-plains-26980.herokuapp.com"

function initMap() {
    map = new google.maps.Map(document.getElementById("map"), {
        center: { lat: 43.624227611324216,  lng: 2.268580304110914 },
        zoom: 5,
    });
}

function goToPlace() {
    var adresse = [];
    initMap();
    $.ajax({
        url : url + "/api/evenements"
    }).done(function(dataJSON) {
        $.ajax({
            url : url + "/api/evenements/"+dataJSON.content[0].id+"/items"
        }).done(function() {
            for (let j=0; j<dataJSON.content.length; j++) {
                adresse[j] = dataJSON.content[j].lieu;
            }
            for (let k=0; k<adresse.length; k++) {
                if (adresse[k] == document.getElementById("adresse").attributes.value.textContent) {
                    lieu = adresse[k];
                    const geocoder = new google.maps.Geocoder();
                    geocoder.geocode({'address':lieu}, function(results, status) {
                        if (status == 'OK') {
                            map.setCenter(results[0].geometry.location);
                        }
                    });
                } 
            }
        })
    });
    $.ajax({
        url : url + "/api/items"
    }).done(function(dataJson) {
        for (let i=0; i<dataJson.content.length; i++) {
            $.ajax({
                url : url+"/api/items/"+dataJson.content[i].id+"/position"
            })
            .done(function(json) {
                var marker = new google.maps.Marker({
                        position: {lat: parseFloat(json.latitude), lng: parseFloat(json.longitude)},
                        title: dataJson.content[i].nomItem,
                        map: map
                });
                var infowindow = new google.maps.InfoWindow({
                  content: "<p>"+dataJson.content[i].nomItem+"</p>"
                });
                marker.addListener("click", () => {
                  infowindow.open(map, marker);
                });
            });
        }
    });
}
