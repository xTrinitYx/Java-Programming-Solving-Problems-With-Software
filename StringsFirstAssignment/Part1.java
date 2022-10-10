import edu.duke.*;
import java.io.File;
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    public String findSimpleGene(String dna){
        String result = "";
        String startCodon = "ATG";
        String stopCodon = "TAA";
        int startIndex = dna.indexOf(startCodon);
        if (startIndex == -1){
            return "No start codon found";
        }
        int stopIndex = dna.indexOf(stopCodon, startIndex + 3);
        if (stopIndex == -1){
            return "No stop codon found";
        }
        String gene = dna.substring(startIndex, stopIndex + 3);
        if (gene.length() % 3 == 0) {
            result = gene;            
        }
        else {
         return "Sequence is not a multiple of 3";   
        }   
        return result;
    }
    
    public void testSimpleGene(){
        
        String dna = "";     
        
        //1
        dna = "CGTATGCAAGCCTAA";
        System.out.println("DNA 1: " + dna);
        System.out.println("Gene: " + findSimpleGene(dna));
        //2
        dna = "AACGTAGAAATAA";
        System.out.println("DNA 1: " + dna);
        System.out.println("Gene: " + findSimpleGene(dna));
        //3
        dna = "CAAATGCGCGAA";
        System.out.println("DNA 1: " + dna);
        System.out.println("Gene: " + findSimpleGene(dna));
        //4
        dna = "CAAATGGAACAATCTAA";
        System.out.println("DNA 1: " + dna);
        System.out.println("Gene: " + findSimpleGene(dna));
        //5
        dna = "GAACCCGTAAAT";
        System.out.println("DNA 1: " + dna);
        System.out.println("Gene: " + findSimpleGene(dna));
        //Quiz
        dna = "AAATGCCCTAACTAGATTAAGAAACC";
        System.out.println("DNA 1: " + dna);
        System.out.println("Gene: " + findSimpleGene(dna));
                                      
    }
    
    public static void main(String[] args){
        Part1 p1 = new Part1();
        p1.testSimpleGene();     
    }
}
