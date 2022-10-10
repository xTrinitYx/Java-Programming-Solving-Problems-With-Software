import edu.duke.*;
/**
 * Write a description of ReadFile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ReadFile {
    public void runFileRead () {
        FileResource sFile = new FileResource("Sample.txt");
        for(String readLine : sFile.lines()){
         System.out.println(readLine);   
        }
    }
    public static void main(String[] args){
        ReadFile objRF = new ReadFile();
        objRF.runFileRead();
    }
}
