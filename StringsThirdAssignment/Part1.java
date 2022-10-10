import edu.duke.*;
import java.io.File;
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 { 
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
    
    public void testFindStopCodon(){
        String dna = "";
        int startIndex = 0;
        String stopCodon = "";
        //1
        dna = "CATGCATCATCATAAGC";
        startIndex = dna.indexOf("ATG");
        stopCodon = "TAA";
        System.out.println("DNA Strand: "+dna);
        System.out.println("Stop Codon Index: "+findStopCodon(dna, startIndex, stopCodon));
        //2
        dna = "CCATGCATCATCACTAGC";
        startIndex = dna.indexOf("ATG");
        stopCodon = "TAG";
        System.out.println("DNA Strand: "+dna);
        System.out.println("Stop Codon Index: "+findStopCodon(dna, startIndex, stopCodon));
        //3
        dna = "CCATGCATCATCACTGAC";
        startIndex = dna.indexOf("ATG");
        stopCodon = "TGA";
        System.out.println("DNA Strand: "+dna);
        System.out.println("Stop Codon Index: "+findStopCodon(dna, startIndex, stopCodon));
        System.out.println("-------------End-------------");

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
    
    public void testFindGene(){
        String dna = "";
        //1
        dna = "cATGchcTGAaccTAG";
        System.out.println("DNA Strand: "+dna);
        System.out.println("Gene: "+findGene(dna));
        //2
        dna = "cgATGcagTAAcgcTAG";
        System.out.println("DNA Strand: "+dna);
        System.out.println("Gene: "+findGene(dna));
        //3
        dna = "cgTAAgctTAG";
        System.out.println("DNA Strand: "+dna);
        System.out.println("Gene: "+findGene(dna));
        //4
        dna = "tcATGacvdTAAcgTAG";
        System.out.println("DNA Strand: "+dna);
        System.out.println("Gene: "+findGene(dna));
        //5
        dna = "tcATGacvdTATGcgTAG";
        System.out.println("DNA Strand: "+dna);
        System.out.println("Gene: "+findGene(dna));
        //Quiz
        dna = "AATGCTAACTAGCTGACTAAT";
        System.out.println("DNA Strand: "+dna);
        System.out.println("Gene: "+findGene(dna));
        System.out.println("-------------End-------------");
        
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
    
    public void testPrintAllGenes(){
        String dna = "";
        //1
        dna = "cATGcgtTAAcccATGctcTGAcgcaATGcgcTAG";
        System.out.println("DNA strand: " + dna);
        printAllGenes(dna);        
        //2
        dna = "cATGcgtaTAAcccATGctcTGAcgcaATGcgcTAG";
        System.out.println("DNA strand: " + dna);
        printAllGenes(dna);  
        //3
        dna = "TAAcccATGcgcTAAccATGcgctacTAG";
        System.out.println("DNA strand: " + dna);
        printAllGenes(dna); 
        //4
        dna = "cATGcgtaTAAcccATGctcTGAcgcaATGcgcTAG";
        System.out.println("DNA strand: " + dna);
        printAllGenes(dna);
        //5
        dna = "ATGcccTAGcATGcccgtcATGcccTAGccATGcgtcagTAA";
        System.out.println("DNA strand: " + dna);
        printAllGenes(dna); 
        //6
        dna = "ATGcccTAGcATGcccgtcATGcccTAGccATGcgtcagTAAcca";
        System.out.println("DNA strand: " + dna);
        printAllGenes(dna); 
        //7
        dna = "";
        System.out.println("DNA strand: " + dna);
        printAllGenes(dna);
        System.out.println("-------------END!!!-------------");
    }
    
    public int countGenes(String dna){
        int geneCount = 0;
        String gene = "";
        
        while(true){
            
            if(findGene(dna).isEmpty()==false){
                gene = findGene(dna);
                dna = dna.substring(dna.indexOf(gene) + gene.length());
                geneCount++;
            }
            else
            {            
                if(dna.isEmpty()){
                    break;    
                }
                dna = dna.substring(dna.indexOf(findGene(dna)) + 3);
            }
        }
        
        return geneCount;
    }   
        
    public void testCountGenes(){
        String dna = "";
        //1
        dna = "cATGcgtTAAcccATGctcTGAcgcaATGcgcTAG";
        System.out.println("DNA strand: " + dna);
        printAllGenes(dna); 
        System.out.println("Count: "+ countGenes(dna));
        //2
        dna = "cATGcgtaTAAcccATGctcTGAcgcaATGcgcTAG";
        System.out.println("DNA strand: " + dna);
        printAllGenes(dna);  
        System.out.println("Count: "+ countGenes(dna));
        //3
        dna = "TAAcccATGcgcTAAccATGcgctacTAG";
        System.out.println("DNA strand: " + dna);
        printAllGenes(dna);
        System.out.println("Count: "+ countGenes(dna));
        //4
        dna = "cATGcgtaTAAcccATGctcTGAcgcaATGcgcTAG";
        System.out.println("DNA strand: " + dna);
        printAllGenes(dna);
        System.out.println("Count: "+ countGenes(dna));       
        //5
        dna = "ATGcccTAGcATGcccgtcATGcccTAGccATGcgtcagTAA";
        System.out.println("DNA strand: " + dna);
        printAllGenes(dna); 
        System.out.println("Count: "+ countGenes(dna));
        //6
        dna = "ATGcccTAGcATGcccgtcATGcccTAGccATGcgtcagTAAcca";
        System.out.println("DNA strand: " + dna);
        printAllGenes(dna); 
        System.out.println("Count: "+ countGenes(dna));
        //7
        dna = "";
        System.out.println("DNA strand: " + dna);
        printAllGenes(dna);
        System.out.println("Count: "+ countGenes(dna));
        System.out.println("-------------END!!!-------------");
        
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
    
    public void testGetAllGenes(){
        String dna = "";
        //1
        dna = "cATGcgtTAAcccATGctcTGAcgcaATGcgcTAG";
        System.out.println("DNA strand: " + dna);
        printAllGenes(dna);   
        getAllGenes(dna);
        for(String gene : getAllGenes(dna).data()){
            System.out.println("Gene List: " + gene);
        }
        //2
        dna = "cATGcgtaTAAcccATGctcTGAcgcaATGcgcTAG";
        System.out.println("DNA strand: " + dna);
        printAllGenes(dna);
        getAllGenes(dna);
        for(String gene : getAllGenes(dna).data()){
            System.out.println("Gene List: " + gene);
        }
        //3
        dna = "TAAcccATGcgcTAAccATGcgctacTAG";
        System.out.println("DNA strand: " + dna);
        printAllGenes(dna);
        getAllGenes(dna);
        for(String gene : getAllGenes(dna).data()){
            System.out.println("Gene List: " + gene);
        }
        //4
        dna = "cATGcgtaTAAcccATGctcTGAcgcaATGcgcTAG";
        System.out.println("DNA strand: " + dna);
        printAllGenes(dna);
        getAllGenes(dna);
        for(String gene : getAllGenes(dna).data()){
            System.out.println("Gene List: " + gene);
        }
        //5
        dna = "ATGcccTAGcATGcccgtcATGcccTAGccATGcgtcagTAA";
        System.out.println("DNA strand: " + dna);
        printAllGenes(dna); 
        getAllGenes(dna);
        for(String gene : getAllGenes(dna).data()){
            System.out.println("Gene List: " + gene);
        }
        //6
        dna = "ATGcccTAGcATGcccgtcATGcccTAGccATGcgtcagTAAcca";
        System.out.println("DNA strand: " + dna);
        printAllGenes(dna); 
        getAllGenes(dna);
        for(String gene : getAllGenes(dna).data()){
            System.out.println("Gene List: " + gene);
        }
        //7
        dna = "";
        System.out.println("DNA strand: " + dna);
        printAllGenes(dna);
        getAllGenes(dna);
        for(String gene : getAllGenes(dna).data()){
            System.out.println("Gene List: " + gene);
        }
        
        System.out.println("-------------END!!!-------------");
    }
            
    public static void main(String[] args){
    Part1 p1 = new Part1();
    //p3.testCountGenes();
    //p3.testFindGene();
    p1.testGetAllGenes();    
    }
}
