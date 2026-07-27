package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_10_Default_Dropdown {
    WebDriver driver;
    Select select, districtDropdown;

    @BeforeClass
    public void  initialBrowser() {
        driver = new FirefoxDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test
    public void TC_01_(){
        driver.get("https://egov.danang.gov.vn/reg");

        select = new Select(driver.findElement(By.cssSelector("select#thuongtru_tinhthanh")));
        select.selectByVisibleText("thành phố Đà Nẵng");
        int tinhThanhNumbers = select.getOptions().size();
        Assert.assertEquals(tinhThanhNumbers,67);
        Assert.assertFalse(select.isMultiple());
        Assert.assertEquals(select.getFirstSelectedOption().getText(),"thành phố Đà Nẵng");

        districtDropdown = new Select(driver.findElement(By.cssSelector("select#thuongtru_quanhuyen")));
        districtDropdown.selectByVisibleText("quận Hải Châu");
        Assert.assertEquals(districtDropdown.getFirstSelectedOption().getText(),"quận Hải Châu");
    }

    @Test
    public void TC_02_(){
        driver.get("https://rode.com/en-au/support/where-to-buy");

        new  Select(driver.findElement(By.cssSelector("select#country"))).selectByVisibleText("Vietnam");
        driver.findElement(By.cssSelector("input#map_search_query")).sendKeys("HO CHI MINH");
        driver.findElement(By.xpath("//button[text()='Search']")).click();

        List<WebElement> dealers = driver.findElements(By.xpath("//h3[text()='Dealers']/following-sibling::div//h4"));

        for (WebElement dearler : dealers)
            System.out.println(dearler.getText());
    }

    @AfterClass
    public void cleanBrowser(){
        driver.quit();

    }
}
