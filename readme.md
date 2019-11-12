Projekt: Bücherei

Ziel: Mittwoch Abend

Anforderungen:

Eine Bücherei
    Bücher dürfen 2 Wochen ausgeliehen werden
    Bücher können auch verlängert werden (jeweils 1 Woche, keine Beschränkung)
    Ein Benutzer darf maximal 4 Bücher gleichzeitig ausleihen

Eine Anwendungen, nach Start aber abfragen in welche “Ansicht”
    Programm zum Editieren der Stammdaten:
        Bücher hinzufügen, bearbeiten & löschen
        Benutzer hinzufügen, bearbeiten & löschen
        Autoren hinzufügen, bearbeiten & löschen
    Buch-Ausleih-Terminal-Gerät-Anwendung (BATGA)
        Authentifizieren (Benutzer)
        Buch ausleihen
        Buch zurückgeben
        Buch verlängern
        Bücher browsen + Verfügbarkeit prüfen

Datenbank:

Kundendaten
    idKd (KDNR-1, KDNR-2,...)
    PIN-Code (gehasht - MD5)
    Email
    Vorname
    Nachname
    Geburtsdatum
    Adresse
    Kreditkarten Nr.
    CVC
    Ablaufdatum

Bücher
    idBuch
    Titel
    Autor-Nr.
    Kategorie
    ISBN
    FSK
    Verlag
    Ausgabe / Version
    Ersterscheinung
    Anzahl Seiten
    Sprache
    Regal
    Spalte

Autoren
    idAutor
    Name
    Geburtsjahr

Kreuztabelle Ausleihvorgänge
    idVorgang
    KDNR
    Buch-Nr
    ausgeliehen am
    zurückgegeben am
    (Verlängerungsvorgänge -> Eintrag in zurückgegeben an und Neueintrag mit kDNR, BuchNr, ausgeliehen am)

Verfügbarkeit? --> Buch, welches entweder nicht in Ausleihvorgänge aufgeführt oder in Ausleihvorgänge im letzten Eintrag sowohl ausgeliehen als auch zurückgegeben-Eintrag hat 
Liste an aktuell ausgeliehenen Büchern --> Bücher, die ein ausgeliehen-Datum und ein offenen zurückgegeben-Datum haben
Liste an allen Büchern die jemals ausgeliehen wurden --> Eintrag in Ausleihvorgang vorhanden (unabhängig von Datums-Einträgen)
die 2 letztgenannten Listen auch kundenspezifisch gestaltbar

Link für DB-Zugriff über Browser: https://w0136ee0.kasserver.com/mysqladmin/
    db: d03037fa
    user: d03037fa
    pw: fpcQdPhv5v4UoQ6H
    server: w0136ee0.kasserver.com