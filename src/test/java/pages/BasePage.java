package pages;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;


import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class BasePage extends data{

    public static WebDriver driver;


    @BeforeTest
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    @BeforeClass
    public void goToBasePage() {
        driver.get(baseUrl);
        if(!(driver.getCurrentUrl().equals(baseUrl))) {
            captureScreenshot("HomePageNotOpened");
            throw new RuntimeException("Main Page Not Opened");
        }
    }

    public static void captureScreenshot(String fileName) {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            File destDir = new File("screenshots");
            if (!destDir.exists()) {
                destDir.mkdir();
            }
            File destFile = new File(destDir, fileName + ".png");
            FileUtils.copyFile(scrFile, destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            captureScreenshot("Fail");
        }
        driver.quit();
    }
}
