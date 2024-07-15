package Pett;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Verify_Register_Now_valid_data {
	
	public static String username;
    public static String repeatedpassword; 
    public static String Firstname;
    public static String Lastname;
    public static String address1;
    public static String address2;
    public static String city;
    public static String State;
    public static String Zip;
    public static String country;
    
    /*Script Title 
    
    Script Name      	: Verify_Register_Now_valid_data
    'Purpose    		: This TC verify the Register PAGE 
    'Developed by  		: ARENAHO 
    'Developed Date 	: 12/06/2024
    'TestDataSheet 		: updatetxt
    'Test LInk TestCase Path  : jetsore ---------landing Page --- OB-14 : Verify Login with valid data 
   */
    
    /*!Function Name	: Verify_Register_Now_valid_data
    '!Purpose			: This TC is verify the REGISTER  with valid data  
    '!Input 			: WebDriver driver - The WebDriver instance used to automate the browser.	
    '!Output 	  		: returns JpetStore page display register fill 	 
    '!Developed By	 	: ARENAHO 
    '!Date	 		  	: 12/06/2024
    */  
    @Test
    public void TC14 () throws IOException, InterruptedException {
    	WebDriver driver = new ChromeDriver();
    	driver.manage().window().maximize();
    	// pre requiste for the test case 
		 ClickSignin signin=new  ClickSignin();
		 Verify_the_URL_OB_06 url=new  Verify_the_URL_OB_06();
		 //url.navigateToSite(driver);
		 signin.clickSignIn(driver);
		 Verify_Register_Now_valid_data.verify_Register_Now_valid_data(driver);
		 driver.quit();
    }
	public static  void verify_Register_Now_valid_data(WebDriver driver) throws IOException, InterruptedException {
			
        // click register on   sign on page 
		  try {
	            driver.findElement(By.xpath("//*[@id=\"Catalog\"]/a")).click();
	        } catch (Exception e) {
	            System.out.println("An error occurred while trying to click on the element: " + e.getMessage());
	            e.printStackTrace();
	        }
	    
	
        Thread.sleep(1500);
        // verify if the register page opens with account information , profile information 
        if (driver.findElement(By.xpath("//*[@id=\"Catalog\"]/form/table[1]")).isDisplayed() 
        		) {
            System.out.println(" the register page open with User Information, Account Information and Profile Information");
            ScreenshotP.takeScreenshot(driver, "verify_Register_Now_valid_dataPASS1.png");
            
            
        } else {
            System.out.println("1. the register page did not open with User Information, Account Information and Profile Information .");
            // takeScreenshotF(driver, "navigateRegisterpage_failed.png");
            ScreenshotP.takeScreenshot(driver, "verify_Register_Now_valid_dataPASS2.png");

        }
       
            // Verify if the check box is checked 

        try {
            WebElement languagePreferenceCheckbox = driver.findElement(By.name("account.languagePreference"));
            if (languagePreferenceCheckbox.isDisplayed()) {
                System.out.println("Language Preference field is displayed.");
                if (!languagePreferenceCheckbox.isSelected()) {
                    System.out.println("Language Preference field is not checked.");
                } else {
                    System.out.println("Language Preference field is checked.");
                }
            } else {
                System.out.println("Language Preference field is not displayed.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred while checking the Language Preference field: " + e.getMessage());
            e.printStackTrace();
        }

        try {
            // Verify if the button is displayed and clickable
            WebElement button = driver.findElement(By.xpath("//*[@id=\"Catalog\"]/form/input"));
            if (button.isDisplayed()) {
                System.out.println("Button is displayed.");
                if (button.isEnabled()) {
                    System.out.println("Button is clickable.");
                } else {
                    System.out.println("Button is not clickable.");
                }
            } else {
                System.out.println("Button is not displayed and not clickable.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred while checking the button: " + e.getMessage());
            e.printStackTrace();
        }

            String filePath = ("C:/Users/MulaloRickyMulaudzi-/Eclipse_New/JPetStore_Scripts/src/update");
            
            String newpassword = "";
            String email = "";
            String phone = "";
            String animal="";
            String language="";
            
            // create random numbers to add to the user to make the username RANDOM  everytime 
            
            Random randomNum = new Random();
            // Take the number to the maximum 91789
            int namee = randomNum. nextInt(91789);
            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            	// Read details  from the notepad 
            	username = br.readLine() +namee;
            	repeatedpassword = br.readLine();
                newpassword= br.readLine();
                Firstname= br.readLine();
                Lastname= br.readLine();
                email= br.readLine();
                phone= br.readLine();
                address1= br.readLine();
                address2= br.readLine();
                city= br.readLine();
                State= br.readLine();
                Zip= br.readLine();
                country= br.readLine();
                language=br.readLine();
                animal=br.readLine();
                
            } catch (IOException e) {
                System.err.println("Error reading the credentials file: " + e.getMessage());
            }
            // enter details to the text field reading from the notepad and verify if it is  accepted and displayed
            driver.findElement(By.name("username")).sendKeys(username);
            
        // verify if the entered detailed are accepted on the textfield , displayed correctly 
         // Check if the username field is displayed and if the entered username matches the expected value
         // Check if the username field is displayed and if the entered username matches the expected value
            if (driver.findElement(By.name("username")).isDisplayed()) {
                System.out.println("User ID field is displayed.");
                ScreenshotP.takeScreenshot(driver, "verify_Register_Now_valid_dataPASS1.png");
            } else {
                System.out.println("User ID field is not displayed.");
                ScreenshotP.takeScreenshotF(driver, "verify_Register_Now_valid_dataFAIL1.png");
            }

            if (repeatedpassword.equalsIgnoreCase(username)) {
                System.out.println("Entered username matches the expected value.");
                ScreenshotP.takeScreenshot(driver, "verify_Register_Now_valid_dataPASS2.png");
            } else {
                System.out.println("Entered username does not match the expected value.");
                ScreenshotP.takeScreenshotF(driver, "verify_Register_Now_valid_dataFAIL2.png");
            }

            // Verify if the password field is displayed and the entered password matches the expected value
            driver.findElement(By.name("password")).sendKeys(newpassword);
            String enteredNewPassword = driver.findElement(By.name("password")).getAttribute("value");

            if (driver.findElement(By.name("password")).isDisplayed()) {
                System.out.println("Password field is displayed.");
                ScreenshotP.takeScreenshot(driver, "verify_Register_Now_valid_dataPASS3.png");
            } else {
                System.out.println("Password field is not displayed.");
                ScreenshotP.takeScreenshotF(driver, "verify_Register_Now_valid_dataFAIL3.png");
            }

            if (enteredNewPassword.equalsIgnoreCase(newpassword)) {
                System.out.println("Entered new password matches the expected value.");
                ScreenshotP.takeScreenshot(driver, "verify_Register_Now_valid_dataPASS4.png");
            } else {
                System.out.println("Entered new password does not match the expected value.");
                ScreenshotP.takeScreenshotF(driver, "verify_Register_Now_valid_dataFAIL4.png");
            }

            // Enter the repeated password and verify
            driver.findElement(By.name("repeatedPassword")).sendKeys(repeatedpassword);
            String enteredRepeatedPassword = driver.findElement(By.name("repeatedPassword")).getAttribute("value");

            if (driver.findElement(By.name("repeatedPassword")).isDisplayed()) {
                System.out.println("Repeated Password field is displayed.");
                ScreenshotP.takeScreenshot(driver, "verify_Register_Now_valid_dataPASS5.png");
            } else {
                System.out.println("Repeated Password field is not displayed.");
                ScreenshotP.takeScreenshotF(driver, "verify_Register_Now_valid_dataFAIL5.png");
            }

            if (repeatedpassword.equals(enteredRepeatedPassword)) {
                System.out.println("Entered repeated password matches the expected value.");
                ScreenshotP.takeScreenshot(driver, "verify_Register_Now_valid_dataPASS6.png");
            } else {
                System.out.println("Entered repeated password does not match the expected value.");
                ScreenshotP.takeScreenshotF(driver, "verify_Register_Now_valid_dataFAIL6.png");
            }

            // Enter the first name and verify
            driver.findElement(By.name("account.firstName")).sendKeys(Firstname);
            String enteredFirstName = driver.findElement(By.name("account.firstName")).getAttribute("value");

            if (driver.findElement(By.name("account.firstName")).isDisplayed()) {
                System.out.println("First Name field is displayed.");
                ScreenshotP.takeScreenshot(driver, "verify_Register_Now_valid_dataPASS7.png");
            } else {
                System.out.println("First Name field is not displayed.");
                ScreenshotP.takeScreenshotF(driver, "verify_Register_Now_valid_dataFAIL7.png");
            }

            if (Firstname.equals(enteredFirstName)) {
                System.out.println("Entered first name matches the expected value.");
                ScreenshotP.takeScreenshot(driver, "verify_Register_Now_valid_dataPASS8.png");
            } else {
                System.out.println("Entered first name does not match the expected value.");
                ScreenshotP.takeScreenshotF(driver, "verify_Register_Now_valid_dataFAIL8.png");
            }

            // Enter the last name and verify
            driver.findElement(By.name("account.lastName")).sendKeys(Lastname);
            String enteredLastName = driver.findElement(By.name("account.lastName")).getAttribute("value");

            if (driver.findElement(By.name("account.lastName")).isDisplayed()) {
                System.out.println("Last Name field is displayed.");
                ScreenshotP.takeScreenshot(driver, "verify_Register_Now_valid_dataPASS9.png");
            } else {
                System.out.println("Last Name field is not displayed.");
                ScreenshotP.takeScreenshotF(driver, "verify_Register_Now_valid_dataFAIL9.png");
            }

            if (Lastname.equals(enteredLastName)) {
                System.out.println("Entered last name matches the expected value.");
                ScreenshotP.takeScreenshot(driver, "verify_Register_Now_valid_dataPASS10.png");
            } else {
                System.out.println("Entered last name does not match the expected value.");
                ScreenshotP.takeScreenshotF(driver, "verify_Register_Now_valid_dataFAIL10.png");
            }

            // Enter the email and verify
            driver.findElement(By.name("account.email")).sendKeys(email);
            String enteredEmail = driver.findElement(By.name("account.email")).getAttribute("value");

            if (driver.findElement(By.name("account.email")).isDisplayed()) {
                System.out.println("Email field is displayed.");
                ScreenshotP.takeScreenshot(driver, "verify_Register_Now_valid_dataPASS11.png");
            } else {
                System.out.println("Email field is not displayed.");
                ScreenshotP.takeScreenshotF(driver, "verify_Register_Now_valid_dataFAIL11.png");
            }

            if (email.equals(enteredEmail)) {
                System.out.println("Entered email matches the expected value.");
                ScreenshotP.takeScreenshot(driver, "verify_Register_Now_valid_dataPASS12.png");
            } else {
                System.out.println("Entered email does not match the expected value.");
                ScreenshotP.takeScreenshotF(driver, "verify_Register_Now_valid_dataFAIL12.png");
            }

            // Enter the phone number and verify
            driver.findElement(By.name("account.phone")).sendKeys(phone);
            String enteredPhone = driver.findElement(By.name("account.phone")).getAttribute("value");

            if (driver.findElement(By.name("account.phone")).isDisplayed()) {
                System.out.println("Phone field is displayed.");
                ScreenshotP.takeScreenshot(driver, "verify_Register_Now_valid_dataPASS13.png");
            } else {
                System.out.println("Phone field is not displayed.");
                ScreenshotP.takeScreenshotF(driver, "verify_Register_Now_valid_dataFAIL13.png");
            }

            if (phone.equals(enteredPhone)) {
                System.out.println("Entered phone matches the expected value.");
                ScreenshotP.takeScreenshot(driver, "verify_Register_Now_valid_dataPASS14.png");
            } else {
                System.out.println("Entered phone does not match the expected value.");
                ScreenshotP.takeScreenshotF(driver, "verify_Register_Now_valid_dataFAIL14.png");
            }

            // Enter the first address and verify
            driver.findElement(By.name("account.address1")).sendKeys(address1);
            String enteredAddress1 = driver.findElement(By.name("account.address1")).getAttribute("value");

            if (driver.findElement(By.name("account.address1")).isDisplayed()) {
                System.out.println("Address1 field is displayed.");
                ScreenshotP.takeScreenshot(driver, "verify_Register_Now_valid_dataPASS15.png");
            } else {
                System.out.println("Address1 field is not displayed.");
                ScreenshotP.takeScreenshotF(driver, "verify_Register_Now_valid_dataFAIL15.png");
            }

            if (address1.equals(enteredAddress1)) {
                System.out.println("Entered Address1 matches the expected value.");
                ScreenshotP.takeScreenshot(driver, "verify_Register_Now_valid_dataPASS16.png");
            } else {
                System.out.println("Entered Address1 does not match the expected value.");
                ScreenshotP.takeScreenshotF(driver, "verify_Register_Now_valid_dataFAIL16.png");
            }

            // Enter the second address and verify
            driver.findElement(By.name("account.address2")).sendKeys(address2);
            String enteredAddress2 = driver.findElement(By.name("account.address2")).getAttribute("value");

            if (driver.findElement(By.name("account.address2")).isDisplayed()) {
                System.out.println("Address2 field is displayed.");
                ScreenshotP.takeScreenshot(driver, "verify_Register_Now_valid_dataPASS17.png");
            } else {
                System.out.println("Address2 field is not displayed.");
                ScreenshotP.takeScreenshotF(driver, "verify_Register_Now_valid_dataFAIL17.png");
            }

            if (address2.equals(enteredAddress2)) {
                System.out.println("Entered Address2 matches the expected value.");
                ScreenshotP.takeScreenshot(driver, "verify_Register_Now_valid_dataPASS18.png");
            } else {
                System.out.println("Entered Address2 does not match the expected value.");
                ScreenshotP.takeScreenshotF(driver, "verify_Register_Now_valid_dataFAIL18.png");
            } 
            
         // Enter and verify the city
            driver.findElement(By.name("account.city")).sendKeys(city);
            String enteredCity = driver.findElement(By.name("account.city")).getAttribute("value");

            if (driver.findElement(By.name("account.city")).isDisplayed()) {
                System.out.println("City field is displayed.");
                ScreenshotP.takeScreenshot(driver, "verify_Register_Now_valid_dataPASS19.png");
            } else {
                System.out.println("City field is not displayed.");
                ScreenshotP.takeScreenshotF(driver, "verify_Register_Now_valid_dataFAIL19.png");
            }

            if (city.equals(enteredCity)) {
                System.out.println("Entered city matches the expected value.");
                ScreenshotP.takeScreenshot(driver, "verify_Register_Now_valid_dataPASS20.png");
            } else {
                System.out.println("Entered city does not match the expected value.");
                ScreenshotP.takeScreenshotF(driver, "verify_Register_Now_valid_dataFAIL20.png");
            }

            // Enter and verify the state
            driver.findElement(By.name("account.state")).sendKeys(State);
            String enteredState = driver.findElement(By.name("account.state")).getAttribute("value");

            if (driver.findElement(By.name("account.state")).isDisplayed()) {
                System.out.println("State field is displayed.");
                ScreenshotP.takeScreenshot(driver, "verify_Register_Now_valid_dataPASS21.png");
            } else {
                System.out.println("State field is not displayed.");
                ScreenshotP.takeScreenshotF(driver, "verify_Register_Now_valid_dataFAIL21.png");
            }

            if (State.equals(enteredState)) {
                System.out.println("Entered state matches the expected value.");
                ScreenshotP.takeScreenshot(driver, "verify_Register_Now_valid_dataPASS22.png");
            } else {
                System.out.println("Entered state does not match the expected value.");
                ScreenshotP.takeScreenshotF(driver, "verify_Register_Now_valid_dataFAIL22.png");
            }

            // Enter and verify the zip code
            driver.findElement(By.name("account.zip")).sendKeys(Zip);
            String enteredZip = driver.findElement(By.name("account.zip")).getAttribute("value");

            if (driver.findElement(By.name("account.zip")).isDisplayed()) {
                System.out.println("Zip field is displayed.");
                ScreenshotP.takeScreenshot(driver, "verify_Register_Now_valid_dataPASS23.png");
            } else {
                System.out.println("Zip field is not displayed.");
                ScreenshotP.takeScreenshotF(driver, "verify_Register_Now_valid_dataFAIL23.png");
            }

            if (Zip.equals(enteredZip)) {
                System.out.println("Entered zip matches the expected value.");
                ScreenshotP.takeScreenshot(driver, "verify_Register_Now_valid_dataPASS24.png");
            } else {
                System.out.println("Entered zip does not match the expected value.");
                ScreenshotP.takeScreenshotF(driver, "verify_Register_Now_valid_dataFAIL24.png");
            }

            // Enter and verify the country
            driver.findElement(By.name("account.country")).sendKeys(country);
            String enteredCountry = driver.findElement(By.name("account.country")).getAttribute("value");

            if (driver.findElement(By.name("account.country")).isDisplayed()) {
                System.out.println("Country field is displayed.");
                ScreenshotP.takeScreenshot(driver, "verify_Register_Now_valid_dataPASS25.png");
            } else {
                System.out.println("Country field is not displayed.");
                ScreenshotP.takeScreenshotF(driver, "verify_Register_Now_valid_dataFAIL25.png");
            }

            if (country.equals(enteredCountry)) {
                System.out.println("Entered country matches the expected value.");
                ScreenshotP.takeScreenshot(driver, "verify_Register_Now_valid_dataPASS26.png");
            } else {
                System.out.println("Entered country does not match the expected value.");
                ScreenshotP.takeScreenshotF(driver, "verify_Register_Now_valid_dataFAIL26.png");
            }

    
            

            WebElement firstDropdown = driver.findElement(By.xpath("//*[@id='Catalog']/form/table[3]/tbody/tr[1]/td[2]/select"));
    		   
            // handling the drop downs 
            Select selectFirstDropdown = new Select(firstDropdown);
            selectFirstDropdown.selectByVisibleText(language);
            WebElement selectedOptionn = selectFirstDropdown.getFirstSelectedOption();

            // Verify if "English" is selected
            if (selectedOptionn.getText().equals(language)) {
                System.out.println("English is selected.");
                ScreenshotP.takeScreenshot(driver, "verify_Register_Now_valid_dataPASS26.png");

            } else {
                System.out.println("English is not selected.");
                ScreenshotP.takeScreenshotF(driver, "verify_Register_Now_valid_dataPASS26.png");

            }
            
         // Locate the second dropdown element using its XPath
            WebElement secondDropdown = driver.findElement(By.xpath("//*[@id=\"Catalog\"]/form/table[3]/tbody/tr[2]/td[2]/select"));

            // Create a Select object for interacting with the second dropdown
            Select selectsecondDropdown = new Select(secondDropdown);

            // Select the option "BIRDS" by visible text from the second dropdown
            selectsecondDropdown.selectByVisibleText(animal);

            // Get the currently selected option in the second dropdown
            WebElement selectedOption = selectsecondDropdown.getFirstSelectedOption();

            if (selectedOption.getText().equals(animal)) {
                System.out.println("Dropdown option 'BIRDS' is selected.");
                ScreenshotP.takeScreenshot(driver, "verify_Register_Now_valid_dataPASS26.png");

            } else {
                System.out.println("Dropdown option 'BIRDS' is not selected.");
                ScreenshotP.takeScreenshotF(driver, "verify_Register_Now_valid_dataPASS26.png");

            }
            // click checkboxs
            // Locate and click the first checkbox multiple times
            WebElement listOptionCheckbox = driver.findElement(By.name("account.listOption"));
            listOptionCheckbox.click();
            listOptionCheckbox.click();
            listOptionCheckbox.click();

            // Verify if the first checkbox is selected
            if (listOptionCheckbox.isSelected()) {
                System.out.println("List Option checkbox is selected.");
                ScreenshotP.takeScreenshot(driver, "verify_Register_Now_valid_dataPASS26.png");

            } else {
                System.out.println("List Option checkbox is not selected.");
                ScreenshotP.takeScreenshotF(driver, "verify_Register_Now_valid_dataPASS26.png");

            }

            // Locate and click the second checkbox
            WebElement bannerOptionCheckbox = driver.findElement(By.name("account.bannerOption"));
            
            bannerOptionCheckbox.click();

            // Verify if the second checkbox is selected
            if (bannerOptionCheckbox.isSelected()) {
                System.out.println("Banner Option checkbox is selected.");
                ScreenshotP.takeScreenshot(driver, "verify_Register_Now_valid_dataPASS26.png");

            } else {
                System.out.println("Banner Option checkbox is not selected.");
                ScreenshotP.takeScreenshotF(driver, "verify_Register_Now_valid_dataPASS26.png");

            }
            ScreenshotP.takeScreenshot(driver, "verify_Register_Now_valid_dataPASS.png");

            Thread.sleep(1500);
            // click on save account information 
            driver.findElement(By.name("newAccount")).click();
            if (driver.findElement(By.xpath("//*[@id=\"LogoContent\"]/a/img")).isDisplayed()&&driver.findElement(By.xpath("//*[@id=\"Sidebar\"]")).isDisplayed()&&driver.findElement(By.xpath("//*[@id=\"Header\"]")).isDisplayed() ) {
	            System.out.println("JpetStore page display with the pet images, links named(Fish,Dogs,Cats,Reptiles and Birds) and a Sign in Link at the top of the website.");
	            ScreenshotP.takeScreenshot(driver, "verify_Register_Now_valid_dataPASSS.png");
	        } else {
	            System.out.println("JpetStore page dont display with the pet images, links named(Fish,Dogs,Cats,Reptiles and Birds) and a Sign in Link at the top of the website.");
	            ScreenshotP.takeScreenshotF(driver, "verify_Register_Now_valid_dataFAIL.png");
	        }
        }
        
        public static void main(String[] args) throws IOException, InterruptedException {
        	
        	WebDriver driver = new ChromeDriver();
        	driver.manage().window().maximize();
        	// pre requiste for the test case 
			 ClickSignin signin=new  ClickSignin();
			 Verify_the_URL_OB_06 url=new  Verify_the_URL_OB_06();
			 url.navigateToSite(driver);
			 signin.clickSignIn(driver);
			 Verify_Register_Now_valid_data.verify_Register_Now_valid_data(driver);
			 driver.quit();
		}

		
		
		
		
	}

