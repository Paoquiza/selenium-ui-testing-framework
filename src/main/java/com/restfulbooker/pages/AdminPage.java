package com.restfulbooker.pages;

import com.restfulbooker.constants.AppConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AdminPage extends BasePage {

    // Login elements
    private static final By USERNAME_INPUT = By.cssSelector("input#username");
    private static final By PASSWORD_INPUT = By.cssSelector("input#password");
    private static final By LOGIN_BUTTON = By.cssSelector("button#doLogin");

    // Admin panel elements
    private static final By ROOM_NAME_INPUT = By.cssSelector("#roomName");
    private static final By ROOM_TYPE_SELECT = By.cssSelector("select#type");
    private static final By ROOM_ACCESSIBLE_SELECT = By.cssSelector("select#accessible");
    private static final By ROOM_PRICE_INPUT = By.cssSelector("#roomPrice");
    private static final By CREATE_ROOM_BUTTON = By.cssSelector("#createRoom");
    private static final By ROOM_LISTINGS = By.cssSelector("[data-testid='roomlisting']");
    private static final By ROOM_DELETE_BUTTON = By.cssSelector(".roomDelete");
    private static final By LOGOUT_LINK = By.linkText("Logout");
    private static final By FRONT_PAGE_LINK = By.linkText("Front Page");

    // Notification / Banner
    private static final By NOTIFICATION_BADGE = By.cssSelector(".notification");

    public AdminPage(WebDriver driver) {
        super(driver);
    }

    public AdminPage openAdminPanel() {
        driver.get(AppConstants.ADMIN_URL);
        return this;
    }

    // Login methods
    public AdminPage enterUsername(String username) {
        type(USERNAME_INPUT, username);
        return this;
    }

    public AdminPage enterPassword(String password) {
        type(PASSWORD_INPUT, password);
        return this;
    }

    public AdminPage clickLogin() {
        click(LOGIN_BUTTON);
        return this;
    }

    public AdminPage login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
        return this;
    }

    public AdminPage loginAsAdmin() {
        return login(AppConstants.ADMIN_USERNAME, AppConstants.ADMIN_PASSWORD);
    }

    public boolean isLoginFormDisplayed() {
        return isElementDisplayed(USERNAME_INPUT)
                && isElementDisplayed(PASSWORD_INPUT)
                && isElementDisplayed(LOGIN_BUTTON);
    }

    public boolean isLoggedIn() {
        try {
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.visibilityOfElementLocated(ROOM_NAME_INPUT),
                    ExpectedConditions.visibilityOfElementLocated(LOGOUT_LINK)
            ));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Room management methods
    public AdminPage enterRoomName(String name) {
        type(ROOM_NAME_INPUT, name);
        return this;
    }

    public AdminPage selectRoomType(String type) {
        selectByVisibleText(ROOM_TYPE_SELECT, type);
        return this;
    }

    public AdminPage selectAccessible(String accessible) {
        selectByVisibleText(ROOM_ACCESSIBLE_SELECT, accessible);
        return this;
    }

    public AdminPage enterRoomPrice(String price) {
        type(ROOM_PRICE_INPUT, price);
        return this;
    }

    public AdminPage clickCreateRoom() {
        click(CREATE_ROOM_BUTTON);
        return this;
    }

    public AdminPage createRoom(String name, String type, String accessible, String price) {
        enterRoomName(name);
        selectRoomType(type);
        selectAccessible(accessible);
        enterRoomPrice(price);
        clickCreateRoom();
        return this;
    }

    public int getRoomCount() {
        return getElementCount(ROOM_LISTINGS);
    }

    public boolean isRoomNameInputDisplayed() {
        return isElementDisplayed(ROOM_NAME_INPUT);
    }

    public void deleteLastRoom() {
        int count = getElementCount(ROOM_DELETE_BUTTON);
        if (count > 0) {
            driver.findElements(ROOM_DELETE_BUTTON).get(count - 1).click();
        }
    }

    public void clickLogout() {
        click(LOGOUT_LINK);
    }

    public boolean isLogoutLinkDisplayed() {
        return isElementDisplayed(LOGOUT_LINK);
    }

    public void clickFrontPage() {
        click(FRONT_PAGE_LINK);
    }
}
