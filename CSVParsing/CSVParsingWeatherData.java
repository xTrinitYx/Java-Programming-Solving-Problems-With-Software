
/**
 * Write a description of CSVParsingWeatherData here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
*/

import edu.duke.*;
import java.io.File;
import org.apache.commons.csv.*;

public class CSVParsingWeatherData {
    
    public CSVRecord getHigherTempRecord (CSVRecord currRecord, CSVRecord highestTempRecord) {
        
        if (highestTempRecord == null) {
                
                highestTempRecord = currRecord;
                
            }
            else
            {
                
                double currTemp = Double.parseDouble(currRecord.get("TemperatureF"));            
                double highestTemp = Double.parseDouble(highestTempRecord.get("TemperatureF"));
                
                if (currTemp > highestTemp) {
                    highestTemp = currTemp;
                    highestTempRecord = currRecord;
                }
                
            }
        
        return highestTempRecord;
    }
    
    public CSVRecord recordOfHighestTempInDayFile (CSVParser parser) {
        
        CSVRecord highestTempRecord = null;        
        
        for (CSVRecord currRecord : parser) {
            
            highestTempRecord = getHigherTempRecord(currRecord, highestTempRecord);
            
        }        
        
        return highestTempRecord;
        
    }
    
    //Overloading recordOfHighestTempInDayFile, and Recursion?
    public CSVRecord recordOfHighestTempInDayFile () {
        
        CSVRecord highestTempRecord = null;
        
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        
        // for (CSVRecord currRecord : parser) {
            
            // highestTempRecord = getHigherTempRecord(currRecord, highestTempRecord);
            
        // }        
        
        //return highestTempRecord;
        
        return recordOfHighestTempInDayFile(parser);
        
    }
    
    public void testrecordOfHighestTempInDayFile () {
        
        // FileResource fr = new FileResource();
        // CSVParser frParser = fr.getCSVParser();
        // CSVRecord record = recordOfHighestTempInDayFile(frParser);
        
        //System.out.println ("Highest Temp: " + record.get("TemperatureF") 
                           // + ", Hottest Hour: " + record.get("TimeEST"));
                           
        //Test Overriding of recordOfHighestTempInDaysFiles();
        CSVRecord record = recordOfHighestTempInDayFile();
        System.out.println ("Highest Temp: " + record.get("TemperatureF") + ", Hottest Hour: "
                             + record.get("TimeEST"));
        
    }
    
    public CSVRecord recordOfHighestTempInDaysFiles () {
        
        DirectoryResource dir = new DirectoryResource();
        
        CSVRecord highestTempRecord = null;
        double highestTemp = 0;        
        
        for (File f : dir.selectedFiles()) {
            
            FileResource fr = new FileResource(f);
            CSVParser multipleParser = fr.getCSVParser();            
            CSVRecord currRecord = recordOfHighestTempInDayFile(multipleParser);
            
            highestTempRecord = getHigherTempRecord(currRecord, highestTempRecord);
            
        }
        
        return highestTempRecord;
    }
    
    public void testRecordOfHighestTempInDaysFiles () {
        
        CSVRecord record = recordOfHighestTempInDaysFiles();               
        System.out.println ("Highest Temp: " + record.get("TemperatureF") + ", Hottest Hour: "
                             + record.get("TimeEST"));        
        
    }
    
    public static void main (String[] args) {
        
        CSVParsingWeatherData weather = new CSVParsingWeatherData();
        //weather.testrecordOfHighestTempInDayFile();
        weather.testRecordOfHighestTempInDaysFiles();
        
    }

}
