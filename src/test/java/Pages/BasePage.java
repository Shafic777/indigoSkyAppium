package Pages;

import com.indigoSky.CommonMethods;
import com.indigoSky.PropertyReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.yandex.qatools.allure.annotations.Step;

import static com.indigoSky.CommonMethods.initDriver;

public class BasePage {

    @BeforeSuite
    public void setup() throws Exception
    {
        System.out.println("Setting up driver");
        PropertyReader propertyReader = new PropertyReader();
        initDriver();

    }
    @AfterSuite
    public void quit()
    {
        System.out.println("closing the driver");
        CommonMethods.quit();
    }


    public void clickMenuOption()
    {

        WebElement menu = CommonMethods.driver.findElement(By.xpath("//*[@id='menu-thumbnail']"));
        menu.click();
    }

    @Step
    public void logout()
    {
        clickMenuOption();
        CommonMethods.wait(3,"Menu option to load");
        WebElement logout = CommonMethods.driver.findElement(By.xpath("//*[@id='menu_logout']"));
        logout.click();
        CommonMethods.wait(3,"logout popup");

        WebElement logoutPopUpMess = CommonMethods.driver.findElement(By.xpath("//div[@class='popup-body']"));
        Assert.assertTrue(logoutPopUpMess.getText().contentEquals("Are you sure you want to logout?"));
        WebElement confirmLogout = CommonMethods.driver.findElement(By.xpath("//button[@class='button ng-binding button-positive']"));
        confirmLogout.click();

    }


}
