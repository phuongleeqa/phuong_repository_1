package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.Random_Email;

public class Topic_09_Textbox_TextArea {
    WebDriver driver;
    String firstName, lastName, fullName, emailAddress, password, reviewProduct;
    @BeforeClass
    public void  initialBrowser() {
        driver = new FirefoxDriver();

        firstName = "John";
        lastName = "Kennedy";
        fullName = firstName + " " + lastName;
        emailAddress = Random_Email.getRandomEmail();
        password = "123456";
        reviewProduct = "Good Phone\n Best Quality n";
    }

    @Test
    public void TC_01_TechPanda() throws InterruptedException {
        driver.get("http://live.techpanda.org/");

        driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();

        driver.findElement(By.cssSelector("a[title='Create an Account']")).click();

        driver.findElement(By.cssSelector("input#firstname")).sendKeys(firstName);
        driver.findElement(By.cssSelector("input#lastname")).sendKeys(lastName);
        driver.findElement(By.cssSelector("input#email_address")).sendKeys(emailAddress);
        driver.findElement(By.cssSelector("input#password")).sendKeys(password);
        driver.findElement(By.cssSelector("input#confirmation")).sendKeys(password);
        driver.findElement(By.cssSelector("button[title='Register']")).click();
        Thread.sleep(2000);

        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),
                "Thank you for registering with Main Website Store." );

        String contactInfor =  driver.findElement
                (By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
        Assert.assertTrue(contactInfor.contains(fullName));
        Assert.assertTrue(contactInfor.contains(emailAddress));

        driver.findElement(By.xpath("//h3[text()='Contact Information']/following-sibling::a")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("input#firstname")).getDomAttribute("value"),firstName);
        Assert.assertEquals(driver.findElement(By.cssSelector("input#firstname")).getDomProperty("value"),firstName);
        Assert.assertEquals(driver.findElement(By.cssSelector("input#lastname")).getDomAttribute("value"),lastName);
        Assert.assertEquals(driver.findElement(By.cssSelector("input#email")).getDomAttribute("value"),emailAddress);


        driver.findElement(By.xpath("//a[text()='Mobile']")).click();
        driver.findElement(By.cssSelector("h2 > a[title='IPhone']")).click();
        driver.findElement(By.xpath("//a[text()='Add Your Review']")).click();

        driver.findElement(By.cssSelector("input[id='Quality 1_5")).click();
        driver.findElement(By.cssSelector("textarea#review_field")).sendKeys(reviewProduct);
        driver.findElement(By.cssSelector("input#summary_field")).sendKeys(reviewProduct);
        driver.findElement(By.cssSelector("input#nickname_field")).sendKeys(fullName);
        driver.findElement(By.cssSelector("button[title='Submit Review")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),
                "Your review has been accepted for moderation." );
    }

    @Test
    public void TC_02_login(){

    }

    @AfterClass
    public void cleanBrowser(){
        driver.quit();

    }
}
