package Scripts;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;



/*

Script Name    	    : Screenshot
'Purpose    		: To take screenshot after executing
'Developed by  		: Ricky 
'Developed Date 	: 10/06/2024
'TestDataSheet 		: NA
'Test LInk TestCase Path:*/
public class Screenshot {
	
	
	

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
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            
            // Create folder with date and time
            String folderPath = "C:/Users/MulaloRickyMulaudzi-/OneDrive - Linkfields innovations/Pictures/Screenshots_" + timeStamp;
            File folder = new File(folderPath);
            if (!folder.exists()) {
                folder.mkdirs();
            }

            // Save the modified screenshot
            File outputFile = new File(folderPath + "/" + fileName + ".png");
            ImageIO.write(image, "png", outputFile);

            System.out.println("Screenshot captured, written text, and saved as: " + outputFile.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Failed to capture screenshot with text: " + e.getMessage());
        }
    }
}
