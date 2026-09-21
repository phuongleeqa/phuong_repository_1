package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_13_Checkbox_Radio {
    WebDriver driver;

    @BeforeClass
    public void  initialBrowser() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test
    public void TC_01_Checkbox() throws InterruptedException {
        driver.get("https://automationfc.github.io/multiple-fields/");

        By cancerCheckbox = By.xpath("//label[contains(text(), 'Cancer')]/preceding-sibling::input");

        //Chon
        if (!driver.findElement(cancerCheckbox).isSelected());
        driver.findElement(cancerCheckbox).click();

        //Verify chon
        Assert.assertTrue(driver.findElement(cancerCheckbox).isSelected());
        Thread.sleep(3000);

        //Bo chon
        if (!driver.findElement(cancerCheckbox).isSelected());
        driver.findElement(cancerCheckbox).click();

        //Verify bo chon
        Assert.assertFalse(driver.findElement(cancerCheckbox).isSelected());

        //Chon het
        List<WebElement> allCheckboxes = driver.findElements(By.cssSelector("span.form-checkbox-item>input"));

        for (WebElement checkbox: allCheckboxes) {
            if (!checkbox.isSelected()){
                checkbox.click();
            }
        }

        //Verify chon het
        for (WebElement checkbox : allCheckboxes) {
            Assert.assertTrue(checkbox.isSelected());
        }

        Thread.sleep(2000);

        for (WebElement checkbox: allCheckboxes) {
            if (checkbox.isSelected()){
                checkbox.click();
            }
        }

        for (WebElement checkbox : allCheckboxes) {
            Assert.assertFalse(checkbox.isSelected());
        }

        for (WebElement checkbox: allCheckboxes) {
            if (!checkbox.isSelected()){
                checkbox.click();
            }
        }

        //Chon bat ky
        for (WebElement checkbox: allCheckboxes) {
            if (!checkbox.isSelected() && checkbox.getDomAttribute("value").equals("Heart Attack")){
                checkbox.click();
            }
        }

    }

    @Test
    public void TC_02_Radio_Btn(){
        driver.get("https://material.angular.dev/components/checkbox/examples");

        By checkedCheckbox = By.xpath("//mat-checkbox[@id='mat-mdc-checkbox-0']//label[normalize-space()='Checked']");
        By indeterminateCheckbox = By.xpath("//label[.//span[normalize-space()='Indeterminate']]//input[@type='checkbox']");
        By disableCheckbox = By.xpath("//checkbox-configurable-example//section[3]//mat-checkbox/label");
        By resultCheckbox = By.xpath("//mat-checkbox[@id='mat-mdc-checkbox-0']//label[normalize-space()=' I'm a checkbox']\"");

        By afterRadio = By.xpath("//mat-radio-button[.//span[normalize-space()='After']]");
        By beforeRadio = By.xpath("///mat-radio-button[.//span[normalize-space()='Before']]");


        // Verify checkbox deselected
        Assert.assertFalse(driver.findElement(checkedCheckbox).isSelected());
        Assert.assertFalse(driver.findElement(indeterminateCheckbox).isSelected());
        Assert.assertFalse(driver.findElement(disableCheckbox).isSelected());
        Assert.assertFalse(driver.findElement(resultCheckbox).isSelected());
        Assert.assertTrue(driver.findElement(afterRadio).isSelected());

        //Click
        driver.findElement(checkedCheckbox).click();
        driver.findElement(indeterminateCheckbox).click();
        driver.findElement(disableCheckbox).click();

        //Verify checkbox selected
        Assert.assertTrue(driver.findElement(checkedCheckbox).isSelected());
        Assert.assertTrue(driver.findElement(indeterminateCheckbox).isSelected());
        Assert.assertTrue(driver.findElement(disableCheckbox).isSelected());
        Assert.assertTrue(driver.findElement(resultCheckbox).isSelected());
        Assert.assertTrue(driver.findElement(beforeRadio).isSelected());

        //Verify checkbox radio disabled and deselected
        Assert.assertFalse(driver.findElement(resultCheckbox).isEnabled());
        Assert.assertFalse(driver.findElement(afterRadio).isEnabled());

    }

    @AfterClass
    public void cleanBrowser(){
        driver.quit();

    }
}
