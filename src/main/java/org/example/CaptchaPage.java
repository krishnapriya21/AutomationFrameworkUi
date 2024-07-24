package org.example;

import org.example.AIIntegration.OpenAIUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.nio.charset.StandardCharsets;


public class CaptchaPage {
    final WebDriver driver;
    String test;
    //Constructor, as every page needs a Webdriver to find elements
    public CaptchaPage(WebDriver driver){
        this.driver=driver;
    }
    @FindBy(id="captchacharacters")
    WebElement CaptchainputTextBox;

    @FindBy(xpath = "//div//img[contains(@src,'captcha')]")
    WebElement captcha;


    public void Getbase64OfScreenshot()
    {
        ScreenshotUtils screenshotUtils=new ScreenshotUtils();
        test=screenshotUtils.takeScreenshot(driver,"src/test/resources/Screenshots/sample.jpg");
        test.getBytes(StandardCharsets.UTF_8);
    }

    public String getSrcOfImage()
    {
        return captcha.getAttribute("src");
    }

    public String GetCaptchaUsingOpenAI(String src)
    {
        OpenAIUtils openAIUtils = new OpenAIUtils();
        String captcha="";
        try {
            captcha=openAIUtils.getCaptchaValueFromOpenAI(src);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return captcha;
    }

}
