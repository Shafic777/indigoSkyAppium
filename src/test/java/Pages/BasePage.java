package Pages;

import com.indigoSky.CommonMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import ru.yandex.qatools.allure.annotations.Step;

public class BasePage {


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
        WebElement logout = CommonMethods.driver.findElement(By.xpath("//*[@id='menu_logou']"));
        logout.click();
        CommonMethods.wait(3,"logout popup");

        WebElement logoutPopUpMess = CommonMethods.driver.findElement(By.xpath("//div[@class='popup-body']"));
        Assert.assertTrue(logoutPopUpMess.getText().contentEquals("Are you sure you want to logout?"));
        WebElement confirmLogout = CommonMethods.driver.findElement(By.xpath("//button[@class='button ng-binding button-positive']"));
        confirmLogout.click();

    }


}
