#------------------------------------------------------------
#        Script MySQL.
#------------------------------------------------------------

DROP DATABASE Inscription_Sportive;
CREATE DATABASE Inscription_Sportive;
USE Inscription_Sportive;

#------------------------------------------------------------
# Table: CANDIDAT
#------------------------------------------------------------

CREATE TABLE CANDIDAT(
        Num_Candidat Int NOT NULL ,
        Nom_Candidat Varchar (20) NOT NULL
	,CONSTRAINT CANDIDAT_PK PRIMARY KEY (Num_Candidat)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: COMPETITION
#------------------------------------------------------------

CREATE TABLE COMPETITION(
        Num_Competition  Int NOT NULL ,
        Nom_Competition  Varchar (50) NOT NULL ,
        DateCloture_Comp Date NOT NULL
	,CONSTRAINT COMPETITION_PK PRIMARY KEY (Num_Competition)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: PERSONNE
#------------------------------------------------------------

CREATE TABLE PERSONNE(
        Num_Candidat    Int NOT NULL ,
        Prenom_Personne Varchar (10) NOT NULL ,
        Mail_Personne   Varchar (50) NOT NULL ,
        Nom_Candidat    Varchar (20) NOT NULL
	,CONSTRAINT PERSONNE_PK PRIMARY KEY (Num_Candidat)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: EQUIPE
#------------------------------------------------------------

CREATE TABLE EQUIPE(
        Num_Candidat Int NOT NULL ,
        Nom_Candidat Varchar (20) NOT NULL
	,CONSTRAINT EQUIPE_PK PRIMARY KEY (Num_Candidat)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: CONSTITUER
#------------------------------------------------------------

CREATE TABLE CONSTITUER(
        Num_Candidat    Int NOT NULL ,
        Num_Competition Int NOT NULL
	,CONSTRAINT CONSTITUER_PK PRIMARY KEY (Num_Candidat,Num_Competition)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: COMPOSER
#------------------------------------------------------------

CREATE TABLE COMPOSER(
        Num_Candidat          Int NOT NULL ,
        Num_Candidat_PERSONNE Int NOT NULL
	,CONSTRAINT COMPOSER_PK PRIMARY KEY (Num_Candidat,Num_Candidat_PERSONNE)
)ENGINE=InnoDB;