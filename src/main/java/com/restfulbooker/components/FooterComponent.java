package com.restfulbooker.components;

import com.restfulbooker.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FooterComponent extends BasePage {

    private static final By FOOTER = By.cssSelector("footer");
    private static final By FOOTER_TEXT = By.cssSelector(".text-muted");
    private static final By FOOTER_LINKS = By.cssSelector("footer a");

    public FooterComponent(WebDriver driver) {
        super(driver);
    }

    public boolean isFooterDisplayed() {
        scrollToElement(FOOTER);
        return isElementDisplayed(FOOTER);
    }

    public String getFooterText() {
        scrollToElement(FOOTER);
        return getText(FOOTER_TEXT);
    }

    public int getFooterLinkCount() {
        return getElementCount(FOOTER_LINKS);
    }
}
