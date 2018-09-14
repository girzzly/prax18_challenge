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

    private static BufferedReader reader;

    private static String code = "code";
    private static String file;

    private static int input;
    private static String output;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        switch (args.length) {
            case 2:
                file = args[0];
                input = parseInt(args[1]);

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

    public static void setUpReader() {

        reader = null;

        try {
            
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(code)));
            output = reader.readLine();
            reader.close();

        } catch (FileNotFoundException e) {

            System.out.println("Datei " + code + " nicht gefunden.");

        } catch (IOException e) {

            System.out.println("Datei " + code + " ist beschädigt.");
        }
    }

    public static int getDecimal(String output) {

        // Länge der Zahlenfolge: 24
        // letztes Zeichen: Position 23
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

    public static void decode() {

        File inputFile = new File(file);
        
        String decodeFilename = file.substring(8, file.length());
        
        File outputFile = new File("decoded_" + decodeFilename);

        FileInputStream reader;
        FileOutputStream writer;

        try {

            reader = new FileInputStream(inputFile);
            writer = new FileOutputStream(outputFile);

            int sign = reader.read();

            while (sign != -1) {

                writer.write(sign - 1);
                sign = reader.read();
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
