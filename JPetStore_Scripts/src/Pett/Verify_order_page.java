package Pett;

import java.io.IOException;
import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

/*
Script Name    	    : OB-77 : Verify order page
'Purpose    		: TC verifies if the details on the confirmation page are correct
'Developed by  		: Ricky 
'Developed Date 	: 10/06/2024
'TestDataSheet 		: NA
'Test LInk TestCase Path  : Online Blooking -->Suite --> JPetStore--> OB -77*/
public class Verify_order_page {
	@Test
	public void TC77 () throws IOException, InterruptedException {
		 WebDriver driver = new ChromeDriver();
	        driver.manage().window().maximize();
	        
	        //prerequisite here
	        //WebDriver driver = new ChromeDriver();
			driver.manage().window().maximize();
			 ClickSignin signin=new  ClickSignin();
			 Verify_the_URL_OB_06 url=new  Verify_the_URL_OB_06();
			 url.navigateToSite(driver);
			 signin.clickSignIn(driver);
			 Verify_Register_Now_valid_data.verify_Register_Now_valid_data(driver);
			 Verify_Login_with_valid_data.verify_Register_Now_valid_data(driver);
			 Verify_fish_link_Home_page11.clickCategory(driver);
			 Verify_the_product_details_page_is_displayed15.verifyProductDetailsPage15(driver);
		        UpdateShoppingCart.updateQuantity(driver);
		        ContinueToCheckout.VerifyCheckout(driver);
			 
			//AddToCart.verifyaddtocartbutton(driver);
			 Verify_Confirmation_page_details.verify_Confirmation_page_details(driver);
	          

	        VerifyOrderDetails(driver); // Call the method to verify confirmation page details
		    driver.quit(); 

	}
	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		// Initialize WebDriver
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        
        //prerequisite here
        //WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		 ClickSignin signin=new  ClickSignin();
		 Verify_the_URL_OB_06 url=new  Verify_the_URL_OB_06();
		 url.navigateToSite(driver);
		 signin.clickSignIn(driver);
		 Verify_Register_Now_valid_data.verify_Register_Now_valid_data(driver);
		 Verify_Login_with_valid_data.verify_Register_Now_valid_data(driver);
		 Verify_fish_link_Home_page11.clickCategory(driver);
		 Verify_the_product_details_page_is_displayed15.verifyProductDetailsPage15(driver);
	        UpdateShoppingCart.updateQuantity(driver);
	        ContinueToCheckout.VerifyCheckout(driver);
		 
		//AddToCart.verifyaddtocartbutton(driver);
		 Verify_Confirmation_page_details.verify_Confirmation_page_details(driver);
          

        VerifyOrderDetails(driver); // Call the method to verify confirmation page details
	    driver.quit(); 
		 
	}
     
	
	
	/**
	 * Function Name: VerifyOderdetails
	 * Purpose      : Verifies the confirmation page details, including the thank you message and the order number.
	 * Input        : WebDriver driver - The WebDriver instance used to automate the browser.
	 * Output       : Outputs messages to the console and takes screenshots based on verification results.
	 * Developed By : Ricky
	 * Date         : 10/06/2024
	 */
	
	// WebDriver driver: The WebDriver instance used to automate browser actions.
	public static void VerifyOrderDetails(WebDriver driver) {
	    try {
	    	
	    	//driver.findElement(By.xpath("//*[@id=\"Catalog\"]/a")).click();
	        // Create an instance of WebDriverWait with a 10-second timeout
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	        // Verify "Thank you, your order has been submitted." text
	        WebElement thankYouMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"Content\"]/ul/li"))); // Wait for and find the thank you message element
	        String thankYouText = thankYouMessage.getText(); // Get the text from the thank you message element
	        if (thankYouText.equals("Thank you, your order has been submitted.")) { // Check if the text matches the expected message
	            System.out.println("Thank you, your order has been submitted."); // Print a confirmation message
	            ScreenshotP.takeScreenshot(driver, "Message_passedyTC77.png"); // Take a screenshot indicating the message verification passed
	        } else {
	            System.out.println("Thank you message is incorrect."); // Print an error message if the text does not match
	            ScreenshotP.takeScreenshot(driver, "Message_failedTC77.png"); // Take a screenshot indicating the message verification failed
	            driver.quit();
	            return;
	        }

	        // Verify order number pattern "Order # followed by digits"
	        WebElement orderNumberElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"Catalog\"]/table/tbody/tr[1]/th"))); // Wait for and find the order number element
	        String orderNumberText = orderNumberElement.getText(); // Get the text from the order number element

	        // Extract the first 5 digits from the order number
	        String extractedOrderNumber = extractOrderNumber(orderNumberText); // Extract the order number from the text
	        if (extractedOrderNumber != null) { // Check if a valid order number was extracted
	            System.out.println("Order number is displayed correctly: " + extractedOrderNumber); // Print the extracted order number
	            ScreenshotP.takeScreenshot(driver, "Order_passed.pngTC77"); // Take a screenshot indicating the order number verification passed
	        } else {
	            System.out.println("No valid order number found."); // Print an error message if no valid order number was found
	            ScreenshotP.takeScreenshot(driver, "Order_failed.pngTC77"); // Take a screenshot indicating the order number verification failed
	            driver.quit();
	            return;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        ScreenshotP.takeScreenshot(driver, "ErrorTC77.png"); // Take a screenshot indicating an error occurred
	    } finally {
	        driver.quit(); // Ensure the driver is closed regardless of success or failure
	    }
	}

	// Helper method to extract the first 5 digits from the order number
	// Return the captured group which is the order number.
	private static String extractOrderNumber(String orderText) {
	    Pattern pattern = Pattern.compile("Order #\\d+"); // Define a regex pattern to match "Order #" followed by digits
	    Matcher matcher = pattern.matcher(orderText); // Create a matcher to find matches in the orderText
	    if (matcher.find()) { // Check if the pattern is found in the orderText
	        String orderDigits = matcher.group().replaceAll("\\D", ""); // Remove non-digit characters from the matched string
	        return orderDigits.substring(0, Math.min(orderDigits.length(), 5)); // Return the first 5 digits of the order number
	    }
	    return null; // Return null if no valid order number was found
	}

	}