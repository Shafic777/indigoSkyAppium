package com.amazon;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.android.AndroidDriver;

public class CommonMethods {

	public static AndroidDriver driver;
	public static void initDriver() throws MalformedURLException, InterruptedException
	{
		
		DesiredCapabilities caps = DesiredCapabilities.android();
		//caps.setCapability("appiumVersion", "1.7.1");
		caps.setCapability("deviceName","Nexis 6 Emulator");
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
		
		driver =  new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), caps);
		
		//Thread.sleep(10000);
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

	public static void getSource()
	{
		System.out.println("...................................................................................................");
		System.out.println(driver.getPageSource());
		System.out.println("...................................................................................................");
	}
	
}
