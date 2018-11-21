package com.indigoSky.app.core;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import com.indigoSky.app.AppiumController;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.android.AndroidDriver;

public class CommonMethods {

	public static AndroidDriver driver;

	public static void initDriver() throws MalformedURLException {
		
		/*DesiredCapabilities caps = DesiredCapabilities.android();
		//caps.setCapability("appiumVersion", "1.7.1");
		caps.setCapability("deviceName","Nexus_6_API_23");
		caps.setCapability("deviceOrientation", "portrait");
		System.out.println("Current Directory "+System.getProperty("user.dir"));
		caps.setCapability("chromedriverExecutable", System.getProperty("user.dir")+"\\src\\test\\resources\\ChromeDriver\\2.20\\chromedriver.exe");
		caps.setCapability("browserName", "");
		caps.setCapability("platformVersion","6.0");
		caps.setCapability("platformName","Android");
		//caps.setCapability("app","C:\\Users\\kumar\\eclipse-workspace\\AppiumMail\\src\\test\\resources\\HUE Mail.apk");
		caps.setCapability("app",System.getProperty("user.dir")+"\\src\\test\\resources\\IndigoSky_QA_3.6.11.apk");
		caps.setCapability("appPackage","com.juniper.android");
		caps.setCapability("appActivity","com.juniper.android.MainActivity");
		
		driver =  new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), caps);*/

		//Thread.sleep(10000);

		AppiumController.LOG.info("Setting Android Driver");

		//AppiumController.startAppiumServer();
		//   AppiumEmulatorController.startEmulator();
		//          AppiumEmulatorController.stoptEmulator();
		try {
			DesiredCapabilities caps = DesiredCapabilities.android();
			caps.setCapability("automationName","appium");
			caps.setCapability("deviceName", PropertyReader.androidDeviceName);
			//caps.setCapability("noReset", true);
			caps.setCapability("recreateChromeDriverSessions", true);
			caps.setCapability("deviceOrientation", PropertyReader.deviceOrientation);
			caps.setCapability("autoAcceptAlerts", true);
			caps.setCapability("chromedriverExecutable", System.getProperty("user.dir")+ PropertyReader.chromedriverExecutable);
		//	caps.setCapability("chromedriverExecutable", chromedriverExecutable);
			caps.setCapability("platformName", PropertyReader.platformName);
			caps.setCapability("app",System.getProperty("user.dir")+"\\src\\test\\resources\\IndigoSky_QA_3.6.11.apk");
		///	caps.setCapability("app",System.getProperty("user.dir")+app);
			caps.setCapability("browserName", "");
			caps.setCapability("appPackage", PropertyReader.appPackage);
			caps.setCapability("appActivity","com.juniper.android.MainActivity");

			driver = new AndroidDriver(new URL("http://"+ PropertyReader.appiumIP+":"+ PropertyReader.appiumPort+"/wd/hub"), caps);
		} catch (MalformedURLException e) {
			AppiumController.LOG.info("Incorrect URL");
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
		//WEBVIEW, WEBVIEW_com.worksap.company.huemail.mobile, NATIVE_APP
		for (Iterator<String> it = contextNames.iterator(); it.hasNext(); ) {
	        String f = it.next();
	        System.out.println("Context :   " +f);
	    }
	    //adb shell am start -n com.worksap.company.huemail.mobile/.MainActivity
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
