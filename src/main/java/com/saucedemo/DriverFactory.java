package com.saucedemo;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static io.github.bonigarcia.wdm.DriverManagerType.CHROME;
import static io.github.bonigarcia.wdm.DriverManagerType.FIREFOX;

public class DriverFactory {

    private static String driverChoice = TestConfig.config.getString("driver");
    private static final String browser = TestConfig.config.getString("browser");

    public static WebDriver getDriver() throws MalformedURLException {
        WebDriver driver = null;
        DesiredCapabilities capability = null;

        switch (driverChoice) {
            case "local":
                switch (browser) {

                    case "chrome":
                        WebDriverManager.getInstance(CHROME).setup();
                        ChromeOptions options = new ChromeOptions();
                        //options.addArguments("--no-sandbox", "--disable-dev-shm-usage", "--headless");
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
                break;

            case "remote":

                switch (browser) {


                    case "chrome":
                        capability = DesiredCapabilities.chrome();
                        driver = new RemoteWebDriver(new URL("http://192.168.29.148:4444/wd/hub"), capability);
                        http://192.168.29.148:4444/grid/console
                        break;

                    case "firefox":
                        capability = DesiredCapabilities.firefox();
                        driver = new RemoteWebDriver(new URL("http://192.168.29.148:4444/wd/hub"), capability);
                        break;

                    default:
                        capability = DesiredCapabilities.chrome();
                        driver = new RemoteWebDriver(new URL("http://192.168.29.148:4444/wd/hub"), capability);
                }

        }
        return driver;

    }
}


