package com.saucedemo;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import static io.github.bonigarcia.wdm.DriverManagerType.CHROME;
import static io.github.bonigarcia.wdm.DriverManagerType.FIREFOX;

public class DriverFactory {
    private static WebDriver driver;
    private static TestConfig testConfig;
    private static final String browser = TestConfig.config.getString("browser");

    public static WebDriver getDriver() {
        switch (browser) {
            case "chrome":
                WebDriverManager.getInstance(CHROME).setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--no-sandbox","--disable-dev-shm-usage","--headless");
                driver = new ChromeDriver(options);
                break;

            case "firefox":
                WebDriverManager.getInstance(FIREFOX).setup();
                driver = new FirefoxDriver();
                break;

            default:
                WebDriverManager.getInstance(CHROME).setup();
                driver = new ChromeDriver();
        }
        return driver;
    }
}

