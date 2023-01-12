package ru.ibs.base;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.ibs.ConfProperties;
import ru.ibs.data.Driver;
import ru.ibs.data.MainPage;
import ru.ibs.data.VoluntaryInsurancePage;

import java.util.concurrent.TimeUnit;

public class BaseTests  {
    public static WebDriver webDriver;
    public static Actions actions;
    public static JavascriptExecutor executor;
    public static WebDriverWait wait;
    public static MainPage mainPage;
    public static VoluntaryInsurancePage voluntaryInsurancePage;


    @BeforeAll
    static void beforeAll() {
        System.out.println("Запуск тестов");
    }

    @BeforeEach
    public void setUp() {
        try {
            String browserName = "webdriver." + getPropertyCustom("browser") + ".driver";
            webDriver = new Driver(browserName).webDriver;
            System.out.println(browserName);
            System.out.println(getPropertyCustom("browser"));
        } catch (NullPointerException ignore) {
        }


        mainPage = new MainPage(webDriver);
        voluntaryInsurancePage = new VoluntaryInsurancePage(webDriver);
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        webDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        executor = (JavascriptExecutor) webDriver;
        actions = new Actions(webDriver);
        wait = new WebDriverWait(webDriver, 5, 500);
        webDriver.get(ConfProperties.getProperty("mainPage"));

    }

    @AfterEach
    void tearDown() {
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        webDriver.quit();
    }

    @AfterAll
    static void afterAll() {
        System.out.println("Завершение тестов");
    }

    public String getPropertyCustom(String property) {
        String value = System.getProperty(property);
        if (value!=null) {
            return value.toLowerCase();
        } else {
            return value;
        }
    }
}
