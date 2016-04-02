import java.util.Scanner;

/**
 * This class takes the user input key and calls aescipher class for processing
 * the keys.
 * 
 * @author siddhartha
 *
 */

public class Driver {

  public static void main(String args[]) {

    String plaintext = "";
    Scanner sc = new Scanner(System.in);
    String inputkey = sc.nextLine();
    inputkey = inputkey.trim();
    if (sc.hasNextLine())
      plaintext = sc.nextLine();
    Aescipher as = new Aescipher();
    Aescipher.aes(inputkey, plaintext);

  }

}
