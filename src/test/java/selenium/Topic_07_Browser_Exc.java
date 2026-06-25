package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Browser_Exc {
    WebDriver driver;
    @BeforeClass
    public void initBrowser(){
        driver = new ChromeDriver();

    }

    @Test
    public void TC_01_Url() {

        driver.get("https://live.techpanda.org/index.php/");
        driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();

        Assert.assertEquals(driver.getCurrentUrl(), "https://live.techpanda.org/index.php/customer/account/login/");

        driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://live.techpanda.org/index.php/customer/account/create/");
    }

    @Test
    public void TC_02_Title() {

        driver.get("https://live.techpanda.org/index.php/");
        driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();

        Assert.assertEquals(driver.getTitle(), "Customer Login");

        driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
        Assert.assertEquals(driver.getTitle(), "Create New Customer Account");

    }

    @Test
    public void TC_03_Page_Source() {

        driver.get("https://live.techpanda.org/index.php/");
        driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();

        Assert.assertTrue(driver.getPageSource().contains("Login or Create an Account"));

        driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
        Assert.assertTrue(driver.getPageSource().contains("Create New Customer Account"));
    }

    @Test
    public void TC_04_Navigate() {

        driver.get("https://live.techpanda.org/index.php/");
        driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();

        driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://live.techpanda.org/index.php/customer/account/create/");

        driver.navigate().back();
        Assert.assertEquals(driver.getCurrentUrl(), "https://live.techpanda.org/index.php/customer/account/login/");

        driver.navigate().forward();
        Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
    }


    @AfterClass
    public void closeBrowser() {
        driver.quit();
    }
}
