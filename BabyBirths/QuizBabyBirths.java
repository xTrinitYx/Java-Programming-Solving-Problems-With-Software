
/**
 * Write a description of QuizBabyBirths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.File;

public class QuizBabyBirths {
    
    public void totalBirths (FileResource fr) {

        int totalBirths = 0;
        int totalGirlBirths = 0;
        int totalBoyBirths = 0;

        String allNames = "";
        String girlNames = "";
        String boyNames = ""; 
        int countGirlNames = 0;
        int countBoyNames = 0;

        CSVParser parser = fr.getCSVParser(false);

        for (CSVRecord record : parser) {
            
            int nameBirths = Integer.parseInt(record.get(2));
            totalBirths += nameBirths;
            allNames = allNames + record.get(0) + ", " + record.get(1) + ", " 
            + record.get(2) + "\n";

            if (record.get(1).equals("F")) {
                totalGirlBirths += nameBirths;
                girlNames = girlNames + record.get(0) + ", " + record.get(1) + ", " 
                + record.get(2) + "\n";
                countGirlNames++;
            }
            else
            {
                totalBoyBirths += nameBirths;
                boyNames = boyNames + record.get(0) + ", " + record.get(1) + ", " 
                + record.get(2) + "\n";
                countBoyNames++;
            }

        }

        System.out.println ("Total Births: " + totalBirths);
        System.out.println ("Total Girl Births: " + totalGirlBirths);
        System.out.println ("Total Boy Births: " + totalBoyBirths);
        System.out.println ("\n" + "Total Names: " + "\n" + allNames);
        System.out.println ("Girl Names: " + "\n" + girlNames);
        System.out.println ("Boy Names: " + "\n" + boyNames);
        System.out.println ("Total Boy Names: " + countBoyNames);
        System.out.println ("Total Girl Names: " + countGirlNames); 

    }

    public void testTotalBirths () {

        FileResource fr = new FileResource();
        totalBirths(fr);

    }

    public int getRank (int year, String name, String gender) {

        //us_babynames_test/yob2012short.csv
        String fileName = "us_babynames_by_year/" + "yob"+year+".csv";
        FileResource fr = new FileResource(fileName); 
        CSVParser parser = fr.getCSVParser(false);

        int rank = 0;
        String rankDetails = "";

        for (CSVRecord record : parser) {

            int nameBirths = Integer.parseInt(record.get(2));            
            String currName = record.get(0);
            String currGender = record.get(1);

            if (currGender.equals(gender)) {
                rank++;
                if (currName.equals(name)) {                    
                    return rank;                    
                }
            }

        }

        return -1 ;

    }

    public void testGetRank () {

        System.out.println (getRank(1974, "Owen", "M"));

    }

    public String getName (int year, int rank, String gender) {

        //us_babynames_test/yob2012short.csv
        String fileName = "us_babynames_by_year/" + "yob"+year+".csv";
        FileResource fr = new FileResource(fileName); 
        CSVParser parser = fr.getCSVParser(false);       

        for (CSVRecord record : parser) {     

            String currName = record.get(0);
            String currGender = record.get(1);            

            if (currGender.equals(gender)) {               

                int currRank = getRank(year, currName, gender);            
                //System.out.println (currName + ", " + "Rank: " + currRank);

                if (currRank == rank) {
                    return currName;
                }

            }
        }

        return "NO NAME FOUND";

    }

    public void testGetName () {

        System.out.println (getName(1980, 350, "F"));

    }

    public String whatIsNameInYear (String name, int year, int newYear, 
    String gender) {

        String newName = "";

        int rankBornYear = getRank(year, name, gender);

        if (rankBornYear != -1) {
            newName = getName(newYear, rankBornYear, gender);
        } 
        else
        {
            newName = "No new name found";
        }

        return newName;

    }

    public void testWhatIsNameInYear () {

        String name = "Owen";
        int year = 1974;
        int newYear = 2014;
        String gender = "M";

        System.out.println (name + " born in " + year + " would be: " + "\n"  
                            + whatIsNameInYear(name, year, newYear, gender) 
                            + " in " + newYear);

    }

    public int yearOfHighestRank (String name, String gender) {

        //us_babynames_test/yob2012short.csv
        int year = 0;
        int rank = 0;
        DirectoryResource dir = new DirectoryResource();       

        for (File f : dir.selectedFiles()) {

            String currFileName = f.getName();             
            int currYear = Integer.parseInt(currFileName.substring(3, 7));
            int currRank = getRank(currYear, name, gender);                        

            if (rank == 0) {
                rank = currRank;
                year = currYear;
            }
            else
            {
                if (currRank < rank) {
                    rank = currRank;
                    year = currYear;
                }
            }

        }

        return year;

    }

    public void testYearOfHighestRank () {

        System.out.println (yearOfHighestRank("Mich", "M"));

    }

    public double getAverageRank (String name, String gender) {

        double averageRank = 0;
        int sumRank = 0;
        int yearCount = 0;
        DirectoryResource dir = new DirectoryResource();

        for (File f : dir.selectedFiles()) {

            int year = Integer.parseInt(f.getName().substring(3, 7));
            double currRank = getRank(year, name, gender);

            if (currRank == -1) {
                averageRank = -1;
            }
            else
            {
                sumRank += currRank;
                yearCount++;
            }

        }        

        if (averageRank != -1) {
            averageRank = (double)sumRank/yearCount;
            //averageRank = Math.round(averageRank * 100.0) / 100.0;            
        }

        return averageRank;

    }

    public void testGetAverageRank () {

        System.out.println ("Average Rank: " + getAverageRank("Robert", "M"));

    }

    public int getTotalBirthsRankedHigher (int year, String name, String gender) {

        //us_babynames_test/yob2012short.csv
        int totalBirthsRankedHigher = 0;

        FileResource fr = new FileResource("us_babynames_by_year/" + "yob"+year+".csv");
        CSVParser parser = fr.getCSVParser(false);

        int nameRank = getRank(year, name, gender);        
        
        if (nameRank != -1) {

            for (CSVRecord record : parser) {

                int currBirths = Integer.parseInt(record.get(2));
                String currGender = record.get(1);
                String currName = record.get(0);

                if (currGender.equals(gender)) {

                    int currRank = getRank(year, currName, currGender);
                    
                    if (currRank < nameRank) {
                        totalBirthsRankedHigher += currBirths;
                        //System.out.println (currRank + ". " + currName 
                        //                      + ", " + currBirths); 
                    }
                    else
                    {
                        break;
                    }

                }                

            }

        }
        else
        {
            System.out.println ("Name not found: " + name);    
        }        

        return totalBirthsRankedHigher;

    }

    public void testGetTotalBirthsRankedHigher () {

        String name = "Drew";
        String gender = "M";
        int year = 1990;
        int nameRank = getRank(year, name, gender);

        System.out.println ("Total Births Ranked Higher than " + name +"'s Rank of " 
            +  nameRank + " are:" + "\n"
            + getTotalBirthsRankedHigher(year, name, gender));               

    }
    
    public static void main (String[] args) {

        QuizBabyBirths quiz = new QuizBabyBirths();
        //quiz.testTotalBirths();
        //quiz.testGetRank();
        //quiz.testGetName();
        //quiz.testWhatIsNameInYear();
        //quiz.testYearOfHighestRank();
        //quiz.testGetAverageRank();
        quiz.testGetTotalBirthsRankedHigher();
        
    }

}
