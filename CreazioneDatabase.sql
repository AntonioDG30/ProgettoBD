DROP DATABASE IF EXISTS NegozioVideogiochi;
CREATE DATABASE NegozioVideogiochi;
USE NegozioVideogiochi;

CREATE TABLE Fornitore
(
    CodFornitore varchar(20) NOT NULL,
    Nome varchar(50) NOT NULL,
    Via varchar(20) NOT NULL,
    Civico varchar(4) NOT NULL,
    Città varchar(50) NOT NULL,
    Telefono varchar(20) NOT NULL,
    PRIMARY KEY(CodFornitore)
);

CREATE TABLE Prodotto
(
    CodSeriale varchar(20) NOT NULL,
    Nome varchar(50) NOT NULL,
    Disponibilità int NOT NULL,
    Prezzo numeric(10,2) NOT NULL,
    DataUscita date NOT NULL,
    CodFornitore varchar(20),
    PRIMARY KEY(CodSeriale),
    FOREIGN KEY(CodFornitore) REFERENCES Fornitore(CodFornitore) ON UPDATE cascade ON DELETE cascade
);

CREATE TABLE Hardware
(
    CodSeriale varchar(20) NOT NULL,
    Tipologia varchar(50) NOT NULL,
    Edizione varchar(50) NOT NULL,
    FOREIGN KEY(CodSeriale) REFERENCES Prodotto(CodSeriale) ON UPDATE cascade ON DELETE cascade
);

CREATE TABLE Videogioco
(
    CodSeriale varchar(20) NOT NULL,
    PEGI int NOT NULL,
    Descrizione text NOT NULL,
    Categoria varchar(50) NOT NULL,
    FOREIGN KEY(CodSeriale) REFERENCES Prodotto(CodSeriale) ON UPDATE cascade ON DELETE cascade
);

CREATE TABLE Piattaforma
(
    Nome varchar(50) NOT NULL,
    PRIMARY KEY(Nome)
);

CREATE TABLE SoftwareHouse
(
    PartitaIVA varchar(12) NOT NULL,
    Nome varchar(50) NOT NULL,
    Email varchar(50) NOT NULL,
    PRIMARY KEY(PartitaIVA)
);

CREATE TABLE Cliente
(
    CodiceFiscale char(16) NOT NULL,
    Nome varchar(50) NOT NULL,
    Cognome varchar(50) NOT NULL,
    Via varchar(20) NOT NULL,
    Civico varchar(4) NOT NULL,
    Città varchar(50) NOT NULL,
    Telefono varchar(20) NOT NULL,
    NumProdottiAcquistati int NOT NULL,
    PRIMARY KEY(CodiceFiscale)
);

CREATE TABLE Acquisto
(
    CodAcquisto int UNSIGNED NOT NULL AUTO_INCREMENT,
    PercentualeSconto int,
    DataAcquisto date NOT NULL,
    CodSeriale varchar(20) NOT NULL,
    CodiceFiscale char(16) NOT NULL,
    PRIMARY KEY(CodAcquisto),
    FOREIGN KEY(CodSeriale) REFERENCES Prodotto(CodSeriale) ON UPDATE cascade ON DELETE cascade,
    FOREIGN KEY(CodiceFiscale) REFERENCES Cliente(CodiceFiscale) ON UPDATE cascade ON DELETE cascade
);

CREATE TABLE TesseraFedeltà
(
    CodiceFiscale char(16) NOT NULL,
    Scadenza date NOT NULL,
    PuntiOttenuti int,
    PRIMARY KEY(CodiceFiscale, Scadenza),
    FOREIGN KEY(CodiceFiscale) REFERENCES Cliente(CodiceFiscale) ON UPDATE cascade ON DELETE cascade    
);

CREATE TABLE Dipendente
(
    CodDipendente varchar(20) NOT NULL,
    Nome varchar(50) NOT NULL,
    Cognome varchar(50) NOT NULL,
    Stipendio float NOT NULL,
    Telefono varchar(20) NOT NULL,    
    PRIMARY KEY(CodDipendente)
);

CREATE TABLE Modifica
(
    CodModifica int UNSIGNED NOT NULL AUTO_INCREMENT,
    DataModifica date NOT NULL,
    CodSeriale varchar(20) NOT NULL,
    CodDipendente varchar(20) NOT NULL,
    PRIMARY KEY(CodModifica),
    FOREIGN KEY(CodSeriale) REFERENCES Prodotto(CodSeriale) ON UPDATE cascade ON DELETE cascade,
    FOREIGN KEY(CodDipendente) REFERENCES Dipendente(CodDipendente) ON UPDATE cascade ON DELETE cascade
); 

CREATE TABLE Sviluppa
(
    CodSeriale varchar(20) NOT NULL,
    PartitaIVA varchar(12) NOT NULL,
    PRIMARY KEY(CodSeriale, PartitaIVA),
    FOREIGN KEY(CodSeriale) REFERENCES Videogioco(CodSeriale) ON UPDATE cascade ON DELETE cascade,
    FOREIGN KEY(PartitaIVA) REFERENCES SoftwareHouse(PartitaIVA) ON UPDATE cascade ON DELETE cascade
);

CREATE TABLE DisponibilePer
(
    CodSeriale varchar(20) NOT NULL,
    Nome varchar(50) NOT NULL,
    PRIMARY KEY(CodSeriale, Nome),
    FOREIGN KEY(CodSeriale) REFERENCES Videogioco(CodSeriale) ON UPDATE cascade ON DELETE cascade,
    FOREIGN KEY(Nome) REFERENCES Piattaforma(Nome) ON UPDATE cascade ON DELETE cascade
);


INSERT INTO Fornitore(CodFornitore, Nome, Via, Civico, Città, Telefono)
VALUES ('05AB19', 'Fast Deliveries', 'Roma', '6', 'Milano', '+393470245691');
INSERT INTO Fornitore(CodFornitore, Nome, Via, Civico, Città, Telefono)
VALUES ('07DR21RE', 'Consegne Italia', 'Firenze', '20', 'Napoli', '+393480214658');
INSERT INTO Fornitore(CodFornitore, Nome, Via, Civico, Città, Telefono)
VALUES ('123NU56', 'Videogame Deliveries', 'Giuseppe Verdi', '1', 'Roma', '+393753684975');
INSERT INTO Fornitore(CodFornitore, Nome, Via, Civico, Città, Telefono)
VALUES ('00DASQ72', 'GoToGo', 'Moscati', '61', 'Bari', '+393247895623');
INSERT INTO Fornitore(CodFornitore, Nome, Via, Civico, Città, Telefono)
VALUES ('A07BR2RG1', 'Porta A Porta', 'Genova', '7', 'Torino', '+3934723458602');


INSERT INTO Prodotto(CodSeriale, Nome, Disponibilità, Prezzo, DataUscita, CodFornitore)
VALUES ('A564DGR4BU54','Grand Theft Auto 5', '7', '14.99', '2013-09-17', '123NU56');
INSERT INTO Prodotto(CodSeriale, Nome, Disponibilità, Prezzo, DataUscita, CodFornitore)
VALUES ('CD9D8G352NC5JA','FIFA 23', '100', '29.99', '2022-09-22', '05AB19');
INSERT INTO Prodotto(CodSeriale, Nome, Disponibilità, Prezzo, DataUscita, CodFornitore)
VALUES ('CUR3N43VS23YA','The Last Of Us Part 2', '10', '19.99', '2020-06-19', 'A07BR2RG1');
INSERT INTO Prodotto(CodSeriale, Nome, Disponibilità, Prezzo, DataUscita, CodFornitore)
VALUES ('CGRD2SDW2FYR','The Last Of Us Remastered', '3', '9.99', '2013-06-14', '00DASQ72');
INSERT INTO Prodotto(CodSeriale, Nome, Disponibilità, Prezzo, DataUscita, CodFornitore)
VALUES ('45HY33DD2SE24','Cyberpunk 2077', '10', '34.99', '2020-12-10', '07DR21RE');
INSERT INTO Prodotto(CodSeriale, Nome, Disponibilità, Prezzo, DataUscita, CodFornitore)
VALUES ('BYD32A5KYNC4E3','Mafia 3', '2', '14.99', '2016-10-07', '123NU56');
INSERT INTO Prodotto(CodSeriale, Nome, Disponibilità, Prezzo, DataUscita, CodFornitore)
VALUES ('ACX5Z6DGXMUJR454','Playstation 5', '10', '550.00', '2020-11-12', 'A07BR2RG1');
INSERT INTO Prodotto(CodSeriale, Nome, Disponibilità, Prezzo, DataUscita, CodFornitore)
VALUES ('FBDTRNCF54','XBOX Series X', '5', '499.00', '2020-11-10', '07DR21RE');
INSERT INTO Prodotto(CodSeriale, Nome, Disponibilità, Prezzo, DataUscita, CodFornitore)
VALUES ('DVCH5342SDR','DualeSense', '25', '55.00', '2020-11-12', 'A07BR2RG1');
INSERT INTO Prodotto(CodSeriale, Nome, Disponibilità, Prezzo, DataUscita, CodFornitore)
VALUES ('AMREDHZOCDS','Nvidia GeForce RTX 4080', '25', '1555.00', '2022-11-16', '00DASQ72');
INSERT INTO Prodotto(CodSeriale, Nome, Disponibilità, Prezzo, DataUscita, CodFornitore)
VALUES ('GHYFVRSTEV','XBOX ONE', '0', '199.00', '2013-11-22', '07DR21RE');

INSERT INTO Hardware(CodSeriale, Tipologia, Edizione)
VALUES ('ACX5Z6DGXMUJR454', 'Console','CFI100XB');
INSERT INTO Hardware(CodSeriale, Tipologia, Edizione)
VALUES ('FBDTRNCF54', 'Console','2020');
INSERT INTO Hardware(CodSeriale, Tipologia, Edizione)
VALUES ('DVCH5342SDR', 'Controller','v1');
INSERT INTO Hardware(CodSeriale, Tipologia, Edizione)
VALUES ('AMREDHZOCDS', 'Scheda Video','16GB');
INSERT INTO Hardware(CodSeriale, Tipologia, Edizione)
VALUES ('GHYFVRSTEV', 'Console','2013');


INSERT INTO Videogioco(CodSeriale, PEGI, Descrizione, Categoria)
VALUES ('A564DGR4BU54', '18', 'Si tratta del quinto capitolo della saga Grand Theft Auto, racconta la storia di tre diversi personaggi molto diversi tra loro la cui vita si intreccia ai fini di diventare ricchi sfondati. Il gioco è ambientato all interno dello Stato immaginario di San Andreas e offre al giocatore la possibilità di muoversi liberamente nella città immaginaria di Los Santos. Il gioco possiede anche una modalità online', 'Avventura Dinamica');
INSERT INTO Videogioco(CodSeriale, PEGI, Descrizione, Categoria)
VALUES ('CD9D8G352NC5JA', '3', 'il nuovo capitolo della saga FIFA, il bellissimo simulatore videoludico di calcio. FIFA permette di sfidarti ogni giorni in gare sempre più difficili, puoi sfidare la CPU o un tuo amico, sia offline che online', 'Sport');
INSERT INTO Videogioco(CodSeriale, PEGI, Descrizione, Categoria)
VALUES ('CUR3N43VS23YA', '18', 'Secondo capitolo della saga di The Last Of Us, esso riprende le vicende di Joel ed Alli nel mondo apocalittico causato dall epidemia generata dal fungo Cordyceps', 'Survivol Horror');
INSERT INTO Videogioco(CodSeriale, PEGI, Descrizione, Categoria)
VALUES ('CGRD2SDW2FYR', '18', 'Remastered del primo capitolo della saga di The Last Of Us, esso racconta le vicende di Joel ed Alli nel mondo apocalittico causato dall epidemia generata dal fungo Cordyceps', 'Survivol Horror');
INSERT INTO Videogioco(CodSeriale, PEGI, Descrizione, Categoria)
VALUES ('45HY33DD2SE24', '18', 'Ambientato nel 2077 esso racconta la storia di V un cittadino di Night City che è una magalopoli comandata dalle corporazioni. V dovrà cercare di sopravvivere in questa città facendo lavori non sempre legali, in uno di questi nascerà un grave intoppo', 'Avventura Dinamica');
INSERT INTO Videogioco(CodSeriale, PEGI, Descrizione, Categoria)
VALUES ('BYD32A5KYNC4E3', '18', 'Terzo capitolo della saga MAFIA, esso racconta la storia di Lincoln Clay che prova a farsi un nome nella MAFIA di New Bordeaux', 'Avventura Dinamica');


INSERT INTO Piattaforma(Nome)
VALUES ('PlayStation 5');
INSERT INTO Piattaforma(Nome)
VALUES ('PlayStation 4');
INSERT INTO Piattaforma(Nome)
VALUES ('XBOX Series X');
INSERT INTO Piattaforma(Nome)
VALUES ('XBOX Series S');
INSERT INTO Piattaforma(Nome)
VALUES ('PC');


INSERT INTO SoftwareHouse(PartitaIVA, Nome, Email)
VALUES ('PLOPTTC00011','CD Projekt','biz@cdprojektred.com');
INSERT INTO SoftwareHouse(PartitaIVA, Nome, Email)
VALUES ('US8740541094','Take Two Interactive','Copyright@take2games.com');
INSERT INTO SoftwareHouse(PartitaIVA, Nome, Email)
VALUES ('JP3435000009','Naughty Dog','ndi-dog@naughtydog.com');
INSERT INTO SoftwareHouse(PartitaIVA, Nome, Email)
VALUES ('US2855121099','Eletronic Arts','lets_talk@ea.com');


INSERT INTO Cliente(CodiceFiscale, Nome, Cognome, Via, Civico, Città, Telefono, NumProdottiAcquistati)
VALUES ('TCCCRL02M12H501L','Carlo','Tucci','Giovanni Falcone','3','Roma','+393342518794','2');
INSERT INTO Cliente(CodiceFiscale, Nome, Cognome, Via, Civico, Città, Telefono, NumProdottiAcquistati)
VALUES ('RSSLCU96M27L219U','Luca','Rossi','Clanio','24','Torino','+393478951025','1');
INSERT INTO Cliente(CodiceFiscale, Nome, Cognome, Via, Civico, Città, Telefono, NumProdottiAcquistati)
VALUES ('LTRSMN88S49L378G','Simona','Altieri','Giovanni Pascoli','7','Trento','+393658742985','2');
INSERT INTO Cliente(CodiceFiscale, Nome, Cognome, Via, Civico, Città, Telefono, NumProdottiAcquistati)
VALUES ('SPSGNR01R10F839M','Gennaro','Esposito','Leonardo Da Vinci','1','Napoli','+393124876592','1');
INSERT INTO Cliente(CodiceFiscale, Nome, Cognome, Via, Civico, Città, Telefono, NumProdottiAcquistati)
VALUES ('RSSLNR97M27H703A','Leornardo','Russo','Irno','10','Salerno','+393359874526','3');


INSERT INTO Acquisto(PercentualeSconto, DataAcquisto, CodiceFiscale, CodSeriale)
VALUES ( '0', '2022-12-12', 'TCCCRL02M12H501L', 'A564DGR4BU54');
INSERT INTO Acquisto(PercentualeSconto, DataAcquisto, CodiceFiscale, CodSeriale)
VALUES ('0', '2020-11-10', 'RSSLCU96M27L219U', 'FBDTRNCF54');
INSERT INTO Acquisto(PercentualeSconto, DataAcquisto, CodiceFiscale, CodSeriale)
VALUES ('0', '2022-10-07', 'SPSGNR01R10F839M', '45HY33DD2SE24');
INSERT INTO Acquisto(PercentualeSconto, DataAcquisto, CodiceFiscale, CodSeriale)
VALUES ('0', '2022-11-10', 'LTRSMN88S49L378G', 'CD9D8G352NC5JA');
INSERT INTO Acquisto(PercentualeSconto, DataAcquisto, CodiceFiscale, CodSeriale)
VALUES ('0', '2022-11-16', 'RSSLNR97M27H703A', 'AMREDHZOCDS');
INSERT INTO Acquisto(PercentualeSconto, DataAcquisto, CodiceFiscale, CodSeriale)
VALUES ('0', '2022-04-21', 'LTRSMN88S49L378G', 'ACX5Z6DGXMUJR454');
INSERT INTO Acquisto(PercentualeSconto, DataAcquisto, CodiceFiscale, CodSeriale)
VALUES ('0', '2022-02-19', 'RSSLNR97M27H703A', 'CUR3N43VS23YA');
INSERT INTO Acquisto(PercentualeSconto, DataAcquisto, CodiceFiscale, CodSeriale)
VALUES ('0', '2021-01-15', 'RSSLNR97M27H703A', 'CGRD2SDW2FYR');
INSERT INTO Acquisto(PercentualeSconto, DataAcquisto, CodiceFiscale, CodSeriale)
VALUES ('0', '2019-02-10', 'TCCCRL02M12H501L', 'CGRD2SDW2FYR');


INSERT INTO TesseraFedeltà(CodiceFiscale, Scadenza, PuntiOttenuti)
VALUES ('RSSLNR97M27H703A','2024-03-19','11600');
INSERT INTO TesseraFedeltà(CodiceFiscale, Scadenza, PuntiOttenuti)
VALUES ('LTRSMN88S49L378G','2023-07-21','750');


INSERT INTO Dipendente(CodDipendente, Nome, Cognome, Stipendio, Telefono)
VALUES ('08GY67','Pasquale','Ricciardi','1023.00','+393345869127');
INSERT INTO Dipendente(CodDipendente, Nome, Cognome, Stipendio, Telefono)
VALUES ('34DT71','Andrea','Franzese','1784.00','+393384129780');
INSERT INTO Dipendente(CodDipendente, Nome, Cognome, Stipendio, Telefono)
VALUES ('05DC00','Antonio','Alberico','1945.40','+393335598741');
INSERT INTO Dipendente(CodDipendente, Nome, Cognome, Stipendio, Telefono)
VALUES ('09FF17','Lorenzo','Ciocia','1200.00','+393259784191');
INSERT INTO Dipendente(CodDipendente, Nome, Cognome, Stipendio, Telefono)
VALUES ('27TG65','Federica','Aversano','1702.00','+39339541286');


INSERT INTO Modifica(DataModifica, CodDipendente, CodSeriale)
VALUES ('2022-03-12', '08GY67','CUR3N43VS23YA');
INSERT INTO Modifica(DataModifica, CodDipendente, CodSeriale)
VALUES ('2022-12-13', '34DT71','A564DGR4BU54');
INSERT INTO Modifica(DataModifica, CodDipendente, CodSeriale)
VALUES ('2014-01-12', '08GY67','CGRD2SDW2FYR');
INSERT INTO Modifica(DataModifica, CodDipendente, CodSeriale)
VALUES ('2023-01-02', '27TG65','AMREDHZOCDS');
INSERT INTO Modifica(DataModifica, CodDipendente, CodSeriale)
VALUES ('2022-09-22', '34DT71','CD9D8G352NC5JA');
INSERT INTO Modifica(DataModifica, CodDipendente, CodSeriale)
VALUES ('2020-11-13', '09FF17','FBDTRNCF54');
INSERT INTO Modifica(DataModifica, CodDipendente, CodSeriale)
VALUES ('2020-12-15', '27TG65','ACX5Z6DGXMUJR454');
INSERT INTO Modifica(DataModifica, CodDipendente, CodSeriale)
VALUES ('2016-12-05', '05DC00','BYD32A5KYNC4E3');
INSERT INTO Modifica(DataModifica, CodDipendente, CodSeriale)
VALUES ('2020-12-10', '05DC00','45HY33DD2SE24');
INSERT INTO Modifica(DataModifica, CodDipendente, CodSeriale)
VALUES ('2020-12-15', '09FF17','DVCH5342SDR');


INSERT INTO Sviluppa(CodSeriale, PartitaIVA)
VALUES ('A564DGR4BU54','US8740541094');
INSERT INTO Sviluppa(CodSeriale, PartitaIVA)
VALUES ('CD9D8G352NC5JA','US2855121099');
INSERT INTO Sviluppa(CodSeriale, PartitaIVA)
VALUES ('CUR3N43VS23YA','JP3435000009');
INSERT INTO Sviluppa(CodSeriale, PartitaIVA)
VALUES ('CGRD2SDW2FYR','JP3435000009');
INSERT INTO Sviluppa(CodSeriale, PartitaIVA)
VALUES ('45HY33DD2SE24','PLOPTTC00011');
INSERT INTO Sviluppa(CodSeriale, PartitaIVA)
VALUES ('BYD32A5KYNC4E3','US8740541094');


INSERT INTO DisponibilePer (CodSeriale, Nome)
VALUES ('A564DGR4BU54','PlayStation 5');
INSERT INTO DisponibilePer (CodSeriale, Nome)
VALUES ('A564DGR4BU54','PlayStation 4');
INSERT INTO DisponibilePer (CodSeriale, Nome)
VALUES ('A564DGR4BU54','XBOX Series X');
INSERT INTO DisponibilePer (CodSeriale, Nome)
VALUES ('A564DGR4BU54','XBOX Series S');
INSERT INTO DisponibilePer (CodSeriale, Nome)
VALUES ('A564DGR4BU54','PC');
INSERT INTO DisponibilePer (CodSeriale, Nome)
VALUES ('CD9D8G352NC5JA','PlayStation 5');
INSERT INTO DisponibilePer (CodSeriale, Nome)
VALUES ('CD9D8G352NC5JA','PlayStation 4');
INSERT INTO DisponibilePer (CodSeriale, Nome)
VALUES ('CD9D8G352NC5JA','XBOX Series X');
INSERT INTO DisponibilePer (CodSeriale, Nome)
VALUES ('CD9D8G352NC5JA','XBOX Series S');
INSERT INTO DisponibilePer (CodSeriale, Nome)
VALUES ('CD9D8G352NC5JA','PC');
INSERT INTO DisponibilePer (CodSeriale, Nome)
VALUES ('CUR3N43VS23YA','PlayStation 5');
INSERT INTO DisponibilePer (CodSeriale, Nome)
VALUES ('CUR3N43VS23YA','PlayStation 4');
INSERT INTO DisponibilePer (CodSeriale, Nome)
VALUES ('CGRD2SDW2FYR','PlayStation 5');
INSERT INTO DisponibilePer (CodSeriale, Nome)
VALUES ('CGRD2SDW2FYR','PlayStation 4');
INSERT INTO DisponibilePer (CodSeriale, Nome)
VALUES ('45HY33DD2SE24','PlayStation 5');
INSERT INTO DisponibilePer (CodSeriale, Nome)
VALUES ('45HY33DD2SE24','PlayStation 4');
INSERT INTO DisponibilePer (CodSeriale, Nome)
VALUES ('45HY33DD2SE24','XBOX Series X');
INSERT INTO DisponibilePer (CodSeriale, Nome)
VALUES ('45HY33DD2SE24','XBOX Series S');
INSERT INTO DisponibilePer (CodSeriale, Nome)
VALUES ('45HY33DD2SE24','PC');
INSERT INTO DisponibilePer (CodSeriale, Nome)
VALUES ('BYD32A5KYNC4E3','PlayStation 5');
INSERT INTO DisponibilePer (CodSeriale, Nome)
VALUES ('BYD32A5KYNC4E3','PlayStation 4');
INSERT INTO DisponibilePer (CodSeriale, Nome)
VALUES ('BYD32A5KYNC4E3','XBOX Series X');
INSERT INTO DisponibilePer (CodSeriale, Nome)
VALUES ('BYD32A5KYNC4E3','XBOX Series S');
INSERT INTO DisponibilePer (CodSeriale, Nome)
VALUES ('BYD32A5KYNC4E3','PC');