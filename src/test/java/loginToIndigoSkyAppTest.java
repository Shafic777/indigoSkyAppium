
import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;

import static com.indigoSky.CommonMethods.initDriver;

//@RunWith(SerenityRunner.class)
@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features="src/test/resources/features/loginFlow.feature")
public class loginToIndigoSkyAppTest {

   /* @BeforeClass
    public static void setup() throws MalformedURLException
    {
        System.out.println("Setting up driver");

        initDriver();
    }*/
}
