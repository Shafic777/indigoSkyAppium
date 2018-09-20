package cucumer;

import cucumber.api.java.en.*;
import org.junit.BeforeClass;
import serenitySteps.LoginSteps;
import io.appium.java_client.android.AndroidDriver;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.util.Iterator;
import java.util.Set;

import static com.indigoSky.CommonMethods.initDriver;

public class LoginScenarioSteps {

    @Managed
    public AndroidDriver driver;



    @Steps
    LoginSteps loginSteps;

    @Given("^User is on login page$")
    public void gotoLoginPage(){
        System.out.println("Enter:loginToApp");
      //  injectAnnotatedFieldsInto(driver, this);

    }

    @When("I login using \"(.*)\" and \"(.*)\" credentials")
    public void enterValidData(String email,String pass){

        loginSteps.enterLoginData(email,pass);
    }

    @Then("I should be able to login to system")
    public void checkErrorMessage(){
        //loginSteps.checkErrorMessage();
    }



}

