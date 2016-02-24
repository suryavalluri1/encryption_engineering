import java.util.Scanner;

/**
 * This class takes the user input key and calls aescipher class for processing
 * the keys.
 * 
 * @author siddhartha
 *
 */

public class driver {

  public static void main(String args[]) {

    Scanner sc = new Scanner(System.in);
    String input = sc.nextLine();
    aescipher as = new aescipher();
    input = input.trim();
    as.processInput(input);

  }

}
