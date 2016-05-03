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

    String plainText = "";
    Scanner sc = new Scanner(System.in);
    String inputkey = sc.nextLine();
    inputkey = inputkey.trim();
    if (sc.hasNextLine())
      plainText = sc.nextLine();
    int length = inputkey.length();
    inputkey = inputkey.toLowerCase();
    plainText = plainText.toLowerCase();
    int row_size = 0;
    int column_size = 0;
    int rounds = 0;
    int inputLength = inputkey.length();
    int[] size_basket = new int[4];

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

    // Aescipher.processInput(plainText,inputkey, size_basket);
    Aesdecipher.processInput(plainText, inputkey, size_basket);

  }

}
