package picturedecoder;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

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
            
            reader = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Users\\Dominik\\Documents\\GitRepositories\\prax18_challenge\\code")));
            output = reader.readLine();
            reader.close();
            
        } catch(FileNotFoundException e) {
            
            System.out.println("Datei nicht gefunden.");
            
        } catch(IOException e) {
            
            System.out.println("Datei ist beschädigt.");
        }
    }
    
    public static void decode(String output) {
        
        System.out.println(output);
    }
}
