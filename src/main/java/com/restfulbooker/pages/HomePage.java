package com.restfulbooker.pages;

import com.restfulbooker.constants.AppConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class HomePage extends BasePage {

    private static final By LOGO = By.cssSelector(".navbar-brand");
    private static final By ROOM_CARDS = By.cssSelector(".card");
    private static final By ROOM_IMAGES = By.cssSelector("img.card-img-top");
    private static final By ROOM_HEADERS = By.cssSelector(".card-title, .card h5, .card h4");
    private static final By ROOM_DESCRIPTIONS = By.cssSelector(".card-text, .card p");
    private static final By BOOK_BUTTONS = By.cssSelector("a.btn-primary[href*='book'], a.btn-primary");
    private static final By MAP_LINKS = By.linkText("OpenStreetMap");
    private static final By HOTEL_DESCRIPTION = By.cssSelector("section p, .container p");
    private static final By BRANDING_IMAGE = By.cssSelector("img.card-img-top");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage open() {
        driver.get(AppConstants.BASE_URL);
        try {
            waitForVisible(LOGO);
            // Wait for SPA content to render
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.presenceOfElementLocated(By.cssSelector(".card")),
                    ExpectedConditions.presenceOfElementLocated(By.cssSelector("input#name"))
            ));
        } catch (Exception e) {
            // SPA may still be loading async content
        }
        return this;
    }

    public boolean isLogoDisplayed() {
        return isElementDisplayed(LOGO);
    }

    public int getRoomCount() {
        try {
            waitForVisible(ROOM_CARDS);
        } catch (Exception ignored) {}
        return getElementCount(ROOM_CARDS);
    }

    public boolean areRoomsDisplayed() {
        return getRoomCount() > 0;
    }

    public boolean areRoomImagesDisplayed() {
        return getElementCount(ROOM_IMAGES) > 0;
    }

    public String getRoomHeaderText(int index) {
        return driver.findElements(ROOM_HEADERS).get(index).getText();
    }

    public boolean isBookButtonDisplayed() {
        return getElementCount(BOOK_BUTTONS) > 0;
    }

    public boolean isMapDisplayed() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(MAP_LINKS));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isHotelDescriptionDisplayed() {
        try {
            waitForVisible(HOTEL_DESCRIPTION);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isHotelLogoDisplayed() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(BRANDING_IMAGE));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
