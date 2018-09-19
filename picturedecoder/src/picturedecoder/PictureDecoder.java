package picturedecoder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;

/**
 *
 * @author Michael Sorban
 */
public class PictureDecoder {

    /* Leser für die codierte Bild-Datei. */
    private static BufferedReader reader;
    /* Name der Datei 'code'. */
    private static String code = "code";
    /* Speicherung des Namens der übergebenen Bild-Datei. */
    private static String file;

    /* Speicherung Zahl die übergeben wird. */
    private static int input;
    /* Speicherung des Inhalts der Datei 'code'. */
    private static String output;

    /**
     * @param args Die übergebenen Komandozeilenargumente.
     */
    public static void main(String[] args) {

        // Überprüfung der Anzahl der mitgegebenen Parameter.
        switch (args.length) {
            case 2:
                file = args[0]; // Erster Parameter Dateiname

                try {
                    input = parseInt(args[1]); // Zweiter Parameter Zahl

                } catch (NumberFormatException e) {
                    System.out.println("Der eingegebene Parameter ist zu lang oder enthält Text");
                }
                setUpReader();

                if (input == getDecimal(output)) {

                    decode();

                } else {

                    System.out.println("Sie haben einen falschen Parameterwert angegeben.");
                }
                break;

            case 1:
                System.out.println("Bitte geben Sie einen Parameter an.");
                break;

            case 0:
                System.out.println("Bitte geben Sie einen Dateinamen an.");
                break;

            default:
                System.out.println("Sie haben zu viele parameter verwendet.");
                break;
        }

    }

    /**
     * Liest die Code-Datei ein und speichert den enthaltenen Wert in die
     * Variable output vom Typ String.
     */
    public static void setUpReader() {

        reader = null;

        try {

            reader = new BufferedReader(new InputStreamReader(new FileInputStream(code))); // Liest Datei 'Code' ein.
            output = reader.readLine(); // List die erste Zeile der Datei 'Code'.
            reader.close(); // Leser wird geschlossen.

        } catch (FileNotFoundException e) {

            System.out.println("Datei " + code + " nicht gefunden.");

        } catch (IOException e) {

            System.out.println("Datei " + code + " ist beschädigt.");
        }
    }

    /**
     * Rechnet die Binärwerte in der Code-Datei in eine dezimale Zahl um.
     *
     * @param output Der inhalt der Datei 'Code' als String.
     * @return Liefert die umgerechnete dezimale Zahl zurück.
     */
    public static int getDecimal(String output) {

        // Länge der Zahlenfolge: 24
        // letztes Zeichen: Position 23
        // 0101 0111 1001 0111 0100 0100
        // 5    7    9    7    4    4
        int blocks = 6;         // es werden 6 Blöcke erzeugt 
        int blockSize = 4;      // jeweils mit der Größe 4

        int[] numbers = new int[blocks];

        // Das Array wird befüllt
        for (int i = 0; i < numbers.length; i++) {

            numbers[i] = parseInt(output.substring(0, blockSize));
            if (output.length() > blockSize) {
                output = output.substring(blockSize, output.length() - 1);
            }
        }

        // Umrechnung von Binär in Dezimal
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

    /**
     * Decodiert die angegebene Bild-Datei und macht sie wieder anzeigbar.
     */
    public static void decode() {

        File inputFile = new File(file);

        String decodeFilename = file.substring(8, file.length()); // Schneidet 'encode_' von dem Dateinamen ab.

        File outputFile = new File("decoded_" + decodeFilename); // Schreibt 'decoded_' an den Dateinamen.

        FileInputStream reader;
        FileOutputStream writer;

        try {

            reader = new FileInputStream(inputFile); // Leser für die codierte Bild-Datei.
            writer = new FileOutputStream(outputFile); // Schreiber für die decodierte Bild-Datei.

            int sign = reader.read(); // Lese erstes Zeichen ein.

            while (sign != -1) {

                writer.write(sign - 1); // Beim decodieren wird der Bytetwert
                // jedes Zeichens wird um eins verringert.
                sign = reader.read(); // Lese nächstes Zeichen ein.
            }

            reader.close();
            writer.close();

            System.out.println("Datei '" + file + "' wurde erfolgreich decodiert.");

        } catch (FileNotFoundException e) {

            System.out.println("Datei nicht gefunden.");

        } catch (IOException e) {

            System.out.println("Datei ist beschädigt.");
        }
    }

}
