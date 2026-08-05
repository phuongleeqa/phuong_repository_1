package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

public class Topic_11_Custom_Dropdown {
    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void  initialBrowser() {
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));


        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test
    public void TC_01_jQuery() throws InterruptedException {
        driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");

        /* ----- Thao tac vs SPEED -----*/
        selectItemDropdown ("span#speed-button","ul#speed-menu div","Slow");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button >span.ui-selectmenu-text")).getText(), "Slow");

        selectItemDropdown ("span#speed-button","ul#speed-menu div","Faster");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button >span.ui-selectmenu-text")).getText(), "Faster");


        /* ----- Thao tac vs SALUTATION -----*/
        selectItemDropdown ("span#salutation-button","ul#salutation-menu div","Dr.");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#salutation-button >span.ui-selectmenu-text")).getText(), "Dr.");


        selectItemDropdown ("span#salutation-button","ul#salutation-menu div","Mrs.");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#salutation-button >span.ui-selectmenu-text")).getText(), "Mrs.");


    }

    @Test
    public void TC_02_React() throws InterruptedException {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");

        selectItemDropdown("div.ui.fluid.selection", "div.visible.menu>div>span", "Matt" );
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Matt");

        selectItemDropdown("div.ui.fluid.selection", "div.visible.menu>div>span", "Elliot Fu" );
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Elliot Fu");

    }

    // Reusable Method: ham tai su dung
    public void selectItemDropdown(String parentLocator, String childLocator, String itemValue) throws InterruptedException {
        driver.findElement(By.cssSelector(parentLocator)).click();
        List<WebElement> childItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy
                (By.cssSelector(childLocator)));
        for (WebElement item: childItems) {
            if (item.getText().equals(itemValue)) {
                item.click();
                Thread.sleep(1000);
                break;
            }
        }

    }

    @Test
    public void TC_03_VueJS() throws InterruptedException {
        driver.get("https://mikerodham.github.io/vue-dropdowns/");

        selectItemDropdown("div.btn-group", "ul.dropdown-menu a", "Second Option" );
        Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Second Option");

        // Third Option
        selectItemDropdown("div.btn-group", "ul.dropdown-menu a", "Third Option" );
        Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Third Option");

    }

    @AfterClass
    public void cleanBrowser(){
        driver.quit();

    }

}
