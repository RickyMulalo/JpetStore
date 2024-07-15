package Pett;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/*

Script Name    	    : Screenshot
'Purpose    		: To take screenshot after executing
'Developed by  		: Ricky 
'Developed Date 	: 10/06/2024
'TestDataSheet 		: NA
'Test LInk TestCase Path:*/
public class ScreenshotP {

	

	/**
	 * Function Name: TakeScreenshot
	 * Purpose      : This script takes screenshot when it is being called in other classes
	 * Input        : WebDriver driver - The WebDriver instance used to automate the browser.
	 * Output       : Outputs messages to the console and takes screenshots based on verification results.
	 * Developed By : Ricky
	 * Date         : 10/06/2024
	 */
    public static void takeScreenshot(WebDriver driver, String fileName) {
        
        	 try {
                 // Capture screenshot method if it passed
                 File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

                 // Load the captured screenshot
                 BufferedImage image = ImageIO.read(screenshotFile);

                 // Write text on the screenshot
                 Graphics2D graphics = image.createGraphics();
                 graphics.setColor(Color.RED);
                 graphics.setFont(new Font("Arial", Font.PLAIN, 40));
                 graphics.drawString("user successfully clicked the button and navigated to next page", 150, 120);
                 graphics.dispose(); // Free up resources 

                 // Get current date and time
                 String timeStamp = new SimpleDateFormat("yyyyMMdd").format(new Date());
                 
                 // Create folder with date and time
                 String folderPath = "C:/Users/MulaloRickyMulaudzi-/OneDrive - Linkfields innovations/Pictures/Screenshots" + timeStamp;
                 
                 
                 File folder = new File(folderPath);
                 if (!folder.exists()) {
                     if (folder.mkdirs()) {
                         //System.out.println("Folder created successfully: " + folderPath);
                     } else {
                         System.out.println("Failed to create the folder: " + folderPath);
                         return;
                     }
                 } else {
                    //System.out.println("Folder already exists: " + folderPath);
                 }

                 // Save the modified screenshot
                 File outputFile = new File(folderPath + "/" + fileName + ".png");
                 ImageIO.write(image, "png", outputFile);

                 System.out.println("Screenshot captured, written text, and saved as: " + outputFile.getAbsolutePath());
             } catch (IOException e) {
                 System.out.println("Failed to capture screenshot with text: " + e.getMessage());
             
         
             } 
    }
    public static void takeScreenshotF(WebDriver driver, String fileName) {
    	try {	
    		
    	       String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

    		 //Capture screenshot method if it failed 
  		  Date dt = new Date();
		        DateFormat df = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            // Load the captured screenshot
            BufferedImage image = ImageIO.read(screenshotFile);
            // Write text on the screenshot 
            Graphics2D graphics = image.createGraphics();
            graphics.setColor(Color.RED);
            graphics.setFont(new Font("Arial", Font.PLAIN, 56));
            graphics.drawString("the  failed  test case was executed   at "+ df.format(dt), 150, 120); 
            // Save the modified screenshot on my PC folders 
            File outputFile = new File("C:/Users/MulaloRickyMulaudzi-/OneDrive - Linkfields innovations/Pictures/Screenshots" + fileName);
            ImageIO.write(image, "png", outputFile);

            System.out.println("1.Screenshot captured with text and saved as: " + fileName);
        } catch (IOException e) {
            System.out.println("1.Failed to capture screenshot with text: " + e.getMessage());
        }
      
    }	
    }

   
    

