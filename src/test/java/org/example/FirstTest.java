package org.example;

import io.appium.java_client.AppiumBy;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class FirstTest
{

    @Test
    public void test1() throws InterruptedException, MalformedURLException {
        new BaseTest().getDriverInstance();
        BaseTest.driver.get().findElement(AppiumBy.accessibilityId("username")).sendKeys("BSTTEST");
        Thread.sleep(2000);
        System.out.println("test1 executed");
    }

}
