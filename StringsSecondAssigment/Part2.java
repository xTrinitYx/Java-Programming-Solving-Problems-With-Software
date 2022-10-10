//import edu.duke.*;
//import java.io.File;
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public int howMany(String a, String b){
        int count = 0;
        int aStartIndex = b.indexOf(a);
        while(aStartIndex != -1){
        aStartIndex = b.indexOf(a, aStartIndex + a.length());
        count++;
        }       
        return count;
    }
    
    public void testHowMany(){
        String a = "";
        String b = "";
        //1
        a = "AN";
        b = "BANANA";
        System.out.println("Count of " + "\""+a+"\""+ " in "+ "\""+b+"\":" + "\n" + howMany(a,b));
        //2
        a = "X";
        b = "XEROX";
        System.out.println("Count of " + "\""+a+"\""+ " in "+ "\""+b+"\":" + "\n" + howMany(a,b));
        //3
        a = "1";
        b = "APPLE";
        System.out.println("Count of " + "\""+a+"\""+ " in "+ "\""+b+"\":" + "\n" + howMany(a,b));
        //4
        a = "APPLE";
        b = "APPLE";
        System.out.println("Count of " + "\""+a+"\""+ " in "+ "\""+b+"\":" + "\n" + howMany(a,b));
        //5
        a = "O";
        b = "POOR";
        System.out.println("Count of " + "\""+a+"\""+ " in "+ "\""+b+"\":" + "\n" + howMany(a,b));
        
        System.out.println("----------------END!!---------------");
    }

    public static void main(String[] args){
    Part2 p2 = new Part2();
    p2.testHowMany();
    }
}
