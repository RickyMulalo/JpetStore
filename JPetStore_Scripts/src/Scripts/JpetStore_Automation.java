package Scripts;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.google.common.io.Files;

public class JpetStore_Automation {
	
	private WebDriver driver;
	public  JpetStore_Automation (WebDriver driver) {
        this.driver = driver;
    }

	public static void main(String[] args) throws InterruptedException, IOException {

		// Read the URL from the external text file
	    Map<String, String> userDetails = readUserDetailsFromFile("src/WebLink");
        
        String link = userDetails.get("link");

        // Initialize WebDriver
        WebDriver driver = new ChromeDriver();
        
        // Open the URL
        driver.get(link);
        driver.manage().window().maximize();
        
        JpetStore_Automation jpet= new JpetStore_Automation(driver);
       
        SignInLink(driver);
        Registerlink(driver);
        
        registerAndlogin(driver);
        verifyReturn(driver);
        
        jpet.VerifyFishLink("Fish");
        
        String itemId = "EST-1"; 
        verifyProductDetailsPage15(driver, itemId); // Pass the itemId parameter
        updateQuantity(driver);
        proceedToCheckout(driver); // Call the method to proceed to checkout
        verifyCheckoutPage(driver); // Call the method to verify the checkout page
        VerifyContinue(driver);
        verifyAndCalculateTotalCost(driver);
 /*Verify order page 
        
        Script Name    	:OB-77 : Verify order page
        'Purpose    		: TC to verify if correct order is submited with correct information
        'Developed by  		: Ricky 
        'Developed Date 	: 07/06/2024*/
        VerifyConfirmationpagedetails(driver);
        driver.quit();
      
        } 
	
	
	static void SignInLink(WebDriver driver) throws InterruptedException { 
		WebDriverWait wait = new WebDriverWait(driver, Duration. ofSeconds (10));
		  driver.findElement(By.xpath("//*[@id=\"MenuContent\"]/a[2]")).click();
		  
		
		 
	}
	
	static void Registerlink(WebDriver driver) throws InterruptedException {

	
		// click register  sign on link 
        driver.findElement(By.xpath("//*[@id=\"Catalog\"]/a")).click();
        // if useer navigated to the next page then the button works 
        if (driver.getCurrentUrl().equals("https://petstore.octoperf.com/actions/Account.action?newAccountForm=")) {
            System.out.println(" User navigated to the register  page  ");
            takeScreenshot(driver, "navigateRegisterpage.png");
        } else {
            System.out.println("User not  navigated to the register  page ");
            takeScreenshot(driver, "navigateRegisterpage_failed.png");
        }
    }
	
	static void registerAndlogin(WebDriver driver) throws InterruptedException {
        String filePath = "C:/Users/MulaloRickyMulaudzi-/OneDrive - Linkfields innovations/Documents/Scriptss.txt";

        String username = "";
        String repeatedpassword = "";
        String newpassword = "";
        String Firstname = "";
        String Lastname = "";
        String email = "";
        String phone = "";
        String address1 = "";
        String address2 = "";
        String city = "";
        String State = "";
        String Zip = "";
        String country = "";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            Random randomNum = new Random();
            int namee = randomNum.nextInt(15000);
            username = br.readLine();
            repeatedpassword = br.readLine();
            newpassword = br.readLine();
            Firstname = br.readLine();
            Lastname = br.readLine();
            email = br.readLine();
            phone = br.readLine();
            address1 = br.readLine();
            address2 = br.readLine();
            city = br.readLine();
            State = br.readLine();
            Zip = br.readLine();
            country = br.readLine();
        } catch (IOException e) {
            System.err.println("Error reading the credentials file: " + e.getMessage());
        }
        
        // Enter valid details to the text field
        driver.findElement(By.name("username")).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(newpassword);
        driver.findElement(By.name("repeatedPassword")).sendKeys(repeatedpassword);
        driver.findElement(By.name("account.firstName")).sendKeys(Firstname);
        driver.findElement(By.name("account.lastName")).sendKeys(Lastname);
        driver.findElement(By.name("account.email")).sendKeys(email);
        driver.findElement(By.name("account.phone")).sendKeys(phone);
        driver.findElement(By.name("account.address1")).sendKeys(address1);
        driver.findElement(By.name("account.address2")).sendKeys(address2);
        driver.findElement(By.name("account.city")).sendKeys(city);
        driver.findElement(By.name("account.state")).sendKeys(State);
        driver.findElement(By.name("account.zip")).sendKeys(Zip);
        driver.findElement(By.name("account.country")).sendKeys(country);

        WebElement firstDropdown = driver.findElement(By.xpath("//*[@id='Catalog']/form/table[3]/tbody/tr[1]/td[2]/select"));
        Select selectFirstDropdown = new Select(firstDropdown);
        selectFirstDropdown.selectByVisibleText("english");

        WebElement secondDropdown = driver.findElement(By.xpath("//*[@id='Catalog']/form/table[3]/tbody/tr[2]/td[2]/select"));
        Select selectSecondDropdown = new Select(secondDropdown);
        selectSecondDropdown.selectByVisibleText("BIRDS");

        // Click checkbox
        driver.findElement(By.name("account.listOption")).click();
        driver.findElement(By.name("account.listOption")).click();
        driver.findElement(By.name("account.listOption")).click();
        driver.findElement(By.name("account.bannerOption")).click();
        takeScreenshot(driver, "register_passed.png");

        //driver.findElement(By.cssSelector("body:nth-child(2) div:nth-child(2) div:nth-child(1) form:nth-child(1) > input:nth-child(7)")).click();
        driver.findElement(By.xpath("//*[@id=\"MenuContent\"]/a[2]")).click();
        // Perform login
        
        performLogin(driver, username, newpassword);
    }

    static void performLogin(WebDriver driver, String username, String password) throws InterruptedException {
        //driver.findElement(By.xpath("//a[contains(text(),'Sign In')]")).click();
    	System.out.println(username);
    	System.out.println(password);
        driver.findElement(By.name("username")).sendKeys(username);
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys(password);
        Thread.sleep(5000);
        driver.findElement(By.xpath("//body/div[@id='Content']/div[@id='Catalog']/form[1]/input[1]")).click();

        if (driver.getCurrentUrl().equals("https://petstore.octoperf.com/actions/Catalog.action")) {
            System.out.println("Login is Successful");
            takeScreenshot(driver, "login_passedy.png");
        } else {
            System.out.println("Login Failed with invalid details or user did not enter any details");
            takeScreenshot(driver, "login_failed.png");
            driver.quit();
        }
    }
            


    /*!Function Name        : verifyReturn13
    '!Purpose             : Verifies that the user can navigate to the Fish category and then return to the main menu.
    '!Input               : WebDriver driver - The WebDriver instance used to automate the browser.
    '!Output              : True/False - Returns true if the navigation to the Fish category and return to the main menu are successful; false otherwise.
    '!Developed By        : RICKY 
    '!Date                : DD/MM/YY*/

    static void verifyReturn(WebDriver driver) throws InterruptedException, IOException {
        try {
            // Precondition: User should be registered and logged in (assuming this is handled elsewhere in the test setup)

            // Step 1: Click the Fish link
            driver.findElement(By.xpath("//*[@id='MainImageContent']/map/area[1]")).click();

            // Step 2: Click the [Return to main menu] link
            driver.findElement(By.xpath("//*[@id='BackLink']/a")).click();
            
            // Expected result: Home page should be displayed
            WebElement homePageElement = driver.findElement(By.xpath("//*[@id=\"MainImageContent\"]/map/area[1]")); // Use an actual identifier for an element on the home page
            if (homePageElement.isDisplayed()) {
                System.out.println("Return to main menu was successful. Home page is displayed.");
            } else {
                System.out.println("Return to main menu failed. Home page is not displayed.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("An error occurred during the test.");
        }
    }


/*!Function Name        : Verifyfishlinkonhome11
'!Purpose             : Verifies that the Fish category link on the homepage is clickable and navigates to the correct Fish category page.
'!Input               : WebDriver driver - The WebDriver instance used to automate the browser.
'!Output              : True/False - Returns true if the Fish category page is displayed correctly; false otherwise.
'!Developed By        : Ricky
'!Date                : 07/06/2024*/
public void VerifyFishLink(String category) {
	
	try {
        // Step 1: Click on the Fish category link
        WebElement fishCategoryLink = driver.findElement(By.xpath("//*[@id=\"QuickLinks\"]/a[1]/img"));
        fishCategoryLink.click();

        // Expected result for step 1
        WebElement categoryName = driver.findElement(By.xpath("//*[@id=\"Catalog\"]/h2"));
        if (categoryName.isDisplayed() && categoryName.getText().equalsIgnoreCase("Fish")) {
            System.out.println("Fish category page is displayed correctly.");
        } else {
            System.out.println("Fish category page is NOT displayed correctly.");
            driver.quit();
        }

        // Step 2: Click on one of the product ID links
        WebElement productLink = driver.findElement(By.xpath("//*[@id=\"Catalog\"]/table/tbody/tr[2]/td[1]/a")); // Assuming the product links have class 'product-id' and tag 'a'
        productLink.click();

        // Expected result for step 2
        WebElement productDetails = driver.findElement(By.xpath("//*[@id=\"Catalog\"]/table/tbody/tr[1]"));
        if (productDetails.isDisplayed()) {
            System.out.println("Product details are displayed correctly.");
        } else {
            System.out.println("Product details are NOT displayed correctly.");
            driver.quit();
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}

public static void clickLinkInTable(WebDriver driver, String productId) {
  // Click on Add to cart
	
    //WebElement addTocart = driver.findElement(By.xpath(""));
   // addTocart.click();

	
    // Find the table row containing the product with the given ID
    WebElement row = driver.findElement(By.xpath("//*[@id=\"Catalog\"]/table/tbody/tr[2]/td[1]/a"));
    
    // Find the link within the row and click it
    WebElement link = row.findElement(By.xpath("//*[@id=\"Catalog\"]/table/tbody/tr[2]/td[1]/a"));
    link.click();
    
    if (driver.getCurrentUrl().equals("https://petstore.octoperf.com/actions/Catalog.action?viewProduct=&productId=FI-SW-01")) {
		 
        System.out.println("User navigated to the shopping cart page");
        takeScreenshot(driver, "shoppingCart_passedy.png");
    	

    } else {
        System.out.println("User not  navigated to the shopping cart page ");
        takeScreenshot(driver, "shoppingCart_failed.png");
        
    }
    
    
    // Optionally, you can add assertions or further actions after clicking the link
}   

/*!Function Name      : verifyProductDetailsPage15
'!Purpose             : Verifies that the fish product details page is displayed with a table, links are clickable, and the add to cart button functions correctly.
'!Input               : WebDriver driver - The WebDriver instance used to automate the browser.
'!Output              : True/False - Returns true if all verifications are successful; false otherwise.
'!Developed By        : Ricky
'!Date                : 10/06/2024*/
@Test
 public static void verifyProductDetailsPage15(WebDriver driver, String itemId) {
	 
	 
	//Step 1 Verify if product details are displayed in a table
	 
     WebElement productTable = driver.findElement(By.xpath("//*[@id=\"Catalog\"]/table/tbody/tr[1]")); 
     
     
     // Adjust the ID as needed
     if (productTable.isDisplayed()) {
         System.out.println("Product details table is displayed correctly.");
     } else {
         System.out.println("Product details table is NOT displayed.");
     }
      
  // Step 2Click on the links to verify if they are clickable
     List<WebElement> productLinks = productTable.findElements(By.tagName("a"));
     boolean allLinksClickable = true;
     for (WebElement link : productLinks) {
         if (!link.isEnabled()) {
             allLinksClickable = false;
             System.out.println("Link is not clickable: " + link.getText());
         }
     }
     if (allLinksClickable) {
         System.out.println("All product links are clickable.");
     }
     
	//My own logic
    WebElement table = driver.findElement(By.xpath("//table"));

    List<WebElement> rows = table.findElements(By.xpath(".//tr"));

    for (WebElement row : rows) {
        List<WebElement> cells = row.findElements(By.xpath(".//td"));
        for (WebElement cell : cells) {
            if (cell.getText().equals(itemId)) {
                WebElement addToCartButton = row.findElement(By.xpath(".//a[text()='Add to Cart']"));
                addToCartButton.click();
                
                
                
                // adding another item to the cart so atleast the can be two
                WebElement Cat = driver.findElement(By.xpath("//*[@id=\"QuickLinks\"]/a[4]/img"));
                Cat.click();
                WebElement CatLink = driver.findElement(By.xpath("//*[@id=\"Catalog\"]/table/tbody/tr[2]/td[1]/a"));
                CatLink.click();
                WebElement addToCartButtonCat = driver.findElement(By.xpath("//*[@id=\"Catalog\"]/table/tbody/tr[2]/td[5]/a"));
                addToCartButtonCat.click();
                
             //Step 3 Verify the Shopping cart page is displayed
                WebElement shoppingCartPage = driver.findElement(By.xpath("//*[@id=\"Cart\"]/h2")); // Adjust the ID as needed
                if (shoppingCartPage.isDisplayed()) {
                    System.out.println("Shopping cart page is displayed correctly.");
                } else {
                    System.out.println("Shopping cart page is NOT displayed.");
                }

                
                return; // Exit after clicking the button
            }
        }
    }
}
   

 static Map<String, String> readUserDetailsFromFile(String filePath) {
     Map<String, String> userDetails = new HashMap<>();
     try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
         String line;
         while ((line = br.readLine()) != null) {
             String[] parts = line.split("=");
             if (parts.length >= 2) {
                 String key = parts[0];
                 String value = parts[1];
                 userDetails.put(key, value);
             }
         }
     } catch (IOException e) {
         e.printStackTrace();
     }
     return userDetails;
 }
 public static void updateQuantity(WebDriver driver) {
     try {
         // Read user details from the file
         Map<String, String> userDetails = readUserDetailsFromFile("src/UpdateQuantity");

         // Find all rows in the cart table
         List<WebElement> rows = driver.findElements(By.xpath("//*[@id=\"Cart\"]/form/table/tbody/tr"));

         for (int i = 2; i <= rows.size(); i++) { // Adjusted to start from 2 to match the row numbers in XPath
             try {
                 // Find the input element for the quantity in each row using relative XPath
                 WebElement quantityInput = driver.findElement(By.xpath("//*[@id=\"Cart\"]/form/table/tbody/tr[" + i + "]/td[5]/input"));
                 quantityInput.clear();
                 quantityInput.sendKeys(userDetails.get("QuantityField" + (i - 1))); // Assuming the file has quantities like "quantity1", "quantity2", etc.
             } catch (NoSuchElementException e) {
                 System.out.println("Skipping row " + i + " as it does not contain a quantity input field.");
                 continue;
             }
         }

         // Click the "Update Cart" button
         WebElement updateCartButton = driver.findElement(By.name("updateCartQuantities"));
         updateCartButton.click();

         // Wait for the update to process
         WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
         wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"Cart\"]/form/table/tbody/tr")));

         // Calculate the total cost
         double totalCost = 0.0;

         for (int i = 2; i <= rows.size(); i++) {
             try {
                 WebElement priceElement = driver.findElement(By.xpath("//*[@id=\"Cart\"]/form/table/tbody/tr[" + i + "]/td[6]"));
                 WebElement quantityElement = driver.findElement(By.xpath("//*[@id=\"Cart\"]/form/table/tbody/tr[" + i + "]/td[5]/input"));

                 double price = Double.parseDouble(priceElement.getText().replace("$", ""));
                 int quantity = Integer.parseInt(quantityElement.getAttribute("value"));
                 totalCost += price * quantity;
             } catch (NoSuchElementException e) {
                 System.out.println("Skipping row " + i + " as it does not contain necessary elements for price or quantity.");
                 continue;
             }
         }

         System.out.println("Total cost after updating quantities: $" + totalCost);
         takeScreenshot(driver, "TotalCost_passedy.png");
         

     } catch (Exception e) {
         System.err.println("An error occurred while updating the quantities: " + e.getMessage());
         e.printStackTrace();
     }
 }
 
 
 public static void proceedToCheckout(WebDriver driver) {
	  
	 //WebDriverWait wait = new WebDriverWait(driver, Duration. ofSeconds (10));
	  WebElement proceedToCheckoutButton = driver.findElement(By.xpath("//*[@id=\"Cart\"]/a"));
      proceedToCheckoutButton.click();
	    
	    if (driver.getCurrentUrl().equals("https://petstore.octoperf.com/actions/Order.action?newOrderForm=")) {
			 
	        System.out.println("User is able to navigate to payment details page");
	        takeScreenshot(driver, "payment_passedy.png");
	    	

	    } else {
	        System.out.println("User not  navigated to the shopping cart page ");
	        takeScreenshot(driver, "payment_failed.png");
	        
	    }
	    
	    
	    // Optionally, you can add assertions or further actions after clicking the link
	}
 
 
 public static void verifyCheckoutPage(WebDriver driver) {
	
     // Verify that the "Billing Information" section is visible
     WebElement billingInfo = driver.findElement(By.xpath("//*[@id=\"Catalog\"]/form/table/tbody/tr[5]/th"));
     if (billingInfo.isDisplayed()) {
         System.out.println("Billing Information section is visible.");
         takeScreenshot(driver, "Billing_passedy.png");
     } else {
         System.out.println("Billing Information section is not visible.");
         takeScreenshot(driver, "Billing_failed.png");
     }

    

     // Verify that the "Payment Information" section is visible
     WebElement paymentInfo = driver.findElement(By.xpath("//*[@id=\"Catalog\"]/form/table/tbody/tr[1]/th"));
     if (paymentInfo.isDisplayed()) {
         System.out.println("Payment Information section is visible.");
         takeScreenshot(driver, "payment_passedy.png");
     } else {
         System.out.println("Payment Information section is not visible.");
         takeScreenshot(driver, "payment_failed.png");
     }

    
 }

 
 public static void VerifyContinue(WebDriver driver) {
	  
	
	  WebElement proceedToCheckoutButton = driver.findElement(By.xpath("//*[@id=\"Catalog\"]/form/input"));
     proceedToCheckoutButton.click();
	    
     // Verify the confirmation message
     WebElement confirmationMessage = driver.findElement(By.xpath("//*[@id=\"Catalog\"]"));
     if (confirmationMessage.isDisplayed()) {
         System.out.println("Please confirm the information below and then press continue... is visible.");
         takeScreenshot(driver, "confirmatin_passedy.png");
     } else {
         System.out.println("Please confirm the information below and then press continue...is not visible.");
         takeScreenshot(driver, "confirmatin_failed.png");
     }

     // Verify the order date
     WebElement orderDate = driver.findElement(By.xpath("//*[@id=\"Catalog\"]/table/tbody/tr[1]/th/font[2]/b"));
     System.out.println("Order date: " + orderDate.getText());

     WebElement continueButton = driver.findElement(By.xpath("//*[@id=\"Catalog\"]/a"));
     continueButton.click();
	}   
 
 
 public static void verifyAndCalculateTotalCost(WebDriver driver) {
	 WebDriverWait wait = new WebDriverWait(driver, Duration. ofSeconds (10));
     // Locate the table
     WebElement table = driver.findElement(By.xpath("//*[@id=\"Catalog\"]/table/tbody/tr[26]/td/table"));

     // Get all rows in the table except the header row
     List<WebElement> rows = table.findElements(By.xpath(".//tr[position() > 1]"));
     double calculatedTotalCost = 0.0;

     // Iterate through each row and calculate the total cost
     for (WebElement row : rows) {
         // Get all cells in the row
         List<WebElement> cells = row.findElements(By.xpath(".//td"));

         if (cells.size() > 0) {
             // Assuming Total Cost is the last column
             String totalCostStr = cells.get(cells.size() - 1).getText().replace("$", "");
             try {
                 calculatedTotalCost += Double.parseDouble(totalCostStr);
             } catch (NumberFormatException e) {
                 System.out.println("Error parsing total cost: " + totalCostStr);
             }
         } else {
             System.out.println("" + row.getText());
         }
     }

     System.out.println("Calculated Total Cost: $" + calculatedTotalCost);
     takeScreenshot(driver, "TotalCost_passedy.png");

     // Verify the displayed total cost
     try {
         WebElement displayedTotalCostElement = driver.findElement(By.xpath("//*[@id=\"Catalog\"]/table/tbody/tr[26]/td/table/tbody/tr[3]/th"));
         double displayedTotalCost = Double.parseDouble(displayedTotalCostElement.getText().replace("$", ""));
         if (calculatedTotalCost == displayedTotalCost) {
             System.out.println("Total Cost is correct.");
             takeScreenshot(driver, "TotalCost_failed.png");
         } else {
             System.out.println("Total Cost is incorrect. Expected: $" + displayedTotalCost + ", but calculated: $" + calculatedTotalCost);
         }
     } catch (Exception e) {
         System.out.println("" );
     }
 }
 /**
  * Function Name: VerifyConfirmationpagedetails
  * Purpose      : Verifies the confirmation page details, including the thank you message and the order number.
  * Input        : WebDriver driver - The WebDriver instance used to automate the browser.
  * Output       : Outputs messages to the console and takes screenshots based on verification results.
  * Developed By : Ricky
  * Date         : 10/06/2024
  */
 public static void VerifyConfirmationpagedetails(WebDriver driver) {
	    // Create an instance of WebDriverWait with a 10-second timeout
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	    // Verify "Thank you, your order has been submitted." text
	    WebElement thankYouMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"Content\"]/ul/li")));
	    String thankYouText = thankYouMessage.getText();
	    if (thankYouText.equals("Thank you, your order has been submitted.")) {
	        System.out.println("Thank you, your order has been submitted..");
	        takeScreenshot(driver, "Message_passedy.png");
	    } else {
	        System.out.println("Thank you message is incorrect.");
	        takeScreenshot(driver, "Message_failed.png");
	    }

	    // Verify order number pattern "Order # followed by digits"
	    WebElement orderNumberElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"Catalog\"]/table/tbody/tr[1]/th")));
	    String orderNumberText = orderNumberElement.getText();

	    // Extract the first 5 digits from the order number
	    String extractedOrderNumber = extractOrderNumber(orderNumberText);
	    if (extractedOrderNumber != null) {
	        System.out.println("Extracted order number: " + extractedOrderNumber);
	        takeScreenshot(driver, "Order_passed.png");
	    } else {
	        System.out.println("No valid order number found.");
	        takeScreenshot(driver, "Order_failed.png");
	    }
	}

	// Helper method to extract the first 5 digits from the order number
	private static String extractOrderNumber(String orderText) {
	    Pattern pattern = Pattern.compile("Order #\\d+");
	    Matcher matcher = pattern.matcher(orderText);
	    if (matcher.find()) {
	        String orderDigits = matcher.group().replaceAll("\\D", ""); // Remove non-digit characters
	        return orderDigits.substring(0, Math.min(orderDigits.length(), 5));
	    }
	    return null;
	}

	 
public static void takeScreenshot(WebDriver driver, String fileName) {
	
    	try {
            // Capture screenshot method if it passsed
            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            // Load the captured screenshot
            BufferedImage image = ImageIO.read(screenshotFile);
            // Write text on the screenshot
            Graphics2D graphics = image.createGraphics();
            graphics.setColor(Color.RED);
            graphics.setFont(new Font("Arial", Font.PLAIN, 40));
            graphics.drawString("user successfully clicked the button and navigated to next page", 150, 120); 

            // Save the modified screenshot
            File outputFile = new File("C:/Users/MulaloRickyMulaudzi-/OneDrive - Linkfields innovations/Pictures/Screenshots" + fileName);
            ImageIO.write(image, "png", outputFile);
        // write the text on the screenshot
            System.out.println("Screenshot captured written a  text and saved as: " + fileName);
        } catch (IOException e) {
            System.out.println("Failed to capture screenshot with text: " + e.getMessage());
        }
    	
    


}
}
        
        
	


