package com.restfulbooker.tests;

import com.restfulbooker.constants.AppConstants;
import com.restfulbooker.pages.AdminPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AdminLoginTest extends BaseTest {

    private AdminPage adminPage;

    @BeforeMethod(alwaysRun = true)
    public void initPage() {
        adminPage = new AdminPage(driver);
        adminPage.openAdminPanel();
    }

    @Test(groups = {"smoke", "regression"}, description = "Verify admin login form is displayed")
    public void testLoginFormDisplayed() {
        Assert.assertTrue(adminPage.isLoginFormDisplayed(),
                "Login form should be displayed on admin page");
    }

    @Test(groups = {"smoke", "regression"}, description = "Login with valid admin credentials")
    public void testLoginWithValidCredentials() {
        adminPage.loginAsAdmin();

        Assert.assertTrue(adminPage.isLoggedIn(),
                "User should be logged in after entering valid credentials");
    }

    @Test(groups = {"regression"}, description = "Login with invalid credentials fails")
    public void testLoginWithInvalidCredentials() {
        adminPage.login("invaliduser", "invalidpass");

        Assert.assertFalse(adminPage.isLoggedIn(),
                "User should not be logged in with invalid credentials");
    }

    @Test(groups = {"regression"}, description = "Login with empty credentials fails")
    public void testLoginWithEmptyCredentials() {
        adminPage.clickLogin();

        Assert.assertFalse(adminPage.isLoggedIn(),
                "User should not be logged in with empty credentials");
    }

    @Test(groups = {"smoke", "regression"}, description = "Logout after successful login")
    public void testLogout() {
        adminPage.loginAsAdmin();
        Assert.assertTrue(adminPage.isLoggedIn(), "Should be logged in first");

        adminPage.clickLogout();

        Assert.assertTrue(adminPage.isLoginFormDisplayed(),
                "Login form should be displayed after logout");
    }
}
