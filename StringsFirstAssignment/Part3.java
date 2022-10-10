import edu.duke.*;
import java.io.File;
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public boolean twoOccurrences(String stringa, String stringb){
        //boolean result = false;
        if(stringa.isEmpty() ==true || stringb.isEmpty()==true){
         return false;   
        }
        
        if (stringb.indexOf(stringa) == -1){
            return false;
        }
        int count = 0;
        int index =0;
        for (int i = 0;i <= stringb.length();i++){
            index = stringb.indexOf(stringa);
            if(index != -1){
                count++;
                //index++;
                index = index + stringa.length();
                stringb = stringb.substring(index, stringb.length());           
            }
        }
        
        if (count>=2) {
        return true;
        }
        else{
            return false;
        }   
        //return false;
    }
    
    public String lastPart(String a, String b){
        String result = "";
        if(b.indexOf(a) == -1){
            result = b;
        }
        else
        {
         result = b.substring(b.indexOf(a)+a.length());   
        }
        
        return result;
    }
    
    public void testing(){
     String a = "";
     String b = "";
     //1
     a = "an";
     b = "anbanna";
     System.out.println(a + "--in--" + b);
     System.out.println(twoOccurrences(a,b));
     //2
     a = "x";
     b = "uhouuse";
     System.out.println(a + "--in--" + b);
     System.out.println(twoOccurrences(a,b));
     //3
     a = "re";
     b = "treefee";
     System.out.println(a + "--in--" + b);
     System.out.println(twoOccurrences(a,b)); 
     
     System.out.println("---------------------"); 
     
     String c ="";
     String d = "";
     //1
     c = "n";
     d = "Unhappy";
     System.out.println(d + "--after--" + c);
     System.out.println(lastPart(c,d));
     //2
     c = "e";
     d = "Unhappy";
     System.out.println(d + "--after--" + c);
     System.out.println(lastPart(c,d));
     //3
     c = "for";
     d = "forgive";
     System.out.println(d + "--after--" + c);
     System.out.println(lastPart(c,d));
     //4
     c = "an";
     d = "banana";
     System.out.println(d + "--after--" + c);
     System.out.println(lastPart(c,d));
     
    }
    
    public static void main(String [] args){
        Part3 p3 = new Part3();
        p3.testing();
    }
    
} 
