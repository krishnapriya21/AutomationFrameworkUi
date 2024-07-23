package org.example;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

public class ScreenshotUtils {

    public String takeScreenshot(WebDriver driver, String screenshotPath)  {
        byte[] screenshotBytes= new byte[1000];
        try
        {
            // Take a screenshot of the page
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileHandler.copy(screenshot, new File(screenshotPath));

            // Convert the screenshot to Base64
             screenshotBytes= Files.readAllBytes(Paths.get(screenshotPath));

        }
        catch(IOException e)
        {
            System.out.println(e);
        }
        finally {
            return Base64.getEncoder().encodeToString(screenshotBytes);
        }
    }
}
