
/**
 * Write a description of X here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class X {    
    // public X (String a, String b){
        // String x = a;
        // String y = b;
    // }
    
    // public String getx(){
        
    // }
    
    public double cgRatio(String dna){  
        int count = 0;
        
        // for(int index = 0; index<dna.length();index++){
            // if(dna.indexOf("C", index) == index || dna.indexOf("G", index) == index){
                // count++;                
            // }            
        // }
        
        int index = 0;
        
        while(index < dna.length()){            
            if(dna.indexOf("C", index) == index || dna.indexOf("G", index) == index){
                count++;
            }            
            index++;
        }
        
        //int cIndex = dna.indexOf("C");
        //int gIndex = dna.indexOf("G");
        // int cIndex = 0;
        // int gIndex = 0;        
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
        System.out.println("Total CG Count: "+count);        
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
        
        dna ="GCAAACCCGGGAAACCCCGGGAAATCTCAAGC";
        System.out.println("DNA: "+dna+"\n"+ cgRatio(dna));
        
        System.out.println("-----------------END!!-----------------");
    }
    
    public void printStrings(String a, String b) {        
        System.out.println("a: " + a);
        System.out.println("b: " + b);
    }
    
    public static void main (String[] args) {
        String string = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int index = 0;
        // while(index < string.length()) {
            // System.out.println("Substring - " + index + ": " + "\n" + 
                                            // string.substring(index));
            // index++;
        // }
        // index = string.indexOf("DEF") + 3;
        // System.out.println("\n" + "Index: " + index);
        // System.out.println("Substring: " + string.substring(index));
        
        //X xtest = new X();
        //xtest.testCGRatio();        
    }

}
