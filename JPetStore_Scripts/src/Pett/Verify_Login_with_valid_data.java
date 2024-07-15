package Pett;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
/*Script Title 

Script Name      	: OB-14 : Verify Login with valid data
'Purpose    		: This TC verify the Login PAGE 
'Developed by  		: ARENAHO 
'Developed Date 	: 12/06/2024
'TestDataSheet 		: ARENAHOTXT
'Test LInk TestCase Path  : jetsore ---------landing Page --- OB-7 : Verify Register Now! Link with valid data 
*/

/*!Function Name	: Verify_Login_Now_valid_data
'!Purpose			: This TC is verify the LOGIN  with valid data after the user has Registered 
'!Input 			: WebDriver driver - The WebDriver instance used to automate the browser.	
'!Output 	  		: returns JpetStore page display with the pet images, links named(Fish,Dogs,Cats,Reptiles and Birds) and a Sign in Link at the top of the website."); on successful navigated to the page  	 
'!Developed By	 	: ARENAHO 
'!Date	 		  	: 12/06/2024
*/


public class Verify_Login_with_valid_data {
	public static  void verify_Register_Now_valid_data(WebDriver driver) throws IOException, InterruptedException {
	       
		try {
		    driver.findElement(By.xpath("//a[contains(text(),'Sign In')]")).click();
		    String usernameV = Verify_Register_Now_valid_data.username;
		    String repeatedPasswordV = Verify_Register_Now_valid_data.repeatedpassword;
		    
		    // Find the username field and enter the retrieved username
		    WebElement usernameField = driver.findElement(By.name("username"));
		    usernameField.sendKeys(usernameV);

		    // Verify if the correct username is entered into the field
		    String actualUsername = usernameField.getAttribute("value");
		    if (actualUsername.equals(usernameV)) {
		        System.out.println("Correct username entered: " + usernameV);
		        ScreenshotP.takeScreenshot(driver, "verify__Now_valid_data.png");
		    } else {
		        System.out.println("Incorrect username entered: " + usernameV);
		    }
		    
		    driver.findElement(By.name("password")).clear();
		    driver.findElement(By.name("password")).sendKeys(repeatedPasswordV);
		    ScreenshotP.takeScreenshot(driver, "verify__Now_valid_data.png");

		    // log in 
		    driver.findElement(By.xpath("//*[@id=\"Catalog\"]/form/input")).click();
		    String welcomeMessage = driver.findElement(By.id("WelcomeContent")).getText();
		    // Define the expected message
		    Thread.sleep(2000); 
		    if (driver.findElement(By.id("WelcomeContent")).isDisplayed()) {
		        System.out.println("1. User " + welcomeMessage + " is successfully logged in.");
		        ScreenshotP.takeScreenshot(driver, "verify_loginp_Now_valid_dataPASS.png");

		    } else {
		        System.out.println("1. User is not logged in");
		        ScreenshotP.takeScreenshot(driver, "verify_loginf_Now_valid_dataPASS.png");            
		    }
		} catch (NoSuchElementException e) {
            System.out.println("error");
		    e.printStackTrace(); // Print the stack trace for detailed error information
		    // Capture screenshot for the error
		    ScreenshotP.takeScreenshot(driver, "error_occurred.png");
		    
        }
    }  

		
		

		// Continue with the next lines of code
	
	@Test
	public void TC11 () throws IOException, InterruptedException {

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		 ClickSignin signin=new  ClickSignin();
		 Verify_the_URL_OB_06 url=new  Verify_the_URL_OB_06();
		 url.navigateToSite(driver);
		 signin.clickSignIn(driver);
		 Verify_Register_Now_valid_data.verify_Register_Now_valid_data(driver);
		 Verify_Login_with_valid_data.verify_Register_Now_valid_data(driver);
		 driver.quit();
		
	}
        
	public static void main(String[] args) throws IOException, InterruptedException {
	
	WebDriver driver = new ChromeDriver();
	driver.manage().window().maximize();
	 ClickSignin signin=new  ClickSignin();
	 Verify_the_URL_OB_06 url=new  Verify_the_URL_OB_06();
	 //url.navigateToSite(driver);
	 signin.clickSignIn(driver);
	 Verify_Register_Now_valid_data.verify_Register_Now_valid_data(driver);
	 Verify_Login_with_valid_data.verify_Register_Now_valid_data(driver);
	 driver.quit();

}
}