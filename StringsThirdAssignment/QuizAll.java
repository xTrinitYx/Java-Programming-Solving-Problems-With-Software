import edu.duke.*;
import java.io.File;
/**
 * Write a description of Part3RealDNA here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class QuizAll {    
    public int findStopCodon(String dna, int startIndex, String stopCodon){   
        int stopCodonIndex = dna.indexOf(stopCodon, startIndex + 3);
        
        while(true){ 
            
            if (stopCodonIndex == -1){
                break;
            }
            
            int diffMod = stopCodonIndex - startIndex;
            
            if (diffMod % 3 == 0){
                return stopCodonIndex;
            }
            else
            {  
                stopCodonIndex = dna.indexOf(stopCodon, stopCodonIndex + 1);                   
            }
        }        
        //System.out.println("No stop codon found");
        return -1;
    }
    
    public String findGene(String dna){
        int startIndex = dna.indexOf("ATG");
        
        if(startIndex == -1){
            return "";
        }
        
        int taaIndex = findStopCodon(dna,startIndex,"TAA");
        int tagIndex = findStopCodon(dna,startIndex,"TAG");
        int tgaIndex = findStopCodon(dna,startIndex,"TGA");
        
        int minIndex = 0;
        //minIndex = Math.min(taaIndex, Math.min(tagIndex,tgaIndex)); 
        
        if(taaIndex == -1 || (tagIndex != -1 && tagIndex<taaIndex)){
            minIndex = tagIndex;
        }
        else{        
            minIndex = taaIndex;
        }
        
        if(minIndex == -1||(tgaIndex != -1 && tgaIndex<minIndex)){
            minIndex = tgaIndex;
        }
        
        if(minIndex == -1){
            return "";
        }       
        
        return dna.substring(startIndex, minIndex + 3);
    }    
    
    public void printAllGenes(String dna){
        int startIndex = 0;
        
        if(dna.isEmpty()==true){
            System.out.println("Empty DNA strand");
        }
        
        int geneCounter = 0;
        String gene = "";
        
        while(true){
            //gene = findGene(dna, startIndex);
            gene = findGene(dna);
            
            if(gene.isEmpty()){                
                break;
            }
            
            geneCounter++;
            System.out.println("Gene "+ geneCounter +": " + gene);
            startIndex = dna.indexOf(gene) + gene.length();
            dna = dna.substring(startIndex);
            //System.out.println("Substring: "+dna);
        }
        
        System.out.println("Done; Out of while loop!");
    }
    
    public StorageResource getAllGenes(String dna){
        int startIndex = 0;
        StorageResource geneList = new StorageResource();
        
        if(dna.isEmpty()==true){
            System.out.println("Empty DNA strand");
        }
        
        int geneCounter = 0;
        String gene = "";
        
        while(true){
            //gene = findGene(dna, startIndex);
            gene = findGene(dna);
            
            if(gene.isEmpty()){ 
                break;
            }
            
            geneList.add(gene);                
            geneCounter++;
            //System.out.println("Gene "+ geneCounter +": " + gene);
            startIndex = dna.indexOf(gene) + gene.length();
            dna = dna.substring(startIndex);
            //System.out.println("Substring: "+dna);
            }        
        
        System.out.println("Done; Got all Genes in List!");
        return geneList;
    }
    
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
        //FileResource fr = new FileResource("brca1.fa");
        //FileResource fr = new FileResource("brca1line.fa");
        //String dnaFileString = fr.asString().toUpperCase();
        //System.out.println(dnaFileString);    
        //StorageResource geneList = getAllGenes(dnaFileString);        
        //processGenes(geneList);
        //System.out.println("\n"+"Gene List size: "+geneList.size()+"\n");
        
        //Quiz
            URLResource ur = new URLResource("https://users.cs.duke.edu/~rodger/GRch38dnapart.fa");
            String dnaFileString = ur.asString().toUpperCase();            
            //System.out.println(dnaFileString);      
            //System.out.println("CTG Count: " + countCTG(dnaFileString) + "\n");
            StorageResource store = getAllGenes(dnaFileString);
            processGenes(store);
            //System.out.println("\n"+"Gene List size: "+store.size()+"\n");
        //           
        
        System.out.println("-----------------END!!-------------------");               
    }
         
           
        public static void main(String[] args){
        QuizAll quiz = new QuizAll();
        //quiz.testCGRatio();
        //quiz.testCountCTG();
        quiz.testProcessGenes();
    }

}
