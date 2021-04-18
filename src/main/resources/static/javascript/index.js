/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

let dates = document.getElementsByClassName("date");
let listeEvenement = document.getElementsByClassName("liste-evenement");

for(let i = 0; i< dates.length;i++){
    dates[i].addEventListener('click',affichage);
}

function searchIndexOf(lienCible){
    let index = 0;
    if (lienCible.className == "date"){
        for (let i=0; i<dates.length;i++){
                return index;
        }
    }
    }
    
function affichage(event){
    let lienCible = event.target;
    let index = searchIndexOf(lienCible);
    let listeUtile = listeEvenement[index];
    if (getComputedStyle(listeUtile).display != "none") {
        listeUtile.style.display = "none";
    } else {
      listeUtile.style.display = "block";
    }
}