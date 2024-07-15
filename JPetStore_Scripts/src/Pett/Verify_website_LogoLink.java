package Pett;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
/*Script Title 

Script Name      	: Verify the URL
'Purpose    		: This TC verify the JPetStore application URL.
'Developed by  		: ARENAHO 
'Developed Date 	: DD/MM/YY
'TestDataSheet 		: ARENAHOTXT
'Test LInk TestCase Path  : jetsore ---------landing Page -----Verify the URL 
*/
/*!Function Name	: Verify the URL
'!Purpose			: This function verify the JPetStore application URL. 
'!Input 			: WebDriver driver 	
'!Output 	  		: returns JpetStore page display with the pet images, links named(Fish,Dogs,Cats,Reptiles and Birds) and a Sign in Link at the top of the website."); on successful navigated to the page  	 
'!Developed By	 	: ARENAHO 
'!Date	 		  	: 12/06/2024
*/
public class Verify_website_LogoLink {
	public static  void verify_website_LogoLinka(WebDriver driver) throws IOException, InterruptedException {
		
		driver.findElement(By.xpath("//*[@id=\"LogoContent\"]/a/img")).click();
		 if (driver.findElement(By.id("WelcomeContent")).isDisplayed()) {
	        	System.out.println("1. User  is successfully navigated to the home page .");
	            // takeScreenshot(driver, "navigateRegisterpage.png");
	            ScreenshotP.takeScreenshot(driver, "Verify_Login_with_valid_data_PASSS.png");
	        } else {
	            System.out.println("1. User  is  not successfully navigated to the home page");
	            ScreenshotP.takeScreenshotF(driver, "Verify_Login_with_valid_data_fail.png");   
	        }
}
	
	public static void main(String[] args) throws IOException, InterruptedException {
		   

		// Initialize WebDriver
	        WebDriver driver = new ChromeDriver();
	        driver.manage().window().maximize();
	        //prerequisite of the test case 
	       
	    	driver.manage().window().maximize();
			 ClickSignin signin=new  ClickSignin();
			 Verify_the_URL_OB_06 url=new  Verify_the_URL_OB_06();
			 url.navigateToSite(driver);
			 signin.clickSignIn(driver);
			 Verify_Register_Now_valid_data.verify_Register_Now_valid_data(driver);
			 Verify_Login_with_valid_data.verify_Register_Now_valid_data(driver);
			 Verify_fish_link_Home_page11.clickCategory(driver);
			 Verify_website_LogoLink.verify_website_LogoLinka(driver);
	       driver.quit();
	    
		}
}