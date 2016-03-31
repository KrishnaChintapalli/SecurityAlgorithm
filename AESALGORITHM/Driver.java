/**
 * file: Driver.java 
 * author: SivaChintapalli 
 * course: MSCS_630L_231_16S
 * assignment: Lab 2 
 * due date: March 31, 2016 
 * version: 1.0
 * This file contains program of AES Algorithm which will generate the round 
 * keys and Cipher text
 */
import java.util.HashMap;
import java.util.Scanner;
/**
 * Driver class 
 * which reads the input from the file in the hex decimal format and generate the 
 * cipher text with help of round keys
 * @author SIVARAMAKRISHNAPRASA
 */
public class Driver {

  static HashMap<Integer, String> roundKey = new HashMap<>();

  public static void main(String[] args) {
    // takes the input from the user and stores in to the key
    Scanner input = new Scanner(System.in);
    String key = input.next();
    String message = input.next();
    String plainText = message;
    String cipherText = "";
    AESChiper aesChiper = new AESChiper();
    //checking the user input is in a 32digits hexadecimal format or not  
    if (key.matches("[0-9A-F]{32}")) {
      // storing 11 round keys in to roundKey HashMap    
      roundKey = aesChiper.aesRoundKey(key);
      Integer[][] outStateXor = new Integer[4][4];
      Integer[][] outStateNibble = new Integer[4][4];
      Integer[][] outStateShiftRow = new Integer[4][4];
      Integer[][] mixColumn = new Integer[4][4];
      //passing  the 11 round keys for the encryption 
      for (int i = 1; i <= roundKey.size(); i++) {
        // addKey values will be stored in the outStateXor for every roundkey
        outStateXor = aesChiper.aesStateXOR(plainText, roundKey.get(i));
        if (i <= 10) {
          // nibble Substitution value of the addkey will be stored in outStateNibble
          outStateNibble = aesChiper.aesNibbleSub(outStateXor);
          /*
           after performing  the left shift on the rows the result is stored in 
           to the outStateShiftRow
           */
          outStateShiftRow = aesChiper.aesShiftRow(outStateNibble);
        }
        if (i <= 9) {
          /*
           after performing the aesMixColumn the valu willbe store in to mixColumn
           */
          mixColumn = aesChiper.aesMixColumn(outStateShiftRow);
        }
        plainText = "";
        for (int k = 0; k < 4; k++) {
          for (int j = 0; j < 4; j++) {
            if (i <= 9) {
              plainText += String.format("%02X", mixColumn[j][k]);
            } else {
              plainText += String.format("%02X", outStateShiftRow[j][k]);
            }
          }
        }
      }
      for (int k = 0; k < 4; k++) {
        for (int j = 0; j < 4; j++) {
          cipherText += String.format("%02X", outStateXor[j][k]);
        }
      }
    } //if the input from user is not in hexadecimal format terminating the program
    else {
      System.out.println("Invalid input key ");
    }
    System.out.println("ciphertext is" + cipherText);

  }
}
