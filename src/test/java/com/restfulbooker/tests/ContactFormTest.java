package com.restfulbooker.tests;

import com.restfulbooker.pages.ContactPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ContactFormTest extends BaseTest {

    private ContactPage contactPage;

    @BeforeMethod(alwaysRun = true)
    public void initPage() {
        contactPage = new ContactPage(driver);
        contactPage.open();
    }

    @Test(groups = {"smoke", "regression"}, description = "Verify contact form is displayed")
    public void testContactFormDisplayed() {
        Assert.assertTrue(contactPage.isContactFormDisplayed(),
                "Contact form should be displayed");
    }

    @Test(groups = {"smoke", "regression"}, description = "Verify submit button is displayed")
    public void testSubmitButtonDisplayed() {
        Assert.assertTrue(contactPage.isSubmitButtonDisplayed(),
                "Submit button should be displayed");
    }

    @Test(groups = {"smoke", "regression"}, description = "Submit contact form with valid data")
    public void testSubmitContactFormSuccess() {
        contactPage.fillContactForm(
                "John Doe",
                "john@example.com",
                "12345678901",
                "Test Subject Here",
                "This is a test message with enough characters to pass validation requirements."
        );
        contactPage.clickSubmit();

        Assert.assertTrue(contactPage.isSuccessMessageDisplayed(),
                "Success message should be displayed after valid submission");
    }

    @Test(groups = {"regression"}, description = "Submit contact form with empty fields shows errors")
    public void testSubmitEmptyFormShowsErrors() {
        contactPage.clickSubmit();

        Assert.assertTrue(contactPage.areErrorsDisplayed(),
                "Error messages should be displayed for empty form submission");
    }

    @Test(groups = {"regression"}, description = "Submit contact form with invalid email")
    public void testSubmitWithInvalidEmail() {
        contactPage.fillContactForm(
                "John Doe",
                "invalid-email",
                "12345678901",
                "Test Subject Here",
                "This is a test message with enough characters to pass validation requirements."
        );
        contactPage.clickSubmit();

        Assert.assertTrue(contactPage.areErrorsDisplayed(),
                "Error should be displayed for invalid email");
    }

    @Test(groups = {"regression"}, description = "Submit contact form with short phone number")
    public void testSubmitWithShortPhone() {
        contactPage.fillContactForm(
                "John Doe",
                "john@example.com",
                "123",
                "Test Subject Here",
                "This is a test message with enough characters to pass validation requirements."
        );
        contactPage.clickSubmit();

        Assert.assertTrue(contactPage.areErrorsDisplayed(),
                "Error should be displayed for short phone number");
    }
}
