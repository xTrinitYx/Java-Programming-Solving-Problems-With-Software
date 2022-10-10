
/**
 * Write a description of BatchInvertImage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.io.*;

public class BatchInvertImage {
    
    public ImageResource invertImage (ImageResource inImage) {
        
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        
        for (Pixel pixel : outImage.pixels()) {
            
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            
            int redInvert = 255 - inPixel.getRed();
            int greenInvert = 255 - inPixel.getGreen();
            int blueInvert = 255 - inPixel.getBlue();
            
            pixel.setRed(redInvert);
            pixel.setGreen(greenInvert);
            pixel.setBlue(blueInvert);            
            
        }
        
        return outImage;
        
    }
    
    public void saveAs (ImageResource inImage, ImageResource outImage, File f) {
        
        String path = f.getParent() + "\\";
        String newFileName = path + "Inverted-" + inImage.getFileName();
        outImage.setFileName(newFileName);
        outImage.save();
        
    }
    
    public void selectAndInvertBatch () {
     
        DirectoryResource dir = new DirectoryResource();
        
        for (File f : dir.selectedFiles()) {
            
            ImageResource inImage = new ImageResource(f);
            ImageResource outImage = invertImage(inImage);
            saveAs(inImage, outImage, f);
            outImage.draw();
            
        }
        
    }
    
    public static void main (String[] args) {
        
        BatchInvertImage invert = new BatchInvertImage();
        invert.selectAndInvertBatch();
        
    }
    
}
