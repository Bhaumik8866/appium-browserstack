package org.example;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest
{
//    AppiumDriver driver;
    public static ThreadLocal<AppiumDriver> driver=new ThreadLocal<>();
    @BeforeSuite
    public void getDriverInstance() throws MalformedURLException, InterruptedException {
        String userName="bhaumikvpatel_qJlS2Q";
        String passKey="sfq9pMXbDK5FqFaWh5e4";
        String connectionUrl="https://"+userName+":"+passKey+"@hub.browserstack.com/wd/hub";

//        String newURLTest="http://hub-cloud.browserstack.com/wd/hub";
        MutableCapabilities caps=new UiAutomator2Options();

        driver.set(new AppiumDriver(new URL(connectionUrl),caps));
        System.out.println("Driver instance saved in thread local");

    }
    @AfterSuite
    public void tearDown()
    {
        driver.get().quit();
    }
}
