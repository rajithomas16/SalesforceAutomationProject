package utils;

import base.DriverManager;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.ByteArrayInputStream;

import java.io.ByteArrayInputStream;

public class ReportUtils {
    /**
     * Use @Step to make this message appear as a formal step in Allure
     */
@Step("{0}")
public static void logStep(String message) {
    System.out.println("LOG: " + message);
}

    /**
     * Captures a screenshot using the ThreadLocal driver
     * and attaches it directly to the Allure report.
     */
    public static void takeScreenshot(String attachmentName) {
        try {
            byte[] screenshot = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment(attachmentName, new ByteArrayInputStream(screenshot));
        } catch (Exception e) {
            System.err.println("Failed to capture screenshot: "+e.getMessage());
        }
    }
}
