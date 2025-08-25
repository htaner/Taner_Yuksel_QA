package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class HomePage extends BasePage {

    public static void goToCareerPage(){
        Actions action = new Actions(driver);
        WebElement company = driver.findElement(By.xpath("//body/nav[@id='navigation']/div[@class='container-fluid']/div[@id='navbarNavDropdown']/ul[@class='navbar-nav']/li[6]/a[1]"));
        action.moveToElement(company).perform();
        WebElement career= driver.findElement(By.xpath("//a[normalize-space()='Careers']"));
        career.click();

    }
    public static void closeCookie() {
        WebElement closeCookieBtn= driver.findElement(By.xpath("//a[@id='wt-cli-accept-btn']"));
        closeCookieBtn.click();
        delay.Delay(2000);
    }
}
