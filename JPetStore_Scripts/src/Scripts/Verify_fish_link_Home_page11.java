package Scripts;

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
'Developed Date 	: 07/06/2024
'Test LInk TestCase Path  : Online Blooking -->Suite --> JPetStore  --> OB -11*/
public class Verify_fish_link_Home_page11 {

	
	public static void main(String[] args) throws IOException, InterruptedException {
   

		// Initialize WebDriver
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        
        //prerequisite here
        Verify_the_URL.navigateToSite(driver);
        JpetStore_Automation.SignInLink(driver);
        Verify_Register_Now_Link_with_valid_data.registerNewUser(driver);
        Verify_Login_with_valid_data.signIn(driver);
        clickCategory(driver);
        driver.quit();
    
	}
	@Test
public void TC11() throws IOException, InterruptedException
{
	// Initialize WebDriver
    WebDriver driver = new ChromeDriver();
    driver.manage().window().maximize();
    
    //prerequisite here
    Verify_the_URL.navigateToSite(driver);
    JpetStore_Automation.SignInLink(driver);
    Verify_Register_Now_Link_with_valid_data.registerNewUser(driver);
    Verify_Login_with_valid_data.signIn(driver);
    clickCategory(driver);
    driver.quit();

}

/*!Function Name        : Verifyfishlinkonhome11
'!Purpose             : Verifies that the Fish category link on the homepage is clickable and navigates to the correct Fish category page.
'!Input               : WebDriver driver - The WebDriver instance used to automate the browser.
'!Output              : True/False - Returns true if the Fish category page is displayed correctly; false otherwise.
'!Developed By        : Ricky
'!Date                : 07/06/2024*/
	


	//String category: This is the second parameter of the method. It is a String type, and category is the name of the variable that will hold the category name to be clicked.
	
public static void clickCategory(WebDriver driver) {
	
	try {
        // Step 1: Click on the Fish category link
        WebElement fishCategoryLink = driver.findElement(By.xpath("//*[@id=\"QuickLinks\"]/a[1]/img"));
        fishCategoryLink.click();

        // Expected result for step 1
        WebElement categoryName = driver.findElement(By.xpath("//*[@id=\"Catalog\"]/h2"));
        if (categoryName.isDisplayed() && categoryName.getText().equalsIgnoreCase("Fish")) {
            System.out.println("Fish category page is displayed correctly,with name of the category visible on the page.");
            Screenshot.takeScreenshot(driver, "Fishpage_passedTC11.png");
        } else {
            System.out.println("Fish category page is NOT displayed correctly.");
            Screenshot.takeScreenshot(driver, "Fishpage_failedTC11.png");
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
            Screenshot.takeScreenshot(driver, "ProductDetails_passedTC11.png");
        } else {
            if (!itemId.isDisplayed()) {
                System.out.println("Test Click Product ID: FAILED - Item ID is not displayed");
                Screenshot.takeScreenshot(driver, "ProductDetails_failedTC11.png");
            }
            if (!productId.isDisplayed()) {
                System.out.println("Test Click Product ID: FAILED - Product ID is not displayed");
                Screenshot.takeScreenshot(driver, "ProductDetails_failedTC11.png");
            }
            if (!description.isDisplayed()) {
                System.out.println("Test Click Product ID: FAILED - Description is not displayed");
                Screenshot.takeScreenshot(driver, "ProductDetails_failedTC11.png");
            }
            if (!listPrice.isDisplayed()) {
                System.out.println("Test Click Product ID: FAILED - List Price is not displayed");
                Screenshot.takeScreenshot(driver, "ProductDetails_failedTC11.png");
            }
        }
    
    } catch (Exception e) {
        e.printStackTrace();
    }
}
	
}
