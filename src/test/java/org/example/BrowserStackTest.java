package org.example;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.nio.file.Files;
import java.util.Map;
import java.util.List;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.MutableCapabilities;
import org.yaml.snakeyaml.Yaml;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;

public class BrowserStackTest {
    public static void main(String[] args) throws Exception {
        // Load the browserstack.yml file
        Yaml yaml = new Yaml();
        Map<String, Object> config = yaml.load(Files.newInputStream(new File("browserstack.yml").toPath()));

        // Read common capabilities
        Map<String, Object> commonCaps = (Map<String, Object>) config.get("common_caps");

        // Read environments
        List<Map<String, Object>> environments = (List<Map<String, Object>>) config.get("platforms");

        // Run tests in parallel for each environment
        environments.parallelStream().forEach(environment -> {
            try {
                // Set desired capabilities
                MutableCapabilities capabilities = new UiAutomator2Options();
                commonCaps.forEach(capabilities::setCapability);
                environment.forEach(capabilities::setCapability);

                // Setup Appium driver
                String username = ((Map<String, Object>) config.get("run_settings")).get("userName").toString();
                String accessKey = ((Map<String, Object>) config.get("run_settings")).get("accessKey").toString();
                String appiumServerURL = "https://" + username + ":" + accessKey + "@hub.browserstack.com/wd/hub";

                AppiumDriver driver = new AndroidDriver(new URL(appiumServerURL), capabilities);

                // Your test code goes here
                driver.findElement(AppiumBy.id("username")).sendKeys("BSTTEST");
                Thread.sleep(2000);
                System.out.println("test1 executed");

                // Quit the driver
                driver.quit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
