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
        driver.get("https://the-internet.herokuapp.com/basic_auth");

        Assert.assertTrue(driver.findElement(By.xpath("div[@class='example']/p[contains(text()," + " 'Congratulations! You must have the proper credentials.')]")).isDisplayed());
    }

    @Test
    public void TC_05_Authen_Alert_2(){
        driver.get("https://the-internet.herokuapp.com");

        String basicAuthenUrl = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getDomProperty("href");
        String [] urlSplit = basicAuthenUrl.split("//");
        basicAuthenUrl = urlSplit[0] + "//" + "admin:admin@" + urlSplit[1];

        driver.get(basicAuthenUrl);
        Assert.assertTrue(driver.findElement(By.xpath("div[@class='example']/p[contains(text()," + " " +
                "'Congratulations! You must have the proper credentials.')]")).isDisplayed());

    }

    @Test
    public void TC_03_Authen_Alert() {
        // Get DevTool object
        DevTools devTools = ((HasDevTools) driver).getDevTools();

        // Start new session
        devTools.createSession();

        // Enable the Network domain of devtools
        devTools.send(Network.enable(Optional.empty(), Optional.empty(),
                Optional.empty(), Optional.empty(), Optional.empty()));

        // Encode username/ password
        Map<String, Object> headers = new HashMap<String, Object>();
        String basicAuthen = "Basic " + new String(new Base64().encode(String.format("%s:%s", "admin", "admin").getBytes()));
        headers.put("Authorization", basicAuthen);

        // Set to Header
        devTools.send(Network.setExtraHTTPHeaders(new Headers(headers)));

        driver.get("http://the-internet.herokuapp.com/basic_auth");

        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='example']/p[contains(text()," +
                "'Congratulations! You must have the proper credentials.')]")).isDisplayed());
    }

    @AfterClass
    public void cleanBrowser(){
        driver.quit();

    }
}
