import edu.duke.*;
import java.io.File;
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public double cgRatio(String dna){  
        int count = 0;
        
        for(int index = 0; index<dna.length();index++){
            if(dna.indexOf("C", index) == index || dna.indexOf("G", index) == index){
                count++;                
            }            
        }
        //int cIndex = dna.indexOf("C");
        //int gIndex = dna.indexOf("G");
        // int cIndex = 0;
        // int gIndex = 0;
        
        // int index = 0;
        // while(index < dna.length()){
            // if(dna.indexOf("C", index) == index || dna.indexOf("G", index) == index){               
               // count++;
            // }
            // index++;
        // }
        
        // while(cIndex != -1 || gIndex != -1){        
            // if(cIndex != -1){ 
                // //System.out.println("C index: " + cIndex);
                // count++;
                // cIndex = dna.indexOf("C", cIndex + 1);
                // //System.out.println("Count: " + count);
            // }
            // if(gIndex != -1){    
                // //System.out.println("G index: " + gIndex);
                // count++;
                // gIndex = dna.indexOf("G", gIndex + 1);
                // //System.out.println("Count : " + count);
            // }                
        // }
        
        double cgRatio = (double)count/dna.length();
        //System.out.println("Total CG Count: "+count);        
        //return "Count: "+count + "\n" + "cgRatio: " + cgRatio + "\n";
        return cgRatio;
    }

    public void testCGRatio(){
        String dna = "";
        //1
        dna ="ATGCCATAG";
        System.out.println("DNA: "+dna+"\n"+ cgRatio(dna)); 
        //2
        dna ="GCATGCCCTAA";
        System.out.println("DNA: "+dna+"\n"+ cgRatio(dna));
        
        System.out.println("-----------------END!!-----------------");
    }
        
    public int countCTG(String dna){
        int ctgCount = 0;
        int startIndex = 0;
        int currIndex = 0;
        
        while(true){
            currIndex = dna.indexOf("CTG", startIndex);
            
            if(currIndex == -1){
                break;
            }  
            
            ctgCount++;
            startIndex = currIndex + 3;                   
        }
        
        return ctgCount;
    }
        
    public void testCountCTG(){
        String dna = "";
        //1
        dna ="ATGCTGTAGAACTG";
        System.out.println("DNA: "+dna+"\n"+ "CTG Count: " + countCTG(dna)); 
        //2
        dna ="CTGCTGCTGCTG";
        System.out.println("DNA: "+dna+"\n"+ "CTG Count: " + countCTG(dna));
        
        // FileResource fr = new FileResource("GRch38dnapart.fa");
        // dna = fr.asString();
        // System.out.println("DNA: "+dna+"\n" + dna.length()+"\n"+dna.indexOf("CTG"));
        //System.out.println("DNA: "+dna+"\n"+ "CTG Count: " + countCTG(dna));
        
        URLResource ur = new URLResource("https://users.cs.duke.edu/~rodger/GRch38dnapart.fa");
        dna = ur.asString();
        System.out.println("DNA: "+dna+"\n" + "Length: "+dna.length()+"\n");
        System.out.println("CTG Count: " + countCTG(dna));
        // System.out.println("DNA: "+dna+"\n"+ "CTG Count: " + countCTG(dna));
        
        
        System.out.println("-----------------END!!-----------------");
    }
    
    public void processGenes(StorageResource sr){
        int lenGrtCount = 0;
        int cgRatioCount = 0;
        int longestStringLength = 0;
        String longestString = "";        
        int geneCount = 0;
        
        for(String gene : sr.data()){            
            geneCount++;
            System.out.println("GENE" + geneCount + ": " + gene);
            
            if(gene.length()>60){                
                System.out.println("Length>60: TRUE");
                lenGrtCount++;
            }
            else
            {
                System.out.println("Length>60: FALSE");
            }
            
            if(cgRatio(gene)>0.35){                
                System.out.println("cgRatio>0.35: TRUE" + "\n");
                cgRatioCount++;
            }
            else
            {
                System.out.println("cgRatio>0.35: FALSE" + "\n");
            }
            
            if(gene.length()>longestStringLength){
                longestString = gene;
                longestStringLength = longestString.length();
            }
        }
        
        System.out.println("--------------------Report--------------------");
        System.out.println("Total Genes: " + geneCount);
        System.out.println("No. of genes with length > 60: " + lenGrtCount);
        System.out.println("No. of genes with cgRatio > 0.35: " + cgRatioCount);
        System.out.println("Longest gene: " + longestString + "\n" + 
                                           "Length: " + longestStringLength);                            
    }
    
    public void testProcessGenes(){
        StorageResource dnaList = new StorageResource();
        String dna = "";
        
        dna = "ATGcgccccTAA";
        //System.out.println("DNA :"+dna);
        dnaList.add(dna);
        
        dna = "ATGcccataTGA";
        //System.out.println("DNA :"+dna);
        dnaList.add(dna);
        
        dna = "ATGcgcccccccTAA";
        //System.out.println("DNA :"+dna);
        dnaList.add(dna);
        
        dna = "ATGcgcTAG";
        //System.out.println("DNA :"+dna);
        dnaList.add(dna);
        
        dna = "ATGcgcTAA";
        //System.out.println("DNA :"+dna);
        dnaList.add(dna);
        
        dna = "ATGaaatttTGA";
        //System.out.println("DNA :"+dna);
        dnaList.add(dna);
        
        processGenes(dnaList);
    }     
           
        public static void main(String[] args){
        Part2 p2 = new Part2();
        //p2.testCGRatio();
        p2.testCountCTG();
        //p2.testProcessGenes();
    }
}
