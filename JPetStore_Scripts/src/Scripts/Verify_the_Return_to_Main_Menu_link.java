package Scripts;

import java.io.IOException;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;



/*

Script Name    	    : OB-13 Verify the [Return to Main Menu]
'Purpose    		: Tc verify the Return to main menu link
'Developed by  		: Ricky 
'Developed Date 	: 10/06/2024
'TestDataSheet 		: NA
'Test LInk TestCase Path  : Online Blooking -->Suite --> JPetStore--> OB -13*/
public class Verify_the_Return_to_Main_Menu_link {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		
		
		// Initialize WebDriver
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        
        //Prerequisite here
        Verify_the_URL.navigateToSite(driver);
        JpetStore_Automation.SignInLink(driver);
        Verify_Register_Now_Link_with_valid_data.registerNewUser(driver);
        Verify_Login_with_valid_data.signIn(driver);
	    
	    verifyReturn(driver);
	    driver.quit();
	}
	@Test
	public void TC13 () throws IOException, InterruptedException {

		// Initialize WebDriver
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        
        //Prerequisite here
        Verify_the_URL.navigateToSite(driver);
        JpetStore_Automation.SignInLink(driver);
        Verify_Register_Now_Link_with_valid_data.registerNewUser(driver);
        Verify_Login_with_valid_data.signIn(driver);
	    
	    verifyReturn(driver);
	    driver.quit();	}
	
	
     

    /*!Function Name        : verifyReturn13
    '!Purpose             : Verifies that the user can navigate to the Fish category and then return to the main menu.
    '!Input               : WebDriver driver - The WebDriver instance used to automate the browser.
    '!Output              : True/False - Returns true if the navigation to the Fish category and return to the main menu are successful; false otherwise.
    '!Developed By        : Name of the Tester who Designed/Wrote
    '!Date                : DD/MM/YY*/

	
	// WebDriver driver: The WebDriver instance used to automate browser actions.
    static void verifyReturn(WebDriver driver) throws InterruptedException, IOException {
        try {
           

            //  Click the Fish link
            driver.findElement(By.xpath("//*[@id=\"QuickLinks\"]/a[1]/img")).click();

            // Step 1: Click the [Return to main menu] link
            driver.findElement(By.xpath("//*[@id='BackLink']/a")).click();
            
            // Expected result: Home page should be displayed
            WebElement homePageElement = driver.findElement(By.xpath("//*[@id=\"MainImageContent\"]/map/area[1]")); //  an actual identifier for an element on the home page
            if (homePageElement.isDisplayed()) {
                System.out.println("Return to main menu was successful. Home page is displayed.");
                Screenshot.takeScreenshot(driver, "ReturnToMain_passedTC13.png");
            } else {
                System.out.println("Return to main menu failed. Home page is not displayed.");
                Screenshot.takeScreenshot(driver, "ReturnToMain_failedTC13.png");
                driver.quit();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("An error occurred during the test.");
        }
    }
}
