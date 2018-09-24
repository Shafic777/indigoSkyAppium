package com.indigoSky;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static com.indigoSky.PropertyReader.*;
import java.net.MalformedURLException;
import java.net.URL;

public class SetDriver {

    public static class SetAndroidDriver {
        Logger LOG = LoggerFactory.getLogger(SetAndroidDriver.class);

        public AndroidDriver createDriver(DesiredCapabilities desiredCapabilities) {
            LOG.info("Setting Android Driver");
            AppiumController.startAppiumServer();
            //   AppiumEmulatorController.startEmulator();
            //          AppiumEmulatorController.stoptEmulator();
            try {
                DesiredCapabilities caps = DesiredCapabilities.android();
                caps.setCapability("automationName", "appium");
                caps.setCapability("deviceName", androidDeviceName);
                //caps.setCapability("noReset", true);
                caps.setCapability("recreateChromeDriverSessions", true);
                caps.setCapability("deviceOrientation", deviceOrientation);
                caps.setCapability("autoAcceptAlerts", true);
                caps.setCapability("chromedriverExecutable", chromedriverExecutable);
                caps.setCapability("platformName", platformName);
                caps.setCapability("app", app);
                caps.setCapability("appPackage", appPackage);

                return new AndroidDriver(new URL("http://" + appiumIP + ":" + appiumPort + "/wd/hub"), caps);
            } catch (MalformedURLException e) {
                LOG.info("Incorrect URL");
                e.printStackTrace();
                throw new RuntimeException("Unable to setup driver");
            }
        }
    }
}
