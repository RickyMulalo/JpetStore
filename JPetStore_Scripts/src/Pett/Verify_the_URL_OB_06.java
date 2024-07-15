package Pett;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

/*Script Title 

Script Name      	: Verify the URL
'Purpose    		: This TC verify the JPetStore application URL.
'Developed by  		: ARENAHO 
'Developed Date 	: DD/MM/YY
'TestDataSheet 		: ARENAHOTXT
'Test LInk TestCase Path  : jetsore ---------Testplan-----Jest Store -----landing Page -----Verify the URL 
*/
/*!Function Name	: Verify the URL
'!Purpose			: This function verify the JPetStore application URL. 
'!Input 			: WebDriver driver 	
'!Output 	  		: returns JpetStore page display with the pet images, links named(Fish,Dogs,Cats,Reptiles and Birds) and a Sign in Link at the top of the website."); on successful navigated to the page  	 
'!Developed By	 	: ARENAHO 
'!Date	 		  	: 12/06/2024
*/
public class Verify_the_URL_OB_06 {
	
	 public static  void navigateToSite(WebDriver driver) throws IOException {
		 ReadData rd =new ReadData();
		
		    // read data from the file 
		    String filePath = "src/update";
		    Map<String, String> userDetails = ReadData.readUserDetailsFromFile(filePath);
		    // read the has the string url only 
	        String url = userDetails.get("url"); 
	        driver.get(url);
	        //String browser = userDetails.get("browser"); 
	        //driver.get(browser);
	        try {
	        	// step 1 
	        	// read the url from the note pad
	        	
	        	//url.navigateToSite(driver);
	            // Maximize the window
	        
	            driver.manage().window().maximize();
		        if (driver.findElement(By.xpath("//*[@id=\"LogoContent\"]/a/img")).isDisplayed()&&driver.findElement(By.xpath("//*[@id=\"Sidebar\"]")).isDisplayed()&&driver.findElement(By.xpath("//*[@id=\"Header\"]")).isDisplayed() ) {
		            System.out.println("JpetStore page display with the pet images, links named(Fish,Dogs,Cats,Reptiles and Birds) and a Sign in Link at the top of the website.");
		            ScreenshotP.takeScreenshot(driver, "Verify_the_URL_OB_06PASS.png");
		        } else {
		            System.out.println("JpetStore page dont display with the pet images, links named(Fish,Dogs,Cats,Reptiles and Birds) and a Sign in Link at the top of the website.");
		            ScreenshotP.takeScreenshot(driver, "Verify_the_URL_OB_06FAIL.png");
		        }
	        } catch (Exception e) {
	            System.out.println("An error occurred while navigating to the site.");
	            
	        } 
	 
	     // step 2 verify if all links are clickable on the home page 
	        List<WebElement> links = driver.findElements(By.tagName("a"));

	        // Iterate through each link and verify if it is clickable
	        for (WebElement link : links) {
	            String linkText = link.getText();
	            // retrieve the URL that a hyperlink (link) points to.
	            String linkUrl = link.getAttribute("href");

	            try {
	               
	                // Open link in a new tab to avoid losing context by clicking Control C and Enter , Keys.chord(Keys.CONTROL, Keys.RETURN): Combines (or "chords") the Control and Enter keys into a single action
	                String clickOnLinkTab = Keys.chord(Keys.CONTROL, Keys.RETURN);
	                link.sendKeys(clickOnLinkTab);
	                
	                // Switch to the new tab and check if the page loads
	                ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
	                driver.switchTo().window(tabs.get(1));
	                String pageTitle = driver.getTitle();
	                // Verify if the new page has loaded by checking the title or 
	                if (driver.getTitle().isEmpty()) {
	                    System.out.println("Link with text '" + linkText + "' and URL '" + linkUrl + "' is not clickable.");
	                    
	                    ScreenshotP.takeScreenshot(driver, "Verify_the_URL_OB_clickable.png");
	                } else {
	                    System.out.println("Link with text '" + linkText + "' and URL '" + linkUrl + "' is clickable.");
	                    ScreenshotP.takeScreenshotF(driver, "Verify_the_URL_OB_LINKS.png");
	                    System.out.println(pageTitle);
	                }

	                // Close the new tab and switch back to the original tab
	                driver.close();
	                driver.switchTo().window(tabs.get(0));
	            } catch (Exception e) {
	                // If an exception is thrown, the link is not clickable
	                System.out.println("Link with text '" + linkText + "' and URL '" + linkUrl + "' is not clickable.");
	               
	            }
	    } 
	 }
	        
	 @Test
		public void TC06 () throws IOException, InterruptedException {

		 WebDriver driver = new ChromeDriver();
			Verify_the_URL_OB_06.navigateToSite(driver);
			 driver.quit();
			
		} 
	
	public static void main(String[] args) throws IOException {
		
	
		WebDriver driver = new ChromeDriver();
		Verify_the_URL_OB_06.navigateToSite(driver);
		driver.quit();
		
	}}