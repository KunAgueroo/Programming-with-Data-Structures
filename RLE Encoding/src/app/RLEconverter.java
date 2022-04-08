package app;
import java.util.Scanner;
import java.io.*;

public class RLEconverter {
   private char[] fileChars; // stores unique chars as they appear in the file.
   private final static int DEFAULT_LEN = 100; // used to create arrays.
   private int numLines; // number of lines in the file that is read.

   /*
    * Initialize the char array to length 2. as the number of characters is 2 for
    * any file.
    */
   public RLEconverter() {
      fileChars = new char[2]; // fileChars[0] = '0', fileChars[1] = '1'
      numLines = 0;
   }
   /*
    * This method reads in an uncompressed ascii image file that contains 2
    * characters. It stores each line of the file in an array. It then calls
    * compressLines to get an array that stores the compressed version of each
    * uncompressed line from the file. The compressed array is then passed to the
    * getCompressedFileStr method which returns a String of all compressed lines
    * (the two charcaters are written in the first line) in CSV format. This String
    * is written to a text file with the prefix "RLE_" added to the original,
    * uncompressed file name.
    */
   public void compressFile(String fileName) throws IOException {
      Scanner scan = new Scanner(new FileReader(fileName));
      String line = null;
      String[] decompressed = new String[DEFAULT_LEN];
      numLines = 0;
      while (scan.hasNext()) {
         line = scan.next();
         if (line != null && line.length() > 0)
            decompressed[numLines] = line;
         numLines++;
      }
      scan.close();
      String[] compressed = compressLines(decompressed);
      writeFile(getCompressedFileStr(compressed, fileChars), "RLE_" + fileName);
   }
   /*
    * This method implements the RLE compression algorithm. It takes a line of
    * uncompressed data from an ascii file and returns the RLE encoding of that
    * line in CSV format. The two characters that make up the image file are passed
    * in as a char array, where the first cell contains the first character that
    * occurred in the file.
    */
   public String compressLine(String line, char[] fileChars) {
      String res = "";
      // TODO1: Implement this method
      int charIndex = 0;
      int count = 0;
      for (int i = 0; i < line.length(); i++) {
         if (line.charAt(i) == fileChars[charIndex]) {
            count++;
         } else {
            res = res + count + ",";
            charIndex++;
            charIndex = charIndex % 2;
            count = 1;
         }
      }
      res += count;
      return res;
   }
   /*
    * This method discovers the two ascii characters that make up the image. It
    * iterates through all of the lines and writes each compressed line to a String
    * array which is returned. The method compressLine is called on each line.
    */
   public String[] compressLines(String[] lines) {
      String[] compressed = null;
      // TODO2: Implement this method
      fileChars[0] = lines[0].charAt(0);
      for (int j = 0; j < numLines; j++) {
         for (int i = 0; i < numLines; i++)
            if (lines[j].charAt(i) != fileChars[0]) {
               fileChars[1] = lines[j].charAt(i);
            }
      }
      compressed = new String[numLines];
      for (int i = 0; i < numLines; i++) {
         compressed[i] = compressLine(lines[i], fileChars);
      }
      return compressed;
   }
   /*
    * This method assembles the lines of compressed data for writing to a file. The
    * first line must be the 2 ascii characters in comma-separated format.
    */
   public String getCompressedFileStr(String[] compressed, char[] fileChars) {
      String data = "";
      data = fileChars[0] + "," + fileChars[1] + "\n";
      // TODO3: Implement this method
      for (int i = 0; i < numLines; i++) {
         data += compressed[i] + "\n";
      }
      return data;
   }
   /*
    * This method reads in an RLE compressed ascii image file that contains 2
    * characters. It stores each line of the file in an array. It then calls
    * decompressLines to get an array that stores the decompressed version of each
    * compressed line from the file. The first row contains the two ascii
    * charcaters used in the original image file. The decompressed array is then
    * passed to the getDecompressedFileStr method which returns a String of all
    * decompressed lines, thus restoring the original, uncompressed image. This
    * String is written to a text file with the prefix "DECOMP_" added to the
    * original, compressed file name.
    */
   public void decompressFile(String fileName) throws IOException {
      Scanner scan = new Scanner(new FileReader(fileName));
      String line = null;
      String[] compressed = new String[DEFAULT_LEN];
      numLines = 0;
      while (scan.hasNext()) {
         line = scan.next();
         if (line != null && line.length() > 0)
            compressed[numLines] = line;
         numLines++;
      }
      scan.close();
      String[] decompressed = decompressLines(compressed);
      writeFile(getDecompressedFileStr(decompressed), "DECOMP_" + fileName);
   }
   /*
    * This method decodes lines that were encoded by the RLE compression algorithm.
    * It takes a line of compressed data and returns the decompressed, or original
    * version of that line. The two characters that make up the image file are
    * passed in as a char array, where the first cell contains the first character
    * that occurred in the file.
    */
   public String decompressLine(String line, char[] fileChars) {
      String res = "";
      // TODO4: Implement this method
      String numberString = "";
      int number = 0;
      int charPointer = 0;

      for (int i = 0; i < line.length(); i++) {
         if (line.charAt(i) == ',') {
            number = Integer.parseInt(numberString);
            for (int j = 0; j < number; j++) {
               res += fileChars[charPointer];
            }
            numberString = "";
            number = 0;
            charPointer++;
            charPointer = charPointer % 2;
         } else {
            numberString += line.charAt(i);
         }
      }
      number = Integer.parseInt(numberString);
      for (int j = 0; j < number; j++) {
         res += fileChars[charPointer];
      }
      return res;
   }
   /*
    * This method iterates through all of the compressed lines and writes each
    * decompressed line to a String array which is returned. The method
    * decompressLine is called on each line. The first line in the compressed array
    * passed in are the 2 ascii characters used to make up the image.
    */
   public String[] decompressLines(String[] lines) {
      String[] decompressed = null;
      // TODO5: Implement this method
      fileChars[0] = lines[0].charAt(0);
      fileChars[0] = lines[0].charAt(2);
      decompressed = new String[numLines];
      for (int i = 1; i <= numLines; i++) {
         decompressed[i] = decompressLine(lines[i], fileChars);
      }
      return decompressed;
   }
   /*
    * This method assembles the lines of decompressed data for writing to a file.
    */
   public String getDecompressedFileStr(String[] decompressed) {
      String data = "";
      // TODO6: Implement this method
      for (int i = 0; i < numLines; i++) {
         data += decompressed[i] + "\n";
      }
      return data;
   }

   public void writeFile(String data, String fileName) throws IOException {
      PrintWriter pw = new PrintWriter(fileName);
      pw.print(data);
      pw.close();
   }
}