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
    (1, 'Coupe du monde', 'La Coupe du monde de football 2018 est la 21e édition de la Coupe du monde de football, une compétition organisée par la FIFA et qui réunit les trente-deux sélections nationales masculines issues de la phase qualificative. Elle se déroule en Russie du 14 juin au 15 juillet 20181 et est remportée par l équipe de France...', 'Russie', TO_DATE('2018/06/14', 'yyyy/mm/dd'), TO_DATE('2018/07/15', 'yyyy/mm/dd'));
INSERT INTO Evenement(id, nom_evenement, description, lieu,  date_debut, date_fin,evenement_principal_id) VALUES    
    (2, '8ème de finale Coupe du monde', 'Dans ce premier huitième de finale, la France affronte l Argentine qui est sortie in extremis de son groupe grâce à une victoire face au Nigeria obtenue dans les derniers instants...', 'Russie', TO_DATE('2018/06/30', 'yyyy/mm/dd'), TO_DATE('2018/07/03', 'yyyy/mm/dd'),1),
    (3, 'quart de finale Coupe du monde', 'La France se qualifie pour la sixième fois de son histoire pour les demi-finales de la Coupe du monde...', 'Russie', TO_DATE('2018/07/06', 'yyyy/mm/dd'), TO_DATE('2018/07/07', 'yyyy/mm/dd'),1),
    (4, 'demi finale Coupe du monde', 'La rencontre est très attendue par l ensemble des observateurs : la Belgique a produit jusque-là un niveau de jeu extrêmement séduisant, tourné vers l attaque (14 buts depuis le début de la compétition) et a réussi le tour de force d éliminer le Brésil, l un des grands favoris de la compétition...', 'Russie', TO_DATE('2018/07/10', 'yyyy/mm/dd'), TO_DATE('2018/07/11', 'yyyy/mm/dd'),1),
    (5, 'finale Coupe du monde', '« Mangés au milieu, transparents dans les duels, pauvres techniquement, incapables de se faire cinq passes », les Bleus souffrent en première période, face à des Croates à plus de 68 % de possession qui dictent le jeu, mais les joueurs de Didier Deschamps parviennent toutefois à atteindre la pause en menant 2-1...', 'Russie', TO_DATE('2018/07/15', 'yyyy/mm/dd'), TO_DATE('2018/07/15', 'yyyy/mm/dd'),1);


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
    (1, 144, 51, 1, 1),
    (2, 48, 75, 50, 1),
    (3, 107, 110, 154, 2),
    (4, 78, 51, 298, 3),
    (5, 181, 2, 306, 4),
    (6, 53, 10, 179, 4),
    (7, 144, 51, 63, 5),
    (8, 48, 75, 123, 6),
    (9, 107, 110, 236, 6),
    (10, 178, 51, 147, 6),
    (11, 181, 52, 89, 7),
    (12, 53, 100, 69, 7),
    (13, 144, 51, 56, 8),
    (14, 48, 75, 354,9),
    (15, 107, 110, 10, 9),
    (16, 78, 51, 126, 10),
    (17, 181, 2, 30, 10),
    (18, 53, 10, 12, 10),
    (19, 144, 66, 158, 11),
    (20, 48, 75, 236, 11),
    (21, 107, 110, 258, 12),
    (22, 8, 59, 247, 13),
    (23, 18, 2, 289, 13),
    (24, 4, 107, 301, 13),
    (25, 144, 166, 129, 14),
    (26, 66, 75, 3, 15),
    (27, 107, 184,158, 15),
    (28, 94, 51, 69, 16),
    (29, 181, 16, 72, 16),
    (30, 147, 10, 103, 16),
    (31, 144, 90, 167, 17),
    (32, 12, 75, 233, 17),
    (33, 107, 129, 11, 18),
    (34, 154, 51, 22, 18),
    (35, 144, 87, 33, 19),
    (36, 78, 34, 44, 20),
    (37, 111, 23, 55, 20),
    (38, 42, 120, 66, 20);
    


