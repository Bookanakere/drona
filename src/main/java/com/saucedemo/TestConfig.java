package com.saucedemo;
import com.typesafe.config.*;



public class TestConfig {

    private static String platform = System.getProperty("platform");

    public static Config config = ConfigFactory.load(""+platform+".conf")
            .withFallback(ConfigFactory.load());
}