package serenitySteps;


import com.indigoSky.CommonMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

import java.net.MalformedURLException;

import static com.indigoSky.CommonMethods.getRandomNumber;
import static com.indigoSky.CommonMethods.initDriver;


public class LoginSteps extends ScenarioSteps {

   // LoginPage loginPage;

    LoginSteps() throws MalformedURLException
    {
        System.out.println("Setting up driver");

        initDriver();
    }
    @Step
    public void enterLoginData(String emailid,String pass){
        System.out.println("Enter:loginToApp");
        CommonMethods.wait(20);
        CommonMethods.setContext("WEBVIEW_com.juniper.android");
        CommonMethods.wait(5);
        //CommonMethods.getSource();
      //  WebElement userid = CommonMethods.driver.findElement(By.xpath("//input[@placeholder='Email address']"));
        WebElement userid = getDriver().findElement(By.xpath("//input[@placeholder='Email address']"));
        System.out.println("sucess");
        WebElement password = CommonMethods.driver.findElement(By.xpath("//input[@placeholder='Password']"));
        WebElement loginButton = CommonMethods.driver.findElement(By.xpath("//div[text()='Login']"));

        userid.sendKeys("buyer@gmail.com");
        password.sendKeys("password");
        CommonMethods.wait(3);
        loginButton.click();

        CommonMethods.wait(3);
        WebElement homeTours = CommonMethods.driver.findElement(By.xpath("//div[text()='Home Tours']"));
        System.out.println("Home page Displayed "+homeTours.isDisplayed());

        CommonMethods.wait(3);
        System.out.println("Exit:loginToApp");
    }

    @Step
    public void enterSignUpData(String emailid){
        System.out.println("Enter:loginToApp");
        CommonMethods.wait(20);
        CommonMethods.setContext("WEBVIEW_com.juniper.android");
        CommonMethods.wait(8);
        //CommonMethods.getSource();
        long number=getRandomNumber();
        WebElement signup = CommonMethods.driver.findElement(By.xpath("//*[@type='button' and text()='Sign Up']"));

        signup.click();
        CommonMethods.wait(5);
/*		CommonMethods.setContext("WEBVIEW_com.juniper.android");
		CommonMethods.wait(5);*/

        WebElement emailId = CommonMethods.driver.findElement(By.xpath("//input[@type='email' and @placeholder='Email Address']"));
        WebElement confirmEmailId = CommonMethods.driver.findElement(By.xpath("//input[@type='email' and @placeholder='Confirm Email Address']"));
        WebElement firstName = CommonMethods.driver.findElement(By.xpath("//input[@type='text' and @placeholder='First Name']"));
        WebElement lastName = CommonMethods.driver.findElement(By.xpath("//input[@type='text' and @placeholder='Last Name']"));
        WebElement password = CommonMethods.driver.findElement(By.xpath("//input[@type='password' and @placeholder='Password']"));
        WebElement confirmPassword = CommonMethods.driver.findElement(By.xpath("//input[@type='password' and @placeholder='Confirm Password']"));
        WebElement nextButton = CommonMethods.driver.findElement(By.xpath("//*[@type='button' and text()='Next']"));
        emailId.sendKeys("test"+number+"@gmail.com");
        confirmEmailId.sendKeys("test"+number+"@gmail.com");
        firstName.sendKeys("testFirestname");
        lastName.sendKeys("lastNametest");
        password.sendKeys("test123");
        confirmPassword.sendKeys("test123");
        nextButton.click();
        CommonMethods.wait(3);
        //*[@id="child"]/div[2]/label/span

        //	WebElement optBuyer = CommonMethods.driver.findElement(By.xpath("//input[@value='BUYER']"));
        WebElement optBuyer = CommonMethods.driver.findElement(By.xpath("//*[@id='child']/div[2]/label/span"));
        WebElement nextButton2 = CommonMethods.driver.findElement(By.xpath("//*[@type='button' and text()='Next']"));

        optBuyer.click();
        nextButton2.click();
        CommonMethods.wait(3);


        WebElement phoneNum = CommonMethods.driver.findElement(By.xpath("//input[@id='phone_input']"));
        WebElement nextButton3 = CommonMethods.driver.findElement(By.xpath("//*[@type='button' and text()='Next']"));
        WebElement retailer = CommonMethods.driver.findElement(By.xpath("//input[@id='agentList_value']"));



        phoneNum.sendKeys("40897899870");
        retailer.sendKeys("s");
        WebElement selectFromDropDown = CommonMethods.driver.findElement(By.xpath("//*[@id='agentList_dropdown']/div[3]"));

        selectFromDropDown.click();
        nextButton3.click();
        CommonMethods.wait(3);

        WebElement agreeTerms = CommonMethods.driver.findElement(By.xpath("//input[@type='checkbox']"));
        WebElement confirmSignUp = CommonMethods.driver.findElement(By.xpath("//*[@type='button']"));
        agreeTerms.click();
        confirmSignUp.click();
        CommonMethods.wait(5);

        System.out.println(CommonMethods.driver);
        WebElement homeTours = CommonMethods.driver.findElement(By.xpath("//div[text()='Home Tours']"));
        System.out.println("Home page Displayed "+homeTours.isDisplayed());

        CommonMethods.wait(3);
        System.out.println("Exit:SignUp Flow");
    }

    /*@Step
    public void checkErrorMessage(){
        assertThat(loginPage.isErrorMessageShownWPLoginPage()).isTrue();
    }*/
}
