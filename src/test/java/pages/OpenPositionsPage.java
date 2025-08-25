package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import static pages.BasePage.captureScreenshot;
import static pages.BasePage.driver;

public class OpenPositionsPage {

    public OpenPositionsPage selectLocation(String location) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement locationDropdown = driver.findElement(By.id("select2-filter-by-location-container"));
        locationDropdown.click();
        WebElement locationOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(text(),'" + location + "')]")));
        locationOption.click();
        delay.Delay(4000);
        return this;
    }

    public OpenPositionsPage checkJobListing(){
        List<WebElement> jobListings = driver.findElements(By.cssSelector("#jobs-list .position-list-item"));

        if(jobListings.isEmpty()){
            captureScreenshot("No Job Listing Found");
            Assert.fail("No Job Listing Found");
        }
        delay.Delay(6000);
         for (WebElement job : jobListings) {

             JavascriptExecutor js = (JavascriptExecutor) driver;
             js.executeScript("arguments[0].scrollIntoView(true);", job);

             // Pozisyonu al
             String positionTitle = job.findElement(By.cssSelector(".position-title")).getText();
             // Departmanı al
             String department = job.findElement(By.cssSelector(".position-department")).getText();
             // Lokasyonu al
             String location = job.findElement(By.cssSelector(".position-location")).getText();


             // Pozisyon isminin "Quality Assurance" içerdiğini kontrol et
            if (!(positionTitle.contains("Quality Assurance")||positionTitle.contains("QA"))) {
                captureScreenshot("Invalid_Position_Title");
                Assert.fail("Position title does not contain 'Quality Assurance': " + positionTitle);
            }

            // Departmanın "Quality Assurance" olup olmadığını kontrol et
            if (!department.equals("Quality Assurance")) {
                captureScreenshot("Invalid_Department");
                Assert.fail("Department is not 'Quality Assurance': " + department);
            }

            // Lokasyonun "Istanbul, Turkey" olup olmadığını kontrol et
            if (!location.equals("Istanbul, Turkiye")) {
                captureScreenshot("Invalid_Location");
                Assert.fail("Location is not 'Istanbul, Turkey': " + location);
            }
        }
        return this;
    }
    public OpenPositionsPage checkRoleRedirection(){
        List<WebElement> viewRoleButtons = driver.findElements(By.cssSelector(".position-list-item a.btn-navy"));
        if(viewRoleButtons.isEmpty()){
            captureScreenshot("No Role Redirection Button Found");
            Assert.fail("No Role Redirection Button Found");
        }
        String mainWindow = driver.getWindowHandle();

        for (WebElement button : viewRoleButtons) {
            Actions action = new Actions(driver);
            action.moveToElement(button).perform();
            button.click();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.numberOfWindowsToBe(2));

            Set<String> windowHandles = driver.getWindowHandles();
            for (String window : windowHandles) {
                if (!window.equals(mainWindow)) {
                    driver.switchTo().window(window);
                    break;
                }
            }

            String currentUrl = driver.getCurrentUrl();
            if (!currentUrl.contains("jobs.lever.co")) {
                captureScreenshot("Incorrect_Redirection");
                Assert.fail("Redirection did not lead to a Lever job application page. Current URL: " + currentUrl);
            }

            // Doğru sayfaya yönlendirildiyse sekmeyi kapat ve ana pencereye dön
            driver.close();
            driver.switchTo().window(mainWindow);
        }
        return this;
    }
}
