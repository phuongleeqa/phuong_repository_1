package selenium;

import com.google.common.eventbus.SubscriberExceptionContext;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_15_Alert {
    WebDriver driver;
    Alert alert;
    WebDriverWait explicitwait;


    @BeforeClass
    public void  initialBrowser() {
        driver = new FirefoxDriver();
        explicitwait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }


    @Test
    public void TC_01_Accept_Alert() throws InterruptedException {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();

        alert = explicitwait.until(ExpectedConditions.alertIsPresent());
//        alert = driver.switchTo().alert();
        Thread.sleep(3000);

        Assert.assertEquals(alert.getText(),"I am a JS Alert");

        alert.accept();
        Thread.sleep(3000);

        Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(),
        "You clicked an alert successfully");

    }

    @Test
    public void TC_02_Confirm_Alert() throws InterruptedException {
        driver.get("https://live.techpanda.org/index.php/");

        driver.findElement(By.cssSelector("input#search")).sendKeys("Samsung Galaxy");
        driver.findElement(By.cssSelector("button.search-button")).click();

        alert = explicitwait.until(ExpectedConditions.alertIsPresent());
        Thread.sleep(2000);

        String alertMessage = alert.getText();

        Assert.assertTrue(alertMessage.contains("The information you have entered"));

        Assert.assertTrue(alertMessage.contains("Are you sure you want to send this information?"));

        alert.accept();
        Thread.sleep(2000);

        List<WebElement> products = driver.findElements(By.cssSelector("ul.product-grid>li"));
        Assert.assertEquals(products.size(), 2);
    }
    @Test
    public void TC_03_Prompt_Alert() throws InterruptedException {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();

        alert = explicitwait.until(ExpectedConditions.alertIsPresent());
        Thread.sleep(2000);

        Assert.assertEquals(alert.getText(),"I am a JS prompt");

        String name = "Test Prompt Alert";

        alert.sendKeys(name);
        Thread.sleep(2000);

        alert.accept();
        Thread.sleep(2000);

        Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(),
                "You entered: + name");

    }

    @Test
    public void TC_04_Authentication_Alert(){

    }

    @AfterClass
    public void cleanBrowser(){
        driver.quit();

    }
}
