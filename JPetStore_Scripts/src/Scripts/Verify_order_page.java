package Scripts;

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
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Verify_order_page {

    private WebDriver driver;

    @BeforeClass
    public void setUp() throws IOException, InterruptedException {
        // Initialize WebDriver
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        
        // Prerequisites here
        Verify_the_URL.navigateToSite(driver);
        JpetStore_Automation.SignInLink(driver);
        Verify_Register_Now_Link_with_valid_data.registerNewUser(driver);
        Verify_Login_with_valid_data.signIn(driver);
        Verify_fish_link_Home_page11.clickCategory(driver);
        Verify_the_product_details_page_is_displayed15.verifyProductDetailsPage15(driver, "EST-1");
        JpetStore_Automation.updateQuantity(driver);
        JpetStore_Automation.proceedToCheckout(driver);
        JpetStore_Automation.verifyCheckoutPage(driver);
        JpetStore_Automation.VerifyContinue(driver);
        JpetStore_Automation.verifyAndCalculateTotalCost(driver);
    }

    @Test
    public void testVerifyOrderDetails() throws IOException {
        // Call the method to verify confirmation page details
        VerifyOrderDetails(driver);
    }

    @AfterClass
    public void tearDown() {
        // Close the browser and end the WebDriver session
        if (driver != null) {
            driver.quit();
        }
    }

    /**
     * Function Name: VerifyOrderDetails
     * Purpose      : Verifies the confirmation page details, including the thank you message and the order number.
     * Input        : WebDriver driver - The WebDriver instance used to automate the browser.
     * Output       : Outputs messages to the console and takes screenshots based on verification results.
     * Developed By : Ricky
     * Date         : 10/06/2024
     */
    public static void VerifyOrderDetails(WebDriver driver) throws IOException {
        try {
            // Create an instance of WebDriverWait with a 10-second timeout
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Verify "Thank you, your order has been submitted." text
            WebElement thankYouMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"Content\"]/ul/li")));
            String thankYouText = thankYouMessage.getText();
            Assert.assertEquals(thankYouText, "Thank you, your order has been submitted.", "Thank you message is incorrect.");
            Screenshot.takeScreenshot(driver, "Message_passed_TC77.png");

            // Verify order number pattern "Order # followed by digits"
            WebElement orderNumberElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"Catalog\"]/table/tbody/tr[1]/th")));
            String orderNumberText = orderNumberElement.getText();
            String extractedOrderNumber = extractOrderNumber(orderNumberText);
            Assert.assertNotNull(extractedOrderNumber, "No valid order number found.");
            Screenshot.takeScreenshot(driver, "Order_passed_TC77.png");
            System.out.println("Order number is displayed correctly: " + extractedOrderNumber);

        } catch (Exception e) {
            e.printStackTrace();
            Screenshot.takeScreenshot(driver, "Error_TC77.png");
            Assert.fail("An error occurred during order details verification.");
        }
    }

    // Helper method to extract the first 5 digits from the order number
    // Return the captured group which is the order number.
    private static String extractOrderNumber(String orderText) {
        Pattern pattern = Pattern.compile("Order #\\d+");
        Matcher matcher = pattern.matcher(orderText);
        if (matcher.find()) {
            String orderDigits = matcher.group().replaceAll("\\D", "");
            return orderDigits.substring(0, Math.min(orderDigits.length(), 5));
        }
        return null;
    }
}