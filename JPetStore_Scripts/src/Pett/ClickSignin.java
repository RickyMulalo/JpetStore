package Pett;
	import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

	public class ClickSignin {
		
		/*Script Title 
	    
	    Script Name      	: Verify the sign IN button
	    'Purpose    		: this TC Verify if the Sign IN button navigates you to sign in page with textfield and password   
	    'Developed by  		: ARENAHO 
	    'Developed Date 	: 12/06/2024
	    'TestDataSheet 		: ARENAHOTXT
	    'Test LInk TestCase Path  : jetsore ---------landing Page --- OB-6 : OB-114 : Verify the sign IN button
	   */
		
		/*!Function Name	: OB-114 : Verify the sign IN button
		'!Purpose			: this TC Verify if the Sign IN button navigates you to sign in page with textfield and password  
		'!Input 			: WebDriver driver - The WebDriver instance used to automate the browser.	
		'!Output 	  		: True/False - returns User navigated to the sign in page on successful validation	 
		'!Developed By	 	: ARENAHO 
		'!Date	 		  	: 12/06/2024
		*/
		public  void clickSignIn(WebDriver driver) throws IOException, InterruptedException {
		        /// to click on the "Sign In" link
		        WebElement signInLink = driver.findElement(By.xpath("//*[@id=\"MenuContent\"]/a[2]"));
		        signInLink.click();
   
		        try {
		            

		            if (driver.findElement(By.xpath("//*[@id=\"Content\"]")).isDisplayed() &&
		                driver.findElement(By.xpath("//*[@id=\"Catalog\"]/form/p[2]/input[2]")).isDisplayed() &&
		                driver.findElement(By.xpath("//*[@id=\"Header\"]")).isDisplayed()) {
		                System.out.println("JpetStore page displays the sign in page with username and password ");
		                ScreenshotP.takeScreenshot(driver, "Verify_the_sign_OB_06PASS.png");
		            } else {
		                System.out.println("JpetStore page doesn't display with the pet images, links named (Fish, Dogs, Cats, Reptiles, and Birds) and a Sign-in Link at the top of the website.");
		                ScreenshotP.takeScreenshotF(driver, "Verify_the_sign_OB_06FAIL.png");
		            }
		        } catch (Exception e) {
		            System.out.println("An error occurred: " + e.getMessage());
		            e.printStackTrace(); // Print the stack trace for detailed error information
		            // Capture screenshot for the error
		            ScreenshotP.takeScreenshot(driver, "error_occurred.png");
		        }
		}
		        
		@Test
		public void TC114 () throws IOException, InterruptedException {
			WebDriver driver = new ChromeDriver();
			driver.manage().window().maximize();
			// pre requiste for the test case 
			 ClickSignin signin=new  ClickSignin();
			 Verify_the_URL_OB_06 url=new  Verify_the_URL_OB_06();
			 signin.clickSignIn(driver);
			 driver.quit();	
		}
		
		public static void main(String[] args) throws IOException, InterruptedException {
			WebDriver driver = new ChromeDriver();
			driver.manage().window().maximize();
			// pre requiste for the test case 
			 ClickSignin signin=new  ClickSignin();
			 Verify_the_URL_OB_06 url=new  Verify_the_URL_OB_06();
			 url.navigateToSite(driver);
			 signin.clickSignIn(driver);
			 driver.quit();
		}

	}

