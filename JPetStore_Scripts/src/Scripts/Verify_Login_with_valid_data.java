package Scripts;

import java.io.IOException;
import java.time.Duration;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Verify_Login_with_valid_data {

	public static void main(String[] args) throws IOException, InterruptedException {
		// Initialize WebDriver
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        Verify_the_URL.navigateToSite(driver);
        JpetStore_Automation.SignInLink(driver);
        Verify_Register_Now_Link_with_valid_data.registerNewUser(driver);
        
        signIn(driver);
		driver.quit();

	}
	
	
	
	public static void signIn(WebDriver driver) {
        // Click on the Sign In link
        driver.findElement(By.xpath("//*[@id=\"MenuContent\"]/a[2]")).click();
        //Read_Data rd =new Read_Data();
        // Read user details from the file
        Map<String, String> userDetails = Read_Data.readUserDetailsFromFile("src/Login");
        
        // Fill in the sign-in form
        driver.findElement(By.name("username")).sendKeys(userDetails.get("username"));
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys(userDetails.get("password"));
        
        // Submit the form
        driver.findElement(By.name("signon")).click();

        // Verify sign-in success
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            WebElement welcomeMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("WelcomeContent")));
            if (welcomeMessage.getText().contains("Welcome")) {
                System.out.println("Login successful: " );
               
            } else {
                System.out.println("Login failed: Unexpected welcome message content.");
            }
        } catch (Exception e) {
        	
            System.out.println("Login failed: Welcome message not found.");
        }
    }
}
