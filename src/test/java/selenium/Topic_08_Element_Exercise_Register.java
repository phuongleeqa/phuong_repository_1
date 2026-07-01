package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Element_Exercise_Register {
    WebDriver driver;

    @BeforeClass
    public void initBrowser(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_SignUp() throws InterruptedException {

        driver.get("https://login.mailchimp.com/signup/");

        // Empty Data
        driver.findElement(By.cssSelector("button#create-account-enabled")).click();
        Thread.sleep(3000);

        Assert.assertEquals(driver.findElement(By.cssSelector("input#email~span.invalid-error")).getText(),
                "An email address must contain a single @.");

        Assert.assertEquals(driver.findElement(By.cssSelector("input#new_username~span.invalid-error")).getText(),
                "Please enter a value");

        // Invalid Email
        driver.findElement(By.cssSelector("input#email")).sendKeys("automationfc");
        driver.findElement(By.cssSelector("button#create-account-enabled")).click();
        Thread.sleep(3000);
        Assert.assertEquals(driver.findElement(By.cssSelector("input#email~span.invalid-error")).getText(),
                "An email address must contain a single @.");

        driver.findElement(By.cssSelector("input#email")).clear();
        driver.findElement(By.cssSelector("input#new_username")).clear();
        driver.findElement(By.cssSelector("input#email")).sendKeys("automationfc@gmail@com");
        driver.findElement(By.cssSelector("button#create-account-enabled")).click();
        Thread.sleep(3000);
        Assert.assertEquals(driver.findElement(By.cssSelector("input#email~span.invalid-error")).getText(),
                "An email address must contain a single @.");

        driver.findElement(By.cssSelector("input#email")).clear();
        driver.findElement(By.cssSelector("input#new_username")).clear();
        driver.findElement(By.cssSelector("input#email")).sendKeys("123@321");
        driver.findElement(By.cssSelector("button#create-account-enabled")).click();
        Thread.sleep(3000);
        Assert.assertEquals(driver.findElement(By.cssSelector("input#email~span.invalid-error")).getText(),
                "The domain portion of the email address is invalid (the portion after the @: 321)");

        //Invalid Password - LowerCaSe
        driver.findElement(By.cssSelector("input#email")).clear();
        driver.findElement(By.cssSelector("input#new_username")).clear();
        driver.findElement(By.cssSelector("input#email")).sendKeys("autotest123@gmail.com");
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("auto");
        driver.findElement(By.cssSelector("button#create-account-enabled")).click();
        Thread.sleep(3000);

        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed'")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.not-completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("input#marketing_newsletter")).isSelected());


        //Invalid Password - UpperCaSe
        driver.findElement(By.cssSelector("input#email")).clear();
        driver.findElement(By.cssSelector("input#new_username")).clear();
        driver.findElement(By.cssSelector("input#email")).sendKeys("autotest123@gmail.com");
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("AUTO");
        driver.findElement(By.cssSelector("button#create-account-enabled")).click();
        Thread.sleep(3000);

        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char completed'")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("input#marketing_newsletter")).isSelected());


        //Invalid Password - Number
        driver.findElement(By.cssSelector("input#email")).clear();
        driver.findElement(By.cssSelector("input#new_username")).clear();
        driver.findElement(By.cssSelector("input#email")).sendKeys("autotest123@gmail.com");
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("012312");
        driver.findElement(By.cssSelector("button#create-account-enabled")).click();
        Thread.sleep(3000);


        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char completed'")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("input#marketing_newsletter")).isSelected());


        //Invalid Password - Special Characters
        driver.findElement(By.cssSelector("input#email")).clear();
        driver.findElement(By.cssSelector("input#new_username")).clear();
        driver.findElement(By.id("new_password")).clear();
        driver.findElement(By.cssSelector("input#email")).sendKeys("autotest123@gmail.com");
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("@#&");
        driver.findElement(By.cssSelector("button#create-account-enabled")).click();
        Thread.sleep(3000);


        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed'")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("input#marketing_newsletter")).isSelected());


        //Invalid Password - 8 Char
        driver.findElement(By.cssSelector("input#email")).clear();
        driver.findElement(By.cssSelector("input#new_username")).clear();
        driver.findElement(By.id("new_password")).clear();
        driver.findElement(By.cssSelector("input#email")).sendKeys("autotest123@gmail.com");
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("auto@aaa");
        driver.findElement(By.cssSelector("button#create-account-enabled")).click();
        Thread.sleep(3000);


        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char completed'")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("input#marketing_newsletter")).isSelected());


        //Valid Password
        driver.findElement(By.cssSelector("input#email")).clear();
        driver.findElement(By.cssSelector("input#new_username")).clear();
        driver.findElement(By.id("new_password")).clear();
        driver.findElement(By.cssSelector("input#email")).sendKeys("autotest123@gmail.com");
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("Automation123@");
        //driver.findElement(By.cssSelector("button#create-account-enabled")).click();
        Thread.sleep(3000);


        Assert.assertFalse(driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li.uppercase-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li.special-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li[class='8-char completed'")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("input#marketing_newsletter")).isSelected());

    }

    @AfterClass
    public void closeBrowser() {
        //driver.quit();
    }
}
