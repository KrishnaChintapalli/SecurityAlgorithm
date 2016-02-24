/**
 * file: Driver.java
 * author: SivaChintapalli
 * course: MSCS_630L_231_16S
 * assignment: Lab 2
 * due date: February 23, 2016
 * version: 1.0
 */

import java.util.HashMap;
import java.util.Scanner;

public class Driver {
 static HashMap<Integer,String> roundKey = new HashMap<>();
    public static void main(String[] args) {
        // takes the input from the user and stores in to the key
    Scanner input = new Scanner(System.in);
    String key = input.next();
    //checking the user input is in a 32digits hexadecimal format or not  
    if(key.matches("[0-9A-F]{32}")){
       AESCipher aesCipher = new AESCipher();
// storing 11 round keys in to roundKey HashMap    
    roundKey =aesCihper.aesRoundKey(key); 
    //printing the 11 round keys 
        for(int i=0;i<=roundKey.size();i++){
            System.out.println(roundKey.get(i));
        }
    } 
    //if the input from user is not in hexadecimal format terminating the program
    else {
        System.out.println("Invalid input key ");
     }
    
    
    }
}
