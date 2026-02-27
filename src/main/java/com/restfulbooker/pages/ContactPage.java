package com.restfulbooker.pages;

import com.restfulbooker.constants.AppConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactPage extends BasePage {

    private static final By NAME_INPUT = By.cssSelector("input#name");
    private static final By EMAIL_INPUT = By.cssSelector("input#email");
    private static final By PHONE_INPUT = By.cssSelector("input#phone");
    private static final By SUBJECT_INPUT = By.cssSelector("input#subject");
    private static final By DESCRIPTION_TEXTAREA = By.cssSelector("textarea#description");
    private static final By SUBMIT_BUTTON = By.xpath("//button[text()='Submit']");
    private static final By SUCCESS_MESSAGE = By.cssSelector(".alert-success, h2");
    private static final By ERROR_MESSAGES = By.cssSelector(".alert-danger, .alert-danger p, .text-danger");
    private static final By CONTACT_FORM = By.cssSelector("form, input#name");

    public ContactPage(WebDriver driver) {
        super(driver);
    }

    public ContactPage open() {
        driver.get(AppConstants.BASE_URL);
        try {
            waitForVisible(NAME_INPUT);
            scrollToElement(NAME_INPUT);
        } catch (Exception e) {
            // SPA may still be loading, continue
        }
        return this;
    }

    public ContactPage enterName(String name) {
        type(NAME_INPUT, name);
        return this;
    }

    public ContactPage enterEmail(String email) {
        type(EMAIL_INPUT, email);
        return this;
    }

    public ContactPage enterPhone(String phone) {
        type(PHONE_INPUT, phone);
        return this;
    }

    public ContactPage enterSubject(String subject) {
        type(SUBJECT_INPUT, subject);
        return this;
    }

    public ContactPage enterDescription(String description) {
        type(DESCRIPTION_TEXTAREA, description);
        return this;
    }

    public ContactPage fillContactForm(String name, String email, String phone,
                                       String subject, String description) {
        enterName(name);
        enterEmail(email);
        enterPhone(phone);
        enterSubject(subject);
        enterDescription(description);
        return this;
    }

    public void clickSubmit() {
        try {
            scrollToElement(SUBMIT_BUTTON);
            Thread.sleep(500);
        } catch (Exception ignored) {}
        try {
            click(SUBMIT_BUTTON);
        } catch (org.openqa.selenium.ElementClickInterceptedException e) {
            // Use JS click as fallback when element is intercepted
            org.openqa.selenium.WebElement btn = driver.findElement(SUBMIT_BUTTON);
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
        }
    }

    public String getSuccessMessage() {
        return getText(SUCCESS_MESSAGE);
    }

    public boolean isSuccessMessageDisplayed() {
        try {
            // After successful form submission, wait for success feedback
            wait.until(org.openqa.selenium.support.ui.ExpectedConditions.or(
                    org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated(SUCCESS_MESSAGE),
                    org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated(
                            By.xpath("//*[contains(text(),'Thanks') or contains(text(),'thank') or contains(text(),'success')]"))
            ));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public int getErrorCount() {
        return getElementCount(ERROR_MESSAGES);
    }

    public boolean areErrorsDisplayed() {
        return getErrorCount() > 0;
    }

    public boolean isContactFormDisplayed() {
        try {
            waitForVisible(NAME_INPUT);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSubmitButtonDisplayed() {
        return isElementDisplayed(SUBMIT_BUTTON);
    }
}
