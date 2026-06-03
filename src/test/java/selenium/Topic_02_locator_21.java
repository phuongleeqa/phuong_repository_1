package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.annotations.*;

public class Topic_02_locator_21 {

    WebDriver driver;

    @BeforeClass
    public void initialBrowser() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demo.nopcommerce.com/login?returnUrl=%2F");
        Thread.sleep(10000);
    }

    @Test
    public void TC_01_Relative_locator() {
      // Element A
        By passwordTextboxBy = By.cssSelector("input#Password");
      // WebElement passwordTextbox = driver.findElement(By.cssSelector("input#Password"));

        // Element B
        By rememberMeCheckboxBy = By.id("RememberMe");

        // Element C
        By forgotPasswordLinkBy = By.cssSelector("span.forgot-password");

        // Element D
        By loginButtonBy = By.cssSelector("button.login-button");

        // Element E
        WebElement rememberMeLabelText = driver.findElement(
                RelativeLocator.with(By.tagName("label"))
                        .above(loginButtonBy)
                        .below(passwordTextboxBy)
                        .toRightOf(rememberMeCheckboxBy)
                        .toLeftOf(forgotPasswordLinkBy)
        );
    }

    @Test
    public void TC_02_Find_element_by_xpath() throws InterruptedException {
        driver.get("https://demoqa.com/");
        // Find and click on the card with name Elements
        driver.findElement(By.xpath("//h5[text()='Elements']")).click();
        Thread.sleep(3000);
    }

    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }
}