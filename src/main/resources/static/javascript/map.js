/**
 * Integration de la carte sur la page
 */

var url = "http://localhost:8080/api";

function initMap() {
    map = new google.maps.Map(document.getElementById("map"), {
        center: { lat: 43.624227611324216,  lng: 2.268580304110914 },
        zoom: 15,
    });
}

function goToPlace() {
    initMap();
    $.ajax({
        url : url + "/evenements"
    }).done(function(dataJSON) {
        for (let j=0; j<dataJSON.content.length; j++) {
            adresse = dataJSON.content[j].lieu;
            var geocoder = new google.maps.Geocoder();
            geocoder.geocode({'address':adresse}, function(results, status) {
                if (status == 'OK') {
                    map.setCenter(results[0].geometry.location);
                }
            });
        }
    });
    $.ajax({
        url : url + "/items"
    }).done(function(dataJson) {
        for (let i=0; i<dataJson.content.length; i++) {
            $.ajax({
                url : url+"/items/"+dataJson.content[i].id+"/position"
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
                    //window.location.href="http://localhost:8080/wiki/showSsEventWikiPage?id="+dataJson.content[i].id;
                  infowindow.open(map, marker);
                });
            });
        }
    });
}
