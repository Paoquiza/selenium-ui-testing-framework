package com.restfulbooker.pages;

import com.restfulbooker.constants.AppConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class HomePage extends BasePage {

    private static final By LOGO = By.cssSelector(".navbar-brand");
    private static final By ROOMS_LIST = By.cssSelector(".row.hotel-room-info");
    private static final By ROOM_IMAGES = By.cssSelector(".row.hotel-room-info img");
    private static final By ROOM_HEADERS = By.cssSelector(".row.hotel-room-info h3");
    private static final By ROOM_DESCRIPTIONS = By.cssSelector(".row.hotel-room-info p");
    private static final By BOOK_BUTTONS = By.cssSelector("button.btn-outline-primary.float-right.openBooking");
    private static final By MAP_ELEMENT = By.cssSelector(".pigeon-tiles-box, [class*='map'] canvas, .map img, .map");
    private static final By HOTEL_DESCRIPTION = By.cssSelector(".col-sm-7 p, .hotel-description");
    private static final By HOTEL_LOGO = By.cssSelector("img.hotel-logoUrl, .col-sm-7 img[src*='logo'], .col-sm-7 img");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage open() {
        driver.get(AppConstants.BASE_URL);
        try {
            waitForVisible(LOGO);
            // Wait for SPA content to render
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.presenceOfElementLocated(By.cssSelector(".row.hotel-room-info")),
                    ExpectedConditions.presenceOfElementLocated(By.cssSelector("#root .container-fluid"))
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
            waitForVisible(ROOMS_LIST);
        } catch (Exception ignored) {}
        return getElementCount(ROOMS_LIST);
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
            wait.until(ExpectedConditions.presenceOfElementLocated(MAP_ELEMENT));
            scrollToElement(MAP_ELEMENT);
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
            wait.until(ExpectedConditions.presenceOfElementLocated(HOTEL_LOGO));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
