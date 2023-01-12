package ru.ibs.data;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.ibs.ConfProperties;

import java.util.List;
import java.util.Set;

public class Driver implements WebDriver {
    public WebDriver webDriver;
    public String browserName;

    public Driver(String browserName) {
        this.browserName = browserName;
        this.webDriver = createDriver(browserName);
    }
    public WebDriver createDriver(String browserName) {
        switch (browserName) {
            case "webdriver.chrome.driver":
                System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("webdriver.chrome.driver"));
                return new ChromeDriver();
            case "webdriver.gecko.driver":
                System.setProperty("webdriver.gecko.driver", ConfProperties.getProperty("webdriver.gecko.driver"));
                return new FirefoxDriver();
            case "webdriver.edge.driver":
                System.setProperty("webdriver.edge.driver", ConfProperties.getProperty("webdriver.edge.driver"));
                return new EdgeDriver();
            default:
                System.setProperty("webdriver.gecko.driver", ConfProperties.getProperty("webdriver.gecko.driver"));
                return new FirefoxDriver();
        }
    }

    @Override
    public void get(String arg0) {
        this.webDriver.get(arg0);
    }

    @Override
    public String getCurrentUrl() {
        return this.webDriver.getCurrentUrl();
    }

    @Override
    public String getTitle() {
        return this.webDriver.getTitle();
    }

    @Override
    public List<WebElement> findElements(By arg0) {
        return this.webDriver.findElements(arg0);
    }

    @Override
    public WebElement findElement(By arg0) {
        return this.webDriver.findElement(arg0);
    }

    @Override
    public String getPageSource() {
        return this.webDriver.getPageSource();
    }

    @Override
    public void close() {
        this.webDriver.close();
    }

    @Override
    public void quit() {
        this.webDriver.quit();
    }

    @Override
    public Set<String> getWindowHandles() {
        return this.webDriver.getWindowHandles();
    }

    @Override
    public String getWindowHandle() {
        return this.webDriver.getWindowHandle();
    }

    @Override
    public TargetLocator switchTo() {
        return this.webDriver.switchTo();
    }

    @Override
    public Navigation navigate() {
        return this.webDriver.navigate();
    }

    @Override
    public Options manage() {
        return this.webDriver.manage();
    }
}
