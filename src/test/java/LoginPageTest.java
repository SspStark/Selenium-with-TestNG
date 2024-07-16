import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import org.testng.Assert;

public class LoginPageTest {
    public WebDriver driver;

    @BeforeMethod
    public void setUp() {
        // Code for setting up WebDriver instance
        System.setProperty("webdriver.chrome.driver", "D:\\sspto\\Downloads1\\Softwares\\Chrome Driver\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();

        // Navigate to the login page
        driver.get("https://rahulnxttrendz.ccbp.tech/login");

    }

    @AfterMethod
    public void tearDown() {
        // Code for closing WebDriver instance
        driver.close();
    }

    @DataProvider
    public Object[][] loginData() {
        return new Object[][] {
                { "praneetha", "praneetha@2021" },
                { "rahul", "rahul@2021" }
        };
    }

    @Test(priority = 1, dataProvider = "loginData")
    public void loginWithValidCredentials(String username, String password) {

        // Locate the username input field element by its id
        WebElement usernameEl = driver.findElement(By.id("username"));
        usernameEl.sendKeys(username);

        // Locate the password input field element by its id
        WebElement passwordEl = driver.findElement(By.id("password"));
        passwordEl.sendKeys(password);

        // Find the login button element by its class name
        WebElement loginButtonEl = driver.findElement(By.className("login-button"));

        // Submit the login form
        loginButtonEl.submit();

        // Declare expected URL
        String expectedUrl = "https://rahulnxttrendz.ccbp.tech/";

        // Wait for the page to be redirected to the expected URL
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(expectedUrl));

        // Get the current URL
        String currentUrl = driver.getCurrentUrl();

        // Verify if the expected heading matches the actual heading
        Assert.assertEquals(expectedUrl, currentUrl, "URLs do not match");
    }

    @Test(priority = 2)
    public void loginWithInvalidCredentials() {
        // Locate and enter valid username input field element by its id
        WebElement usernameEl = driver.findElement(By.id("username"));
        usernameEl.sendKeys("rahul");

        // Locate and enter invalid password input field element by its id
        WebElement passwordEl = driver.findElement(By.id("password"));
        passwordEl.sendKeys("rahul2021");

        // Find the login button element by its class name
        WebElement loginButtonEl = driver.findElement(By.className("login-button"));

        // Submit the login form
        loginButtonEl.submit();

        // Wait for the Error message to be loaded
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error-message")));

        // Verify the error message
        WebElement errorMessageEl = driver.findElement(By.className("error-message"));
        String errorMessage = errorMessageEl.getText();
        Assert.assertEquals(errorMessage, "*username and password didn't match");
    }



}