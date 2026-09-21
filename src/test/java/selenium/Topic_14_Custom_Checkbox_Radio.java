package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_14_Custom_Checkbox_Radio {
    WebDriver driver;
    @BeforeClass
    public void  initialBrowser() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

//    2 - Action/Execute: Tuong tac len elements enter verify/....

    @Test
    public void TC_01_Ubuntu() throws InterruptedException {
        driver.get("https://login.ubuntu.com/");
        By ubuntuAccountRadioLabel = By.xpath("//label[@for='id_new_user']");
        By ubuntuAccountInput = By.xpath("//label[@for='id_new_user']/preceding-sibling::input");

        driver.findElement(ubuntuAccountRadioLabel).click();
        Thread.sleep(4000);

        Assert.assertTrue(driver.findElement(ubuntuAccountInput).isSelected());
    }

    @Test
    public void TC_02_Google_Form() throws InterruptedException {
        driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
        Thread.sleep(3000);

        By canthoCity = By.cssSelector("div[aria-label='Cần Thơ']");

        Assert.assertEquals(driver.findElement(canthoCity).getDomAttribute("aria-checked"), "false");

        driver.findElement(canthoCity).click();
        Thread.sleep(3000);

        Assert.assertEquals(driver.findElement(canthoCity).getDomAttribute("aria-checked"), "true");

    }
    //    3 - Clean
    @AfterClass
    public void cleanBrowser(){
        driver.quit();

    }
}
