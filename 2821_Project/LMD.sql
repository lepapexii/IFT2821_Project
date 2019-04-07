--1. Trouver tous les bénévoles sans voiture et sans équipe:

SELECT BENEVOLE.NOM, BENEVOLE.PRENOM, B.BSV_EQUIPE_ID FROM BENEVOLE
  inner join BSV B on BENEVOLE.BENEVOLE_ID = B.BSV_BENEVOLE_ID
  WHERE BSV_EQUIPE_ID IS NULL;


-- 2. Trouver les bénévoles habitant dans le secteur H5B:

SELECT NOM, PRENOM, CODE_POSTAL
  FROM benevole
    WHERE benevole_secteur_id LIKE (SELECT secteur_id FROM secteur
      WHERE prefix_postal LIKE 'H5B');


-- 3. Trouver les bénévoles ayant donnés un ou des services avec incident:

SELECT benevole.nom, benevole.prenom, incident FROM benevole
  inner join bav ON benevole.benevole_id = bav.bav_benevole_id
    inner join equipe ON bav.bav_equipe_id = equipe.equipe_id
      inner join service ON equipe.equipe_id=service.service_equipe_id
    WHERE incident != 'aucun incident'
UNION(SELECT benevole.nom, benevole.prenom, incident FROM benevole
  inner join bsv ON benevole.benevole_id = bsv.bsv_benevole_id
    inner join equipe ON bsv.bsv_equipe_id = equipe.equipe_id
      inner join service ON equipe.equipe_id=service.service_equipe_id
    WHERE incident != 'aucun incident');


-- 4. Trouver les équipes disponibles pour les services en attente:

SELECT EQUIPE.EQUIPE_ID, EQUIPE.ETAT, S2.SERVICE_ID, S2.TYPE, S2.STATUS, S.PREFIX_POSTAL FROM equipe
    inner join secteur s ON equipe.equipe_secteur_id = s.secteur_id
        inner join service s2 ON s.secteur_id = s2.service_secteur_id
    WHERE equipe.etat=0 AND s2.status=0;


-- 5.	Trouver le nombre de services par type donnés dans les secteurs dont le préfixe postal commence pas H2

SELECT COUNT(SERVICE.TYPE), SERVICE.TYPE FROM SERVICE
    INNER JOIN SECTEUR ON SERVICE.SERVICE_SECTEUR_ID = SECTEUR.SECTEUR_ID
    WHERE SECTEUR.PREFIX_POSTAL LIKE 'H2%'
    GROUP BY(SERVICE.TYPE);


-- 6.	Clients ayant reçu un service du bénévole Poulsen Noah:

SELECT CLIENT.NOM, CLIENT.PRENOM, SERVICE.SERVICE_ID, SERVICE.TYPE, SERVICE."DATE" FROM CLIENT
  INNER JOIN SERVICE ON CLIENT.CLIENT_ID = SERVICE.SERVICE_CLIENT_ID
  INNER JOIN EQUIPE ON SERVICE.SERVICE_EQUIPE_ID = EQUIPE.EQUIPE_ID
  INNER JOIN BSV ON EQUIPE.EQUIPE_ID = BSV.BSV_EQUIPE_ID
  INNER JOIN BENEVOLE ON BENEVOLE.BENEVOLE_ID = BSV.BSV_BENEVOLE_ID
  WHERE(BENEVOLE.NOM = 'Poulsen' AND BENEVOLE.PRENOM = 'Noah')
UNION SELECT CLIENT.NOM, CLIENT.PRENOM, SERVICE.SERVICE_ID, SERVICE.TYPE, SERVICE."DATE" FROM CLIENT
  INNER JOIN SERVICE ON CLIENT.CLIENT_ID = SERVICE.SERVICE_CLIENT_ID
  INNER JOIN EQUIPE ON SERVICE.SERVICE_EQUIPE_ID = EQUIPE.EQUIPE_ID
  INNER JOIN BAV ON EQUIPE.EQUIPE_ID = BAV.BAV_EQUIPE_ID
  INNER JOIN BENEVOLE ON BENEVOLE.BENEVOLE_ID = BAV.BAV_BENEVOLE_ID
  WHERE(BENEVOLE.NOM = 'Poulsen' AND BENEVOLE.PRENOM = 'Noah');


-- 7.	Les clients qui habitent dans une zone où il n'y a pas d'équipe disponible:

SELECT CLIENT.NOM, CLIENT.PRENOM, S.PREFIX_POSTAL FROM CLIENT
  INNER JOIN SECTEUR S on CLIENT.CLIENT_SECTEUR_ID = S.SECTEUR_ID
  INNER JOIN EQUIPE E on S.SECTEUR_ID = E.EQUIPE_SECTEUR_ID
  WHERE E.ETAT=1;


-- 8.	Trouver le nom, prénom et numéro d'équipe de tous les bénévoles avec voiture et habitant le même secteur que Taylor Zachary:

SELECT BENEVOLE.NOM, BENEVOLE.PRENOM, BAV.BAV_EQUIPE_ID, SECTEUR.PREFIX_POSTAL FROM BENEVOLE
    INNER JOIN SECTEUR ON BENEVOLE.BENEVOLE_SECTEUR_ID = SECTEUR.SECTEUR_ID
    INNER JOIN BAV ON BENEVOLE.BENEVOLE_ID = BAV.BAV_BENEVOLE_ID
    WHERE(SECTEUR.SECTEUR_ID = (SELECT BENEVOLE_SECTEUR_ID FROM BENEVOLE
        WHERE(NOM='Taylor' AND PRENOM='Zachary'))
        AND BENEVOLE.NOM != 'Taylor' AND BENEVOLE.PRENOM != 'Zachary');


-- 9.	Trouver les secteurs où il y a des bénévoles mais pas de clients:

SELECT SECTEUR.PREFIX_POSTAL FROM SECTEUR
    INNER JOIN BENEVOLE ON BENEVOLE.BENEVOLE_SECTEUR_ID = SECTEUR.SECTEUR_ID
MINUS SELECT SECTEUR.PREFIX_POSTAL FROM SECTEUR
    INNER JOIN CLIENT ON SECTEUR.SECTEUR_ID = CLIENT.CLIENT_SECTEUR_ID;


-- 10.	Trouver le nombre de services demandés dans le même secteur que la dernière position de l'équipe dont fait partie Thompson Lauren:

SELECT COUNT(SERVICE.SERVICE_SECTEUR_ID), SECTEUR.PREFIX_POSTAL FROM SERVICE
    INNER JOIN SECTEUR ON SERVICE.SERVICE_SECTEUR_ID = SECTEUR.SECTEUR_ID
    WHERE(SECTEUR.SECTEUR_ID = (SELECT EQUIPE.EQUIPE_SECTEUR_ID FROM EQUIPE
        INNER JOIN BSV ON EQUIPE.EQUIPE_ID = BSV.BSV_EQUIPE_ID
        INNER JOIN BENEVOLE ON BSV.BSV_BENEVOLE_ID = BENEVOLE.BENEVOLE_ID
        WHERE(BENEVOLE.NOM = 'Thompson' AND BENEVOLE.PRENOM = 'Lauren')))
    GROUP BY(SECTEUR.PREFIX_POSTAL)
UNION SELECT COUNT(SERVICE.SERVICE_SECTEUR_ID), SECTEUR.PREFIX_POSTAL FROM SERVICE
    INNER JOIN SECTEUR ON SERVICE.SERVICE_SECTEUR_ID = SECTEUR.SECTEUR_ID
    WHERE(SECTEUR.SECTEUR_ID = (SELECT EQUIPE.EQUIPE_SECTEUR_ID FROM EQUIPE
        INNER JOIN BAV ON EQUIPE.EQUIPE_ID = BAV.BAV_EQUIPE_ID
        INNER JOIN BENEVOLE ON BAV.BAV_BENEVOLE_ID = BENEVOLE.BENEVOLE_ID
        WHERE(BENEVOLE.NOM = 'Thompson' AND BENEVOLE.PRENOM = 'Lauren')))
    GROUP BY(SECTEUR.PREFIX_POSTAL);