package ru.ibs.data;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.ibs.base.BaseTests;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class VoluntaryInsurancePage extends BaseTests {
    public VoluntaryInsurancePage(WebDriver driver) {
        PageFactory.initElements(BaseTests.webDriver, this);
        BaseTests.webDriver = driver;
    }
    @FindBy(xpath = "//h1[contains(text(), 'Добровольное медицинское страхование')]")
    private WebElement volInsurHeader;

//    //button[@class=\"action-item btn--basic\"]
    @FindBy(xpath = "//a[@class=\"action-item btn--basic\"]")
    private WebElement sendReqButton;
    @FindBy(xpath = "//input[@name=\"userName\"]")
    private WebElement userNameField;
    @FindBy(xpath = "//input[@name=\"userTel\"]")
    private WebElement userTelField;
    @FindBy(xpath = "//input[@name=\"userEmail\"]")
    private WebElement userEmailField;
    @FindBy(xpath = "//input[@class=\"vue-dadata__input\"]")
    private WebElement addressInputField;
    @FindBy(xpath = "//div[@class=\"vue-dadata__suggestions\"]")
    private WebElement addressDropDown;
    @FindBy(xpath = "//input[@type=\"checkbox\"]/..")
    private WebElement checkBox;
    @FindBy(xpath = "//button[contains(text(), 'Свяжитесь со мной')]")
    private WebElement submitButton;
    @FindBy(xpath = "//span[contains(text(), 'Введите корректный адрес электронной почты')]")
    private WebElement emailErrorMessage;
    @FindBy(xpath = "//button[@class=\"form__button-submit btn--basic btn--disabled\"]")
    private WebElement disabledSubmitButton;


    public WebElement getVolInsurHeader() {
        return volInsurHeader;
    }

    public WebElement getSendReqButton() {
        return sendReqButton;
    }

    public WebElement getUserNameField() {
        return userNameField;
    }

    public WebElement getUserTelField() {
        return userTelField;
    }

    public WebElement getUserEmailField() {
        return userEmailField;
    }

    public WebElement getAddressInputField() {
        return addressInputField;
    }

    public WebElement getCheckBox() {
        return checkBox;
    }

    public WebElement getSubmitButton() {
        return submitButton;
    }

    public WebElement getEmailErrorMessage() {
        return emailErrorMessage;
    }

    public WebElement getDisabledSubmitButton() {
        return disabledSubmitButton;
    }

    public WebElement getAddressDropDown() {
        return addressDropDown;
    }

    public void clickSendRequestButton() {
        actions.click(sendReqButton).perform();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    public void fillUserNameField(String fio) {
        wait.until(ExpectedConditions.visibilityOf(userNameField));
        userNameField.sendKeys(fio);
    }
    public void fillUserTelField(String tel) {
        wait.until(ExpectedConditions.visibilityOf(userTelField));
        userTelField.sendKeys(tel);
    }
    public void fillUserEmailField(String email) {
        wait.until(ExpectedConditions.visibilityOf(userTelField));
        userEmailField.sendKeys(email);
    }
    public void fillUserAddressField(String address) {
        wait.until(ExpectedConditions.visibilityOf(addressInputField));
        addressInputField.sendKeys(address);
        webDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        List<WebElement> dropDownList = webDriver.findElements(By.xpath("//span[@class='vue-dadata__suggestions-item']"));
        dropDownList.get(0).submit();
//        actions.click(dropDownList.get(0)).perform();

    }
    public void checkBoxActivation() {
        executor.executeScript("arguments[0].scrollIntoView(true);", checkBox);
        wait.until(ExpectedConditions.visibilityOf(checkBox));
        executor.executeScript("arguments[0].click();", checkBox);
    }
    public void clickSubmitButton() {
        submitButton.submit();
    }
}
