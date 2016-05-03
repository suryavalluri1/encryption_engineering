/**
 * File: Aescipher.java
 * 
 * @author Surya Valluri,Sri Immadisetty It accepts user input, key and decrypts
 *         the cipher
 *
 */



public class Aescipher {

  /**
   * S_BOX static variable which is used for s-box transformations and used in
   * aesBox
   */
  private static final String[][] S_BOX = {
      { "63", "7C", "77", "7B", "F2", "6B", "6F", "C5", "30", "01", "67", "2B",
          "FE", "D7", "AB", "76" },
      { "CA", "82", "C9", "7D", "FA", "59", "47", "F0", "AD", "D4", "A2", "AF",
          "9C", "A4", "72", "C0" },
      { "B7", "FD", "93", "26", "36", "3F", "F7", "CC", "34", "A5", "E5", "F1",
          "71", "D8", "31", "15" },
      { "04", "C7", "23", "C3", "18", "96", "05", "9A", "07", "12", "80", "E2",
          "EB", "27", "B2", "75" },
      { "09", "83", "2C", "1A", "1B", "6E", "5A", "A0", "52", "3B", "D6", "B3",
          "29", "E3", "2F", "84" },
      { "53", "D1", "00", "ED", "20", "FC", "B1", "5B", "6A", "CB", "BE", "39",
          "4A", "4C", "58", "CF" },
      { "D0", "EF", "AA", "FB", "43", "4D", "33", "85", "45", "F9", "02", "7F",
          "50", "3C", "9F", "A8" },
      { "51", "A3", "40", "8F", "92", "9D", "38", "F5", "BC", "B6", "DA", "21",
          "10", "FF", "F3", "D2" },
      { "CD", "0C", "13", "EC", "5F", "97", "44", "17", "C4", "A7", "7E", "3D",
          "64", "5D", "19", "73" },
      { "60", "81", "4F", "DC", "22", "2A", "90", "88", "46", "EE", "B8", "14",
          "DE", "5E", "0B", "DB" },
      { "E0", "32", "3A", "0A", "49", "06", "24", "5C", "C2", "D3", "AC", "62",
          "91", "95", "E4", "79" },
      { "E7", "C8", "37", "6D", "8D", "D5", "4E", "A9", "6C", "56", "F4", "EA",
          "65", "7A", "AE", "08" },
      { "BA", "78", "25", "2E", "1C", "A6", "B4", "C6", "E8", "DD", "74", "1F",
          "4B", "BD", "8B", "8A" },
      { "70", "3E", "B5", "66", "48", "03", "F6", "0E", "61", "35", "57", "B9",
          "86", "C1", "1D", "9E" },
      { "E1", "F8", "98", "11", "69", "D9", "8E", "94", "9B", "1E", "87", "E9",
          "CE", "55", "28", "DF" },
      { "8C", "A1", "89", "0D", "BF", "E6", "42", "68", "41", "99", "2D", "0F",
          "B0", "54", "BB", "16" } };

  /**
   * R_CON static variable which is used for r-con transformations and used in
   * aesRcon
   */

  private static final String[][] R_CON = {
      { "8D", "01", "02", "04", "08", "10", "20", "40", "80", "1B", "36", "6C",
          "D8", "AB", "4D", "9A" },
      { "2F", "5E", "BC", "63", "C6", "97", "35", "6A", "D4", "B3", "7D", "FA",
          "EF", "C5", "91", "39" },
      { "72", "E4", "D3", "BD", "61", "C2", "9F", "25", "4A", "94", "33", "66",
          "CC", "83", "1D", "3A" },
      { "74", "E8", "CB", "8D", "01", "02", "04", "08", "10", "20", "40", "80",
          "1B", "36", "6C", "D8" },
      { "AB", "4D", "9A", "2F", "5E", "BC", "63", "C6", "97", "35", "6A", "D4",
          "B3", "7D", "FA", "EF" },
      { "C5", "91", "39", "72", "E4", "D3", "BD", "61", "C2", "9F", "25", "4A",
          "94", "33", "66", "CC" },
      { "83", "1D", "3A", "74", "E8", "CB", "8D", "01", "02", "04", "08", "10",
          "20", "40", "80", "1B" },
      { "36", "6C", "D8", "AB", "4D", "9A", "2F", "5E", "BC", "63", "C6", "97",
          "35", "6A", "D4", "B3" },
      { "7D", "FA", "EF", "C5", "91", "39", "72", "E4", "D3", "BD", "61", "C2",
          "9F", "25", "4A", "94" },
      { "33", "66", "CC", "83", "1D", "3A", "74", "E8", "CB", "8D", "01", "02",
          "04", "08", "10", "20" },
      { "40", "80", "1B", "36", "6C", "D8", "AB", "4D", "9A", "2F", "5E", "BC",
          "63", "C6", "97", "35" },
      { "6A", "D4", "B3", "7D", "FA", "EF", "C5", "91", "39", "72", "E4", "D3",
          "BD", "61", "C2", "9F" },
      { "25", "4A", "94", "33", "66", "CC", "83", "1D", "3A", "74", "E8", "CB",
          "8D", "01", "02", "04" },
      { "08", "10", "20", "40", "80", "1B", "36", "6C", "D8", "AB", "4D", "9A",
          "2F", "5E", "BC", "63" },
      { "C6", "97", "35", "6A", "D4", "B3", "7D", "FA", "EF", "C5", "91", "39",
          "72", "E4", "D3", "BD" },
      { "61", "C2", "9F", "25", "4A", "94", "33", "66", "CC", "83", "1D", "3A",
          "74", "E8", "CB", "8D" } };

  static String[][] GaloisMatrix = { { "02", "03", "01", "01" },
      { "01", "02", "03", "01" }, { "01", "01", "02", "03" },
      { "03", "01", "01", "02" } };
  // masterKey array is declared to save the key user gives
  public static String[][] masterKey_encrypt;
  public static String[][] masterText_encrypt;
  // keyMatrixW array is declared to save the key's which will be generated
  public static String[][] keyMatrixW_encrypt;

  /**
   * This method accepts user given key and saves it into a 4*4 matrix. Once the
   * input is processed generateWMatrix() method is called.
   * 
   * @param key
   *          : Input key
   */
  public static void processInput(String plainText, String inputKey,
      int[] size_basket) {

    int i = 0;
    int j = 0;
    int col_valueforInput;
    int column_size;
    int rounds;
    col_valueforInput = size_basket[0];
    column_size = size_basket[1];
    rounds = size_basket[2];
    keyMatrixW_encrypt = new String[4][column_size];
    masterKey_encrypt = new String[4][col_valueforInput];
    masterText_encrypt = new String[4][4];
    for (int column = 0; column < col_valueforInput; column++) {
      for (int row = 0; row < 4; row = row + 1) {
        masterKey_encrypt[row][column] = inputKey.substring(i, i + 2);
        i = i + 2;
      }
    }

    for (int column = 0; column < 4; column++) {
      for (int row = 0; row < 4; row = row + 1) {
        masterText_encrypt[row][column] = plainText.substring(j, j + 2);
        j = j + 2;
      }
    }
    generateWMatrix(col_valueforInput, column_size, rounds);
    // generateCipher(masterKey,masterText,column_size,row_size,rounds);
  }

  public static void generateCipher(String[][] masterKey,
      String[][] masterText, int column_size, int row_size, int rounds) {

    String[][] keyHex = new String[4][4];
    int WCol = 0;

    int roundCounter = 0;
    while (WCol < column_size) {
      // while (WCol < column_size) {
      for (int cols = 0; cols < 4; cols++, WCol++) {
        for (int row = 0; row < 4; row++) {
          keyHex[row][cols] = keyMatrixW_encrypt[row][WCol];
        }
      }

      if (roundCounter != (rounds - 1)) {
        roundCounter++;
        masterText = aesStateXor(masterText, keyHex);
        // Exclusive or output is passed to nibble substitution is called
        masterText = aesNibbleSub(masterText);
        // Nibble substituted output is called to shiftrows method
        masterText = aesShiftRow(masterText);
        // Shifted output is sent to mixing columns function
        if (roundCounter != (rounds - 1)) {
          masterText = aesMixColumn(masterText);
        }

      } else
        // In the tenth round we do only plain xor
        masterText = aesStateXor(masterText, keyHex);
    }
    System.out.println("The Cipher Text is");
    for (int cols = 0; cols < 4; cols++) {
      for (int row = 0; row < 4; row++) {
        System.out.print(masterText[row][cols]+ "\t");
      }
      System.out.println();
    }
  }

  /**
   * generateWMatrix() method starts processing the keys for the 4*44 keys
   * matrix
   */
  public static void generateWMatrix(int col_valueforInput, int column_size,
      int rounds) { // Saving the user key in keyMatrixW by filling the first
                    // 4*4 cells
    int roundCounter = 0;
    for (int row = 0; row < 4; row = row + 1) {
      for (int column = 0; column < col_valueforInput; column++) {
        keyMatrixW_encrypt[row][column] = masterKey_encrypt[row][column];
      }
    }

    // Processing the rest keys for keyMatrixW , by taking an intermediate
    // matrix wNewMatrix for processing purpose
    String[][] wNewMatrix = null;
    for (int column = col_valueforInput; column < column_size; column++) {
      /**
       * if the column number is not a multiple of 4 the following steps are to
       * be implemented
       */

      if (column % col_valueforInput != 0 && col_valueforInput == 8) {
        if (column % 4 == 0) {
          for (int row = 0; row < 4; row++) {
            keyMatrixW_encrypt[row][column] = aesSbox(keyMatrixW_encrypt[row][column - 1]);
            keyMatrixW_encrypt[row][column] = exclusiveOr(
                keyMatrixW_encrypt[row][column - col_valueforInput],
                keyMatrixW_encrypt[row][column]);

          }
        } else {
          for (int row = 0; row < 4; row++) {

            keyMatrixW_encrypt[row][column] = exclusiveOr(
                keyMatrixW_encrypt[row][column - col_valueforInput],
                keyMatrixW_encrypt[row][column - 1]);
          }
        }
      } else if (column % col_valueforInput != 0 && col_valueforInput != 8) {
        for (int row = 0; row < 4; row++) {

          keyMatrixW_encrypt[row][column] = exclusiveOr(
              keyMatrixW_encrypt[row][column - col_valueforInput],
              keyMatrixW_encrypt[row][column - 1]);
        }

      } else if (column % col_valueforInput == 0) {

        // If its a multiple of 4 the following steps will be implemented
        wNewMatrix = new String[1][4];
        // Inserting values and shifting cells in the intermediate matrix
        wNewMatrix[0][0] = keyMatrixW_encrypt[1][column - 1];
        wNewMatrix[0][1] = keyMatrixW_encrypt[2][column - 1];
        wNewMatrix[0][2] = keyMatrixW_encrypt[3][column - 1];
        wNewMatrix[0][3] = keyMatrixW_encrypt[0][column - 1];
        // Once the shifting is done we do the s-box transformation
        for (int i = 0; i < 1; i++) {
          for (int j = 0; j < 4; j++) {
            wNewMatrix[i][j] = aesSbox(wNewMatrix[i][j]);
          }
        }
        int r = column / col_valueforInput;
        // Performing XOR of the R_CON value and new matrix value
        wNewMatrix[0][0] = exclusiveOr(aesRcon(r), wNewMatrix[0][0]);
        // Final computation of recursively XOR the values
        for (int row = 0; row < 4; row++) {
          keyMatrixW_encrypt[row][column] = exclusiveOr(
              keyMatrixW_encrypt[row][column - col_valueforInput],
              wNewMatrix[0][row]);
        }
      }
    }
    generateCipher(masterKey_encrypt, masterText_encrypt, column_size,
        col_valueforInput, rounds);
  }

  /**
   * This method takes two input strings which are hexadecimal values and
   * convert them into decimal and performe exclusive OR. Saves and returns the
   * result back.
   * 
   * @param val1
   *          : Inputs to be XORed
   * @param val2
   *          : Inputs to be XORed
   * @return : Returns hexadecimal string after exclusive OR operation
   */
  private static String exclusiveOr(String val1, String val2) {
    int decimalValue1 = Integer.parseInt(val1, 16);
    int decimalValue2 = Integer.parseInt(val2, 16);
    int exclusiveOutput = decimalValue1 ^ decimalValue2;
    String hexResult = Integer.toHexString(exclusiveOutput);
    return hexResult.length() == 1 ? ("0" + hexResult) : hexResult;
  }

  /**
   * This method takes a hexadecimal value as the input, splits it and converts
   * into decimal. Based on the two integers generated we map the S_BOX matrix
   * and find the respective value and return it back.
   * 
   * @param sBoxInput
   *          : String which is split and used as index on s_box
   * @return : Returns the value from s-box matrix
   */
  private static String aesSbox(String sBoxInput) {

    int firstDigitInt = Integer.parseInt(sBoxInput.split("")[0], 16);
    int secondDigitInt = Integer.parseInt(sBoxInput.split("")[1], 16);
    String sboxOutput = S_BOX[firstDigitInt][secondDigitInt];
    return sboxOutput;
  }

  /**
   * This method takes a the string and finds the respective element in the
   * R_CON matrix.
   * 
   * @param rConInput
   *          : Index to lookup R_CON matrix
   * @return : Value from the R_CON matrix
   */
  private static String aesRcon(int rConInput) {

    String rConOutput = R_CON[0][rConInput];
    return rConOutput;
  }

  public static String[][] aesStateXor(String[][] sHex, String[][] keyHex) {
    String exclusiveOrArray[][] = new String[4][4];
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        exclusiveOrArray[i][j] = exclusiveOr(sHex[i][j], keyHex[i][j]);
      }
    }
    
    return exclusiveOrArray;

  }

  /**
   * Accepts Exclusiveor output and finds the respective element in S_BOX matrix
   * 
   * @param exclusive
   * @return
   */
  public static String[][] aesNibbleSub(String[][] exclusive) {
    String sBoxValues[][] = new String[4][4];
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        sBoxValues[i][j] = aesSbox(exclusive[i][j]);
      }
    }
    return sBoxValues;
  }

  /**
   * Once the S_BOX values are returned they are shifted
   * 
   * @param sHex
   * @return
   */
  public static String[][] aesShiftRow(String[][] sHex) {
    String[][] outStateHex = new String[4][4];
    int counter = 4;
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        if (i > 0)
          outStateHex[i][(j + counter) % 4] = sHex[i][j];

        else
          outStateHex[i][j] = sHex[i][j];
      }
      counter--;
    }
    return outStateHex;
  }

  protected static String[][] aesMixColumn(String[][] inStateHex) {
    String sum;
    String Product[][] = new String[4][4];

    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        sum = "0";
        for (int k = 0; k < 4; k++) {

          switch (GaloisMatrix[i][k]) {
          // checks data from galios matrix and verifies data to perform
          // multiplication
          case "01":
            sum = exclusiveOr(sum, inStateHex[k][j]);
            break;
          case "02":
            // If its 02 then multiply2 function is called
            sum = exclusiveOr(sum, multiply2(inStateHex[k][j]));
            break;
          case "03":
            // If its 02 then multiply3 function is called
            sum = exclusiveOr(sum, multiply3(inStateHex[k][j]));
            break;
          }
        }

        Product[i][j] = sum;
      }
    }
    return Product;
  }

  /**
   * In this function , mix columns fexclyoperations having multiplication with
   * 3 are considered here and operation is performed.
   * 
   * @param InputHex
   * @return
   */
  protected static String multiply3(String InputHex) {
    return exclusiveOr(multiply2(InputHex), InputHex);
  }

  /**
   * In Mix columns operation if the element is to be multiplied with 2, we will
   * use this function to perform the operation of checking most significant bit
   * shifting the bits
   * 
   * @param InputHex
   * @return
   */
  protected static String multiply2(String InputHex) {
    // String Input = InputHex.length() < 8 ? ("0" + InputHex) : InputHex;
    InputHex = Integer.toBinaryString(Integer.parseInt(InputHex, 16));
    int lenthOfInput = 8 - (InputHex.length());
    String pads = new String();
    for (int i = 0; i < lenthOfInput; i++) {
      pads += "0";
    }
    String Input = pads.concat(InputHex);
    String oneB = Integer.toHexString(27);
    String shiftedBinary = Integer
        .toBinaryString(Integer.parseInt(Input, 2) << 1);
    if (shiftedBinary.length() > 8) {
      shiftedBinary = shiftedBinary.substring(1);
    }
    String shifted = Integer.toHexString(Integer.parseInt(shiftedBinary, 2));

    if (Input.substring(0, 1).equals("1")) {
      return exclusiveOr(shifted, oneB);
    } else
      return shifted;
  }

}