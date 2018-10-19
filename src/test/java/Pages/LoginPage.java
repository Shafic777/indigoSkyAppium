package Pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.indigoSky.CommonMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import ru.yandex.qatools.allure.annotations.Step;
import static com.codeborne.selenide.Selenide.$;
import com.indigoSky.AndroidCommonFunctions;
public class LoginPage extends AndroidCommonFunctions {

    private SelenideElement userid = $(By.xpath("//input[@placeholder='Email address']"));
    private SelenideElement password = $(By.xpath("//input[@placeholder='Password']"));
    private SelenideElement loginButton = $(By.xpath("//div[text()='Login']"));
    private SelenideElement homeTours = $(By.xpath("//div[text()='Home Tours']"));

    @Step
    public LoginPage validLogin(String uname,String pass ) {
        setWebViewContext();
        startActivity();
        //CommonMethods.getSource();
       /* WebElement userid = CommonMethods.driver.findElement(By.xpath("//input[@placeholder='Email address']"));
        WebElement password = CommonMethods.driver.findElement(By.xpath("//input[@placeholder='Password']"));
        WebElement loginButton = CommonMethods.driver.findElement(By.xpath("//div[text()='Login']"));*/

        enterText(userid,uname);
        enterText(password,pass);

        //CommonMethods.wait(3);
        clickElement(loginButton);

        return Selenide.page(LoginPage.class);

    }

    public void setWebViewContext() {
        CommonMethods.wait(20);
        CommonMethods.setContext("WEBVIEW_com.juniper.android");
        CommonMethods.wait(5);
    }


    @Step
    public LoginPage validSignUp(long number) {
        setWebViewContext();

        WebElement signup = CommonMethods.driver.findElement(By.xpath("//*[@type='button' and text()='Sign Up']"));

        signup.click();
        CommonMethods.wait(5);
        WebElement emailId = CommonMethods.driver.findElement(By.xpath("//input[@type='email' and @placeholder='Email Address']"));
        WebElement confirmEmailId = CommonMethods.driver.findElement(By.xpath("//input[@type='email' and @placeholder='Confirm Email Address']"));
        WebElement firstName = CommonMethods.driver.findElement(By.xpath("//input[@type='text' and @placeholder='First Name']"));
        WebElement lastName = CommonMethods.driver.findElement(By.xpath("//input[@type='text' and @placeholder='Last Name']"));
        WebElement password = CommonMethods.driver.findElement(By.xpath("//input[@type='password' and @placeholder='Password']"));
        WebElement confirmPassword = CommonMethods.driver.findElement(By.xpath("//input[@type='password' and @placeholder='Confirm Password']"));
        WebElement nextButton = CommonMethods.driver.findElement(By.xpath("//*[@type='button' and text()='Next']"));
        emailId.sendKeys("test" + number + "@gmail.com");
        confirmEmailId.sendKeys("test" + number + "@gmail.com");
        firstName.sendKeys("testFirstname");
        lastName.sendKeys("lastNametest");
        password.sendKeys("test123");
        confirmPassword.sendKeys("test123");
        nextButton.click();
        CommonMethods.wait(3);

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
        CommonMethods.wait(1);
        selectFromDropDown.click();
        nextButton3.click();
        CommonMethods.wait(3);
        WebElement agreeTerms = CommonMethods.driver.findElement(By.xpath("//input[@type='checkbox']"));
        WebElement confirmSignUp = CommonMethods.driver.findElement(By.xpath("//*[@type='button']"));
        agreeTerms.click();
        confirmSignUp.click();
        CommonMethods.wait(5,"home page load");
        return Selenide.page(LoginPage.class);
    }

    @Step
    public LoginPage assertHomePage() {

      //  WebElement homeTours = CommonMethods.driver.findElement(By.xpath("//div[text()='Home Tours']"));
        Assert.assertTrue(homeTours.isDisplayed(),"verify home page screen");
        return Selenide.page(LoginPage.class);

    }
}
