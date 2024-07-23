package TestNg;

import org.example.AIIntegration.OpenAIUtils;
import org.example.CaptchaPage;
import org.example.ScreenshotUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Base64;

public class TestNGAmazon {
    WebDriver driver ;
    @BeforeTest
    public void Launch() {

        String baseUrl = "https://www.amazon.com/";


        System.out.println("Launching Google Chrome browser");
        driver = new ChromeDriver();
        driver.get(baseUrl);
        String testTitle = "Amazon.com";
        String originalTitle = driver.getTitle();

    }

    @Test(priority = 1)
    public void captchaValidation()
    {

        CaptchaPage captchaPage= PageFactory.initElements(driver,CaptchaPage.class);
        try {
            String src=captchaPage.getSrcOfImage();
            captchaPage.GetCaptchaUsingOpenAI(src);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("Starting Test On Chrome Browser");
    }

    @AfterMethod
    public void afterMethod() {
        driver.close();
        System.out.println("Finished Test On Chrome Browser");
    }
}