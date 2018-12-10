Insert Into COMPETITION (Num_Competition, Nom_Competition, DateCloture_Comp)
VALUES
(1, 'Mondial Flechette', '2020/01/01');

Insert Into CANDIDAT (Num_Candidat, Nom_Candidat)
VALUES
(1, 'Boris');

Insert Into CONSTITUER (Num_Candidat, Num_Competition)
VALUES
(1, 1);

Insert Into PERSONNE (Num_Candidat, Prenom_Personne, Mail_Personne, Nom_Candidat)
VALUES
(1, 'Boris', 'leHachoir@mail.com', 'ytreza');

Insert Into EQUIPE (Num_Candidat, Nom_Candidat)
VALUES
(1, 'FlechetteFrance');


Insert Into COMPOSER (Num_Candidat, Num_Candidat_PERSONNE)
VALUES
(1, 1);