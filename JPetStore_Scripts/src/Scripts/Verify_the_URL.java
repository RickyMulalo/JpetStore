package Scripts;

import java.io.IOException;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Verify_the_URL {

	public static void navigateToSite(WebDriver driver) throws IOException {
		 Read_Data rd =new Read_Data();
	        Map<String, String> userDetails = Read_Data.readUserDetailsFromFile("src/WebLink");
	        String link = userDetails.get("link"); 
	        driver.get(link);
	       
	    }
	
	public static void main(String[] args) {
		WebDriver driver= new ChromeDriver();
		// TODO Auto-generated method stub
		try {
            // Navigate to the site
            navigateToSite(driver);

            // Maximize the window
            driver.manage().window().maximize();

          
            boolean isUrlWorking = driver.findElement(By.xpath("//*[@id=\"LogoContent\"]/a/img")).isDisplayed();
	        if (isUrlWorking) {
	            System.out.println("JpetStore page should display with the pet images, five links named(Fish,Dogs,Cats,Reptiles and Birds) and a Sign in Link at the top of the website.");
	        } else {
	        	
	            System.out.println("Finish button is not clickable.");
	            
	        }
        } catch (Exception e) {
            System.out.println("An error occurred while navigating to the site.");
            e.printStackTrace();
        } finally {
            // Close the browser
            //driver.quit();
        }
	}

}
