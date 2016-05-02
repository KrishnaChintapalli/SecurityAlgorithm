package AESALGORITHM_192;

/**
 * file: Driver.java author: SivaChintapalli
 * course: MSCS_630L_231_16S
 * assignment: Project due date: May 03, 2016 version: 1.0 
 * his file contains program of AES Algorithm which will generate the round keys 
 * 
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * Driver class which reads the input from the file in the hex decimal format
 * and generate the cipher text with help of round keys
 *
 * @author SIVARAMAKRISHNAPRASA
 */
public class Driver {

  private static HashMap<Integer, String> roundKey = new HashMap<>();
  private static List<String> decryptMsgList = new ArrayList<>();

  public static void main(String[] args) {
    // takes the input from the user and stores in to the key
    System.out.println("Enter 0 for 128-bit encryption");
    System.out.println("Enter 1 for 192-bit encryption");
    System.out.println("Enter 2 for 256-bit encryption");
    Scanner input = new Scanner(System.in);
    String encryptionType = input.next();
    System.out.println("Enter 'e' encryption");
    System.out.println("Enter 'd' decryption");
    String mode = input.next();
    String key = input.next();
    String cipherText = "";
    AESCipher aesChiper = new AESCipher();
    if (encryptionType.matches("[0-2q]{1}")) {
      switch (encryptionType) {
        case "0":
          if (key.matches("[0-9a-fA-F]{32}")) {
            roundKey = aesChiper.aesRoundKey(key);
          } else {
            System.out.println("invalid key size entered");
          }
          break;
        case "1":
          if (key.matches("[0-9a-fA-F]{48}")) {
            roundKey = aesChiper.aesRoundKey(key);
          } else {
            System.out.println("invalid key size entered");
          }
          break;
        case "2":
          if (key.matches("[0-9a-fA-F]{64}")) {
            roundKey = aesChiper.aesRoundKey(key);
          } else {
            System.out.println("invalid key size entered");
          }
          break;
      }
      if (!roundKey.isEmpty()) {
        int rounds = roundKey.size();
        int encryptType;
        if (rounds == 11) {
          encryptType = 128;
        } else if (rounds == 13) {
          encryptType = 192;
        } else {
          encryptType = 256;
        }
        String message = input.next();
        if(message.length()%2==0){
          String plainText = "";
        int paddingSize;
        String[] array = new String[message.length() / 4];
        if (message.length() != 32) {
          if (message.length() > 32) {
            int x = 0;
            String temp = "";
            for (int j = 0; j < message.length(); j++) {
              if (temp.length() < 32) {
                temp += message.charAt(j);
              } else {
                array[x] = temp;
                temp = "";
                temp += message.charAt(j);
                x++;
              }
            }
            array[x] = temp;
          }
          if (message.length() < 32) {
            array[0] = messagePadding(message);
          }
        } else {
          array[0] = message;
        }
        for (String array1 : array) {
          if (array1 != null) {
            if (array1.length() == 32) {
              plainText = array1;
            } else {
              plainText = messagePadding(array1);
            }
            if (mode.equals("e")) {
              aesChiper.encryption(plainText, encryptType);
            }
            if (mode.equals("d")) {
              String decryptMsg = aesChiper.decryption(plainText);
              decryptMsgList.add(decryptMsg);
            }
          }
        }
        }
        else{
          System.out.println("Invalid PlainText entered");
        }
        
        
        for (int k = 0; k < decryptMsgList.size(); k++) {
          if(k== decryptMsgList.size()-1){
          int count = 0;
          String PaddedMsg = decryptMsgList.get(k);
          String paddedVal = PaddedMsg.substring(30, 32);
          for (int i = 0; i < 16; i++) {
            String val = PaddedMsg.charAt(i * 2) + "" + PaddedMsg.charAt(i * 2 + 1);
            if (paddedVal.equals(val)) {
              count++;
            }
          }
           if (count == Integer.parseInt(paddedVal)) {
            PaddedMsg = PaddedMsg.replace(paddedVal, "");
          }
           System.out.print(encryptType + "-bit decrypted message is " + PaddedMsg);
        
        }else{
          System.out.print(encryptType + "-bit decrypted message is " + decryptMsgList.get(k));
        }
        }     
      }
    } else {
      System.out.println("invalid input enter");
    }
  }
  public static String messagePadding(String message) {
    StringBuffer str = new StringBuffer(message);
    int paddingSize = 32 - message.length();
    for (int i = 0; i < paddingSize / 2; i++) {
      str = str.append("0").append(paddingSize / 2);
    }
    return str.toString();
  }
}
