package com.restfulbooker.tests;

import com.restfulbooker.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HomePageTest extends BaseTest {

    private HomePage homePage;

    @BeforeMethod(alwaysRun = true)
    public void initPage() {
        homePage = new HomePage(driver);
        homePage.open();
    }

    @Test(groups = {"smoke", "regression"}, description = "Verify home page loads successfully")
    public void testHomePageLoads() {
        Assert.assertTrue(homePage.isLogoDisplayed(), "Logo should be displayed on home page");
    }

    @Test(groups = {"regression"}, description = "Verify hotel logo is displayed")
    public void testHotelLogoDisplayed() {
        Assert.assertTrue(homePage.isHotelLogoDisplayed(), "Hotel logo should be displayed");
    }

    @Test(groups = {"regression"}, description = "Verify rooms are displayed on home page")
    public void testRoomsAreDisplayed() {
        Assert.assertTrue(homePage.areRoomsDisplayed(), "At least one room should be displayed");
    }

    @Test(groups = {"regression"}, description = "Verify room images are displayed")
    public void testRoomImagesDisplayed() {
        Assert.assertTrue(homePage.areRoomImagesDisplayed(), "Room images should be displayed");
    }

    @Test(groups = {"regression"}, description = "Verify hotel description is displayed")
    public void testHotelDescriptionDisplayed() {
        Assert.assertTrue(homePage.isHotelDescriptionDisplayed(),
                "Hotel description should be displayed");
    }

    @Test(groups = {"smoke", "regression"}, description = "Verify map is displayed on home page")
    public void testMapIsDisplayed() {
        Assert.assertTrue(homePage.isMapDisplayed(), "Map should be displayed on home page");
    }
}
