package Pett;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UpdateShoppingCart {

    public static void main(String[] args) throws IOException, InterruptedException {

        // Initialize WebDriver
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Prerequisite steps
        ClickSignin signin = new ClickSignin();
        Verify_the_URL_OB_06 url = new Verify_the_URL_OB_06();
        url.navigateToSite(driver);
        signin.clickSignIn(driver);
        Verify_Register_Now_valid_data.verify_Register_Now_valid_data(driver);
        Verify_Login_with_valid_data.verify_Register_Now_valid_data(driver);
        Verify_fish_link_Home_page11.clickCategory(driver);

        Verify_the_product_details_page_is_displayed15.verifyProductDetailsPage15(driver);
        updateQuantity(driver);
        //driver.quit();
    }

    public static void updateQuantity(WebDriver driver) {
        
        	driver.findElement(By.xpath("//*[@id=\"Cart\"]/a")).click();
        	
        
    }}
