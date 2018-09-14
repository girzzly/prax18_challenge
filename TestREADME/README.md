# PRAX18 Challenge - Cybertronic Inc. Data-Extractor

## Challengebeschreibung
Sie sind ein Industriespion und wollen der Firma Cybertronic Inc. geheime Daten
über einen Prototypen extrahieren. Dafür haben Sie sich Zugang zum Anmeldeprogramm
der Firma für Angestellte verschafft. Sie haben IT-Fachkräfte der Firma in der Pause über
die Serverkonfiguration des Anmeldeservers reden hören. Dabei haben Sie aufgeschnappt,
dass der Server über den <b>Port 60065</b> kommuniziert.

Ihre Aufgabe ist es nun sich am Server mit Hilfe des Anmeldeprogramms
anzumelden und die geheimen Daten zu extrahieren.

## Aufbau der Challengeumgebung
Für die Challenge müssen einige Schritte unternommen werden, bevor mit der Bearbeitung begonnen werden kann.
Die Challenge benötigt einige Pakete, die Sie vor Beginn installieren müssen. Um Ihnen diese Arbeit etwas zu
erleichtern, wurde eine Datei mit dem Namen <b>setup.sh</b> erstellt. Diese Datei beinhaltet die benötigten Installationsbefehle,
die vorgenommen werden müssen. Sollten Fehler beim Starten der Datei auftreten, können Sie die Befehle auch
per Hand in ein Terminalfenster kopieren und nacheinander ausführen.

<u>Im Folgenden werden die notwendigen Schritte zum Aufbau der Challengeumgebung aufgeführt:</u>

### 0. Kali Linux Maschine neu aufsetzen
Wenn Sie auf der Kali Linux Maschine schon eine Weile gearbeitet haben, könnte es sein, dass Sie die Repositories
aktualisiert haben. Ist dies der Fall, dann ist der Eintrag für das Paket <b>openjfx</b> höchst wahrscheinlich verloren gegangen. Aus diesem
Grund muss die Kali Linux Maschine neu aufgesetzt werden. Das Paket <b>openjfx</b> wird für die grafische Darstellung von Java-Programmen benötigt.
Alle Dateien, die für die Challenge benötigt werden, sind im Ordner <b>Challenge_Cybertronic_Inc</b> zu finden.

(Voraussetzung VirtaulBox wurde richtig installiert)
* Die Linux Kali Maschine kann unter https://www.internet-sicherheit.de/kali-linux-2018.1-vbox-amd64.ova heruntergeladen werden.
* VirtualBox starten.
* Datei -> Appliance importieren...
* auf das Ordner-Symbol klicken.
* Speicherort der Kali Linux Maschine OVA-Datei angeben und auf Öffnen und Ok klicken.
* Erstellen Sie an einem beliebigen Ort einen Order mit dem Namen <b>SharedFolder</b>.
* Rechtsklick auf die Kali Lunux Maschine -> Ändern
* Unter <b>Gemeinsamer Ordner</b> den SharedFolder Ordner einbinden und darauf achten, dass <b>Automatisch einbinden</b> eingehakt ist und dann auf Ok klicken.
* Starten Sie nun die Kali Linux Maschine und melden Sie sich mit dem Benutzer <b>root</b> und dem Passwort <b>toor</b> an.
* Mit der Befehlszeile ```gnome-control-center region``` können Sie die Einstellungen für Sprache und Tastaturlayout anpassen (Neuanmeldung erforderlich).

### 1. Challengeorder auf die Kali Linux Maschine kopieren
* Öffnen Sie ein neues Terminalfenster und erstellen Sie unter Documents einen Order mit dem Befehl ```mkdir /root/Documents/SharedFolder```.
* Binden Sie den SharedFolder Ordner in die Kali Linux Maschine mit der Befehlszeile ```mount -t vboxsf SharedFolder ~/Documents/SharedFolder``` ein.
* Kopieren Sie jetzt den Challengeordner <b>Challenge_Cybertronic_Inc</b> in den Ordner <b>SharedFolder</b>.
* Wechseln Sie nun in den Ordner <b>Challenge_Cybertronic_Inc</b> mit der Befehlszeile ```cd /root/Documents/SharedFolder/Challenge_Cybertronic_Inc```.

### 2. SetUp Datei ausführen
* Geben Sie den Dateien <b>setup.sh</b>, <b>loginclient-1.0.jar</b> und <b>jd-gui-1.4.0.jar</b> ausführbare Rechte mit dem Befehl ```chmod +x <DATEINAME MIT ENDUNG>```.
* Führen Sie nun mit der Befehlzeile ```./setup.sh``` die Installationsdatei aus.

Nachdem Sie nun die Kali Linux Maschine neu aufgesetzt und die notwendigen Aufbauschritte durchgeführt haben, können Sie mit der Bearbeitung
der Challenge beginnen.

## Bearbeitung der Challenge - Cybertronc Inc. Data-Extractor
Der Server in dieser Challenge ist natürlich kein echter im Internet verfügbarer Server, sondern dieser wird lokal auf Ihrem 
Gerät gestartet. Diese Situation entspricht nicht der Realität, aber für die Zwecke dieser Challenge tun wir mal so, als ob der Server
weit entfernt irgendwo im Internet steht.
Nachdem die Einrichtung der Challengeumgebung abgeschlossen wurde, können Sie nun den Startzustand der Challenge erzeugen.

Dafür befolgen Sie folgenden Schritte:
* Starten Sie mit der Befehlszeile ```node server.js``` den Server der Firma.
* Öffnen Sie ein neues Terminalfenster und wechseln Sie das in das Challenge-Verzeichnis mit der Befehlszeile ```cd /root/Documents/SharedFolder/Challenge_Cybertronic_Inc```.
* Starten Sie im neu geöffneten Terminalfenster mit der Befehlszeile ```java -jar loginclient-1.0.jar``` das Anmeldeprogramm der Firma.

Möchten Sie den Server und das Anmeldeprogramm beenden, dann drücken Sie im jeweiligen Terminalfenster die Tastenkombination ```STRG + C``` 
und starten Sie die Programme wie oben beschrieben, falls dies nötig sein sollte, erneut.

Wenn Sie das Programm Wireshark benötigen, können Sie dies ebenfalls in einem neuen Terminalfenster mit der Befehlszeile ```wireshark &``` starten.
Durch das Zusatzzeichen <b>&</b> wird der Prozess von Wireshark im Hintergrund gestartet und das Terminalfenster bleibt weiter nutzbar.

Nun wissen Sie alle benötigten Befehle, um den Server und das Anmeldeprogramm der Firma selbständig zu starten.

Versuchen Sie nun selbständig die Challenge zu bearbeiten.<BR>
Viel erfolg :)

---

## Musterlösung
Die Musterlösung der Challenge "Cybertronic Inc. Data-Extractor" kann auch im folgenden Video nachvollzogen werden:<BR>
[![Musterlösung](https://i.ytimg.com/vi/0N1JNVKjI3I/hqdefault.jpg?sqp=-oaymwEZCPYBEIoBSFXyq4qpAwsIARUAAIhCGAFwAQ==&rs=AOn4CLBIE2n1Jjh9vFbCiHWHFosFFQZQjw)](https://www.youtube.com/watch?v=0N1JNVKjI3I)

### 1. Das Anmeldeprogramm decompilieren
Um sich einen Überblick über das Anmeldeprogramm der Firma Cybertronic Inc. zu verschaffen, sollte das Programm decompiliert werden.
Ein Decompilier befindet sich bereits im Ordner <b>Challenge_Cybertronic_Inc</b>, dieser wird mit ```java -jar jd-gui-1.4.0.jar``` gestartet.
Jetzt wird die Jar-Datei des Anmdeldeprogramms geöffnet. In der Class-Datei <b>LoginClientWindow.class</b> ist eine Variable zu sehen, die den
String <b>wavehackr</b> beinhaltet. Dieser String ist der Username des Anmeldeprogramms. In Abbildung 1 sind der decompilierte Code und der Username zusehen.

![picture alt](https://images.grizzlyboer.com/prax18/Username.png "Decompiliertes Anmeldeprogramm")<BR>
<b>Abbildung 1:</b> Decompiliertes Anmeldeprogramm.

### 2. Netzwerkverkehr mitschneiden
Da wir wissen, dass der Server über das Internet auf dem <b>Port 60065</b> kommuniziert, können wir versuchen, den Kommunikationsaustausch auf <b>localhost</b> mit Hilfe von
Wireshark mitzuschneiden. Dabei beinhaltet ein HTTP-Paket die Nachricht <b>zbwlynlolpt</b> wie in Abbildung 2 dargestellt.
Klickt man mit einem Rechtsklick auf das HTTP-Paket, <b>Folgen -> TCP Stream</b>, dann kann man die Nachricht mit <b>STRG + C</b> in den Zwischenspeicher kopieren.
 
![picture alt](https://images.grizzlyboer.com/prax18/WiresharkMitschnitt.png "Wireshark WebSocket-Protokoll Mitschnitt")<BR>
<b>Abbildung 2:</b> Mitschnitt der Client-Server-Kommunikation.

### 3. Cäsar-Verschlüsselung
Die Nachricht sieht aus, als wäre sie mit einer Cäsar-Verschlüsselung codiert. Die kopierte Nachricht kann dann in eine Cäsar-Entschlüsselungs-Seite
im Internet eingefügt werden. Heraus kommt das Wort <b>supergeheim</b>. Dieses Wort stellt das Passwort für das Anmeldeprogramm der Firma Cybertronic Inc. dar.

### 4. Daten extrahieren
Geben Sie nun den Usernamen <b>wavehackr</b> und das Passwort <b>supergeheim</b> ein. Nun haben Sie sich erfolgreich Zugang zu den Prototyp-Daten verschafft.
Die dargestellte Flagge kennzeichnet den Erfolg der Bearbeitung der Challenge. Speichern Sie die dargestellte Flagge in eine Datei names <b>flag.txt</b> im
Challengeordner.

---

## Hinweise

### 1. Hinweis
Anmeldeprogramm decompilieren.<BR>
http://jd.benow.ca/

### 2. Hinweis
Netzwerkkommunikation auf Port 60065 auf localhost mitschneiden.<BR>
Eigenschaft für das Interface localhost: <b>port 60065</b><BR>
-> Capture starten.

Tipp: Es wäre günstiger erst Wireshark zu starten, dann den Server und dann das Anmeldeprogramm.

### 3. Hinweis
Cäsar-Verschlüsselung.<BR>
https://gc.de/gc/caesar/


