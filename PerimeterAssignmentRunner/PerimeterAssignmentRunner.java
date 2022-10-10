import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("Perimeter: " + length);
    }
    
    public int getNumPoints (Shape s) {
        // Number of points counter = 0
        int numPts = 0;
        for(Point pt:s.getPoints()){
            numPts = numPts + 1;
        }
        return numPts;
    }
    
    public void testNumPts() {
        FileResource file = new FileResource();
        Shape shFile = new Shape(file);
        int outNumPts = getNumPoints(shFile);
        System.out.println("Total Points in Shape: " + outNumPts);        
        }

    public double getAverageLength(Shape s) {
        // Get perimeter
        double perimeter = getPerimeter(s);
        // Get total Points
        int totPts = getNumPoints(s);
        // Calcuate Average Length
        double avgLen = perimeter/totPts;       
        return avgLen;
    }
    
    public void testAvgLen() {
        FileResource file = new FileResource();
        Shape shFile = new Shape(file);
        double outAvgLen = getAverageLength(shFile);
        System.out.println("Average Length of Sides: " + outAvgLen);        
    }

    public double getLargestSide(Shape s) {
        // initialize side length
        double largestSideLen = 0;
        // Last point to start
        Point prevPt = s.getLastPoint();
        for(Point currPt:s.getPoints()){
            double sideLen = prevPt.distance(currPt);
            if(sideLen>largestSideLen){;
                largestSideLen = sideLen;
            }
            prevPt = currPt;
        }     
        return largestSideLen;
    }
    
    public void testLargestSide() {
        FileResource file = new FileResource();
        Shape shFile = new Shape(file);
        double outLargestSide = getLargestSide(shFile);
        System.out.println("Largest Side Length: " + outLargestSide);        
    }

    public double getLargestX(Shape s) {
        // Put code here
        double largestX = 0;
        for(Point currPt : s.getPoints()){
            double currX = currPt.getX();
            if( currX>largestX){
                largestX = currX;
            }    
        }           
        return largestX;
    }
    
    public void testLargestX() {
        FileResource file = new FileResource();
        Shape shFile = new Shape(file);
        double outLargestX = getLargestX(shFile);
        System.out.println("Largest x: " + outLargestX);        
    }

    public double getLargestPerimeterMultipleFiles() {
        // Initialize largest Perimeter 
        double largestPerimeter = 0;
        // Initialize directory resource object
        DirectoryResource dr = new DirectoryResource();        
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            Shape shf = new Shape(fr);
            double shfPerimeter = getPerimeter(shf);
            if(shfPerimeter > largestPerimeter){
                largestPerimeter = shfPerimeter;
            }
        }        
        return largestPerimeter;
    }
    
    public void testLargestPerimeterMultipleFiles(){
        //double largestPerim = getLargestPerimeterMultipleFiles();
        System.out.println("Largest Perimeter: " + getLargestPerimeterMultipleFiles());        
    }

    public String getFilenameWithLargestPerimeter() {
        // Initialize file for filename
        //File ftemp = null;    
        // Initialize filename string;
        String fName = "";
        double largestPerimeter = 0;
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            Shape shf = new Shape(fr);
            double shfPerimeter = getPerimeter(shf);
            if(shfPerimeter>largestPerimeter){
                largestPerimeter = shfPerimeter;
                //ftemp = f; 
                fName = f.getName();
            }
        }        
        //return ftemp.getName();
        return fName;
    }    
    
    public void testFilenameWithLargestPerimeter(){
        System.out.println("File with Largest Perimeter: " + getFilenameWithLargestPerimeter());
    
    }
    
    public void testPerimeterMultipleFiles() {
        // Put code here
    }

    public void testFileWithLargestPerimeter() {
        // Put code here
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        //pr.testPerimeter();
        //pr.testNumPts();
        pr.testAvgLen();
        //pr.testLargestSide();
        //pr.testLargestX();
        //pr.testLargestPerimeterMultipleFiles();
        //pr.testFilenameWithLargestPerimeter();
    }
}
