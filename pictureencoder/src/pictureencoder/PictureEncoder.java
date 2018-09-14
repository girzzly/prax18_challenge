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

    private static String directory;
    private static String file;
    private static String transforationType;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        directory = "";

        switch (args.length) {
            case 2:
                file = args[0];
                transforationType = args[1];
                transform(directory, file, transforationType);
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

    public static void transform(String directory, String file, String transformationType) {

        if (transformationType.equals("encode") || transformationType.equals("decode")) {

            File inputFile = new File(directory + file);
            File outputFile = new File(directory + transformationType + "d_" + file);

            FileInputStream reader;
            FileOutputStream writer;

            try {

                reader = new FileInputStream(inputFile);
                writer = new FileOutputStream(outputFile);

                int sign = reader.read();

                while (sign != -1) {

                    if (transforationType.equals("encode")) {

                        writer.write(sign + 1);

                    } else if (transforationType.equals("decode")) {

                        writer.write(sign - 1);
                    }

                    sign = reader.read();
                }

                reader.close();
                writer.close();
                
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
            
            System.out.println("Falsche Parameter verwendet.\nNutzen sie bitte die Parameter 'encode' oder 'decode'.");
        }
    }
}
