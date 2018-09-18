import com.amazon.CommonMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class TestForIndigoSky {
	
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
		CommonMethods.wait(20);
		CommonMethods.setContext("WEBVIEW_com.juniper.android");
		CommonMethods.wait(5);
		//CommonMethods.getSource();
		WebElement userid = CommonMethods.driver.findElement(By.xpath("//input[@placeholder='Email address']"));
		WebElement password = CommonMethods.driver.findElement(By.xpath("//input[@placeholder='Password']"));
		WebElement loginButton = CommonMethods.driver.findElement(By.xpath("//div[text()='Login']"));

		userid.sendKeys("buyer@gmail.com");
		password.sendKeys("password");
		CommonMethods.wait(3);
		loginButton.click();

		WebElement homeTours = CommonMethods.driver.findElement(By.xpath("//div[text()='Home Tours']"));
		System.out.println("Home page Displayed "+homeTours.isDisplayed());

		CommonMethods.wait(3);
		System.out.println("Exit:loginToApp");
	}
	
	@AfterTest
	public void quit() 
	{
		System.out.println("closing the driver");
		CommonMethods.quit();
	}
	

}
