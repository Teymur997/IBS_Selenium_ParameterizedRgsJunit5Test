package ru.ibs.tests;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.ibs.base.BaseTests;
import ru.ibs.data.ValueGenerators;

import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class ParameterizedRgsTest extends BaseTests {
    @ParameterizedTest
    @MethodSource("fios")
    @DisplayName("Параметризированный тест с 3-мя разными ФИО")
    public void parameterizedTest(String string) {
        wait.until(ExpectedConditions.visibilityOf(mainPage.getCookieCloseButton()));

        //Проверить, загрузилась ли страница:
        Assertions.assertTrue(mainPage.getRgsHeader().isDisplayed(), "Страница не загружена");

        //Закрыть cookie:
        mainPage.closeCookie();

        //Выбрать Меню: Компаниям:
        mainPage.clickCompanyButton();

        //Закрыть всплывающее окно:
        mainPage.closeFrame();
        Assertions.assertFalse(mainPage.getFrame().isDisplayed(), "Всплывающее окно не закрыто");

        //Проверить, был ли осуществлен переход в меню "Компаниям":
        Assertions.assertTrue(mainPage.getCompanyMenuButton().getAttribute("class").contains("active"), "Выбор раздела \"Компаниям\" не осуществлен");

        //Выбрать Меню: Здоровье:
        mainPage.clickHealthMenuButton();

        //Проверить, был ли осуществлен переход в меню "Здоровье":
        Assertions.assertTrue(mainPage.getHealthMenuPresence().getAttribute("class").contains("active"), "Выбор раздела \"Здоровье\" не осуществлен");

        //Выбрать Меню: Добровольное медицинское страхование:
        mainPage.clickVoluntaryInsuranceButton();



        //Проверить наличие заголовка – Добровольное медицинское страхование:
        Assertions.assertEquals("Добровольное медицинское страхование", voluntaryInsurancePage.getVolInsurHeader().getText(),"Заголовок не совпадает");

        //Нажать на кнопку – Отправить заявку:
        Assertions.assertTrue(voluntaryInsurancePage.getSendReqButton().isDisplayed(), "Кнопка не отображена");
        voluntaryInsurancePage.clickSendRequestButton();

        //Проверить видимость формы для заполнения:
        Assertions.assertAll("Видимость полей ввода",
                () -> Assertions.assertTrue(voluntaryInsurancePage.getUserNameField().isDisplayed(), "Поле ввода имени отсутствует"),
                () -> Assertions.assertTrue(voluntaryInsurancePage.getUserTelField().isDisplayed(), "Поле ввода телефона отсутствует"),
                () -> Assertions.assertTrue(voluntaryInsurancePage.getUserEmailField().isDisplayed(), "Поле ввода адреса почты отсутствует"),
                () -> Assertions.assertTrue(voluntaryInsurancePage.getAddressInputField().isDisplayed(), "Поле ввода адреса отсутствует")
        );

        //Заполнить поля:
        String fio = string;
        voluntaryInsurancePage.fillUserNameField(fio);

        String tel = ValueGenerators.randomPhoneNumberGenerator();
        voluntaryInsurancePage.fillUserTelField(tel);

        String email = "qwertyqwerty";
        voluntaryInsurancePage.fillUserEmailField(email);

        String address = ValueGenerators.randomAdressGenerator();
        voluntaryInsurancePage.fillUserAddressField(address);

        //Проверить, что все поля заполнены введенными значениями
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

        //Поставить галочку в чекбоксе "Я согласен на обработку":
        voluntaryInsurancePage.checkBoxActivation();

        //Нажать "Свяжитесь со мной"
        voluntaryInsurancePage.clickSubmitButton();

        //Проверить, что кнопка "Свяжитесь со мной" неактивна"
        Assertions.assertFalse(voluntaryInsurancePage.getDisabledSubmitButton().isEnabled(), "Кнопка все еще активна");

        //Проверить, что у поля – Эл. почта присутствует сообщение об ошибке – "Введите корректный адрес электронной почты"
        Assertions.assertEquals("Введите корректный адрес электронной почты",
                voluntaryInsurancePage.getEmailErrorMessage().getText(), "Сообщение о неверно введенном адресе почты отсутствует");

        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public static Stream<String> fios() {
        String fio1 = ValueGenerators.randomFioGenerator();
        String fio2 = ValueGenerators.randomFioGenerator();
        String fio3 = ValueGenerators.randomFioGenerator();
        return Stream.of(fio1, fio2, fio3);
    }
}
