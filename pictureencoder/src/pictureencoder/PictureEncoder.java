package pictureencoder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author Michael Sorban
 */
public class PictureEncoder {
    
    /* Datei die codiert oder decodiert werden soll. */
    private static String file;
    /* Art der Codierung (encode, decode). */
    private static String transforationType;

    /**
     * @param args die übergebenen Parameter in der Comandozeile.
     */
    public static void main(String[] args) {

        // Überprüfung der Anzahl der mitgegebenen Parameter.
        switch (args.length) {
            case 2:
                file = args[0]; // Erster Parameter Dateiname
                transforationType = args[1]; // Zweiter Parameter Codierungsart
                transform(file, transforationType);
                break;
            case 1:
                System.out.println("Bitte geben Sie einen Parameter an.\nVerwenden Sie 'encode' oder 'decode'.");
                break;
            case 0:
                System.out.println("Bitte geben Sie einen Dateinamen an.");
                break;
            default:
                System.out.println("Sie haben zu viele parameter verwendet.\nVerwenden Sie nur 'encode' oder 'decode'.");
                break;
        }
    }

    /**
     * Codiert oder decodiert eine Bild-Datei.
     * @param file Name der Bild-Datei.
     * @param transformationType Codierungsart die vorgenommen werden soll (encode, decode).
     */
    public static void transform(String file, String transformationType) {

        if (transformationType.equals("encode") || transformationType.equals("decode")) {

            File inputFile = new File(file);
            File outputFile = new File(transformationType + "d_" + file);

            FileInputStream reader;
            FileOutputStream writer;

            try {

                reader = new FileInputStream(inputFile); // Einlesen der Datei.
                writer = new FileOutputStream(outputFile); // Schreiben der codierten/decodierten Datei.

                int sign = reader.read(); // Lese erstes Zeichen ein.

                while (sign != -1) { // Abbruch wenn kein Zeichen mehr vorkommt.

                    if (transforationType.equals("encode")) {

                        writer.write(sign + 1); // Beim codieren wird der Bytewert
                                                // jedes Zeichens um eins erhöht.

                    } else if (transforationType.equals("decode")) {

                        writer.write(sign - 1); // Beim decodieren wird der Bytewert
                                                // jedes Zeichens wird um eins verringert.
                    }

                    sign = reader.read(); // Lese nächstes Zeichen ein.
                }

                reader.close();
                writer.close();
                
                // Ausgabe der getätigten Operation
                if(transforationType.equals("encode")) {
                    System.out.println("Datei wurde erfolgreich codiert.");
                } else if(transforationType.equals("decode")) {
                    System.out.println("Datei wurde erfolgreich decodiert.");
                }

            } catch (FileNotFoundException e) {

                System.out.println("Datei nicht gefunden.");

            } catch (IOException e) {

                System.out.println("Datei ist beschädigt.");
            }
        } else {
            
            // Meldung falls ein falscher Parameterwert angegeben wurde.
            System.out.println("Falsche Parameter verwendet.\nNutzen sie bitte die Parameter 'encode' oder 'decode'.");
        }
    }
}
