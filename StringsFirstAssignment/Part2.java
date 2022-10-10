import edu.duke.*;
import java.io.File;
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public String findSimpleGene(String dna, String startCodon, String stopCodon){
        String result = "";
        //startCodon = "ATG";
        //stopCodon = "TAA";        
        //if (dna.contains(startCodon) == false & dna.contains(stopCodon) == false){
        //    return "ERROR !! No codons found";
        //}
        if ( dna == dna.toUpperCase() || dna == dna.toLowerCase()) {
        
            if (dna == dna.toUpperCase()){
                startCodon = startCodon.toUpperCase();
                stopCodon = stopCodon.toUpperCase();
                }
            
            if (dna == dna.toLowerCase()){
                startCodon = startCodon.toLowerCase();
                stopCodon = stopCodon.toLowerCase();            
            }
                       
            int startIndex = dna.indexOf(startCodon);
            int stopIndex = dna.indexOf(stopCodon, startIndex + 3);
                
            if (startIndex == -1 && stopIndex == -1){
                return "ERROR !! No codons found";
            }
            
            if (startIndex == -1){
                return "ERROR !!  No start codon found";
            }
            
            if (stopIndex == -1){
                return "ERROR !!  No stop codon found";
            }
            String gene = dna.substring(startIndex, stopIndex + 3);
            if (gene.length() % 3 == 0) {
                result = "FOUND!--->> " + gene;            
            }
            else {
             return "ERROR !! Sequence is not a multiple of 3";   
            }  
        }
        else {
            return "Invalid case";
        }
        return result;
    }
    
    public void testSimpleGene(){
        String dna = "";
        String startCodon = "ATG";
        String stopCodon = "TAA";        
        //1
        dna = "1CGTATGCAAGCCTAA";
        System.out.println("DNA: " + dna);
        System.out.println("Gene: " + findSimpleGene(dna,startCodon,stopCodon));
        //2
        dna = "2AACGTAGAAATAA";
        System.out.println("DNA: " + dna);
        System.out.println("Gene: " + findSimpleGene(dna,startCodon,stopCodon));
        //3
        dna = "3CAAATGCGCGAA";
        System.out.println("DNA: " + dna);
        System.out.println("Gene: " + findSimpleGene(dna,startCodon,stopCodon));
        //4
        dna = "4CAAATGGAACAATCTAA";
        System.out.println("DNA: " + dna);
        System.out.println("Gene: " + findSimpleGene(dna,startCodon,stopCodon));
        //5
        dna = "5GAACCCGTGAACTAG";
        System.out.println("DNA: " + dna);
        System.out.println("Gene: " + findSimpleGene(dna,startCodon,stopCodon));
        //6
        dna = "6GAATGCGTGAACGCTAAG";
        System.out.println("DNA: " + dna);
        System.out.println("Gene: " + findSimpleGene(dna,startCodon,stopCodon));
        //7
        dna = "7acatghjuigctaacg";
        System.out.println("DNA: " + dna);
        System.out.println("Gene: " + findSimpleGene(dna,startCodon,stopCodon));
        //8
        dna = "8adgATGasdfTAA";
        System.out.println("DNA: " + dna);
        System.out.println("Gene: " + findSimpleGene(dna,startCodon,stopCodon));
        
        System.out.println("----------------------------------------");
                                      
    }
    
    public static void main(String[] args){
        Part2 p2 = new Part2();
        p2.testSimpleGene();     
    }

}
