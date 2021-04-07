/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

document.getElementById("test").addEventListener('click', () => {
    if (getComputedStyle(document.getElementById("2010")).display != "none") {
        document.getElementById("2010").style.display = "none";
    } else {
      document.getElementById("2010").style.display = "block";
    }
})