package Scripts;

import java.io.IOException;
import java.time.Duration;
import java.util.Map;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Verify_Register_Now_Link_with_valid_data {

	public static void main(String[] args) throws IOException, InterruptedException{
		// Initialize WebDriver
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        Verify_the_URL.navigateToSite(driver);
        JpetStore_Automation.SignInLink(driver);
        registerNewUser(driver);
        
        driver.quit();
	}
	@Test
	public void TC0 () throws IOException, InterruptedException {
		// Initialize WebDriver
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        Verify_the_URL.navigateToSite(driver);
        JpetStore_Automation.SignInLink(driver);
        registerNewUser(driver);
        
        driver.quit();
	}
	
	public static void registerNewUser(WebDriver driver) throws InterruptedException, IOException{
		//Read_Data rd =new Read_Data();
       Map<String, String> userDetails =Read_Data. readUserDetailsFromFile("src/RegDetailss");

       // Navigate to the registration page
       driver.findElement(By.xpath("//*[@id=\"Catalog\"]/a")).click();
       //takeScreenshot("regPage");
       Random randomNum = new Random();
       int namee = randomNum. nextInt(900);
       // Fill in User Information
       driver.findElement(By.name("username")).sendKeys(userDetails.get("username")+namee);
       
       driver.findElement(By.name("password")).sendKeys(userDetails.get("password"));
       driver.findElement(By.name("repeatedPassword")).sendKeys(userDetails.get("repeatedPassword"));

    
       
       // Fill in Account Information
       driver.findElement(By.name("account.firstName")).sendKeys(userDetails.get("firstName"));
       driver.findElement(By.name("account.lastName")).sendKeys(userDetails.get("lastName"));
       driver.findElement(By.name("account.email")).sendKeys(userDetails.get("email"));
       driver.findElement(By.name("account.phone")).sendKeys(userDetails.get("phone"));
       driver.findElement(By.name("account.address1")).sendKeys(userDetails.get("address1"));
       driver.findElement(By.name("account.address2")).sendKeys(userDetails.get("address2"));
       driver.findElement(By.name("account.city")).sendKeys(userDetails.get("city"));
       driver.findElement(By.name("account.state")).sendKeys(userDetails.get("state"));
       driver.findElement(By.name("account.zip")).sendKeys(userDetails.get("zip"));
       driver.findElement(By.name("account.country")).sendKeys(userDetails.get("country"));

       // Fill in Profile Information
       driver.findElement(By.name("account.languagePreference")).sendKeys(userDetails.get("languagePreference"));
       driver.findElement(By.name("account.favouriteCategoryId")).sendKeys(userDetails.get("favouriteCategoryId"));

       // Check the boxes for MyList and MyBanner
       WebElement myListCheckbox = driver.findElement(By.name("account.listOption"));
       if (!myListCheckbox.isSelected()) {
           myListCheckbox.click();
       }
       WebElement myBannerCheckbox = driver.findElement(By.name("account.bannerOption"));
       if (!myBannerCheckbox.isSelected()) {
           myBannerCheckbox.click();
       }

       // Submit the form
       driver.findElement(By.xpath("//*[@id=\"Catalog\"]/form/input")).click();

       // Verify registration success - assume a success message element exists
       WebDriverWait wait = new WebDriverWait(driver,Duration. ofSeconds (20));
       try {
           WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Sign In")));
           System.out.println("Registration successful!");
           //takeScreenshot("Registration");
       } catch (Exception e) {
           //WebElement errorMessage = driver.findElement(By.xpath("//div[@class='errorMessage']"));
           System.out.println("Registration failed: " );
           driver.quit();
       }
   }
}
