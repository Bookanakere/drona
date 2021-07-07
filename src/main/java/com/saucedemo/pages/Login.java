package com.saucedemo.pages;

import com.saucedemo.TestConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login {
    private final WebDriver driver;
    private static TestConfig testConfig;
    private static final String LOGIN_URL = TestConfig.config.getString("LOGIN_URL");
    

    @FindBy(name = "user-name")
    private WebElement username;

    @FindBy(name = "password")
    private WebElement password;

    @FindBy(name = "login-button")
    private WebElement login;

    @FindBy(className = "title")
    private WebElement title;

    @FindBy(xpath = "//h3[@data-test='error']")
    private WebElement lockOutError;

    public Login(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private void setUsername(String username) {
        this.username.sendKeys(username);
    }

    private void setPassword(String password) {
        this.password.sendKeys(password);
    }

    private void clickLogin() {
        login.click();
    }

    public String getTitle() {
        return title.getAttribute("innerHTML");
    }

    public String getLoginLockOutError() {
        return lockOutError.getText();
    }

    public void login(String username, String password) {
        driver.get(LOGIN_URL);
        setUsername(username);
        setPassword(password);
        clickLogin();
    }
}

