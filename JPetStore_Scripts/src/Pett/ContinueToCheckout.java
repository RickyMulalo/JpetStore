package Pett;

import java.io.IOException;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

//import proceedToCheckout.Checkout;
//import readData.Data;
//import setUp.SetupBrowser;
//import utils.TestProof;

public class ContinueToCheckout {
	private WebDriver driver; // Declare driver as a class-level variable

    @BeforeClass
    public void setUp() {
        // Set up ChromeDriver
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
	@Test
	
	public void TC114 () throws IOException, InterruptedException {
		WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        
        //prerequisite here
        ClickSignin signin = new ClickSignin();
        Verify_the_URL_OB_06 url = new Verify_the_URL_OB_06();
        url.navigateToSite(driver);
        signin.clickSignIn(driver);
        Verify_Register_Now_valid_data.verify_Register_Now_valid_data(driver);
        Verify_Login_with_valid_data.verify_Register_Now_valid_data(driver);
        Verify_fish_link_Home_page11.clickCategory(driver);

        Verify_the_product_details_page_is_displayed15.verifyProductDetailsPage15(driver);
        UpdateShoppingCart.updateQuantity(driver);
        VerifyCheckout(driver);	
	}
	@AfterClass
    public void tearDown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }
	public static void main(String[] args) throws IOException, InterruptedException {
		// Initialize WebDriver
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        
        //prerequisite here
        ClickSignin signin = new ClickSignin();
        Verify_the_URL_OB_06 url = new Verify_the_URL_OB_06();
        url.navigateToSite(driver);
        signin.clickSignIn(driver);
        Verify_Register_Now_valid_data.verify_Register_Now_valid_data(driver);
        Verify_Login_with_valid_data.verify_Register_Now_valid_data(driver);
        Verify_fish_link_Home_page11.clickCategory(driver);

        Verify_the_product_details_page_is_displayed15.verifyProductDetailsPage15(driver);
        UpdateShoppingCart.updateQuantity(driver);
        VerifyCheckout(driver);
		//tearDown();
	}
	
	
	/*!Function Name      : OB-28 : Verify CheckOut _Continue  with valid data 
	'!Purpose             : This  TC verify the Continue  Link on checkout  page using valid data  and if the displayed Billing Address information items match the one the user registered with 
	'!Input               : WebDriver driver - The WebDriver instance used to automate the browser.
	'!Output              : True/False - Returns true if the input field are displayed correctly abd the button is working; false otherwise.
	'!Developed By        :	Zono
	'!Date                : 12/06/2024*/
	public static void VerifyCheckout(WebDriver driver) throws InterruptedException, IOException {
	    try {
	        
	    	String filePath = "src/update";
            Map<String, String> userDetails = ReadData.readUserDetailsFromFile(filePath);
	      
	        // Fill Payment details
	        // Step 1: Select the card type on the dropdown
	        try {
	            WebElement cardTypeField = driver.findElement(By.name("order.cardType"));
	            String cardType = userDetails.get("cardType");
	            cardTypeField.sendKeys(cardType);

	            // Verifying if the selected card type is displayed in the dropdown
	            Select dropdown = new Select(cardTypeField);
	            WebElement selectedOption = dropdown.getFirstSelectedOption();
	            String displayedCardType = selectedOption.getText();

	            if (displayedCardType.equals(cardType)) {
	                System.out.println("The card type '" + cardType + "' is displayed in the dropdown.");
	            } else {
	                System.out.println("Error: The selected card type is not displayed in the dropdown.");
	            }
	        } catch (NoSuchElementException e) {
	            System.out.println("Error: Card type field is not found.");
	        }

	        // Step 2: Enter valid Card Number
	        try {
	            WebElement creditCardField = driver.findElement(By.name("order.creditCard"));
	            creditCardField.clear();
	            creditCardField.sendKeys(userDetails.get("cardNumber"));

	            // Check if the entered card number matches the expected value
	            String enteredCardNumber = creditCardField.getAttribute("value");
	            String expectedCardNumber = userDetails.get("cardNumber");

	            if (enteredCardNumber.equals(expectedCardNumber)) {
	                System.out.println("The Card Number is accepted, displayed " + enteredCardNumber);
	            } else {
	                System.out.println("Card number entered does not match expected value.");
	            }
	        } catch (NoSuchElementException e) {
	            System.out.println("Error: Credit card field is not found.");
	        }

	        // Step 3: Enter Expiry Date in the format: (MM/YY)
	        try {
	            WebElement expiryDateField = driver.findElement(By.name("order.expiryDate"));
	            expiryDateField.clear();
	            expiryDateField.sendKeys(userDetails.get("expiryDate"));

	            String enteredExpiryDate = expiryDateField.getAttribute("value");
	            String expectedExpiryDate = userDetails.get("expiryDate");

	            if (enteredExpiryDate.equals(expectedExpiryDate)) {
	                System.out.println("The card expiry date is accepted and displayed in the format (MM/YY): " + enteredExpiryDate);
	            } else {
	                System.out.println("Expiry date entered does not match expected value.");
	            }
	        } catch (NoSuchElementException e) {
	            System.out.println("Error: Expiry date field is not found.");
	        }

	        //Map<String, String> userDetails1 = ReadData.readUserDetailsFromFile("src/update");

	        // Step 4: Verify first name
	        try {
	            WebElement firstNameField = driver.findElement(By.name("order.billToFirstName"));
	            String displayedFirstName = firstNameField.getAttribute("value"); // Retrieve the value attribute

	            if (displayedFirstName.equals(userDetails.get("firstName"))) {
	                System.out.println("The correct first name is accepted and displayed.");
	            } else {
	                System.out.println("Error: The displayed first name does not match the one from the text file.");
	            }
	        } catch (NoSuchElementException e) {
	            System.out.println("Error: First name field is not found.");
	        }

	        // Step 5: Verify last name
	        try {
	            WebElement lastNameField = driver.findElement(By.name("order.billToLastName"));
	            String displayedLastName = lastNameField.getAttribute("value"); // Retrieve the value attribute

	            if (displayedLastName.equals(userDetails.get("lastName"))) {
	                System.out.println("The correct last name is accepted and displayed.");
	            } else {
	                System.out.println("Error: The displayed last name does not match the one from the text file.");
	            }
	        } catch (NoSuchElementException e) {
	            System.out.println("Error: Last name field is not found.");
	        }

	        // Step 6: Verify address 1
	        try {
	            WebElement address1Field = driver.findElement(By.name("order.billAddress1"));
	            String displayedAddress1 = address1Field.getAttribute("value"); // Retrieve the value attribute

	            if (displayedAddress1.equals(userDetails.get("address1"))) {
	                System.out.println("The address 1 is displayed");
	            } else {
	                System.out.println("Error: The displayed address 1 does not match the one from the text file.");
	            }
	        } catch (NoSuchElementException e) {
	            System.out.println("Error: Address 1 field is not found.");
	        }

	        // Step 7: Verify address 2
	        try {
	            WebElement address2Field = driver.findElement(By.name("order.billAddress2"));
	            String displayedAddress2 = address2Field.getAttribute("value"); // Retrieve the value attribute

	            if (displayedAddress2.equals(userDetails.get("address2"))) {
	                System.out.println("The address 2 is displayed");
	            } else {
	                System.out.println("Error: The displayed address 2 does not match the one from the text file.");
	            }
	        } catch (NoSuchElementException e) {
	            System.out.println("Error: Address 2 field is not found.");
	        }
	        

	        // Step 8: Verify city name
	        try {
	            WebElement cityNameField = driver.findElement(By.name("order.billCity"));
	            String displayedCityName = cityNameField.getAttribute("value"); // Retrieve the value attribute

	            if (displayedCityName.equals(userDetails.get("city"))) {
	                System.out.println("The city name is accepted and displayed");
	            } else {
	                System.out.println("Error: The displayed city name does not match the one from the text file.");
	            }
	        } catch (NoSuchElementException e) {
	            System.out.println("Error: City name field is not found.");
	        }

	        // Step 9: Verify state name
	        try {
	            WebElement stateField = driver.findElement(By.name("order.billState"));
	            String displayedState = stateField.getAttribute("value"); // Retrieve the value attribute

	            if (displayedState.equals(userDetails.get("state"))) {
	                System.out.println("The correct state is displayed.");
	            } else {
	                System.out.println("Error: The displayed state does not match the one from the text file.");
	            }
	        } catch (NoSuchElementException e) {
	            System.out.println("Error: State field is not found.");
	        }

	        // Step 10: Verify zip code
	        try {
	            WebElement zipField = driver.findElement(By.name("order.billZip"));
	            String displayedZip = zipField.getAttribute("value"); // Retrieve the value attribute

	            if (displayedZip.equals(userDetails.get("zip")) && displayedZip.matches("\\d+")) {
	                System.out.println("The correct zip code is accepted, displayed, and should only display digits.");
	            } else {
	                System.out.println("Error: The displayed zip code does not match the one from the text file.");
	            }
	        } catch (NoSuchElementException e) {
	            System.out.println("Error: Zip code field is not found.");
	        }

	        // Step 11: Verify country name
	        try {
	            WebElement countryField = driver.findElement(By.name("order.billCountry"));
	            String displayedCountry = countryField.getAttribute("value"); // Retrieve the value attribute

	            if (displayedCountry.equals(userDetails.get("country"))) {
	                System.out.println("The correct country is displayed.");
	            } else {
	                System.out.println("Error: The displayed country does not match the one from the text file.");
	            }
	        } catch (NoSuchElementException e) {
	            System.out.println("Error: Country field is not found.");
	        }

	        // Step 12: Click on Continue button
	        try {
	            WebElement proceedToCheckoutButton = driver.findElement(By.xpath("//*[@id=\"Catalog\"]/form/input"));
	            proceedToCheckoutButton.click();

	            WebElement confirmationMessage = driver.findElement(By.xpath("//*[@id=\"Catalog\"]"));
	            if (confirmationMessage.isDisplayed()) {
	                System.out.println("It is navigated to checkout confirm page showing the \"Please confirm the information below and then press continue.....\" order, Billing Address, Shipping Address.");
	            } else {
	                System.out.println("Please confirm the information below and then press continue is not visible.");
	            }
	        } catch (NoSuchElementException e) {
	            System.out.println("Error: Proceed to Checkout button is not found.");
	        }
	    } catch (Exception e) {
	        System.err.println("An error occurred during checkout verification: " + e.getMessage());
	        e.printStackTrace();
	    }
	}


}