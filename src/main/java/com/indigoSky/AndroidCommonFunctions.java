package com.indigoSky;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import lombok.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashSet;
import java.util.Set;
import static com.codeborne.selenide.Selenide.$$;
import static com.indigoSky.PropertyReader.app;
import static com.indigoSky.PropertyReader.appActivity;
import static com.indigoSky.PropertyReader.appPackage;
import static org.testng.Assert.assertTrue;


@Data
public class AndroidCommonFunctions extends CommonMethods{

    Logger LOG = LoggerFactory.getLogger(AndroidCommonFunctions.class);
    public static AndroidDriver androidDriver = (AndroidDriver) WebDriverRunner.getWebDriver();
    private static boolean isSourceCodeError = false;
    public HashSet<String> getAllMobileContext()
    {
        LOG.info("getAllMobileContext:ENTER");
        HashSet<String> contextSet = new HashSet<>();
        androidDriver = (AndroidDriver)WebDriverRunner.getWebDriver();
        Set<String> contexts = androidDriver.getContextHandles();
        for(String context: contexts)
        {
            LOG.info("Contexts "+context);
            contextSet.add(context);

        }
        LOG.info("getAllMobileContext:EXIT");
        return contextSet;
    }

    public boolean waitForContext(String contextName)
    {
        LOG.info("waitForContext:ENTER");
        boolean matchFound = false;
        for(int i=0;i<5;i++)
        {
            if (getAllMobileContext().contains(contextName))
            {
                return true;
            }
            else
            {
                waitForIndexingAfterCreation(TIME_OUT_TWO_MS);
            }
        }
        LOG.info("waitForContext:EXIT");
        return matchFound;
    }
    public void waitForVisibilityOf(WebElement webElement) {
        LOG.info("waitForVisibilityOf:ENTER");
        WebDriverWait wait = new WebDriverWait(androidDriver, 20);
        wait.until(ExpectedConditions.visibilityOf(webElement));
        LOG.info("waitForVisibilityOf:EXIT");
    }

    public boolean isElementVisible(By wifiWarningContinue, int seconds)
    {
        LOG.info("isElementVisible:ENTER");
        boolean isVisible = false;
        WebDriverWait wait;
        for(int i=0;(i<(seconds))&&(!isVisible);i++)
        {
            LOG.info("Retrying visibility for :"+i);
            try{
                wait = new WebDriverWait(androidDriver, 2);
                waitForIndexingAfterCreation(1000);
                wait.until(ExpectedConditions.visibilityOf(androidDriver.findElement(wifiWarningContinue)));
                isVisible = true;
            }catch (Exception e)
            {
                //
            }
        }

        LOG.info("isElementVisible:EXIT");
        return isVisible;
    }



    public void clickElement(SelenideElement elementToClick) {
        LOG.info("clickElement:ENTER");
        elementToClick.shouldBe(Condition.visible);
        elementToClick.click();
        LOG.info("clickElement:EXIT");
    }

    public void enterText(WebElement element, String text)
    {
        LOG.info("enterText:ENTER");
        waitForVisibilityOf(element);
        element.sendKeys(text);
        LOG.info("enterText:EXIT");
    }
    public void enterText(SelenideElement element, String text)
    {
        LOG.info("enterText:ENTER");
        element.shouldBe(Condition.visible);
        element.clear();
        element.sendKeys(text);
        LOG.info("enterText:EXIT");
    }

    public void switchToWebView()
    {
        LOG.info("switchToWebView:ENTER");
        if(androidDriver.getContext().equalsIgnoreCase("WEBVIEW_"+appPackage))
        {
            LOG.info("Context is already active as required, so No need to switch context");
        }

        else {
            LOG.info("Switching the context to Web View "+"WEBVIEW_" + appPackage);
            waitForIndexingAfterCreation(MOBILE_SWITCH_CONTEXT_WAIT);
            androidDriver.context("WEBVIEW_" + appPackage);
        }
        LOG.info("switchToWebView:EXIT");
    }

    public void switchToNativeView()
    {
        LOG.info("switchToWebView:ENTER");
        androidDriver.context("NATIVE_APP");
        LOG.info("switchToWebView:EXIT");
    }
    public void startActivity(String... activityName)
    {
        LOG.info("startActivity:ENTER");
        String currentActivity = androidDriver.currentActivity();
        LOG.info("Current activity is : "+currentActivity);
        if(activityName.length > 0) {
            if(currentActivity.equalsIgnoreCase(activityName[0])) {
                LOG.info("Required activity is already running");
            }
            else
            {
                LOG.info("Required activity is being started ");
                androidDriver.startActivity(new Activity(appPackage, activityName[0]));
                waitForIndexingAfterCreation(5000);
            }
        }
        else {
            if(currentActivity.equalsIgnoreCase(appActivity)) {
                LOG.info("Required activity is already running");
            }
            else
            {
                LOG.info("Required activity is being started ");
                androidDriver.startActivity(new Activity(appPackage, appActivity));
                waitForIndexingAfterCreation(5000);
            }
        }
        currentActivity = androidDriver.currentActivity();
        LOG.info("At end current activity is : "+currentActivity);
        LOG.info("startActivity:EXIT");
    }
    public void waitForMobilePageLoad()
    {
        LOG.info("waitForMobilePageLoad:ENTER");
        isSourceCodeError =false;
        String pageSource = androidDriver.getPageSource();
        String newPageSource = "";
        int maxTry =8;
        int i = 0;
        while( (!pageSource.equalsIgnoreCase(newPageSource)) && (i<=maxTry))
        {
            LOG.info("Retry the Page Source with retry # = "+(i+1));
            pageSource = androidDriver.getPageSource();
            waitForIndexingAfterCreation(5000);
            newPageSource = androidDriver.getPageSource();
            if(newPageSource.contains("Tenant ID"))
            {
                LOG.info("We found an entry for Tenant ID which should go in some time. Waiting for it to go..");
                while(i<maxTry && newPageSource.contains("Tenant ID"))
                {
                    LOG.info("Retry the Page Source with retry # = "+(i+2));
                    pageSource = androidDriver.getPageSource();
                    waitForIndexingAfterCreation(5000);
                    newPageSource = androidDriver.getPageSource();
                    i++;
                }
            }
            i++;

        }
        LOG.info(newPageSource);
        if(i>maxTry)
        {
            isSourceCodeError = true;
            assertTrue(false, "Unable to load Mobile page");
        }

        LOG.info("waitForMobilePageLoad:EXIT");

    }
    public void installAppIfNotInstalled()
    {
        if(androidDriver.isAppInstalled(appPackage))
        {
            LOG.info("App is already installed "+appPackage);
        }
        else
        {
            LOG.info("Installing App");
            androidDriver.installApp(app);
            if(androidDriver.isAppInstalled(appPackage))
                LOG.info("App is successfully installed");
            else
                LOG.info("Some problem in installing app");
        }
    }
    public void resetToAppHome()
    {
        LOG.info("resetToAppHome:ENTER");

        SelenideElement backButton = $$(By.xpath("//div[contains(@class,'mobile-navbar')]//i[contains(@class,'wap-icon-angle-left')]")).filterBy(Condition.visible).get(0);
        SelenideElement cancelButton = $$(By.xpath("//div[contains(@class,'mobile-navbar')]//i[contains(@class,'wap-icon-close')]")).filterBy(Condition.visible).get(0);
        while(backButton.is(Condition.visible) || cancelButton.is(Condition.visible))
        {
            backButton = $$(By.xpath("//div[contains(@class,'mobile-navbar')]//i[contains(@class,'wap-icon-angle-left')]")).filterBy(Condition.visible).get(0);
            cancelButton = $$(By.xpath("//div[contains(@class,'mobile-navbar')]//i[contains(@class,'wap-icon-close')]")).filterBy(Condition.visible).get(0);

            if (backButton.is(Condition.visible))
                clickElement(backButton);
            if (cancelButton.is(Condition.visible))
                clickElement(cancelButton);
        }
        androidDriver.pressKeyCode(AndroidKeyCode.HOME);
        if(isSourceCodeError) {
            LOG.info("As there was problem with loading the source code, resetting the app");
            androidDriver.resetApp();
        }
        LOG.info("resetToAppHome:EXIT");
    }

}