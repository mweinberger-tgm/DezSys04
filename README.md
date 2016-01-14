# DezSys04
DezSys04 - Authentifizierung und Autorisierung

## Einführung

Diese Übung soll zur Vertiefung der Begriffe "Authentifizierung und Autorisierung" dienen. 

## Ziele

Das Ziel dieser Übung ist die Funktionsweise eines Verzeichnisdienstes zu verstehen und Erfahrungen mit der Administration auszuprobieren.  
Ebenso soll die Verwendung des Dienstes aus einer Anwendung heraus mit Hilfe der JNDI geübt werden.

Authentifizierung bedeutet hier, dass per Username und Passwort eine Anmeldung beim Verzeichnisdienst erfolgt.  
Autorisierung wird hier im Zusammenhang mit Service-Gruppen und zugeordneten Usern durchgefuehrt.

## Voraussetzungen

- Grundlagen Verzeichnisdienst
- Administration eines LDAP Dienstes
- Verwendung von Commandline Werkzeugen fuer LDAP (LDAPSEARCH, LDAPMODIFY)
- Grundlagen der JNDI API für eine JAVA Implementierung
- Verwendung einer virtuellen Instanz für den Betrieb des Verzeichnisdienstes

## Aufgabenstellung

Mit Hilfe der zur Verfuegung gestellten VM wird ein vorkonfiguriertes LDAP Service zur Verfuegung gestellt.  
Dieser Verzeichnisdienst soll um folgende Eintraege erweitert werden.  
Das verwendete Namensschema (eg. group.service1 oder vorname.nachname) soll fuer alle Eintraege verwendet werden.

- 5 Posix Groups (beliebe Zuweisung von UserIDs)
- 10 User Accounts

Weiters soll eine Java-Applikationen zur Authentifizierung und Autorisierung entwickelt werden. Folgende Fragestellungen stehen dabei im Mittelpunkt:

- Sind Username und Passwort korrekt? (Identifikation des Benutzers)
- Ist der User berechtigt ein bestimmtes Service zu nutzen? (Benutzer-Berechtigung)

Bewertung:

- Dokumentation der einzelnen Arbeitsschritte im Protokoll
- Anlegen von 5 Gruppen und 10 User Accounts
- wenn fremdes LDAP-Service verwendet wird, dann Dokumentation von 3 LDAPSEARCH und 2 LDAPMODIFY Befehlen
- Authentifizierung
- Autorisierung
- Wie ist eine LDAP Aenderung moeglich mit bestimmten Benutzer (ungleich admin)?
- Brute Force Implementierung
