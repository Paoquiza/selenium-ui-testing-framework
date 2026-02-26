package com.restfulbooker.components;

import com.restfulbooker.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class HeaderComponent extends BasePage {

    private static final By BRAND_LOGO = By.cssSelector(".navbar-brand");
    private static final By NAV_LINKS = By.cssSelector(".navbar-nav a.nav-link");

    public HeaderComponent(WebDriver driver) {
        super(driver);
    }

    public boolean isBrandLogoDisplayed() {
        return isElementDisplayed(BRAND_LOGO);
    }

    public String getBrandText() {
        return getText(BRAND_LOGO);
    }

    public void clickBrandLogo() {
        click(BRAND_LOGO);
    }

    public List<String> getNavLinkTexts() {
        return driver.findElements(NAV_LINKS).stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public int getNavLinkCount() {
        return getElementCount(NAV_LINKS);
    }

    public void clickNavLink(String linkText) {
        List<WebElement> links = driver.findElements(NAV_LINKS);
        for (WebElement link : links) {
            if (link.getText().equalsIgnoreCase(linkText)) {
                link.click();
                return;
            }
        }
        throw new RuntimeException("Nav link not found: " + linkText);
    }
}
