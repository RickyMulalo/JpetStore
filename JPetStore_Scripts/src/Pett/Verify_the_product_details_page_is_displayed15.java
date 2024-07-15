package Pett;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;




/*Script Name    	: OB-15 : Verify the product details page  
'Purpose    		: TC to verify if the  fish product details page is displayed with a table 
'Developed by  		:Ricky 
'Developed Date 	: 10/06/2024
'TestDataSheet 		: NA
'Test LInk TestCase Path:Online Blooking -->Suite --> JPetStore--> OB - 15*/

public class Verify_the_product_details_page_is_displayed15 {
	@Test
	public void TC15 () throws IOException, InterruptedException {
		WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        
        //prerequisite here
        ClickSignin signin=new  ClickSignin();
		 Verify_the_URL_OB_06 url=new  Verify_the_URL_OB_06();
		 //url.navigateToSite(driver);
		 signin.clickSignIn(driver);
		 Verify_Register_Now_valid_data.verify_Register_Now_valid_data(driver);
		 Verify_Login_with_valid_data.verify_Register_Now_valid_data(driver);
		 Verify_fish_link_Home_page11.clickCategory(driver);

        verifyProductDetailsPage15(driver);
        driver.quit();
	}
    public static void main(String[] args) throws IOException, InterruptedException {

		// Initialize WebDriver
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        
        //prerequisite here
        ClickSignin signin=new  ClickSignin();
		 Verify_the_URL_OB_06 url=new  Verify_the_URL_OB_06();
		 url.navigateToSite(driver);
		 signin.clickSignIn(driver);
		 Verify_Register_Now_valid_data.verify_Register_Now_valid_data(driver);
		 Verify_Login_with_valid_data.verify_Register_Now_valid_data(driver);
		 Verify_fish_link_Home_page11.clickCategory(driver);

        verifyProductDetailsPage15(driver);
        driver.quit();
    }

    /**
     * Function Name: verifyProductDetailsPage15
     * Purpose      : Verifies that the fish product details page is displayed with a table, links are clickable, and the add to cart button functions correctly.
     * Input        : WebDriver driver - The WebDriver instance used to automate the browser.
     * Output       : True/False - Returns true if all verifications are successful; false otherwise.
     * Developed By : Ricky
     * Date         : 10/06/2024
     */
    
 // WebDriver driver: The WebDriver instance used to automate browser actions.
    // String itemId: The item ID used to locate the specific product on the product details page.
    public static void verifyProductDetailsPage15(WebDriver driver) {
        try {
        	String itemId ="";
            // Step 1: Verify if product details are displayed in a table correctly
            WebElement table = driver.findElement(By.xpath("//*[@id=\"Catalog\"]/table")); // Locate the table element

            List<WebElement> rows = table.findElements(By.xpath(".//tr")); // Get all rows within the table
            boolean itemFound = false;

            for (WebElement row : rows) {
                List<WebElement> cells = row.findElements(By.xpath(".//td")); // Get all cells within the row
                for (WebElement cell : cells) {
                    if (cell.getText().equals(itemId)) { // Check if the cell contains the item ID
                        System.out.println("Product details page are displayed in a table correctly with the correct item.");
                        ScreenshotP.takeScreenshot(driver, "ProductDetails_passedTC15.png");
                        itemFound = true;
                        break; // Exit the inner loop once the item is found
                    }
                }
                if (itemFound) {
                    break; // Exit the outer loop once the item is found
                }
            }

            if (!itemFound) {
                System.out.println("Product details table is NOT displayed.");
                ScreenshotP.takeScreenshot(driver, "ProductDetailsTC15_failed.png");
                return;
            }

            // Step 2: Check if links are clickable
            List<WebElement> productLinks = table.findElements(By.tagName("a"));
            boolean allLinksClickable = true;
            for (WebElement link : productLinks) {
                if (!link.isEnabled()) {
                    allLinksClickable = false;
                    System.out.println("Link is not clickable: " + link.getText());
                    ScreenshotP.takeScreenshot(driver, "Links_failedTC15.png");
                }
            }
            if (allLinksClickable) {
                System.out.println("All product links are clickable.");
                ScreenshotP.takeScreenshot(driver, "Links_passedTC15.png");
            }

            // Step 3: Verify the Shopping cart page is displayed
            WebElement addToCartButtonCat = driver.findElement(By.xpath("//*[@id=\"Catalog\"]/table/tbody/tr[2]/td[5]/a"));
            addToCartButtonCat.click();
            // Wait for the shopping cart page to load
            WebElement shoppingCartPage = driver.findElement(By.xpath("//*[@id=\"Cart\"]/form/table/tbody/tr[3]/td[1]/input")); // I select any unique object on the shopping cart page
            if (shoppingCartPage.isDisplayed()) {
                System.out.println("Shopping cart page is displayed correctly.");
                ScreenshotP.takeScreenshot(driver, "ShoppingCart_passedTC15.png");
            } else {
                System.out.println("Shopping cart page is NOT displayed.");
                ScreenshotP.takeScreenshot(driver, "ShoppingCart_failedTC15.png");
            }
        } catch (Exception e) {
            e.printStackTrace();
            ScreenshotP.takeScreenshot(driver, "ErrorTC15.png"); // Take a screenshot indicating an error occurred
        } finally {
           // driver.quit(); // Ensure the driver is closed regardless of success or failure
        }
    }
}
