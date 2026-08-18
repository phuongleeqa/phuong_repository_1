package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class Topic_11_Custom_Dropdown {
    WebDriver driver;
    WebDriverWait explicitWait;
    By loadingIcon = By.cssSelector("div.oxd-loading-spinner");
    String firstName, lastName, employeeID, password, userName, passportNumber, passportComment;

    @BeforeClass
    public void  initialBrowser() {
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        firstName= "Advance";
        lastName = "Buk";
        userName= "Advance.Buk" + new Random().nextInt(9999);
        password = "Advance123@";
        passportNumber = "1301-3302-5528";
        passportComment = "123 PO Box\n Tokyo";
    }

    @Test
    public void TC_01_jQuery() {
        driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");

        /* ----- Thao tac vs SPEED -----*/
        selectItemDropdownBycss("span#speed-button","ul#speed-menu div","Slow");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button >span.ui-selectmenu-text")).getText(), "Slow");

        selectItemDropdownBycss("span#speed-button","ul#speed-menu div","Faster");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button >span.ui-selectmenu-text")).getText(), "Faster");

        /* ----- Thao tac vs SALUTATION -----*/
        selectItemDropdownBycss("span#salutation-button","ul#salutation-menu div","Dr.");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#salutation-button >span.ui-selectmenu-text")).getText(), "Dr.");

        selectItemDropdownBycss("span#salutation-button","ul#salutation-menu div","Mrs.");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#salutation-button >span.ui-selectmenu-text")).getText(), "Mrs.");


    }

    @Test
    public void TC_02_React()  {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");

        selectItemDropdownBycss("div.ui.fluid.selection", "div.visible.menu>div>span", "Matt" );
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Matt");

        selectItemDropdownBycss("div.ui.fluid.selection", "div.visible.menu>div>span", "Elliot Fu" );
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Elliot Fu");

    }

    @Test
    public void TC_03_VueJS()  {
        driver.get("https://mikerodham.github.io/vue-dropdowns/");

        selectItemDropdownBycss("div.btn-group", "ul.dropdown-menu a", "Second Option" );
        Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Second Option");

        // Third Option
        selectItemDropdownBycss("div.btn-group", "ul.dropdown-menu a", "Third Option" );
        Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Third Option");

    }

    @Test
    public void TC_04_OrangeHRM() throws InterruptedException{
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

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
        //Assert.assertTrue(driver.findElement(By.xpath
                //("//div[contains(@class,'oxd-toast-content--success')]/p[text()='Successfully Saved']")).isDisplayed());

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

        // Select Nationality dropdown
        selectItemDropdown(By.xpath("//label[text()='Nationality']/parent::div/following-sibling::div//i"),
                By.xpath("//label[text()='Nationality']/parent::div/following-sibling::div//div[@class='oxd-select-option']/span"), "Vietnamese");
        
        // Select Marital Status dropdown
        selectItemDropdown(By.xpath("//label[text()='Marital Status']/parent::div/following-sibling::div//i"),
                By.xpath("//label[text()='Marital Status']/parent::div/following-sibling::div//div[@class='oxd-select-option']/span"), "Single");

        // Select Blood Type dropdown
        selectItemDropdown(By.xpath("//label[text()='Blood Type']/parent::div/following-sibling::div//i"),
                By.xpath("//label[text()='Blood Type']/parent::div/following-sibling::div//div[@class='oxd-select-option']/span"), "B+");


    }

    @Test
    public void TC_05_Editable_React()  {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");

        // Selectable:
        selectItemDropdownBycss("div.ui.fluid.selection", "div.visible.menu>div>span", "Argentina" );
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Argentina");

        selectItemDropdownBycss("div.ui.fluid.selection", "div.visible.menu>div>span", "Belgium" );
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Belgium");

        // Editable:
        selectItemEditableDropdown("div.ui.fluid.selection>input","div.visible.menu>div>span","Argentina");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Argentina");

        selectItemEditableDropdown("div.ui.fluid.selection>input","div.visible.menu>div>span","Belgium");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Belgium");

    }

    @Test
    public void TC_06_Editable_FinPeace(){
        driver.get("https://sps.finpeace.vn/tools/sktccn");

        selectItemEditableDropdown("input#job_id","div#job_id_list~div div.ant-select-item-option-content","Công nghệ thông tin");
        Assert.assertEquals(driver.findElement(By.xpath("//input[@id='job_id']/parent::span/following-sibling::span")).getText(), "Công nghệ thông tin");

        selectItemEditableDropdown("input#gender","div#gender_list~div div.ant-select-item-option-content","Nam");
        Assert.assertEquals(driver.findElement(By.xpath("//input[@id='gender']/parent::span/following-sibling::span")).getText(), "Nam");

        selectItemEditableDropdown("input#married_status","div#married_status_list~div div.ant-select-item-option-content","Độc thân, chưa có con");
        Assert.assertEquals(driver.findElement(By.xpath("//input[@id='married_status']/parent::span/following-sibling::span")).getText(), "Độc thân, chưa có con");
    }

    // Reusable Method: ham tai su dung
    public void selectItemDropdownBycss(String parentLocator, String childLocator, String itemValue)  {
        driver.findElement(By.cssSelector(parentLocator)).click();
        List<WebElement> childItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy
                (By.cssSelector(childLocator)));
        for (WebElement item: childItems) {
            if (item.getText().equals(itemValue)) {
                item.click();
                sleepInSecond(1);
                break;
            }
        }

    }

    public void selectItemEditableDropdown(String parentLocator, String childLocator, String itemValue) {
        driver.findElement(By.cssSelector(parentLocator)).sendKeys(itemValue);
        sleepInSecond(2);
        List<WebElement> childItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy
                (By.cssSelector(childLocator)));
        for (WebElement item: childItems) {
            if (item.getText().equals(itemValue)) {
                item.click();
                break;
            }
        }

    }

    public void sleepInSecond(long timeInSecond){
        try {
            Thread.sleep(timeInSecond *1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void selectItemDropdown(By parentLocator, By childLocator, String itemValue) {
        driver.findElement(parentLocator).click();
        sleepInSecond(2);
        List<WebElement> childItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy
                (childLocator));
        for (WebElement item: childItems) {
            if (item.getText().equals(itemValue)) {
                item.click();
                sleepInSecond(1);
                break;
            }
        }

    }

    @AfterClass
    public void cleanBrowser(){
        driver.quit();

    }

}
