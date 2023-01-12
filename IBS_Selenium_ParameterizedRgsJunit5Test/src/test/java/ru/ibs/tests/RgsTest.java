package ru.ibs.tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.ibs.base.BaseTests;

import static ru.ibs.data.ValueGenerators.*;


public class RgsTest extends BaseTests {
    @RepeatedTest(2)
    @DisplayName("Простой Тест")
    public void test() {
        Assertions.assertTrue(mainPage.getRgsHeader().isDisplayed(), "Страница не загружена");

        wait.until(ExpectedConditions.visibilityOf(mainPage.getCookieCloseButton()));
        mainPage.closeCookie();

        mainPage.clickCompanyButton();

        mainPage.closeFrame();
        Assertions.assertFalse(mainPage.getFrame().isDisplayed(), "Всплывающее окно не закрыто");
        Assertions.assertTrue(mainPage.getCompanyMenuButton().getAttribute("class").contains("active"), "Выбор раздела \"Компаниям\" не осуществлен");

        mainPage.clickHealthMenuButton();
        Assertions.assertTrue(mainPage.getHealthMenuPresence().getAttribute("class").contains("active"), "Выбор раздела \"Здоровье\" не осуществлен");

        mainPage.clickVoluntaryInsuranceButton();

        Assertions.assertEquals("Добровольное медицинское страхование", voluntaryInsurancePage.getVolInsurHeader().getText(),"Заголовок не совпадает");

        Assertions.assertTrue(voluntaryInsurancePage.getSendReqButton().isDisplayed(), "Кнопка не отображена");
        voluntaryInsurancePage.clickSendRequestButton();

        Assertions.assertAll("Видимость полей ввода",
                () -> Assertions.assertTrue(voluntaryInsurancePage.getUserNameField().isDisplayed(), "Поле ввода имени отсутствует"),
                () -> Assertions.assertTrue(voluntaryInsurancePage.getUserTelField().isDisplayed(), "Поле ввода телефона отсутствует"),
                () -> Assertions.assertTrue(voluntaryInsurancePage.getUserEmailField().isDisplayed(), "Поле ввода адреса почты отсутствует"),
                () -> Assertions.assertTrue(voluntaryInsurancePage.getAddressInputField().isDisplayed(), "Поле ввода адреса отсутствует")
        );


        voluntaryInsurancePage.checkBoxActivation();

        String fio = randomFioGenerator();
        voluntaryInsurancePage.fillUserNameField(fio);

        String tel = randomPhoneNumberGenerator();
        voluntaryInsurancePage.fillUserTelField(tel);

        String email = "qwertyqwerty";
        voluntaryInsurancePage.fillUserEmailField(email);

        String address =randomAdressGenerator();
        voluntaryInsurancePage.fillUserAddressField(address);

        Assertions.assertAll(
                () -> Assertions.assertEquals(fio, voluntaryInsurancePage.getUserNameField().getAttribute("value"),
                        "Введенное в поле ФИО не соответствует заданному"),
                () -> Assertions.assertEquals("+7".concat(tel), voluntaryInsurancePage.getUserTelField().getAttribute("value")
                                .replaceAll("[\\p{Punct}&&[^+]]+", "").replaceAll("\\s", ""),
                        "Введенный в поле номер не соответствует заданному"),
                () -> Assertions.assertEquals(email, voluntaryInsurancePage.getUserEmailField().getAttribute("value"),
                        "Введенный в поле адрес почты не соответствует заданному"),
                () -> Assertions.assertEquals(address, voluntaryInsurancePage.getAddressInputField().getAttribute("value"),
                        "Введенный в поле адрес не соответствует заданному")
        );




        voluntaryInsurancePage.clickSubmitButton();

        Assertions.assertFalse(voluntaryInsurancePage.getDisabledSubmitButton().isEnabled(), "Кнопка все еще активна");


        Assertions.assertEquals("Введите корректный адрес электронной почты",
                voluntaryInsurancePage.getEmailErrorMessage().getText(), "Сообщение о неверно введенном адресе почты отсутствует");

    }

}
