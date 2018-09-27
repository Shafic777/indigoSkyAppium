import Pages.BasePage;
import Pages.HomePage;
import Pages.LoginPage;
import com.indigoSky.CommonMethods;
import org.graalvm.compiler.replacements.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;
import ru.yandex.qatools.allure.annotations.Description;
import sun.security.jgss.LoginConfigImpl;

import java.net.MalformedURLException;


import static com.indigoSky.CommonMethods.getRandomNumber;
import static com.indigoSky.CommonMethods.initDriver;

public class indigoSkyTest extends BasePage {

	LoginPage loginPage=new LoginPage();
	HomePage homePage=new HomePage();
/*	String uname="buyer@gmail.com";
	String pass="password";*/
	String uname1="caestledemo.agent@gmail.com";
    String pass="password";
	
	@BeforeMethod
	public void setup() throws MalformedURLException
	{
		System.out.println("Setting up driver");
		initDriver();
	}
	
	
	@Test
	@Description("Verify Valid login flow with default user and logout")
	public void loginToApp()
	{

		System.out.println("Enter:loginToApp");
		loginPage.validLogin(uname1,pass);
		loginPage.assertHomePage();
		logout();
		System.out.println("Exit:loginToApp");
	}

	@Test
    @Description("Verify user feedback flow")
    public void enterFeedback()
    {
		Log.print("Feedback flow");
        loginPage.validLogin(uname1,pass);
        homePage.selectTour();
        homePage.verifyTourDetailsAndEnterFeedback();
		Log.print("End of feedback flow");
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
