package com.restfulbooker.tests;

import com.restfulbooker.components.FooterComponent;
import com.restfulbooker.components.HeaderComponent;
import com.restfulbooker.constants.AppConstants;
import com.restfulbooker.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class NavigationTest extends BaseTest {

    private HeaderComponent header;
    private FooterComponent footer;
    private HomePage homePage;

    @BeforeMethod(alwaysRun = true)
    public void initPage() {
        header = new HeaderComponent(driver);
        footer = new FooterComponent(driver);
        homePage = new HomePage(driver);
        homePage.open();
    }

    @Test(groups = {"smoke", "regression"}, description = "Verify brand logo is displayed in header")
    public void testBrandLogoDisplayed() {
        Assert.assertTrue(header.isBrandLogoDisplayed(),
                "Brand logo should be displayed in the header");
    }

    @Test(groups = {"smoke", "regression"}, description = "Verify navigation links are present")
    public void testNavigationLinksPresent() {
        int linkCount = header.getNavLinkCount();
        Assert.assertTrue(linkCount > 0,
                "Navigation links should be present in the header");
    }

    @Test(groups = {"regression"}, description = "Verify clicking brand logo returns to home page")
    public void testBrandLogoNavigatesToHome() {
        driver.get(AppConstants.ADMIN_URL);
        header.clickBrandLogo();

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains(AppConstants.BASE_URL) ||
                        currentUrl.equals(AppConstants.BASE_URL),
                "Clicking brand logo should navigate to home page");
    }

    @Test(groups = {"smoke", "regression"}, description = "Verify footer is displayed")
    public void testFooterDisplayed() {
        Assert.assertTrue(footer.isFooterDisplayed(),
                "Footer should be displayed on the page");
    }

    @Test(groups = {"regression"}, description = "Verify page title is not empty")
    public void testPageTitleNotEmpty() {
        String title = homePage.getPageTitle();
        Assert.assertNotNull(title, "Page title should not be null");
        Assert.assertFalse(title.isEmpty(), "Page title should not be empty");
    }
}
