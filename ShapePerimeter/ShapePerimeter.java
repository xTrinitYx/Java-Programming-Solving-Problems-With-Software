import edu.duke.*;
/**
 * Write a description of ShapePerimeter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ShapePerimeter {
    
    public double getPerimeter(Shape s){
       // Start with totalPerim = 0
       double totalPerim = 0;       
       // prevPt = lastPt
       Point prevPt = s.getLastPoint();
       // For each currPt in shape:
            // Find distance from prevPt to currPt, name it currDist
            // totalPerim = totalPerim + currDist
            // prevPt = currPt
        for(Point currPt:s.getPoints()){
            double currDist = prevPt.distance(currPt);
            totalPerim = totalPerim + currDist;
        }
        // totalPerim is the answer  
        return totalPerim;
    }
    
    public void findPerimeter(){
        FileResource shFile = new FileResource();
        Shape sh = new Shape(shFile);
        double perim = getPerimeter(sh);
        System.out.println("Perimiter: "+perim);
    }
    
    public static void main(String[] args) {
        ShapePerimeter shpPer = new ShapePerimeter();
        shpPer.findPerimeter();        
    }
        
        
    }
            

