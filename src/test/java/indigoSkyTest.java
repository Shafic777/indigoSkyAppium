import Pages.LoginPage;
import com.indigoSky.CommonMethods;
import com.indigoSky.SetDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;
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
        try {
            new SetDriver.SetAndroidDriver().createDriver();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //initDriver();

	}
	
	
	@Test
   // @Parameters({ "sUsername", "sPassword" })
	@Description("Verify Valid login flow with default user")
	public void loginToApp()
	{
       /* System.out.println("Enter:"+uname);
        System.out.println("Enter:"+pass);*/

		System.out.println("Enter:loginToApp");
		loginPage.validLogin();
		loginPage.assertHomePage();
		System.out.println("Exit:loginToApp");
	}




	//@Test
	//@Description("Verify Valid SignUp flow as buyer")
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
		//CommonMethods.quit();
	}
	

}
