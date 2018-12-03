     Script MySQL.
#------------------------------------------------------------

DROP DATABASE Inscription_Sportive;
CREATE DATABASE Inscription_Sportive;
USE Inscription_Sportive;

DROP TABLE Candidat;
DROP TABLE Competition;
DROP TABLE Personne;
DROP TABLE Equipe;
DROP TABLE Etre_inscrit;
DROP TABLE Appartenir;

#------------------------------------------------------------
# Table: Candidat
#------------------------------------------------------------

CREATE TABLE Candidat(
        idCandidat  Int NOT NULL ,
        nomCandidat Varchar (50) NOT NULL
	,CONSTRAINT Candidat_PK PRIMARY KEY (idCandidat)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Competition
#------------------------------------------------------------

CREATE TABLE Competition(
        idCompetition  Int  Auto_increment  NOT NULL ,
        nomCompetition Varchar (50) NOT NULL ,
        dateClo        Date NOT NULL ,
        enEquipe       Bool NOT NULL
	,CONSTRAINT Competition_PK PRIMARY KEY (idCompetition)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Personne
#------------------------------------------------------------

CREATE TABLE Personne(
        idCandidat  Int NOT NULL ,
        prenom      Varchar (50) NOT NULL ,
        mail        Varchar (50) NOT NULL ,
        nomCandidat Varchar (50) NOT NULL
	,CONSTRAINT Personne_PK PRIMARY KEY (idCandidat)

	,CONSTRAINT Personne_Candidat_FK FOREIGN KEY (idCandidat) REFERENCES Candidat(idCandidat)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Equipe
#------------------------------------------------------------

CREATE TABLE Equipe(
        idCandidat  Int NOT NULL ,
        nomCandidat Varchar (50) NOT NULL
	,CONSTRAINT Equipe_PK PRIMARY KEY (idCandidat)

	,CONSTRAINT Equipe_Candidat_FK FOREIGN KEY (idCandidat) REFERENCES Candidat(idCandidat)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Etre Inscrit
#------------------------------------------------------------

CREATE TABLE Etre_Inscrit(
        idCompetition Int NOT NULL ,
        idCandidat    Int NOT NULL
	,CONSTRAINT Etre_Inscrit_PK PRIMARY KEY (idCompetition,idCandidat)

	,CONSTRAINT Etre_Inscrit_Competition_FK FOREIGN KEY (idCompetition) REFERENCES Competition(idCompetition)
	,CONSTRAINT Etre_Inscrit_Candidat0_FK FOREIGN KEY (idCandidat) REFERENCES Candidat(idCandidat)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Appartenir
#------------------------------------------------------------

CREATE TABLE Appartenir(
        idCandidat          Int NOT NULL ,
        idCandidat_Personne Int NOT NULL
	,CONSTRAINT Appartenir_PK PRIMARY KEY (idCandidat,idCandidat_Personne)

	,CONSTRAINT Appartenir_Equipe_FK FOREIGN KEY (idCandidat) REFERENCES Equipe(idCandidat)
	,CONSTRAINT Appartenir_Personne0_FK FOREIGN KEY (idCandidat_Personne) REFERENCES Personne(idCandidat)
)ENGINE=InnoDB;

