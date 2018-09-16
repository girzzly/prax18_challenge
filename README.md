# PRAX18 Challenge


Sie sind in der IT-Abteilung bei Volkswagen und werden beauftragt die Kommunikation bei der Konkurrenz zu entschlüsseln.
Da ihr Unternehmen in der Vergangenheit mit dem großen Abgas-Skandal zu kämpfen hatte, beschließt die Geschäftsführung 
ihr Image wieder auf Vordermann zu bringen und möchte durch Nachhaltigkeit, Umweltbewusstsein und Transparenz glänzen.
Um es genauer zu sagen: VW setzt auf E-Mobilität.

wie schön, dass es dort schon ein Unternehmen gibt, bei dem man sich "Inspiration holen kann"...

Die Konkurrenz ist kein geringerer als **TESLA**. Der bekannteste Hersteller für Elektro-Autos.
Ihre IT-Abteilung war netterweise so lieb und hat für sie Datenpakete zwischen Ingenieuren, Auto-Designern und
Firmenchef Elon Musk höchstpersönlich abgefangen.

Jedoch kann mit dem Inhalt dieser Pakete niemand etwas anfangen, da sie verschlüsselt sind...

Da kommen sie als ehemaliger Penetration-Tester ins Spiel. Sie kennen sich hervorragend mit dem
Aufspüren von Sicherheitslücken, dem Ver- und Entschlüsseln von Dateien sowie mit dem Verändern von Porgrammen aus.
Schließlich waren Sie damals treibende Kraft bei der Software-Manipulation im ihrem Unternehmen... ;-)



## Aufbau der Challenge

Zunächst empfiehlt es sich die Challenge auf der **Kali-Linux Maschine** durchzuführen, da für Windows etwaige Python-Module besorgt werden müssen.

Sie bekommen einen Ordner mit folgenden Datein und Programmen:
- code 
- encoded_tesla.png
- jd-gui-1.4.0_decompiler.jar
- picturedecoder.jar
- stegolyzer.py
- decoding.py

Ihre Aufgabe besteht nun darin mit den gegebenen Tools so viele Informationen wie möglich zu extrahieren. Einige davon sollten Ihnen aus dem PRAX-Praktikum bekannt sein.
Ich wünsche Ihnen viel Spaß bei der Durchführung der Challenge!

## Hinweise
 
#### 1.
Sie werden nicht drumherum kommen das Programm zu decompilieren. Nur so wissen Sie, was das Programm genau macht.
Ein Decompiler befindet sich im gegebenen Ordner. Benutzen Sie ihn.

#### 2.
Man könnte denken, dass der gegebene Code eine große Zahl repräsentiert...vielmehr ist er aufgeteilt in gleichgroße Blöcke...
Schauen sie sich den Programmcode vom Picturedecoder genau an...

#### 3.
Wenn wir etwas sehen, dann sehen wir es als großes Ganzes. Uns ist oft garnicht bewusst, dass sich das "große Ganze" aus Millionen von kleinen Dingen zusammensetzt. Manchmal hilft es mit der Lupe genauer hinzuschauen...


                                                           α

## Musterlösung
In der Musterlösung gebe ich eine detaillierten Beschreibung zur vollständigen Lösung meiner PRAX-Challenge.
Ich werde Schritt für Schritt aufzeigen wie und in welcher Reihenfolge man vorzugehen hat, um die benötigten Informationen zu exfiltrieren.

Als erstes kopieren Sie den Ordner "tesla-spionage" auf den **Desktop** unserer Kali-Linux Maschine
Anschließend navigieren Sie per Terminal in den Ordner mit dem folgenden Befehl
```
cd Desktop/tesla-spionage
```
Sie sehen nun folgende in dem Ornder befindliche Dateien:
- code 
- encoded_tesla.png
- jd-gui-1.4.0_decompiler.jar
- picturedecoder.jar
- stegolyzer.py
- decoding.py

Der erste eigentliche Schritt beginnt mit dem Decompilieren des picturedecoder. Dies machen Sie über diesen Befehl

```
java -jar jd-gui-1.4.0_dempiler.jar picturedecoder.jar 
```
Nun öffnet sich eine grafische Oberfläche und wir können uns damit den Programmcode vom pictureencoder ansehen.
Sie sehen, dass die Methode "decode()" aufgerufen wird, wenn der input (zweiter Parameter) gleich dem zurückgegebenen Wert der "getDecimal()" Methode ist.

```
                if (input == getDecimal(output)) {

                    decode();

                }
```                
Nun hilft es einen Blick auf die "getDecimal" Methode zu werfen.
````
    public static int getDecimal(String output) {

        // Länge der Zahlenfolge: 24
        // 0101 0111 1001 0111 0100 0100
        // 5    7    9    7    4    4
        
        int blocks = 6;
        int blockSize = 4;
        
        int[] numbers = new int[blocks];

        for (int i = 0; i < numbers.length; i++) {

            numbers[i] = parseInt(output.substring(0, blockSize));
            if (output.length() > blockSize) {
                output = output.substring(blockSize, output.length() - 1);
            }
        }
        
        int number = 0;
        int j = 100000;
        for (int i = 0; i < numbers.length; i++) {
            number = number + ((numbers[i] % 10 * 1) + (numbers[i] / 10 % 10 * 2)
                    + (numbers[i] / 100 % 10 * 4)
                    + (numbers[i] / 1000 % 10 * 8)) * j;
            j = j / 10;

        }

        return number;
    }
````
"output" übergibt hier den Code (010101111001011101000100). Dieser wird in der ersten for-Schleife in 6 gleichgroße Blöcke a 4 Ziffern in einem Array gespeichert. In der zweiten for-Schleife geschieht die Umrechnung von Binär zu Dezimal. Allerdings wird nicht die gesamte Binärzahl als Ganzes umgerechnet, sondern Block für Block. Sprich jede 4 Bit repräsentieren eine Dezimal-Zahl.
Somit wird am Ende folgende Dezimalzahl zurückgegeben.

        // 0101 0111 1001 0111 0100 0100
        // 5    7    9    7    4    4           <--- Diese Dezimalzahl gibt die Methode zurück.
        
Damit haben wissen wir welchen (zweiten) Parameter wir verwenden müssen, um das Bild zu entschlüsseln.
Der erste Parameter ist der Dateiname des Bildes. Also müssen Sie folgenden Befehl eingeben um das Bild zu decoden.
````
java -jar picturedecoder.jar encoded_tesla.png 579744
````

Im Ordner "tesla-spionage" sehen sie nun das entschlüsselte Bild mit dem Namen "decoded_tesla.png"
Jetzt wissen wir schon mal, dass Tesla die besseren Designer als unser Unternehmen Volkswagen hat ;)
Das Bild können sie sich über die GUI ansehen.





                                                            
                                                            
                                                            








| **Aufgabe01** | **Aufgabe02** | **Aufgabe03** | **Aufgabe04** |
|---------------|---------------|---------------|---------------|
|    erledigt   |    erledigt   |    erledigt   |  ausstehend   |

## Änderungen

* Lösung für Aufgabe01 - 1.1 abgegeben
* Bearbeitung Aufgabe01 - 1.5 läuft

## Test
                                     
```diff                              
- this text is highlighted in green 
+ this text is highlighted in red    
```                         
        
- [x] Finish my changes
- [ ] Push my commits to GitHub
- [ ] Open a pull request

Some basic Git commands are:
```
git status
git add
git commit
```
