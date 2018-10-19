package com.indigoSky;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;
import com.codeborne.selenide.ex.ElementShould;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.switchTo;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.indigoSky.AppiumController.LOG;
import static com.indigoSky.PropertyReader.*;
import static com.indigoSky.PropertyReader.appiumPort;
import static org.testng.Assert.assertTrue;

public class CommonMethods {

	public static boolean IS_NEW_WINDOW = false;
	public static final int RETRY_COUNT = 5;
	public static final int WAIT_FIFTEEN_SEC = 15;
	public static final int WAIT_TEN_SEC = 10;
	public static final int TIME_OUT_MS = 20000;
	public static final int TIME_OUT_TWO_MS = 2000;
	public static final int ZERO = 0;
	public static final int TWO_SEC = 2;
	public static final int TWENTY_SEC = 20;
	public static final int FIVE_SEC = 5000;
	public static final int TEN_SEC_MILLI = 10000;
	public static final int ONE_SEC_MILLI=1000;
	public static final int FIFTEEN_SEC_MILLI = 15000;
	public static final int MOBILE_SWITCH_CONTEXT_WAIT = 30000;
	public static long tcManualWait = 0;
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
			caps.setCapability("app",System.getProperty("user.dir")+"\\src\\test\\resources\\IndigoSky_QA_3.6.11.apk");
			caps.setCapability("browserName", "");
			caps.setCapability("appPackage",appPackage);
			caps.setCapability("appActivity","com.juniper.android.MainActivity");
			if(saucelab_execute==true) {
				caps.setCapability("testobjectApiKey", saucelab_testobjectApiKey);
				caps.setCapability("deviceName",saucelab_androidDeviceName);
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
	public void clickOnOneElementAndAssertNewElement(SelenideElement webElementToClick, SelenideElement elementToAppear) throws WebDriverException {
		Configuration.timeout = TIME_OUT_TWO_MS;
		int windowsCount = getWebDriver().getWindowHandles().size();
		for (int i = 0; i < RETRY_COUNT; i++) {
			try {
				webElementToClick.shouldBe(visible);
				webElementToClick.click();
				if (windowsCount < getWebDriver().getWindowHandles().size()) {
					switchTo().window(windowsCount);
					IS_NEW_WINDOW = true;
				}
				elementToAppear.shouldBe(visible);
				if (elementToAppear.isDisplayed() && elementToAppear.is(visible)) {
					break;
				} else {
					LOG.info("Retrying to click");
					if (windowsCount < getWebDriver().getWindowHandles().size()) {
						switchToPreviousTab();
						IS_NEW_WINDOW = false;
					}
					continue;
				}
			} catch (WebDriverException | ElementNotFound | ElementShould e) {
				if (windowsCount < getWebDriver().getWindowHandles().size()) {
					switchToPreviousTab();
					IS_NEW_WINDOW = false;
				}
				continue;
			}
		}
		Configuration.timeout = TIME_OUT_MS;
		elementToAppear.shouldBe(visible);
	}

	public void clickOnOneElementAndAssertNewElement(SelenideElement webElementToClick, SelenideElement elementToAppear, String iframe) throws WebDriverException {
		for (int i = 0; i < RETRY_COUNT; i++) {
			try {
				webElementToClick.shouldBe(visible);
				webElementToClick.click();
				Selenide.switchTo().frame(iframe);
				elementToAppear.shouldBe(visible);
				//   waitUntilElementAppears(elementToAppear);
				if (elementToAppear.isDisplayed() && elementToAppear.is(visible)) {
					break;
				} else {
					// LOG.info("Retrying to click");
					continue;
				}
			} catch (WebDriverException | ElementNotFound | ElementShould e) {
				switchTo().parentFrame();
				continue;
			}
		}
		assertTrue(elementToAppear.is(visible), elementToAppear.getText() + " Element is not visible after clicking on web element ");
	}

	public void insertTextInput(SelenideElement webElement, String inputValue) {
		try {
			for (int i = 0; i < 3; i++) {
				webElement.shouldBe(visible);
				webElement.click();
				webElement.clear();
				LOG.info("Inserting value: " + inputValue);
				webElement.setValue(inputValue);
				webElement.pressTab();
				if ((webElement.getValue() != null || webElement.getValue() != "") && webElement.getValue().equalsIgnoreCase(inputValue)) {
					break;
				}
			}
		} catch (Exception | ElementShould e) {
			LOG.info(inputValue + " text is not properly inserted in the inputbox");
		}
	}

	public void insertTextFromSuggestion(SelenideElement webElement, String inputValue) {

		webElement.sendKeys(inputValue);
		SelenideElement suggestion = webElement.parent().$(By.xpath(".//span[contains(@class, 'ac-suggest-text-bold')]"));
		waitForIndexingAfterCreation(FIVE_SEC);
		suggestion.shouldBe(visible).click();
		waitForIndexingAfterCreation(TIME_OUT_TWO_MS);
	}

	public void switchToPreviousTab() {
		int noOftabs = getWebDriver().getWindowHandles().size();
		LOG.info("Number of tabs- "+noOftabs);
		if (noOftabs > 1) {
			switchTo().window(noOftabs - 1).close();
			switchTo().window(getWebDriver().getWindowHandles().size() - 1);
			waitForPageLoad(getWebDriver());
		} else {
			switchTo().window(getWebDriver().getWindowHandles().size() - 1);
		}
	}
	public void waitForIndexingAfterCreation(int milliSeconds) {
		//Using in EC to give time to index before we search for anything like Saleable quantity, description
		LOG.info("waitForIndexingAfterCreation:ENTER:");
		tcManualWait = tcManualWait + milliSeconds;
		try {

			Thread.sleep(milliSeconds);
		} catch (Exception e) {
			LOG.info("waitForIndexingAfterCreation:Some exception occurred");
			e.printStackTrace();
		}
		LOG.info("waitForIndexingAfterCreation:EXIT:");
	}

	public void waitForPageLoad(WebDriver driver) {
		ExpectedCondition<Boolean> pageLoadCondition = new
				ExpectedCondition<Boolean>() {
					public Boolean apply(WebDriver driver) {
						return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
					}
				};
		WebDriverWait wait = new WebDriverWait(driver, WAIT_FIFTEEN_SEC);
		wait.until(pageLoadCondition);
	}


}
