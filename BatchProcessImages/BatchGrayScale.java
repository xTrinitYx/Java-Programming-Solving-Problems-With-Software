
/**
 * Write a description of BatchGrayScale here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.io.*;

public class BatchGrayScale 
{
    public ImageResource grayScaleConverter (ImageResource inImage) {

        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());

        for(Pixel pixel : outImage.pixels()) {

            Pixel inImagePixel = inImage.getPixel(pixel.getX(), pixel.getY()); 

            int average = (inImagePixel.getRed() + inImagePixel.getGreen() 
                    + inImagePixel.getBlue()) / 3;
            pixel.setRed(average);
            pixel.setGreen(average);
            pixel.setBlue(average);

        }        

        return outImage;

    }

    public void testGrayScaleConverter () {

        ImageResource ir = new ImageResource();
        ImageResource outputGray = grayScaleConverter(ir);
        outputGray.draw();

    }
    
    public void saveAs (ImageResource inImage, ImageResource outImage, File f) {
        
        String path = f.getParent() + "\\";
        String fileName = inImage.getFileName();
        String newFileName = path + "Gray-" + fileName;
        outImage.setFileName(newFileName);
        outImage.save();

    } 

    public void selectAndConvertGrayBatch () {

        DirectoryResource dir = new DirectoryResource();

        for (File f : dir.selectedFiles()) {

            ImageResource inImage = new ImageResource(f);
            ImageResource outImage = grayScaleConverter(inImage);
            saveAs(inImage, outImage, f);
            outImage.draw();

        }

    }    

    public static void main (String[] args) {

        BatchGrayScale gray = new BatchGrayScale();
        //gray.testGrayScaleConverter();
        //gray.selectAndConvertGray();
        gray.selectAndConvertGrayBatch();

    }
}
