package com.restfulbooker.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.restfulbooker.utils.DriverFactory;
import com.restfulbooker.utils.ExtentReportManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;
import java.util.Base64;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setUp(Method method) {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        ExtentReportManager.createTest(
                method.getName(),
                method.getDeclaringClass().getSimpleName() + " - " + method.getName()
        );
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        ExtentTest test = ExtentReportManager.getTest();

        if (result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, "Test Failed: " + result.getThrowable());
            captureScreenshot(test);
        } else if (result.getStatus() == ITestResult.SKIP) {
            test.log(Status.SKIP, "Test Skipped: " + result.getThrowable());
        } else {
            test.log(Status.PASS, "Test Passed");
        }

        DriverFactory.quitDriver();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDownSuite() {
        ExtentReportManager.flush();
    }

    private void captureScreenshot(ExtentTest test) {
        try {
            WebDriver currentDriver = DriverFactory.getDriver();
            if (currentDriver != null) {
                String base64Screenshot = ((TakesScreenshot) currentDriver)
                        .getScreenshotAs(OutputType.BASE64);
                test.addScreenCaptureFromBase64String(base64Screenshot, "Failure Screenshot");
            }
        } catch (Exception e) {
            test.log(Status.WARNING, "Could not capture screenshot: " + e.getMessage());
        }
    }
}
