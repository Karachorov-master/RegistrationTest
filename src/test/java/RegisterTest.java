import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

import org.testng.Assert;
import org.testng.annotations.Test;

public class RegisterTest {

    @Test
    public void succesfulRegistration(){

        WebDriver driver = new ChromeDriver();

        driver.get("https://auto.pragmatic.bg/");
        driver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[normalize-space()='My Account']/parent::a")
        )).click();

        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[normalize-space()='Register']")
        )).click();

        driver.findElement(By.id("input-firstname")).sendKeys("Petar");
        driver.findElement(By.id("input-lastname")).sendKeys("Karachorov");
        String email = "petar" + System.currentTimeMillis() + "@gmail.com";

        driver.findElement(By.id("input-email")).sendKeys(email);
        driver.findElement(By.id("input-password")).sendKeys("Test12345!");
        driver.findElement(By.name("agree")).click();
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        String successMessage = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//h1[text()='Your Account Has Been Created!']")
                )
        ).getText();

        Assert.assertEquals(successMessage, "Your Account Has Been Created!");
    }

}
