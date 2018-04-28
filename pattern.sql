DROP TABLE IF EXISTS "Applicability";
CREATE TABLE "Applicability" ("ID" VARCHAR PRIMARY KEY , "CONTENT" VARCHAR, "ID_DESCRIPTION" VARCHAR);
INSERT INTO "Applicability" VALUES('01','Vous voulez utiliser une classe existante, mais dont l''interface ne coïncide pas avec celle escomptée.','01');
INSERT INTO "Applicability" VALUES('02','Vous souhaitez créer une classe réutilisable qui collabore avec des classes sans relations avec elle et encore inconnues, c''est-à-dire avec des classes qui n''auront pas nécessairement des interfaces compatibles.','01');
INSERT INTO "Applicability" VALUES('03','(pour le cas adaptateur d''objet seulement) vous avez besoin d''utiliser plusieurs sous-classes existantes, mais l''adaptation de leur interface par dérivation de chacune d''entre elles est impraticable. Un adaptateur objet peut adapter l''interface de sa classe parente.','01');
DROP TABLE IF EXISTS "Content_Key_Point";
CREATE TABLE "Content_Key_Point" ("ID" VARCHAR PRIMARY KEY , "ID_KEY_POINT" VARCHAR, "VALUE" VARCHAR);
DROP TABLE IF EXISTS "Description";
CREATE TABLE "Description" ("ID" VARCHAR PRIMARY KEY  NOT NULL  UNIQUE , "TYPE" VARCHAR, "INTENT" VARCHAR, "ID_PATTERN" VARCHAR);
INSERT INTO "Description" VALUES('01','Structurel','Convertit l''interface d''une classe en une autre conformément à l''attente du client. L''Adaptateur permet à des classes de collaborer, alors qu''elles n''auraient pas pu le faire du fait d''interfaces incompatibles.','01');
DROP TABLE IF EXISTS "Key_point";
CREATE TABLE "Key_point" ("ID" VARCHAR PRIMARY KEY , "TITLE" VARCHAR, "ID_PATTERN" VARCHAR);
DROP TABLE IF EXISTS "Participant";
CREATE TABLE "Participant" ("ID" VARCHAR PRIMARY KEY , "ID_PATTERN" VARCHAR, "TITLE" VARCHAR, "CONTENT" VARCHAR);
INSERT INTO "Participant" VALUES('01','01','But','Définit une interface spécifique du domaine qu''utilise le client.');
INSERT INTO "Participant" VALUES('02','01','Client','Collabore avec les objets en se conformant à l''interface du But.');
INSERT INTO "Participant" VALUES('03','01','Adapté','Définit une interface existante qui demande adaptation.');
INSERT INTO "Participant" VALUES('04','01','Adaptateur','Adapte l''interface de l''adapté à l''interface But.');
DROP TABLE IF EXISTS "Pattern";
CREATE TABLE "Pattern" ("ID" VARCHAR PRIMARY KEY  NOT NULL  UNIQUE , "TITLE" VARCHAR, "IMAGE" VARCHAR);
INSERT INTO "Pattern" VALUES('01','adapteur.svg','Adaptateur');
INSERT INTO "Pattern" VALUES('02','chaine_responsabilite.svg','Chaîne de responsabilité');
