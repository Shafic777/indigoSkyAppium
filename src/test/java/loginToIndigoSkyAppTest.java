
import cucumber.api.CucumberOptions;
import cucumber.api.java.Before;
import cucumber.api.junit.Cucumber;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;

import static com.indigoSky.CommonMethods.initDriver;

//@RunWith(SerenityRunner.class)
@RunWith(CucumberWithSerenity.class)
//@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources/features/loginFlow.feature")
public class loginToIndigoSkyAppTest {


}
