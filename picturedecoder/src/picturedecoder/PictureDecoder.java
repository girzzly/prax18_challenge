package picturedecoder;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;

/**
 *
 * @author Michael Sorban
 */
public class PictureDecoder {

    private static BufferedReader reader;

    private static String input;
    private static String output;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

//        input = args[0];
        setUpReader();
        decode(output);
    }

    public static void setUpReader() {

        reader = null;

        try {

            reader = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Users\\Micha\\Documents\\prax18_challenge\\code")));
            output = reader.readLine();
            reader.close();

        } catch (FileNotFoundException e) {

            System.out.println("Datei nicht gefunden.");

        } catch (IOException e) {

            System.out.println("Datei ist beschädigt.");
        }
    }

    public static int decode(String output) {

        // Länge der Zahlenfolge: 24
        // letztes Zeichen: Position 23
        // 0101 0111 1001 0111 0100 0100
        // 5    7    9    7    4    4
        int[] zahlen = new int[6];

        for (int i = 0; i < zahlen.length; i++) {

            zahlen[i] = parseInt(output.substring(0, 4));
            if (output.length() > 4) {
                output = output.substring(4, output.length() - 1);
            }
        }
        for (int i = 0; i < zahlen.length; i++) {
            System.out.println(zahlen[i]);

        }

        int zahl = 0;
        int j = 100000;
        for (int i = 0; i < zahlen.length; i++) {
            zahl = zahl + ((zahlen[i] % 10 * 1) + (zahlen[i] / 10 % 10 * 2)
                    + (zahlen[i] / 100 % 10 * 4)
                    + (zahlen[i] / 1000 % 10 * 8)) * j;
            j = j / 10;

        }
        return zahl;

    }
}
