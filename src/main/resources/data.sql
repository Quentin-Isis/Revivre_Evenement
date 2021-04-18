/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Mathieu
 * Created: 8 févr. 2021
 */

INSERT INTO Evenement(id, nom_evenement, description, lieu,  date_debut, date_fin,evenement_principal_id) VALUES
    (1, 'Coupe du monde', 'La Coupe du monde de football 2018 est la 21e édition de la Coupe du monde de football, une compétition organisée par la FIFA et qui réunit les trente-deux sélections nationales masculines issues de la phase qualificative. Elle se déroule en Russie du 14 juin au 15 juillet 20181 et est remportée par l équipe de France...', 'Russie', TO_DATE('2018/06/14', 'yyyy/mm/dd'), TO_DATE('2018/07/15', 'yyyy/mm/dd'),1),    
    
    (12, 'Huitième de finale Coupe du monde', 'Après être sorti des phases de poules, les 8 équipes restantes s', 'Russie', TO_DATE('2018/06/30', 'yyyy/mm/dd'), TO_DATE('2018/07/03', 'yyyy/mm/dd'),1),
    (121, 'Huitième de finale de la Coupe du monde 1', 'Uruguay-Portugal: 2-1', 'Russie, Sochi, Fisht Olympic Stadium', TO_DATE('2018/06/30', 'yyyy/mm/dd'), TO_DATE('2018/06/30', 'yyyy/mm/dd'), 12),
    (122, 'Huitième de finale de la Coupe du monde 2', 'France-Argentine: 2-1', 'Russie, Kazan, Ak Bars Arena', TO_DATE('2018/06/30', 'yyyy/mm/dd'), TO_DATE('2018/06/30', 'yyyy/mm/dd'), 12),
    (123, 'Huitième de finale de la Coupe du monde 3', 'Brésil-Mexique: 2-0', 'Russie, Samara, Solidarnost Arena', TO_DATE('2018/07/02', 'yyyy/mm/dd'), TO_DATE('2018/07/02', 'yyyy/mm/dd'), 12),
    (124, 'Huitième de finale de la Coupe du monde 4', 'Belgique-Japon: 3-2', 'Russie, Rostov-on-Don, Rostov Arena', TO_DATE('2018/07/02', 'yyyy/mm/dd'), TO_DATE('2018/07/02', 'yyyy/mm/dd'), 12),
    (125, 'Huitième de finale de la Coupe du monde 5', 'Espagne-Russie: 1-1 (3-4)', 'Russie, Samara, Solidarnost Arena', TO_DATE('2018/07/01', 'yyyy/mm/dd'), TO_DATE('2018/07/01', 'yyyy/mm/dd'), 12),
    (126, 'Huitième de finale de la Coupe du monde 6', 'Croatie-Danemark: 1-1 (3-2)', 'Russie, Nizhny Novgorod, Nizhny Novgorod Stadium', TO_DATE('2018/07/01', 'yyyy/mm/dd'), TO_DATE('2018/07/01', 'yyyy/mm/dd'), 12),
    (127, 'Huitième de finale de la Coupe du monde 7', 'Suède-Suisse: 1-0', 'Russie, Saint Petersburg, Krestovsky Stadium', TO_DATE('2018/07/03', 'yyyy/mm/dd'), TO_DATE('2018/07/03', 'yyyy/mm/dd'), 12),
    (129, 'Huitième de finale de la Coupe du monde 8', 'Colombie-Angleterre: 1-1 (3-4)', 'Russie, Moscow, Otkritie Arena', TO_DATE('2018/07/03', 'yyyy/mm/dd'), TO_DATE('2018/07/03', 'yyyy/mm/dd'), 12),


    (13, 'Quarts de finale de la Coupe du monde', 'La France se qualifie pour la sixième fois de son histoire pour les demi-finales de la Coupe du monde...', 'Russie', TO_DATE('2018/07/06', 'yyyy/mm/dd'), TO_DATE('2018/07/07', 'yyyy/mm/dd'),1),
    (131, 'Quart de finale 1' , ' Uruguay-France: 0-2', 'Russie, Nizhny Novgorod, Nizhny Novgorod Stadium', TO_DATE('2018/07/06', 'yyyy/mm/dd'), TO_DATE('2018/07/06', 'yyyy/mm/dd'), 13),
    (132, 'Quart de finale 2',' Brésil-Belgique: 1-2', 'Russie, Kazan, Ak Bars Arena', TO_DATE('2018/07/06', 'yyyy/mm/dd'), TO_DATE('2018/07/06', 'yyyy/mm/dd'), 13), 
    (133, 'Quart de finale 3',' Russie-Croatie: 2-2 (3-4)', 'Russie, Sochi, Fisht Olympic Stadium', TO_DATE('2018/07/07', 'yyyy/mm/dd'), TO_DATE('2018/07/07', 'yyyy/mm/dd'), 13),
    (134, 'Quart de finale 4', 'Suède-Angleterre: 0-2', 'Russie, Samara, Solidarnost Arena', TO_DATE('2018/07/07', 'yyyy/mm/dd'), TO_DATE('2018/07/07', 'yyyy/mm/dd'), 13),
    
    (14, 'Demi-finales de la Coupe du monde', 'La rencontre est très attendue par l ensemble des observateurs : la Belgique a produit jusque-là un niveau de jeu extrêmement séduisant, tourné vers l attaque (14 buts depuis le début de la compétition) et a réussi le tour de force d éliminer le Brésil, l un des grands favoris de la compétition...', 'Russie', TO_DATE('2018/07/10', 'yyyy/mm/dd'), TO_DATE('2018/07/11', 'yyyy/mm/dd'),1),
    (141, 'Demi-finale 1', 'France-Belgique: 1-0', 'Russie, Saint Petersburg, Krestovsky Stadium', TO_DATE('2018/07/10', 'yyyy/mm/dd'), TO_DATE('2018/07/10', 'yyyy/mm/dd'), 14),
    (142, 'Demi-finale 2', 'Croatie-Angleterre: 2-1', 'Russie, Moscow, Luzhniki Stadium', TO_DATE('2018/07/11', 'yyyy/mm/dd'), TO_DATE('2018/07/11', 'yyyy/mm/dd'), 14),
    
    (15, 'Finale Coupe du monde', '« Mangés au milieu, transparents dans les duels, pauvres techniquement, incapables de se faire cinq passes », les Bleus souffrent en première période, face à des Croates à plus de 68 % de possession qui dictent le jeu, mais les joueurs de Didier Deschamps parviennent toutefois à atteindre la pause en menant 2-1...', 'Russie', TO_DATE('2018/07/15', 'yyyy/mm/dd'), TO_DATE('2018/07/15', 'yyyy/mm/dd'),1);
    
INSERT INTO Position(id, longitude, latitude, orientation) VALUES 
    (1, 144, 51, 1),
    (2, 48, 75, 50),
    (3, 107, 110, 154),
    (4, 78, 51, 298),
    (5, 181, 2, 306),
    (6, 53, 10, 179),
    (7, 144, 51, 63),
    (8, 48, 75, 123),
    (9, 107, 110, 236),
    (10, 178, 51, 147),
    (11, 181, 52, 89),
    (12, 53, 100, 69),
    (13, 144, 51, 56),
    (14, 48, 75, 354),
    (15, 107, 110, 10),
    (16, 78, 51, 126),
    (17, 181, 2, 30),
    (18, 53, 10, 12),
    (19, 144, 66, 158),
    (20, 48, 75, 236),
    (21, 107, 110, 258),
    (22, 8, 59, 247),
    (23, 18, 2, 289),
    (24, 4, 107, 301),
    (25, 144, 166, 129),
    (26, 66, 75, 3),
    (27, 107, 184,158),
    (28, 94, 51, 69),
    (29, 181, 16, 72),
    (30, 147, 10, 103),
    (31, 144, 90, 167),
    (32, 12, 75, 233),
    (33, 107, 129, 11),
    (34, 154, 51, 22),
    (35, 144, 87, 33),
    (36, 78, 34, 44),
    (37, 111, 23, 55),
    (38, 42, 120, 66);
    

INSERT INTO Item(id, type_item, date_item, evenement_id, position_id) VALUES
    (1, 'image', TO_DATE('2018/06/14', 'yyyy/mm/dd'), 1, 1),
    (2, 'vidéo', TO_DATE('2018/06/15', 'yyyy/mm/dd'), 1,2),
    (3, 'image', TO_DATE('2018/06/25', 'yyyy/mm/dd'), 1,3),
    (4, 'tweet', TO_DATE('2018/06/26', 'yyyy/mm/dd'), 1,4),
    (5, 'image', TO_DATE('2018/06/30', 'yyyy/mm/dd'), 12,5),
    (6, 'vidéo', TO_DATE('2018/07/02', 'yyyy/mm/dd'), 12,6),
    (7, 'tweet', TO_DATE('2018/07/03', 'yyyy/mm/dd'), 12,7),
    (8, 'tweet', TO_DATE('2018/07/06', 'yyyy/mm/dd'), 13,8),
    (9, 'tweet', TO_DATE('2018/07/06', 'yyyy/mm/dd'), 13,9),
    (10, 'tweet', TO_DATE('2018/07/07', 'yyyy/mm/dd'), 13,10),
    (11, 'tweet', TO_DATE('2018/07/07', 'yyyy/mm/dd'), 13,11),
    (12, 'image', TO_DATE('2018/07/10', 'yyyy/mm/dd'), 14,12),
    (13, 'vidéo', TO_DATE('2018/07/10', 'yyyy/mm/dd'), 14,13),
    (14, 'image', TO_DATE('2018/07/10', 'yyyy/mm/dd'), 14,14),
    (15, 'tweet', TO_DATE('2018/07/11', 'yyyy/mm/dd'), 14,15),
    (16, 'image', TO_DATE('2018/07/11', 'yyyy/mm/dd'), 14,16),
    (17, 'vidéo', TO_DATE('2018/07/11', 'yyyy/mm/dd'), 14,17),
    (18, 'image', TO_DATE('2018/07/15', 'yyyy/mm/dd'), 15,18),
    (19, 'tweet', TO_DATE('2018/07/15', 'yyyy/mm/dd'), 15,19),
    (20, 'vidéo', TO_DATE('2018/07/15', 'yyyy/mm/dd'), 15,20);




