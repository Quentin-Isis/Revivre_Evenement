/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

document.getElementById("date").addEventListener('click', () => {
    if (getComputedStyle(document.getElementById("liste-evenement")).display != "none") {
        document.getElementById("liste-evenement").style.display = "none";
    } else {
      document.getElementById("liste-evenement").style.display = "block";
    }
})