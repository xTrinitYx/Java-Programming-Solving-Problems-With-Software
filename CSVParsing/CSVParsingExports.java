/**
 * Write a description of CSVParsingExports here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
*/

import edu.duke.*;
import org.apache.commons.csv.*;

public class CSVParsingExports {
    
    public String countryInfo (CSVParser parser, String country) {
                
        for(CSVRecord record : parser) {
            
            if (record.get("Country").trim().equals(country)) {                
                return country + ": " + record.get("Exports") + ": " + record.get("Value (dollars)");
            }            
            
        }
        
        return "NOT FOUND";
        
    }
    
    public String listExportersTwoProducts (CSVParser parser, String export1, String export2) {
        String output = "";
        
        for(CSVRecord record : parser) { 
            
            if (record.get("Exports").contains(export1) && record.get("Exports").contains(export2)) {
                output = output + record.get("Country") + ": " + record.get("Exports") + "\n";
            }
            
        }
        
        if (output.isEmpty()){
            return "NOT FOUND";
        }
        
        return output.trim();
        
    }
    
    public int numberOfExporters (CSVParser parser, String export) {
        int count = 0;
        String countries = "";
        
        System.out.println("Countries that export " + "'" + export + "'" + " are: ");
        
        for(CSVRecord record : parser) {
            
            if (record.get("Exports").contains(export)) {
                count++;
                countries = countries + record.get("Country") + "\n";                
            }
            
        }
        
        if (countries.isEmpty()) {
            countries = "No countries found";
        }
        
        System.out.println(countries.trim());
        
        return count;
        
    }
    
    public String bigExporters (CSVParser parser, String amount) {
        String output = "";
        
        for (CSVRecord record : parser) {
            
            if (record.get("Value (dollars)").trim().length() > amount.length()) {
                output = output + record.get("Country") + ": " + "Amount: " + record.get("Value (dollars)") + "\n";
            }
            
        }
        
        if(output.isEmpty()) {
            output = "No Exporters with export amount > " + amount;
        }
        else
        {
            output = "Big Exporters: " + "\n" + output;
        }        
        
        return output.trim();
        
    }
    
    public void tester() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        
        //countryInfo
        // parser = fr.getCSVParser();
        // System.out.println("1) Method: " + "countryInfo");
        // System.out.println(countryInfo(parser,"Nauru") + "\n");        
                
        //listExportersTwoProducts
        // parser = fr.getCSVParser();
        // System.out.println("2) Method: " + "listExportersTwoProducts");
        // //System.out.println(listExportersTwoProducts(parser,"gold", "diamonds") + "\n");
        // System.out.println(listExportersTwoProducts(parser,"cotton", "flowers") + "\n");
        
        //numberOfExporters
        // parser = fr.getCSVParser();
        // System.out.println("3) Method: " + "numberOfExporters");
        // //System.out.println("Count: " + numberOfExporters(parser, "gold") + "\n");
        // System.out.println("Count: " + numberOfExporters(parser, "cocoa") + "\n");
        
        //bigExporters
        parser = fr.getCSVParser();
        System.out.println("4) Method: " + "bigExporters");
        System.out.println(bigExporters(parser, "$999,999,999,999") + "\n");
        
    }
    
    public static void main (String[] args) {
        
        CSVParsingExports csv = new CSVParsingExports();
        csv.tester();
        
    }

}
