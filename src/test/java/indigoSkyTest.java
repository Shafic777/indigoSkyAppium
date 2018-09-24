import Pages.LoginPage;
import com.indigoSky.CommonMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Description;

import java.net.MalformedURLException;


import static com.indigoSky.CommonMethods.getRandomNumber;
import static com.indigoSky.CommonMethods.initDriver;

public class indigoSkyTest {

	LoginPage loginPage=new LoginPage();
	
	@BeforeMethod
	public void setup() throws MalformedURLException
	{
		System.out.println("Setting up driver");

		//initDriver();

	}
	
	
	@Test
	@Description("Verify Valid login flow with default user")
	public void loginToApp()
	{

		System.out.println("Enter:loginToApp");
		loginPage.validLogin();
		loginPage.assertHomePage();
		System.out.println("Exit:loginToApp");
	}




	@Test
	@Description("Verify Valid SignUp flow as buyer")
	public void signUpToApp()
	{
		long number=getRandomNumber();
		System.out.println("Enter:SingUP");
		loginPage.validSignUp(number);
		loginPage.assertHomePage();
		System.out.println("Exit:SignUp Flow");
	}


	@AfterTest
	public void quit() 
	{
		System.out.println("closing the driver");
		CommonMethods.quit();
	}
	

}
