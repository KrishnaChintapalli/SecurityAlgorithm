import java.util.Scanner;
public class Driver{
public static void main(String[] args){
Scanner input = new Scanner(System.in);
String m =input.nextLine();
while(input.hasNext()){
m = m+"\n"+ input.nextLine();
}
System.out.println(m);
String mHex =mToHex(m);
System.out.println(mHex);
String mHexBlocks[][] =mHexToBlocks(mHex, 128);
for (int i = 0; i < 4; i++) {
System.out.println();
      for (int j = 0; j < 4; j++) {
System.out.print(mHexBlocks[i][j]);
}
}
}
protected static String mToHex(String m){
StringBuilder temp = new StringBuilder();
for (char ch: m.toCharArray()) {
    if (temp.length() > 0)
      temp.append(' ');
    temp.append(String.format("%2x", (int) ch));
  }
  return temp.toString();
}
protected static String[][] mHexToBlocks(String mHex,int bSize){
int matrixSize = bSize/mHex.length();
int row = matrixSize;
    int coloumn = matrixSize;
    String[][] mHexBlock = new String[row][coloumn];
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < coloumn; j++) {
        // coping  input key into 4*4 matrix
        mHexBlock[i][j] = String.valueOf(mHex.charAt(row*i
+j));
        
      }
    }

    return mHexBlock;
}
}
