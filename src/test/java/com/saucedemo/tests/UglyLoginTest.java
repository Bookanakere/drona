package com.saucedemo.tests;

import com.saucedemo.DriverFactory;
import com.saucedemo.pages.Login;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UglyLoginTest {
    private WebDriver driver;
    private Login login;

    @BeforeEach
    public void setup() throws MalformedURLException {

        driver = DriverFactory.getDriver();
        login = new Login(driver);
    }

    @AfterEach
    public void teardown() {
        driver.quit();
    }

    @ParameterizedTest
    @CsvSource({"standard_user,secret_sauce",
            "problem_user,secret_sauce"
    })
    public void assertThatAValidUserCanLogin(String username, String password) {
        login.login(username, password);
        assertEquals("Products", login.getTitle());
    }

    @Test
    public void assertThatALockedOutUserCanNotLogIn() {
        login.login("locked_out_user", "secret_sauce");
        assertEquals("Epic sadface: Sorry, this user has been locked out.", login.getLoginLockOutError());
    }

    @Test
    public void assertThatAPerformanceIssueUserCanLogInButAfterSomeDelays() {
        login.login("performance_glitch_user", "secret_sauce");
        assertEquals("Products", login.getTitle());
    }
}
