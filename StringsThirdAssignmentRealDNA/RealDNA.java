import edu.duke.*;
import java.io.File;
/**
 * Write a description of RealDNA here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RealDNA {
    public float cgRatio(String dna){
        float cgRatio;
        int cIndex = dna.indexOf("C");
        int gIndex = dna.indexOf("G");    
        int count = 0;
        while(cIndex != -1 || gIndex != -1){        
            if(cIndex != -1){ 
                //System.out.println("C index: " + cIndex);
                count++;
                cIndex = dna.indexOf("C", cIndex + 1);
                //System.out.println("Count: " + count);
            }
            if(gIndex != -1){    
                //System.out.println("G index: " + gIndex);
                count++;
                gIndex = dna.indexOf("G", gIndex + 1);
                //System.out.println("Count : " + count);
            }                
        }
        System.out.println("Total CG Count: "+count);
        cgRatio = (float)count/dna.length();
        //return "Count: "+count + "\n" + "cgRatio: " + cgRatio + "\n";
        return cgRatio;
    }
    
    public void processGenes(StorageResource sr){
        int lenGrtNineCount = 0;
        int cgRatioCount = 0;
        int longestStringLength = 0;
        String longestString = "";
        String stringUC = "";
        for(String string : sr.data()){
            System.out.println("DNA: " + string);
            stringUC = string.toUpperCase();
            if(stringUC.length()>9){
                //System.out.println("DNA: " + string);
                System.out.println("Length>9: TRUE");
                lenGrtNineCount++;
            }
            else
            {
                System.out.println("Length>9: FALSE");
            }
            if(cgRatio(stringUC)>0.35){
                //System.out.println("cgRatio>0.35: " + "\""+string+"\"");
                System.out.println("cgRatio>0.35: TRUE" + "\n");
                cgRatioCount++;
            }
            else
            {
                System.out.println("cgRatio>0.35: FALSE" + "\n");
            }
            if(string.length()>longestStringLength){
                longestString = string;
                longestStringLength = string.length();
            }
        }
        System.out.println("No. of strings with length>9: "+lenGrtNineCount);
        System.out.println("No. of strings with cgRatio>0.35: "+cgRatioCount);
        System.out.println("Longest String: "+"\""+longestString+"\""
                            +", Length: " + longestStringLength);
    }
    
    public void testProcessGenes(){
        FileResource fr = new FileResource();
        String dnaFile = fr.asString();
        StorageResource dnaList = new StorageResource();
        Part1 p1 = new Part1();
        dnaList = Part1.getAllGenes(dnaFile);
        
        
        
        
        // String dna = "";
        
        // dna = "ATGcgccccTAA";
        // //System.out.println("DNA :"+dna);
        // dnaList.add(dna);
        
        // dna = "ATGcccataTGA";
        // //System.out.println("DNA :"+dna);
        // dnaList.add(dna);
        
        // dna = "ATGcgcccccccTAA";
        // //System.out.println("DNA :"+dna);
        // dnaList.add(dna);
        
        // dna = "ATGcgcTAG";
        // //System.out.println("DNA :"+dna);
        // dnaList.add(dna);
        
        // dna = "ATGcgcTAA";
        // //System.out.println("DNA :"+dna);
        // dnaList.add(dna);
        
        // dna = "ATGaaatttTGA";
        // //System.out.println("DNA :"+dna);
        // dnaList.add(dna);
        
        processGenes(dnaList);
    }  

    public static void main (String[] args){
        RealDNA dnaObj = new RealDNA();
        
    }
}
