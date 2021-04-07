/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Mathieu
 * Created: 8 févr. 2021
 */

INSERT INTO Evenement(id, nom_evenement, description, lieu,  date_debut, date_fin) VALUES
    (1, 'Coupe du monde', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.', 'Russie', TO_DATE('2018/06/14', 'yyyy/mm/dd'), TO_DATE('2018/07/15', 'yyyy/mm/dd'));

INSERT INTO Item(id, type_item, date_item, evenement_id) VALUES
    (1, 'image', TO_DATE('2018/06/14', 'yyyy/mm/dd'), 1),
    (2, 'vidéo', TO_DATE('2018/06/15', 'yyyy/mm/dd'), 1),
    (3, 'image', TO_DATE('2018/06/25', 'yyyy/mm/dd'), 1),
    (4, 'tweet', TO_DATE('2018/06/26', 'yyyy/mm/dd'), 1);

INSERT INTO Position(id, longitude, latitude, orientation, item_id) VALUES 
    (1, 144, 51, 'Nord Ouest', 1),
    (2, 48, 75, 'Nord', 1),
    (3, 107, 110, 'Sud', 2),
    (4, 78, 51, 'Est', 3),
    (5, 181, 2, 'Sud Ouest', 4),
    (6, 53, 10, 'Ouest', 4);


