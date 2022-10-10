import edu.duke.*;
import java.io.File;
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1alt {
    public int findStopCodon(String dna, int startIndex, String stopCodon){   
        int stopCodonIndex = dna.indexOf(stopCodon, startIndex + 3);
        //int startCodonIndex = startIndex;
        while(stopCodonIndex != -1){
            int diffMod = stopCodonIndex - startIndex;
            if (diffMod % 3 == 0){
                return stopCodonIndex;
            }
            else
            {  
                stopCodonIndex = dna.indexOf(stopCodon, stopCodonIndex + 1);
                //startCodonIndex = dna.indexOf("ATG", stopCodonIndex);
                //stopCodonIndex = dna.indexOf(stopCodon, startCodonIndex + 3);                 
            }
        }
        //return dna.length();
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
            //return "No Start Codon: ATG";
            return "";
        }        
        int taaIndex = findStopCodon(dna,startIndex,"TAA");
        int tagIndex = findStopCodon(dna,startIndex,"TAG");
        int tgaIndex = findStopCodon(dna,startIndex,"TGA");
        //Find lowest stop codon index
        //int stopIndex = Math.min(taaIndex,Math.min(tagIndex,tgaIndex)); 
        int minIndex = 0;
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
            //return "Empty: No Gene Found";
            //System.out.println("Empty: No Gene Found");
            return "";
        }       
        startIndex = dna.lastIndexOf("ATG", minIndex);
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
        System.out.println("-------------End-------------");
        
    }
    
    public void printAllGenes(String dna){
        int startIndex = 0;
        if(dna.isEmpty()==true){
            System.out.println("Empty DNA strand");
        }
        int geneCounter = 1;
        String gene = "";
        while(true){
            if(findGene(dna).isEmpty()==false){
                gene = findGene(dna);                
                System.out.println("Gene "+ geneCounter +": " + gene);
                //startIndex = dna.indexOf(gene, startIndex) + gene.length();
                dna = dna.substring(dna.indexOf(gene) + gene.length());
                if(dna.isEmpty() == false){
                System.out.println("Substring: "+dna);
            }
                geneCounter++;
            }
            else{
                if (dna.isEmpty()){
                    break;
                }
                dna = dna.substring(dna.indexOf(findGene(dna))+3);                               
        }
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
        
    public static void main(String[] args){
        Part1alt p1alt = new Part1alt();
        //p1.testFindStopCodon();
        //p1.testFindGene();
        p1alt.testPrintAllGenes();            
    }
}
