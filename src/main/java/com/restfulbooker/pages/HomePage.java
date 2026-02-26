package com.restfulbooker.pages;

import com.restfulbooker.constants.AppConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    private static final By LOGO = By.cssSelector(".navbar-brand");
    private static final By ROOMS_LIST = By.cssSelector(".hotel-room-info");
    private static final By ROOM_IMAGES = By.cssSelector(".hotel-room-info img");
    private static final By ROOM_HEADERS = By.cssSelector(".hotel-room-info h3");
    private static final By ROOM_DESCRIPTIONS = By.cssSelector(".hotel-room-info p");
    private static final By BOOK_BUTTONS = By.cssSelector("button.btn-outline-primary.float-right.openBooking");
    private static final By MAP_ELEMENT = By.cssSelector(".map");
    private static final By HOTEL_DESCRIPTION = By.cssSelector(".hotel-description");
    private static final By HOTEL_LOGO = By.cssSelector("img.hotel-logoUrl");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage open() {
        driver.get(AppConstants.BASE_URL);
        waitForVisible(LOGO);
        return this;
    }

    public boolean isLogoDisplayed() {
        return isElementDisplayed(LOGO);
    }

    public int getRoomCount() {
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
        scrollToElement(MAP_ELEMENT);
        return isElementDisplayed(MAP_ELEMENT);
    }

    public boolean isHotelDescriptionDisplayed() {
        return isElementDisplayed(HOTEL_DESCRIPTION);
    }

    public boolean isHotelLogoDisplayed() {
        return isElementDisplayed(HOTEL_LOGO);
    }
}
