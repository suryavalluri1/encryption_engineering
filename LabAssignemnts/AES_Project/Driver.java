import java.util.Scanner;

/**
 * This class takes the user input key and calls aescipher class for processing
 * the keys.If the input is less than 32 bits, then it pads and processes
 * 
 * @author Surya Valluri,Sri Immadisetty
 *
 */

public class Driver {

  public static void main(String args[]) {

    String plainText = "";
    Scanner sc = new Scanner(System.in);
    String inputkey = sc.nextLine();
    inputkey = inputkey.trim();
    if (sc.hasNextLine())
      plainText = sc.nextLine();
    inputkey = inputkey.toLowerCase();
    plainText = plainText.toLowerCase();
    int row_size = 0;
    int column_size = 0;
    int rounds = 0;
    int inputLength = plainText.length();
    int padLength = 32 - plainText.length();
    String padString = Integer.toString(padLength);
    if (padLength < 10)
      padString = "0".concat(padString);
    int[] size_basket = new int[4];
    /**
     * Assigning values based on input key size
     */
    if (inputkey.length() == 32) {
      row_size = 4;
      column_size = 44;
      rounds = 11;
    } else if (inputkey.length() == 48) {
      row_size = 6;
      column_size = 52;
      rounds = 13;
    }

    else if (inputkey.length() == 64) {
      row_size = 8;
      column_size = 60;
      rounds = 15;
    }
    size_basket[0] = row_size;
    size_basket[1] = column_size;
    size_basket[2] = rounds;
    /**
     * Based on input message size padding is decided
     */
    try {
      if (plainText.length() == 32 && plainText.substring(30, 32) == "00") {
        String cipher = Aescipher
            .processInput(plainText, inputkey, size_basket);
        System.out.println("Encrypted message is");
        System.out.println(cipher);

      } else if (plainText.length() <= 30) {

        for (int i = 0; i < padLength / 2; i++) {
          plainText = plainText.concat(padString);

        }

      }

      
      
      String cipher = Aescipher.processInput(plainText, inputkey, size_basket);
      System.out.println("Encrypted message is");
      System.out.println(cipher);

      String decipher = Aesdecipher.processInput(cipher, inputkey, size_basket);

      if (padLength > 0) {
        System.out.println("Number of bits to be padded are ");
        System.out.println(padLength);
        decipher = decipher.substring(0, inputLength);
      }

      System.out.println("Decrypted message is");
      System.out.println(decipher);
    } catch (Exception se) {
      se.printStackTrace();
    }

  }
}
