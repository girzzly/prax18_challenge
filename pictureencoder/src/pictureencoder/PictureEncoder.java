package pictureencoder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 *
 * @author Michael Sorban
 */
public class PictureEncoder {
    
    private static String directory;
    private static String file;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        directory = "";
//        file = args[0];
        file = "tesla.png";
        
        encode(directory, file);
//        decode(directory, file);
        
          
        
    }
    
    public static void encode(String directory, String file) {
        
        File inputFile = new File(directory + file);
        File outputFile = new File(directory + "encoded_" + file);
        
        InputStreamReader reader;
        OutputStreamWriter writer;
        
        try {
            
            reader = new InputStreamReader(new FileInputStream(inputFile));
            writer = new OutputStreamWriter(new FileOutputStream(outputFile));
            
            int sign = reader.read();
         
            while(sign != -1) {
                
                System.out.println(sign);
                writer.write(sign);
                sign = reader.read();
            }
            
            reader.close();
            writer.close();
            
            System.out.println("Datei wurde erstellt.");
            
            
        } catch(FileNotFoundException e) {
            
            System.out.println("Datei nicht gefunden.");
            
        } catch(IOException e) {
            
            System.out.println("Datei ist beschädigt.");
        }
    }
    
    public static void decode(String directory, String file) {
        
        FileInputStream reader;
        FileOutputStream writer;
        
        int sign;
        
        try {
            
            reader = new FileInputStream(directory + file);
            writer = new FileOutputStream(directory + "decoded_" + file);
            
            sign = reader.read();
            
            while(sign > 0) {
                
                System.out.println(sign);
                writer.write(sign - 7);
                sign = reader.read();
            }
            
            reader.close();
            writer.close();
            
            System.out.println("Datei wurde erstellt.");
            
            
        } catch(FileNotFoundException e) {
            
            System.out.println("Datei nicht gefunden.");
            
        } catch(IOException e) {
            
            System.out.println("Datei ist beschädigt.");
        }
    }
}
