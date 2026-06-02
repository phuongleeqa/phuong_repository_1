package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Selenium_Locator {
    //    1 - Setup: OS/Browser/Web/Page/Data
    WebDriver driver;

//    Biến
//    Access Modifier -- Kiểu dữ liệu-- tên biến -- Giá trị của biến
//    private String fullname = "Phuong";
    @BeforeClass
    public void  initialBrowser() throws InterruptedException {
        driver = new FirefoxDriver();

        driver.get("https://demoqa.com/text-box");
        Thread.sleep(3000);
    }

//    2 - Action/Execute: Tuong tac len elements enter verify/....

    @Test
    public void TC_01_Register() throws InterruptedException {
//        Tìm 1 element
//        driver.findElement(By.id("userName")).sendKeys("Test1");
//        Thread.sleep(3000);
//        driver.findElements(By.cssSelector(""));
//        Những action lên element/browser không return( click, select, sendkey)
//        lấy ra elements, or text -- trả về
//        Cách tìm 8 loại locator


    }

    @Test
    public void TC_01_locator_ID(){
        driver.findElement(By.id("currentAddress")).sendKeys("Da Nang");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void TC_02_locator_Class(){
//        Nó ko lấy hết toàn bộ giá trị (nếu có khoảng trắng) nên cần lấy elements nào là duy nhất
        driver.findElement(By.className("form-control")).sendKeys("Tester");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
    @Test
    public void TC_03_locator_link_text() throws InterruptedException {
//        Chỉ làm việc với element dạng link
//        thẻ a và có thuộc tính href
//        lấy hết toàn bộ text
        driver.findElement(By.xpath("//span[text()='Links']")).click();
        Thread.sleep(3000);
        driver.findElement(By.linkText("Home")).click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
//     Không tìm được vi du lay locator cho Partial Link
    @Test
    public void TC_04_TagName(){
        driver.findElement(By.tagName("button"));

     }

    @Test
    public void TC_05_Css(){
        driver.findElement(By.cssSelector("div.card-body h5")).click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

     //    3 - Clean
    @AfterClass
    public void cleanBrowser(){
        driver.quit();

    }

}
