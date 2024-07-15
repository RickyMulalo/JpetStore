package Pett;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
/*Verify fish link on home page on JPetStore 

Script Name    	    :OB-11 : Verify fish link on home page on JPetStore
'Purpose    		: To verify link on home page
'Developed by  		: Ricky 
'Developed Date 	: 07/06/2024*/
public class Verify_fish_link_Home_page11 {
	 @Test
		public void TC11 () throws IOException, InterruptedException {

		// Initialize WebDriver
	        WebDriver driver = new ChromeDriver();
	        driver.manage().window().maximize();
	        //prerequisite of the test case        
	    	driver.manage().window().maximize();
			 ClickSignin signin=new  ClickSignin();
			 
			 Verify_the_URL_OB_06 url=new  Verify_the_URL_OB_06();
			
			 signin.clickSignIn(driver);
			 Verify_Register_Now_valid_data.verify_Register_Now_valid_data(driver);
			 Verify_Login_with_valid_data.verify_Register_Now_valid_data(driver);
			 clickCategory(driver);
			 driver.quit();
			
		} 
	
public static void main(String[] args) throws IOException, InterruptedException {
   

	// Initialize WebDriver
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        //prerequisite of the test case        
    	driver.manage().window().maximize();
		 ClickSignin signin=new  ClickSignin();
		 
		 Verify_the_URL_OB_06 url=new  Verify_the_URL_OB_06();
		// url.navigateToSite(driver);
		 signin.clickSignIn(driver);
		 Verify_Register_Now_valid_data.verify_Register_Now_valid_data(driver);
		 Verify_Login_with_valid_data.verify_Register_Now_valid_data(driver);
		 clickCategory(driver);
		 driver.quit();
	}


/*!Function Name        : Verifyfishlinkonhome11
'!Purpose             : Verifies that the Fish category link on the homepage is clickable and navigates to the correct Fish category page.
'!Input               : WebDriver driver - The WebDriver instance used to automate the browser.
'!Output              : True/False - Returns true if the Fish category page is displayed correctly; false otherwise.
'!static Developed By        : Ricky
'!Date                : 07/06/2024*/
	


	//String category: This is the second parameter of the method. It is a String type, and category is the name of the variable that will hold the category name to be clicked.Z
public static void clickCategory(WebDriver driver) {
	
	try {
        // Step 1: Click on the Fish category link
        WebElement fishCategoryLink = driver.findElement(By.xpath("//*[@id=\"QuickLinks\"]/a[1]/img"));
       // driver.findElement(By.xpath("//*[@id=\"Catalog\"]/form/p[2]/input[2]")).isDisplayed() &&

        fishCategoryLink.click();

        // Expected result for step 1
        WebElement categoryName = driver.findElement(By.xpath("//*[@id=\"Catalog\"]/h2"));
        if (categoryName.isDisplayed() && categoryName.getText().equalsIgnoreCase("Fish")) {
            System.out.println("Fish category page is displayed correctly,with name of the category visible on the page.");
            ScreenshotP.takeScreenshot(driver, "Fishpage_passedTC11.png");
        } else {
            System.out.println("Fish category page is NOT displayed correctly.");
            ScreenshotP.takeScreenshot(driver, "Fishpage_failedTC11.png");
            driver.quit();
        }

        // Step 2: Click on one of the product ID links
        WebElement productLink = driver.findElement(By.xpath("//*[@id=\"Catalog\"]/table/tbody/tr[2]/td[1]/a")); // the product links have class 'product-id' and tag 'a'
        productLink.click();
        
        //expected results
        // Verify the product details are displayed
        WebElement itemId = driver.findElement(By.xpath("//*[@id=\"Catalog\"]/table/tbody/tr[2]/td[1]/a"));
        WebElement productId = driver.findElement(By.xpath("//*[@id=\"Catalog\"]/table/tbody/tr[2]/td[2]"));
        WebElement description = driver.findElement(By.xpath("//*[@id=\"Catalog\"]/table/tbody/tr[2]/td[3]"));
        WebElement listPrice = driver.findElement(By.xpath("//*[@id=\"Catalog\"]/table/tbody/tr[2]/td[4]"));

        if (itemId.isDisplayed() && productId.isDisplayed() && description.isDisplayed() && listPrice.isDisplayed()) {
            System.out.println("Test Click Product ID: PASSED - All product details are displayed correctly");
            ScreenshotP.takeScreenshot(driver, "ProductDetails_passedTC11.png");
        } else {
            if (!itemId.isDisplayed()) {
                System.out.println("Test Click Product ID: FAILED - Item ID is not displayed");
                ScreenshotP.takeScreenshot(driver, "ProductDetails_failedTC11.png");
            }
            if (!productId.isDisplayed()) {
                System.out.println("Test Click Product ID: FAILED - Product ID is not displayed");
                ScreenshotP.takeScreenshot(driver, "ProductDetails_failedTC11.png");
            }
            if (!description.isDisplayed()) {
                System.out.println("Test Click Product ID: FAILED - Description is not displayed");
                ScreenshotP.takeScreenshot(driver, "ProductDetails_failedTC11.png");
            }
            if (!listPrice.isDisplayed()) {
                System.out.println("Test Click Product ID: FAILED - List Price is not displayed");
                ScreenshotP.takeScreenshot(driver, "ProductDetails_failedTC11.png");
            }
        }
    
    } catch (Exception e) {
        e.printStackTrace();
    }
}
	
}