package org.example.taner_yuksel_qa;


import org.testng.annotations.Test;
import pages.*;

public class MainTest extends BasePage {
    HomePage homePage=new HomePage();
    CareerPage careerPage=new CareerPage();
    QAPage qaPage=new QAPage();

    OpenPositionsPage openPositionsPage=new OpenPositionsPage();


    @Test
    public void mainTest() {

        homePage.closeCookie();
        homePage.goToCareerPage();

        careerPage.checkTeams()
                .checkLocation()
                .checkLife();
        qaPage.goToAllJobs();
        delay.Delay(12000);
        openPositionsPage.selectLocation("Istanbul, Turkiye")
                .checkJobListing()
                .checkRoleRedirection();

    }

}
