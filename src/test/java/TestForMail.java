import java.net.MalformedURLException;

import org.openqa.selenium.*;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.By;
import org.testng.annotations.AfterTest;
import org.openqa.selenium.WebElement;

import com.amazon.CommonMethods;
import com.amazon.CommonMethods.*;

public class TestForMail {
	
	@BeforeTest
	public void setup() throws MalformedURLException
	{
		System.out.println("Setting up driver");
		try {
			CommonMethods.initDriver();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void loginToApp() throws MalformedURLException
	{
		System.out.println("Enter:loginToApp");
		WebElement acceptLicenseButton =  CommonMethods.driver.findElement(By.id("com.worksap.company.huemail.mobile:id/button_license_accept"));
		System.out.println(acceptLicenseButton.isDisplayed());
		acceptLicenseButton.click();
		CommonMethods.wait(10);
		CommonMethods.setContext("WEBVIEW_com.worksap.company.huemail.mobile");
		WebElement userIDTextBox =  CommonMethods.driver.findElement(By.id("userId"));
		WebElement passwordTextBox =  CommonMethods.driver.findElement(By.id("password"));
		WebElement loginButton =  CommonMethods.driver.findElement(By.id("login-btn"));
		
		
	
		
//		SelenideElement se = $(By.id("com.worksap.company.huemail.mobile:id/button_license_accept"));
//		se.shouldBe(Condition.visible);
		CommonMethods.wait(3);
		userIDTextBox.isEnabled();
		System.out.println(userIDTextBox.isDisplayed());
		userIDTextBox.sendKeys("k");
		CommonMethods.wait(3);
		//passwordTextBox.sendKeys("Welcome12#");
		loginButton.click();
		//assert true;
		System.out.println("Exit:loginToApp");
	}
	
	@AfterTest
	public void quit() 
	{
		System.out.println("closing the driver");
		CommonMethods.quit();
	}
	

}
