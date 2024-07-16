import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import org.testng.Assert;

public class HomePageTest {
    public WebDriver driver;

    @BeforeMethod
    public void setUp() {
        // Code for setting up WebDriver instance
        System.setProperty("webdriver.chrome.driver", "D:\\sspto\\Downloads1\\Softwares\\Chrome Driver\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();

        // Navigate to the login page
        driver.get("https://rahulnxttrendz.ccbp.tech/login");

        WebElement usernameEl = driver.findElement(By.id("username"));
        usernameEl.sendKeys("rahul");

        WebElement passwordEl = driver.findElement(By.id("password"));
        passwordEl.sendKeys("rahul@2021");

        WebElement loginButtonEl = driver.findElement(By.className("login-button"));
        loginButtonEl.submit();

    }

    @AfterMethod
    public void tearDown() {
        // Code for closing WebDriver instance
        driver.close();
    }

    @Test
    public void ClickOnShopNowButton(){
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement shopNowEl= wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("shop-now-button")));
        shopNowEl.click();

        String expectedUrl="https://rahulnxttrendz.ccbp.tech/products";
        String currentUrl=driver.getCurrentUrl();
        Assert.assertEquals(expectedUrl,currentUrl, "URLs do not match");
    }

    @Test
    public void CheckHeadingElement(){
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement headingEl= wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("home-heading")));

        Assert.assertTrue(headingEl.isDisplayed());
    }
}
