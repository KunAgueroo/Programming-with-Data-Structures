package app;

public class RLEmain {

   public static void main(String[] args) throws Exception{
   
      RLEconverter con = new RLEconverter ();
      
      
      con.compressFile("cherries.txt");
     // con.decompressFile("RLE_heart.txt");
   }
}