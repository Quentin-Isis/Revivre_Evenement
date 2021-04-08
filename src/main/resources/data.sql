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
    (1, 'Coupe du monde', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.', 'Russie', TO_DATE('2018/06/14', 'yyyy/mm/dd'), TO_DATE('2018/07/15', 'yyyy/mm/dd')),
    (2, '8ème de finale Coupe du monde', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.', 'Russie', TO_DATE('2018/06/30', 'yyyy/mm/dd'), TO_DATE('2018/07/03', 'yyyy/mm/dd')),
    (3, 'quart de finale Coupe du monde', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.', 'Russie', TO_DATE('2018/07/06', 'yyyy/mm/dd'), TO_DATE('2018/07/07', 'yyyy/mm/dd')),
    (4, 'demi finale Coupe du monde', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.', 'Russie', TO_DATE('2018/07/10', 'yyyy/mm/dd'), TO_DATE('2018/07/11', 'yyyy/mm/dd')),
    (5, 'finale Coupe du monde', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.', 'Russie', TO_DATE('2018/07/15', 'yyyy/mm/dd'), TO_DATE('2018/07/15', 'yyyy/mm/dd'));


INSERT INTO Item(id, type_item, date_item, evenement_id) VALUES
    (1, 'image', TO_DATE('2018/06/14', 'yyyy/mm/dd'), 1),
    (2, 'vidéo', TO_DATE('2018/06/15', 'yyyy/mm/dd'), 1),
    (3, 'image', TO_DATE('2018/06/25', 'yyyy/mm/dd'), 1),
    (4, 'tweet', TO_DATE('2018/06/26', 'yyyy/mm/dd'), 1),
    (5, 'image', TO_DATE('2018/06/30', 'yyyy/mm/dd'), 2),
    (6, 'vidéo', TO_DATE('2018/07/02', 'yyyy/mm/dd'), 2),
    (7, 'tweet', TO_DATE('2018/07/03', 'yyyy/mm/dd'), 2),
    (8, 'tweet', TO_DATE('2018/07/06', 'yyyy/mm/dd'), 3),
    (9, 'tweet', TO_DATE('2018/07/06', 'yyyy/mm/dd'), 3),
    (10, 'tweet', TO_DATE('2018/07/07', 'yyyy/mm/dd'), 3),
    (11, 'tweet', TO_DATE('2018/07/07', 'yyyy/mm/dd'), 3),
    (12, 'image', TO_DATE('2018/07/10', 'yyyy/mm/dd'), 4),
    (13, 'vidéo', TO_DATE('2018/07/10', 'yyyy/mm/dd'), 4),
    (14, 'image', TO_DATE('2018/07/10', 'yyyy/mm/dd'), 4),
    (15, 'tweet', TO_DATE('2018/07/11', 'yyyy/mm/dd'), 4),
    (16, 'image', TO_DATE('2018/07/11', 'yyyy/mm/dd'), 4),
    (17, 'vidéo', TO_DATE('2018/07/11', 'yyyy/mm/dd'), 4),
    (18, 'image', TO_DATE('2018/07/15', 'yyyy/mm/dd'), 5),
    (19, 'tweet', TO_DATE('2018/07/15', 'yyyy/mm/dd'), 5),
    (20, 'vidéo', TO_DATE('2018/07/15', 'yyyy/mm/dd'), 5);

INSERT INTO Position(id, longitude, latitude, orientation, item_id) VALUES 
    (1, 144, 51, 'Nord Ouest', 1),
    (2, 48, 75, 'Nord', 1),
    (3, 107, 110, 'Sud', 2),
    (4, 78, 51, 'Est', 3),
    (5, 181, 2, 'Sud Ouest', 4),
    (6, 53, 10, 'Ouest', 4),
    (7, 144, 51, 'Nord Ouest', 5),
    (8, 48, 75, 'Nord', 6),
    (9, 107, 110, 'Sud', 6),
    (10, 178, 51, 'Est', 6),
    (11, 181, 52, 'Sud Ouest', 7),
    (12, 53, 100, 'Ouest', 7),
    (13, 144, 51, 'Nord Ouest', 8),
    (14, 48, 75, 'Nord', 9),
    (15, 107, 110, 'Sud', 9),
    (16, 78, 51, 'Est', 10),
    (17, 181, 2, 'Sud Ouest', 10),
    (18, 53, 10, 'Ouest', 10),
    (19, 144, 66, 'Nord Ouest', 11),
    (20, 48, 75, 'Nord', 11),
    (21, 107, 110, 'Sud', 12),
    (22, 8, 59, 'Est', 13),
    (23, 18, 2, 'Sud', 13),
    (24, 4, 107, 'Ouest', 13),
    (25, 144, 166, 'Ouest', 14),
    (26, 66, 75, 'Nord', 15),
    (27, 107, 184, 'Sud', 15),
    (28, 94, 51, 'Est', 16),
    (29, 181, 16, 'Sud Ouest', 16),
    (30, 147, 10, 'Ouest', 16),
    (31, 144, 90, 'Nord Ouest', 17),
    (32, 12, 75, 'Nord', 17),
    (33, 107, 129, 'Sud', 18),
    (34, 154, 51, 'Est', 18),
    (35, 144, 87, 'Nord Ouest', 19),
    (36, 78, 34, 'Ouest', 20),
    (37, 111, 23, 'Sud', 20),
    (38, 42, 120, 'Est', 20);
    


