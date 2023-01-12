package ru.ibs.data;


import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.ibs.base.BaseTests;

import java.util.concurrent.TimeUnit;

public class MainPage extends BaseTests {

    public MainPage(WebDriver driver) {
        PageFactory.initElements(BaseTests.webDriver, this);
        BaseTests.webDriver = driver;
    }
    @FindBy(xpath = "//div[@class=\"logotype\"]")
    private WebElement rgsHeader;
    @FindBy(xpath = "//button[@class=\"btn--text\"]")
    private WebElement cookieCloseButton;
    @FindBy(xpath = "//a[contains(text(), 'Компаниям')]")
    private WebElement companyMenuButton;
    @FindBy(xpath = "//iframe[@id=\"fl-616371\"]")
    private WebElement frame;
    @FindBy(xpath = "//div[@data-fl-track=\"click-close-login\"]")
    private WebElement frameCloseButton;
    @FindBy(xpath = "//span[contains(text(), 'Здоровье')]")
    private WebElement healthMenuButton;
    @FindBy(xpath = "//span[contains(text(), 'Здоровье')]/..")
    private WebElement healthMenuPresence;
    @FindBy(xpath = "//a[contains(text(), 'Добровольное медицинское страхование')]")
    private WebElement voluntaryInsurance;

    public WebElement getRgsHeader() {
        return rgsHeader;
    }

    public WebElement getCookieCloseButton() {
        return cookieCloseButton;
    }

    public WebElement getCompanyMenuButton() {
        return companyMenuButton;
    }

    public WebElement getFrame() {
        return frame;
    }

    public WebElement getFrameCloseButton() {
        return frameCloseButton;
    }

    public WebElement getHealthMenuButton() {
        return healthMenuButton;
    }

    public WebElement getHealthMenuPresence() {
        return healthMenuPresence;
    }

    public WebElement getVoluntaryInsurance() {
        return voluntaryInsurance;
    }

    public void closeCookie() {
        try {
            cookieCloseButton.click();
        } catch (NoSuchElementException ignore) {
        } finally {
            webDriver.switchTo().defaultContent();
        }
    }
    public void clickCompanyButton() {
        companyMenuButton.click();}
    public void closeFrame() {
        try {
            wait.until(ExpectedConditions.visibilityOf(frame));
            webDriver.switchTo().frame(frame);
            webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            actions.click(frameCloseButton).perform();
        } catch (NoSuchElementException ignore) {
        } finally {
            webDriver.switchTo().defaultContent();
        }
    }
    public void clickHealthMenuButton() {
        healthMenuButton.click();}
    public void clickVoluntaryInsuranceButton() {voluntaryInsurance.click();}
}
