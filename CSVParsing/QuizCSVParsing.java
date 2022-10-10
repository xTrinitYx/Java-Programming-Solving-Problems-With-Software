
/**
 * Write a description of QuizCSVParsing here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.io.File;
import org.apache.commons.csv.*;

public class QuizCSVParsing {
    
    public CSVRecord colderTempRecord (CSVRecord currRecord, CSVRecord coldestTempRecord) {
        
        if (coldestTempRecord == null) {
            coldestTempRecord = currRecord;
        }
        else
        {
                        
            double currTemp = Double.parseDouble(currRecord.get("TemperatureF"));
            double coldestTemp = Double.parseDouble(coldestTempRecord.get("TemperatureF"));
            
            if (currTemp > -200 && currTemp < 200 && currTemp < coldestTemp) {                
                coldestTemp = currTemp;
                coldestTempRecord = currRecord;
            }
            
        }
        
        return coldestTempRecord;
        
    }
    
    public CSVRecord lowerHumidityRecord (CSVRecord currRecord, CSVRecord lowestHumidityRecordinFile) {
        
        if (lowestHumidityRecordinFile == null) {
                lowestHumidityRecordinFile = currRecord;
            }
            else
            {
                if (currRecord.get("Humidity").contains("N/A") == false) {
                    
                int currRecordTemp = Integer.parseInt(currRecord.get("Humidity"));
                int lowestTemp = Integer.parseInt(lowestHumidityRecordinFile.get("Humidity"));           
                
                    if (currRecordTemp < lowestTemp) {
                        lowestTemp = currRecordTemp;
                        lowestHumidityRecordinFile = currRecord;
                    }
                
            }                
            }
        
        return  lowestHumidityRecordinFile;
        
    }
    
    public CSVRecord coldestHourInFile (CSVParser parser) {
        
        CSVRecord coldestHourRecord = null;
        
        for (CSVRecord currRecord : parser) {
            
            coldestHourRecord = colderTempRecord(currRecord, coldestHourRecord);
            
        }       
        
        return coldestHourRecord;
        
    }
    
    //Overloading coldestHourInFile();
    public CSVRecord coldestHourInFile () {
        
        CSVRecord coldestHourRecord = null;
        
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        
        for (CSVRecord currRecord : parser) {
            
            coldestHourRecord = colderTempRecord(currRecord, coldestHourRecord);
            
        }       
        
        return coldestHourRecord;
        
    }
    
    public void testColdestHourInFile () {
        
        CSVRecord record = coldestHourInFile();
        System.out.println ("Coldest Temp: " + record.get("TemperatureF") + ", Coldest Hour: "
                             + record.get("DateUTC"));
        
    }
    
    public CSVRecord coldestHourInMultipleFiles () {
        
        CSVRecord coldestTempRecord = null; 
        DirectoryResource dir = new DirectoryResource();
        
        for (File f : dir.selectedFiles()) {
            
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord currRecord = coldestHourInFile(parser);
            
            coldestTempRecord = colderTempRecord(currRecord, coldestTempRecord);
            
            if (currRecord == coldestTempRecord) {
                coldestTempRecord = currRecord;                
            }
            
        }
                
        return coldestTempRecord;
        
    }
    
    public void testColdestHourInMultipleFiles () {
        
        CSVRecord record = coldestHourInMultipleFiles();
        System.out.println ("Coldest Temp: " + record.get("TemperatureF") + ", Coldest Hour: "
                             + record.get("DateUTC"));
        
    }
    
    public String fileWithColdestTemperature () {
        
        String fileName = "";
        DirectoryResource dir = new DirectoryResource();
        File fileWithColdestTemp = null;
        CSVRecord coldestTempRecord = null;        
        
        for (File f : dir.selectedFiles()) {
            
            FileResource fr = new FileResource(f);
            CSVParser parser= fr.getCSVParser();
            CSVRecord currRecord = coldestHourInFile(parser);
            coldestTempRecord = colderTempRecord(currRecord, coldestTempRecord);
            
            if (currRecord == coldestTempRecord) {
                coldestTempRecord = currRecord;
                fileWithColdestTemp = f;
            }
        
        }
        
        fileName = fileWithColdestTemp.getName();
        System.out.println ("Coldest Day is in File: " + fileName);
        
        FileResource fr = new FileResource(fileWithColdestTemp);
        CSVParser newParser = fr.getCSVParser();
        System.out.println ("Coldest Temperature on that Day was: "
                             + coldestHourInFile(newParser).get("TemperatureF"));
        
        System.out.println ("All the Temperatures on the Coldest Day were: ");
        
        CSVParser xParser = fr.getCSVParser();
        
        for (CSVRecord record : xParser) {
            
            System.out.println (record.get("DateUTC") + ": " + record.get("TemperatureF"));
            
        }
        
        return fileName;
        
    }
    
    public void testFileWithColdestTemperature () {
        
        fileWithColdestTemperature();
        
        // String fileName = fileWithColdestTemperature();
        
        // System.out.println ("Coldest Day is in File: " + fileName);       
             
        // FileResource fr = new FileResource(fileName);
        // CSVParser newParser = fr.getCSVParser();
        // System.out.println ("Coldest Temperature on that Day was: "
                             // + coldestHourInFile(newParser).get("TemperatureF"));
        
        // System.out.println ("All the Temperatures on the Coldest Day were: ");
        
        // CSVParser xParser = fr.getCSVParser();
        // for (CSVRecord record : xParser) {
            
            // System.out.println (record.get("DateUTC") + ": " + record.get("TemperatureF"));
            
        // }
        
    }
    
    public CSVRecord lowestHumidityInFile (CSVParser parser) {
        
        CSVRecord lowestHumidityRecordinFile = null;
        
        for (CSVRecord currRecord : parser) {                
            
            lowestHumidityRecordinFile = lowerHumidityRecord(currRecord, lowestHumidityRecordinFile);
            
        }
        
        return lowestHumidityRecordinFile;
        
    }
    
    public void testLowestHumidityInFile() {
        
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord record = lowestHumidityInFile(parser);
        
        System.out.println ("Lowest Humidity was: " + record.get("Humidity") + " at: " + record.get("DateUTC"));
        
    }
    
    public CSVRecord lowestHumidityInManyFiles () {
        
        CSVRecord lowestHumidityInManyFiles = null;
        DirectoryResource dir = new DirectoryResource();
        
        for (File f : dir.selectedFiles()) {
            
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord currRecord = lowestHumidityInFile(parser);
            lowestHumidityInManyFiles = lowerHumidityRecord(currRecord, lowestHumidityInManyFiles);
            
            if (currRecord == lowestHumidityInManyFiles) {
                lowestHumidityInManyFiles = currRecord;                
            }
            
        }        
        
        return lowestHumidityInManyFiles;
        
    }
    
    public void testLowestHumidityInManyFiles () {
        
        CSVRecord record = lowestHumidityInManyFiles();
        System.out.println ("Lowest Humidity was: " + record.get("Humidity") + " at: " + record.get("DateUTC"));
        
    }
    
    public double averageTemperatureInFile (CSVParser parser) {
        
        double average = 0;
        double sumTemp = 0;
        int count = 0;
        
        for (CSVRecord record : parser) {
            
            Double currTemp = Double.parseDouble(record.get("TemperatureF"));
            
            if (currTemp > -200 && currTemp < 200) {
                sumTemp += currTemp;
                count++;
            }
            else
            {
                count--;
            }
            
        }
        
        average = sumTemp / count;
        return average;
        
    }
    
    public void testAverageTemperatureInFile () {
        
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.println ("Average temperature in file is: " + averageTemperatureInFile(parser));
        
    }
    
    public double averageTemperatureWithHighHumidityInFile (CSVParser parser, int value) {
        
        double average = 0;
        double sumTemp = 0;
        int count = 0;
        
        for (CSVRecord currRecord : parser) {
            
            if (currRecord.get("Humidity").contains("N/A") == false) {
                
                Double currHumidity = Double.parseDouble(currRecord.get("Humidity"));
                Double currTemp = Double.parseDouble(currRecord.get("TemperatureF"));
                
                if (currHumidity >= value) {
                    sumTemp += currTemp;
                    count++;
                }
                
            }        
            
        }
        
        if (count != 0) {
            average = sumTemp / count;
        }
        
        return average;
        
    }
    
    public void testAverageTemperatureWithHighHumidityInFile () {
        
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();    
        
        int value = 80;
        double averageHighTemp = averageTemperatureWithHighHumidityInFile (parser, value);
        
        if (averageHighTemp == 0) {
            System.out.println ("No temperatures with that humidity");
        }
        else
        {
            System.out.println ("Average Temp when High Humidity is @ " 
                                + value + " is: " + averageHighTemp); 
        }
        
    }
    
    
    public static void main (String[] args) {
        
        QuizCSVParsing quiz = new QuizCSVParsing();
        //quiz.testColdestHourInFile();
        quiz.testColdestHourInMultipleFiles();
        //quiz.testFileWithColdestTemperature();
        //quiz.testLowestHumidityInFile();
        //quiz.testLowestHumidityInManyFiles();
        //quiz.testAverageTemperatureInFile();
        //quiz.testAverageTemperatureWithHighHumidityInFile();
    }

}
