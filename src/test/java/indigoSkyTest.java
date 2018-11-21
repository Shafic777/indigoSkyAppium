import Pages.BasePage;
import Pages.HomePage;
import Pages.LoginPage;
import com.indigoSky.app.core.CommonMethods;
import com.indigoSky.app.core.PropertyReader;
import com.indigoSky.app.dataPojo.loginData;
import com.paypal.selion.platform.dataprovider.DataProviderFactory;
import com.paypal.selion.platform.dataprovider.SeLionDataProvider;
import com.paypal.selion.platform.dataprovider.impl.FileSystemResource;
import io.qameta.allure.Description;
//import jdk.internal.jline.internal.Log;
import org.apache.commons.logging.impl.Log4JLogger;
import org.testng.annotations.*;
//import ru.yandex.qatools.allure.annotations.Description;


import java.io.IOException;

import static com.indigoSky.app.core.CommonMethods.getRandomNumber;
import static com.indigoSky.app.core.CommonMethods.initDriver;

public class indigoSkyTest extends BasePage {

	LoginPage loginPage=new LoginPage();


	public indigoSkyTest() throws Exception {
	}

	HomePage homePage=new HomePage();
	//loginData loginData =new loginData();
/*	String uname="buyer@gmail.com";
	String pass="password";*/
	String uname1="caestledemo.agent@gmail.com";
    String pass="password";

	@BeforeTest
	public void setup() throws Exception
	{
		System.out.println("Setting up driver");
		PropertyReader propertyReader = new PropertyReader();
		initDriver();
        loginPage.setWebViewContext();

    }

	@DataProvider(name ="alldata")
	public Object[][] getAllDataYamlDataProvider() throws IOException {
		FileSystemResource resource =
				new FileSystemResource("src/test/resources/data/testData.yaml");
		SeLionDataProvider dataProvider =
				DataProviderFactory.getDataProvider(resource);
        System.out.println("data provider len:"+dataProvider.getAllData().length);

		return dataProvider.getAllData();
	}
	/*@DataProvider(name = "yamlDataProvider")
	public Object[][] getYamlDataProvider() throws Exception {
		FileSystemResource resource =
				new FileSystemResource("C:\\Users\\Shafic77\\indigoAppium\\src\\test\\resources\\data\\testData.yaml");
		SeLionDataProvider dataProvider =
				DataProviderFactory.getDataProvider(resource);
		String[] keyArray = new String[] {"test1"};
		return dataProvider.getDataByKeys(keyArray);
	}*/
	@Test(dataProvider ="alldata")
	@Description("Verify Valid login flow with default user and logout")
	public void loginToApp(loginData loginDataObj)
	{

        System.out.println("Enter:loginToApp");
        System.out.println("Test For Input Data:"+loginDataObj.toString());
		loginPage.validLogin(loginDataObj.getLoginID().toString(),loginDataObj.getPassword().toString());
		loginPage.assertHomePage();
		logout();
        System.out.println("Exit:loginToApp");
	}

	//@Test
    @Description("Verify user feedback flow")
    public void enterFeedback()
    {
		//Log.debug("Feedback flow");
        loginPage.validLogin(uname1,pass);
        homePage.selectTour();
        homePage.verifyTourDetailsAndEnterFeedback();
		//Log.debug("End of feedback flow");
    }



	@Test
	@Description("Verify Valid SignUp flow as buyer")
	public void signUpToApp()
	{
		long number=getRandomNumber();
		System.out.println("Enter:SingUP");
		loginPage.validSignUp(number);
		loginPage.assertHomePage();
		logout();
		System.out.println("Exit:SignUp Flow");
	}


	@AfterTest
	public void quit() 
	{
		System.out.println("closing the driver");
		CommonMethods.quit();
	}
	

}
