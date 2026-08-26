package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.openqa.selenium.support.Color.fromString;

public class Topic_12_Button {
    WebDriver driver;


    @BeforeClass
    public void  initialBrowser() {
        driver = new FirefoxDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test
    public void TC_01_Huawei() {
        driver.get("https://id5.cloud.huawei.com/CAS/portal/userRegister/regbyemail.html");

        By Register_Btn = By.cssSelector("div.hwid-btn-reg");

        // Verify button disable
        //Assert.assertFalse(driver.findElement(Register_Btn).isEnabled());
        Assert.assertTrue(driver.findElement(Register_Btn).getDomProperty("className").contains("hwid-disable"));
        Assert.assertTrue(driver.findElement(Register_Btn).getDomAttribute("class").contains("hwid-disable"));

        //Verify Button text
        Assert.assertEquals(driver.findElement(Register_Btn).getText(), "REGISTER");

        //Background color
        String registerBtnRgbColor = driver.findElement(Register_Btn).getCssValue("background-color");
        String registerBtnHexColor = Color.fromString(registerBtnRgbColor).asHex();
        Assert.assertEquals(registerBtnHexColor, "#ca141d");
    }

    @Test
    public void TC_02_Fahasa() throws InterruptedException {
        driver.get("https://www.fahasa.com/customer/account/create");

        driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();

        By loginBtn = By.cssSelector("button.fhs-btn-login");

        //Verify disable
        Assert.assertFalse(driver.findElement(loginBtn).isEnabled());

        //Background color
        String loginBtnRgbColor = driver.findElement(loginBtn).getCssValue("background-color");
        System.out.println(loginBtnRgbColor);
        Assert.assertEquals(Color.fromString(loginBtnRgbColor).asHex().toUpperCase(), "#000000");

        driver.findElement(By.cssSelector("input#login_username")).sendKeys("phuong@testgmail.com");
        driver.findElement(By.cssSelector("input#login_password")).sendKeys("Auto123@");
        Thread.sleep(2000);

        //Verify Enable
        Assert.assertTrue(driver.findElement(loginBtn).isEnabled());

        loginBtnRgbColor = driver.findElement(loginBtn).getCssValue("background-color");
        Assert.assertEquals(Color.fromString(loginBtnRgbColor).asHex().toUpperCase(), "#C92127");
    }



    @AfterClass
    public void cleanBrowser(){
        driver.quit();

    }

}
