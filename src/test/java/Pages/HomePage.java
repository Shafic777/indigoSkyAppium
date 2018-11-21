package Pages;

import com.indigoSky.app.core.CommonMethods;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
//import ru.yandex.qatools.allure.annotations.Step;

public class HomePage {

    @Step
    public void selectTour() {
        WebElement tours = CommonMethods.driver.findElement(By.xpath("//*[@id='tour_creator']"));
        tours.click();
        CommonMethods.wait(8, "tour details to load");
    }

    @Step
    public void verifyTourDetailsAndEnterFeedback() {
        WebElement tourDate = CommonMethods.driver.findElement(By.xpath(" //div[@class='header-title ng-binding']"));
        WebElement tourDescription = CommonMethods.driver.findElement(By.xpath("//div[@class='text-box ng-binding']"));
        WebElement enterFeadback = CommonMethods.driver.findElement(By.xpath("//div[@class='vermilion no-underline margin-t5 ng-binding']"));
        System.out.println("tour Date:" + tourDate.getText());
        System.out.println("tour descriptoin:" + tourDescription.getText());
        enterFeadback.click();
        CommonMethods.wait(3, "wait for feedback popup to load");

        WebElement linkTocomment_whatIlike = CommonMethods.driver.findElement(By.xpath("//div[4]/div/div[2]/ion-content/div/div/table/tbody/tr[2]/td/div/div[1]/img"));

        linkTocomment_whatIlike.click();
        CommonMethods.wait(5, "what i like feedback popuup");
        WebElement submitComment = CommonMethods.driver.findElement(By.xpath("//*[@class='button ng-binding button-positive']"));
        WebElement addText = CommonMethods.driver.findElement(By.xpath("//*[@class='ng-pristine ng-untouched ng-valid']"));
//        WebElement addText = CommonMethods.driver.findElement(By.xpath("//div[6]/div/div[2]/div/textarea"));

        addText.sendKeys("shafic auto feedback : what i like");
        CommonMethods.wait(2,"wait for ok button to be visible");
        Actions actions = new Actions(CommonMethods.driver);
        actions.moveToElement(submitComment).click().perform();
        CommonMethods.wait(3, "updated feedback");

        WebElement whatIdontLike=CommonMethods.driver.findElementByXPath("//div[4]/div/div[2]/ion-content/div/div/table/tbody/tr[2]/td/div/div[2]/img");
        whatIdontLike.click();
        CommonMethods.wait(3, "what i dont like feedback popuup");
        WebElement submitComment2 = CommonMethods.driver.findElement(By.xpath("//*[@class='button ng-binding button-positive']"));
        WebElement addText2 = CommonMethods.driver.findElement(By.xpath("//*[@class='ng-pristine ng-untouched ng-valid']"));
        addText2.sendKeys("shafic auto feedback : what i dont like");
        CommonMethods.wait(2,"wait for ok button to be visible");
        actions.moveToElement(submitComment2).click().perform();
        CommonMethods.wait(3, "updated feedback");

        WebElement adviceToSeller=CommonMethods.driver.findElementByXPath("//div[4]/div/div[2]/ion-content/div/div/table/tbody/tr[2]/td/div/div[3]/img");
        adviceToSeller.click();
        CommonMethods.wait(3, "feedback popuup");
        WebElement submitComment3 = CommonMethods.driver.findElement(By.xpath("//*[@class='button ng-binding button-positive']"));
        WebElement addText3 = CommonMethods.driver.findElement(By.xpath("//*[@class='ng-pristine ng-untouched ng-valid']"));
        addText3.sendKeys("shafic auto advice to seller : Good job");
        CommonMethods.wait(2,"wait for ok button to be visible");
        actions.moveToElement(submitComment3).click().perform();
        CommonMethods.wait(3, "updated feedback");

        WebElement likelySellPrice = CommonMethods.driver.findElement(By.xpath("//*[@id='enterOfferPrice']"));
        likelySellPrice.sendKeys("600000");


        WebElement star = CommonMethods.driver.findElement(By.xpath("//tr[2]/td/div/div[5]/div[2]/div/span[3]"));
        star.click();

     /*   WebElement saveComments = CommonMethods.driver.findElement(By.xpath("//*[@class='button ng-binding button-positive']"));
        actions.moveToElement(saveComments).click().perform();
        CommonMethods.wait(3, "Feedback being saved");

        Assert.assertNull(tourDate.getText());*/

    }
}
