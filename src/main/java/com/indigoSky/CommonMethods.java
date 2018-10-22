package com.indigoSky;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.android.AndroidDriver;

import static com.indigoSky.AppiumController.LOG;
import static com.indigoSky.PropertyReader.*;
import static com.indigoSky.PropertyReader.appiumPort;

public class CommonMethods {

	public static AndroidDriver driver;

	public static void initDriver() throws MalformedURLException {


		LOG.info("Setting Android Driver");
		try {
			DesiredCapabilities caps = DesiredCapabilities.android();

			caps.setCapability("automationName","appium");
			caps.setCapability("recreateChromeDriverSessions", true);
			caps.setCapability("deviceOrientation", deviceOrientation);
			caps.setCapability("autoAcceptAlerts", true);
			caps.setCapability("chromedriverExecutable", System.getProperty("user.dir")+chromedriverExecutable);
			caps.setCapability("platformName",platformName);
			caps.setCapability("app",app);
			caps.setCapability("browserName", "");
			caps.setCapability("appPackage",appPackage);
			caps.setCapability("appActivity","com.juniper.android.MainActivity");
			if(saucelab_execute==true) {
				//caps.setCapability("testobjectApiKey", saucelab_testobjectApiKey);
				caps.setCapability("deviceName",saucelab_androidDeviceName);
				caps.setCapability("build", System.getenv("JOB_NAME") + "__" + System.getenv("BUILD_NUMBER"));

				driver = new AndroidDriver(new URL(saucelab_url), caps);
				driver.getCapabilities().getCapability("testobject_test_report_url");
				driver.getCapabilities().getCapability("testobject_test_live_view_url");
			}
			else
			{
				caps.setCapability("deviceName",androidDeviceName);
				driver = new AndroidDriver(new URL("http://"+appiumIP+":"+appiumPort+"/wd/hub"), caps);
			}

		} catch (MalformedURLException e) {
			LOG.info("Incorrect URL");
			e.printStackTrace();
			throw new RuntimeException("Unable to setup driver");
		}
	}
	
	public static void wait(int seconds)
	{
		try {
		System.out.println("Waiting for "+seconds +" second(s)..." );
		Thread.sleep(seconds*1000);
		}catch(Exception e) {}
	}
	
	public static void quit()
	{
			
		driver.closeApp();
		
	}
	public static void setContext(String contextName)
	{
			
		Set<String> contextNames = driver.getContextHandles();
		for (Iterator<String> it = contextNames.iterator(); it.hasNext(); ) {
	        String f = it.next();
	        System.out.println("Context :   " +f);
	    }
		driver.context(contextName);
		System.out.println(driver.context(contextName).getCurrentUrl());
		wait(2);

	}

	public static long getRandomNumber()
	{
		Date date= new Date();
		long time = date.getTime();

		return time;
	}
	public static void getSource()
	{
		System.out.println("...................................................................................................");
		System.out.println(driver.getPageSource());
		System.out.println("...................................................................................................");
	}

	public static void wait(int seconds, String mess) {
		try {
			System.out.println("Waiting for "+seconds +" second(s) for "+mess );
			Thread.sleep(seconds*1000);
		}catch(Exception e) {}
	}
}
