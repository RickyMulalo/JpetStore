package Pett;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
/*Script Title 

Script Name      	: OB-51 : Verify Confirmation page details
'Purpose    		: Verifies TC verifies if the details on the confirmation page are correctly displayed with the ones that the user used to register with 
'Developed by  		: ARENAHO 
'Developed Date 	: 12/06/2024
'TestDataSheet 		: ARENAHOTXT
'Test LInk TestCase Path  : jetsore ---------landing Page --- OOB-51 : Verify Confirmation page details 
*/
import org.testng.annotations.Test;

/*!Function Name	: OB-51 : Verify Confirmation page details
'!Purpose			: Verifies TC verifies if the details on the confirmation page are correct 
'!Input 			: WebDriver driver - The WebDriver instance used to automate the browser.	
'!Output 	  		:  returns  details entered by the user  on successful verification of the page 	 
'!Developed By	 	: ARENAHO 
'!Date	 		  	: 12/06/2024\

*/



public class Verify_Confirmation_page_details {
	// Method to compare cell texts 
	 // private static void compareAdressdetails(String actual1, String actual2, String expected, String fieldName, WebDriver driver) {
		  private static void compareAdressdetails(String actual1, String actual2, String expected, String fieldName, WebDriver driver) {
			    try {
			        if (actual1.trim().equalsIgnoreCase(expected.trim()) && actual2.trim().equalsIgnoreCase(expected.trim())) {
			            System.out.println("Both cells have the Address details that the user used to register on his account: " + fieldName + " text: " + actual1);
			            ScreenshotP.takeScreenshot(driver, "Verify_Confirmation_page_details.png");
			        } else {
			            System.out.println(fieldName + " Both cells have the Address details that the user used to register on his account: " + expected + ", Found: " + actual1 + " and " + actual2);
			            ScreenshotP.takeScreenshotF(driver, "Verify_Confirmation_page_details.png");
			            driver.quit();
			        }
			    } catch (Exception e) {
			        System.out.println("Error comparing address details for " + fieldName + ": " + e.getMessage());
			        // Optionally, take a screenshot on exception
			        ScreenshotP.takeScreenshotF(driver, "Error_Comparing_" + fieldName + ".png");
			    }
			}


	    public static void verify_Confirmation_page_details(WebDriver driver) throws IOException, InterruptedException {
	        // Get the values from the registration
	        String FirstnameVOI = Verify_Register_Now_valid_data.Firstname;
	        String LastnameVOI = Verify_Register_Now_valid_data.Lastname;
	        String addressVOI = Verify_Register_Now_valid_data.address1;
	        String addressVOI2 = Verify_Register_Now_valid_data.address2;
	        String CityV = Verify_Register_Now_valid_data.city;
	        String StateV = Verify_Register_Now_valid_data.State;
	        String ZipV = Verify_Register_Now_valid_data.Zip;
	        String CountryV = Verify_Register_Now_valid_data.country;

	        try {
	            driver.findElement(By.xpath("//*[@id=\"Catalog\"]/a")).click();
	            //driver.findElement(By.xpath("//*[@id=\"Catalog\"]/form/input")).click();
	          //*[@id="Catalog"]/a
	        } catch (Exception e) {
	            System.out.println("Error navigating to cart or form: " + e.getMessage());
	            return;
	        }
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        try {
	        	WebElement dateElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='Catalog']/table/tbody/tr[1]/th/font[2]/b")));
	            //WebElement dateElement = driver.findElement(By.xpath("//*[@id=\"Catalog\"]/table/tbody/tr[1]/th/font[2]/b"));
	            String dateText = dateElement.getText();
	            if (dateText.contains(" ")) {
	                dateText = dateText.split(" ")[0];
	            }
	            System.out.println("Date from webpage (date only): " + dateText);

	            LocalDate currentDate = LocalDate.now();
	            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd", Locale.ENGLISH);
	            String formattedCurrentDate = currentDate.format(formatter);

	            System.out.println("The current date the user is making the order: " + formattedCurrentDate);
	            if (dateText.equals(formattedCurrentDate)) {
	                ScreenshotP.takeScreenshot(driver, "Verify_Confirmation_page_DATE.png");
	                System.out.println("The date of the order matches with the date that the user has the order on.");
	            } else {
	                ScreenshotP.takeScreenshotF(driver, "Verify_Confirmation_page_date.png");
	                driver.quit();
	                System.out.println("The date on the order is incorrect.");
	                return;
	            }
	        } catch (Exception e) {
	            System.out.println("Error verifying date: " + e.getMessage());
	            return;
	        }

	        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
	        String[][] xpaths = {
	                {"//*[@id=\"Catalog\"]/table/tbody/tr[3]/td[2]", "//*[@id=\"Catalog\"]/table/tbody/tr[12]/td[2]", FirstnameVOI, "Firstname"},
	                {"//*[@id=\"Catalog\"]/table/tbody/tr[4]/td[2]", "//*[@id=\"Catalog\"]/table/tbody/tr[13]/td[2]", LastnameVOI, "Lastname"},
	                {"//*[@id=\"Catalog\"]/table/tbody/tr[5]/td[2]", "//*[@id=\"Catalog\"]/table/tbody/tr[14]/td[2]", addressVOI, "Address1"},
	                {"//*[@id=\"Catalog\"]/table/tbody/tr[6]/td[2]", "//*[@id=\"Catalog\"]/table/tbody/tr[15]/td[2]", addressVOI2, "Address2"},
	                {"//*[@id=\"Catalog\"]/table/tbody/tr[7]/td[2]", "//*[@id=\"Catalog\"]/table/tbody/tr[16]/td[2]", CityV, "City"},
	                {"//*[@id=\"Catalog\"]/table/tbody/tr[8]/td[2]", "//*[@id=\"Catalog\"]/table/tbody/tr[17]/td[2]", StateV, "State"},
	                {"//*[@id=\"Catalog\"]/table/tbody/tr[9]/td[2]", "//*[@id=\"Catalog\"]/table/tbody/tr[18]/td[2]", ZipV, "Zip"},
	                {"//*[@id=\"Catalog\"]/table/tbody/tr[10]/td[2]", "//*[@id=\"Catalog\"]/table/tbody/tr[19]/td[2]", CountryV, "Country"}
	        };

	        for (String[] xpath : xpaths) {
	            try {
	                String actual1 = driver.findElement(By.xpath(xpath[0])).getText();
	                String actual2 = driver.findElement(By.xpath(xpath[1])).getText();
	                compareAdressdetails(actual1, actual2, xpath[2], xpath[3], driver);
	                driver.findElement(By.xpath("//*[@id=\"Catalog\"]/a")).click();
	            } catch (Exception e) {
	                System.out.println("Error comparing address details for " + xpath[3] + ": " + e.getMessage());
	                continue;
	            }
	        }

	        try {
	            driver.findElement(By.xpath("//*[@id=\"Catalog\"]/a")).click();
	            WebElement thankYouMessage = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"Content\"]/ul/li")));
	            String thankYouText = thankYouMessage.getText();

	            if (thankYouText.equals("Thank you, your order has been submitted.")) {
	                System.out.println("The message 'Thank you, your order has been submitted' is displayed.");
	                ScreenshotP.takeScreenshot(driver, "Verify_Confirmation_page_message.png");
	            } else {
	                System.out.println("Thank you message is incorrect.");
	                ScreenshotP.takeScreenshotF(driver, "Verify_Confirmation_page_message.png");
	                driver.quit();
	            }
	        } catch (Exception e) {
	            System.out.println("Error verifying thank you message: " + e.getMessage());
	        }
	    }

	    @Test
		public void TC51 () throws IOException, InterruptedException {
	    	WebDriver driver = new ChromeDriver();
			driver.manage().window().maximize();
			 ClickSignin signin=new  ClickSignin();
			 Verify_the_URL_OB_06 url=new  Verify_the_URL_OB_06();
			 //url.navigateToSite(driver);
			 signin.clickSignIn(driver);
			 Verify_Register_Now_valid_data.verify_Register_Now_valid_data(driver);
			 Verify_Login_with_valid_data.verify_Register_Now_valid_data(driver);
			 Verify_fish_link_Home_page11.clickCategory(driver);
			 Verify_the_product_details_page_is_displayed15.verifyProductDetailsPage15(driver);
		        UpdateShoppingCart.updateQuantity(driver);
		        ContinueToCheckout.VerifyCheckout(driver);
	    }

	public static void main(String[] args) throws IOException, InterruptedException {
		// preconditions for the test case 
		WebDriver driver = new ChromeDriver();
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
		driver.quit();
		 

	}
}
