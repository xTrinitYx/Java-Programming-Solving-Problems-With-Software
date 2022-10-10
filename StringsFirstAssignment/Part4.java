import edu.duke.*;
import java.io.File;
/**
 * Write a description of Part4alt here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part4 {
    public void findURL(String url, String search){
        String resultURL = "";
        //url = "https://www.dukelearntoprogram.com/course2/data/manylinks.html";
        //search = "youtube.com";
        URLResource urObj = new URLResource(url);        
        for (String word : urObj.words()){
            //System.out.println(word);
            String lcaseWord = word.toLowerCase();
            int pos = lcaseWord.indexOf(search);
            if(pos != -1){             
                //int startIndex = word.indexOf("\"");
                //int lastIndex = word.indexOf("\"", startIndex + 1);
                int startIndex = word.lastIndexOf("\"",pos);
                int endIndex = word.indexOf("\"",pos + 1);
                resultURL = word.substring(startIndex + 1, endIndex);            
                System.out.println(resultURL);
            }            
        }  
        System.out.println("----------DONE!!!-----------");
    }
    
    public void testURL(){
     String url = "https://www.dukelearntoprogram.com//course2/data/manylinks.html";
     String search = "youtube.com";
     findURL(url, search);
    }
    
    public void check(){
    String a = "(href=\"https://www.youtube.com/watch?feature=player_embedded&v=MOXQo7nURs0\">\"ExperienceThePowerOfA-bookbook\"";
    int startIndex = a.indexOf("\"");
    int stopIndex = a.indexOf("\"",startIndex + 1);
    //int stopIndex = a.lastIndexOf("\"", a.substring(startIndex).length());
    System.out.println(a.substring(startIndex + 1,stopIndex));       
    }
    
    public static void main(String [] args){
        Part4 p4 = new Part4();
        //p4.findURL("https://www.dukelearntoprogram.com//course2/data/manylinks.html", "youtube.com");
        //p4.check();
        p4.testURL();                
    }

}
