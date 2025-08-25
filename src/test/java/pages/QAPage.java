package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static pages.BasePage.driver;

public class QAPage {

    public QAPage goToAllJobs(){
        driver.get("https://useinsider.com/careers/quality-assurance/");
        WebElement allJobsBTN = driver.findElement(By.xpath("//a[normalize-space()='See all QA jobs']"));
        allJobsBTN.click();
        return this;
    }
}
