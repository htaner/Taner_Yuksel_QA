package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import static pages.BasePage.driver;

public class CareerPage {
    public CareerPage checkTeams() {
        try {
            WebElement teams = driver.findElement(By.xpath("//h3[normalize-space()='Find your calling']"));

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", teams);
            delay.Delay(1000);
        } catch (NoSuchElementException e) {

            BasePage.captureScreenshot("Teams not exist");
            Assert.fail("Teams not exist");
        }
        return this;
    }
    public CareerPage checkLocation() {
        try {
            WebElement teams = driver.findElement(By.xpath("//h3[normalize-space()='Our Locations']"));

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", teams);
            delay.Delay(1000);
        } catch (NoSuchElementException e) {

            BasePage.captureScreenshot("Location not exist");
            Assert.fail("Location not exist");
        }
        return this;
    }
    public CareerPage checkLife() {
        try {
            WebElement teams = driver.findElement(By.xpath("//h2[normalize-space()='Life at Insider']"));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", teams);
            delay.Delay(1000);
        } catch (NoSuchElementException e) {
            BasePage.captureScreenshot("Life at Insider not exist");
            Assert.fail("Life at Insider not exist");
        }
        return this;
    }
}
