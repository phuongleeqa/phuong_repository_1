package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Topic_09_Textbox_TextArea_II {
    WebDriver driver;
    By loadingIcon = By.cssSelector("div.oxd-loading-spinner");
    String firstName, lastName, employeeID, password, userName, passportNumber, passportComment;
    @BeforeClass
    public void initialBrowser() {
        driver = new FirefoxDriver();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        firstName= "Soaen";
        lastName = "elemphen";
        userName= "Soaen.elemphen" + new Random().nextInt(9999);
        password = "Phillip123@";
        passportNumber = "1522-3356-4426";
        passportComment = "123 PO Box\n New York";
    }

    @Test
    public void TC_01_Employee() throws InterruptedException {
        //Login
        driver.findElement(By.cssSelector("input[name='username']")).sendKeys("Admin");
        driver.findElement(By.cssSelector("input[type='password']")).sendKeys("admin123");
        driver.findElement(By.cssSelector("button.orangehrm-login-button")).click();
        //Handle Loading Icon
        Assert.assertTrue(new WebDriverWait(driver, Duration.ofSeconds(30)).until
                (ExpectedConditions.invisibilityOfAllElements(driver.findElements(loadingIcon))));

        //Employee List
        driver.findElement(By.xpath("//span[text()='PIM']/parent::a")).click();
        //Handle Loading Icon
        Assert.assertTrue(new WebDriverWait(driver, Duration.ofSeconds(30)).until
                (ExpectedConditions.invisibilityOfAllElements(driver.findElements(loadingIcon))));

        //Add Employee
        driver.findElement(By.xpath("//button[contains(string(),'Add')]")).click();
        //Handle Loading Icon
        Assert.assertTrue(new WebDriverWait(driver, Duration.ofSeconds(30)).until
                (ExpectedConditions.invisibilityOfAllElements(driver.findElements(loadingIcon))));

        driver.findElement(By.cssSelector("input[name='firstName']")).sendKeys(firstName);
        driver.findElement(By.cssSelector("input[name='lastName']")).sendKeys(lastName);

        //Get Employee ID
        employeeID = driver.findElement(By.xpath
                ("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getDomProperty("value");
        System.out.println("Employee ID =" + employeeID);

        driver.findElement(By.xpath("//p[text()='Create Login Details']/following-sibling::div/label")).click();
        //Handle Loading Icon
        Assert.assertTrue(new WebDriverWait(driver, Duration.ofSeconds(30)).until
                (ExpectedConditions.invisibilityOfAllElements(driver.findElements(loadingIcon))));

        //Create new Employee
        driver.findElement(By.xpath("//label[text()='Username']/parent::div/following-sibling::div/input")).sendKeys(userName);
        driver.findElement(By.xpath("//label[text()='Password']/parent::div/following-sibling::div/input")).sendKeys(password);
        driver.findElement(By.xpath("//label[text()='Confirm Password']/parent::div/following-sibling::div/input")).sendKeys(password);

        driver.findElement(By.xpath("//button[contains(string(),'Save')]")).click();
        Thread.sleep(6000);

        //Verify  success mgs
        //Assert.assertEquals
                //(driver.findElement(By.cssSelector("div.oxd-toast-content--success>p.oxd-text--toast-message")).getText(),
                        //"Successfully Saved");
        Assert.assertTrue(driver.findElement(By.xpath
                ("//div[contains(@class,'oxd-toast-content--success')]/p[text()='Successfully Saved']")).isDisplayed());*/

        // Loading Icon 1 (Add new Employee)
        Assert.assertTrue(new WebDriverWait(driver, Duration.ofSeconds(30)).until
                (ExpectedConditions.invisibilityOfAllElements(driver.findElements(loadingIcon))));

        // Loading Icon 2 ( Personal Details)
        Assert.assertTrue(new WebDriverWait(driver, Duration.ofSeconds(30)).until
                (ExpectedConditions.invisibilityOfAllElements(driver.findElements(loadingIcon))));

        //Verify employeeID
        Assert.assertEquals(driver.findElement(By.xpath
                ("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getDomProperty("value"),employeeID);

        Assert.assertEquals(driver.findElement(By.cssSelector("input[name='firstName']")).getDomProperty("value"),firstName);
        Assert.assertEquals(driver.findElement(By.cssSelector("input[name='lastName']")).getDomProperty("value"),lastName);
        Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).isEnabled());

        //navigate ton Immigration screen
        driver.findElement(By.xpath("//a[text()='Immigration']")).click();

        // Loading Icon 1
        Assert.assertTrue(new WebDriverWait(driver, Duration.ofSeconds(30)).until
                (ExpectedConditions.invisibilityOfAllElements(driver.findElements(loadingIcon))));

        driver.findElement(By.xpath
                ("//h6[text()='Assigned Immigration Records']/following-sibling::button[contains(string(),'Add')]")).click();

        driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).sendKeys(passportNumber);
        driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea")).sendKeys(passportComment);

        driver.findElement(By.xpath("//button[contains(string(),'Save')]")).click();
        Thread.sleep(5000);

        //Verify  success mgs
        Assert.assertEquals
                (driver.findElement(By.cssSelector("div.oxd-toast-content--success>p.oxd-text--toast-message")).getText(),
                        "Successfully Saved");
        //Assert.assertTrue(driver.findElement(By.xpath("//div[contains(@class,'oxd-toast-content--success')]/p[text()='Successfully Saved']")).isDisplayed());


        // Loading Icon 1 (Add new Employee)
        Assert.assertTrue(new WebDriverWait(driver, Duration.ofSeconds(30)).until
                (ExpectedConditions.invisibilityOfAllElements(driver.findElements(loadingIcon))));

        driver.findElement(By.xpath
                ("div[text()= '" + passportNumber + "']/parent::div/following-sibling::div//i[contains(@class,'bi-pencil-fill')]")).click();

        Assert.assertTrue(new WebDriverWait(driver, Duration.ofSeconds(30)).until
                (ExpectedConditions.invisibilityOfAllElements(driver.findElements(loadingIcon))));

        //Verify
        Assert.assertEquals(driver.findElement(By.xpath
                ("//label[text()='Number']/parent::div/following-sibling::div/input")).getDomProperty("value"),passportNumber);

        Assert.assertEquals(driver.findElement(By.xpath
                ("//label[text()='Comments']/parent::div/following-sibling::div/textarea")).getDomProperty("value"),passportComment);

    }

    @AfterClass
    public void cleanBrowser(){
        driver.quit();

    }
}
