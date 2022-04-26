package homepage;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import utilities.Utility;

public class TopMenuTest extends Utility {
    String baseUrl = "https://mobile.x-cart.com/";

    @Before
    public void SetUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void verifyUserShouldNavigateToShippingPageSuccessfully() {
        clickOnElement(By.xpath("//li[@class='leaf']/child::a/following::span[contains(text(),'Shipping')]"));
        //Verify the text “Shipping”
        verifyText(By.xpath("//h1[@id='page-title']"),"Shipping", getTextFromElement(By.xpath("//h1[@id='page-title']")));
    }

    @Test
    public void verifyUserShouldNavigateToNewPageSuccessfully() {
        clickOnElement(By.xpath("//ul[contains(@class,'nav')]/descendant::span[text()='New!']"));
        //2 Verify the text “New arrivals”
        verifyText(By.xpath("//h1[@id='page-title']"), "New arrivals", getTextFromElement(By.xpath("//h1[@id='page-title']")));

    }
    @Test
    public void verifyUserShouldNavigateToComingsoonPageSuccessfully() {
        clickOnElement(By.xpath("//ul[contains(@class,'nav')]/descendant::span[contains(text(),'Coming soon')]"));
        //2 Verify the text “Coming soon”
        verifyText(By.xpath("//h1[@id='page-title']"),"Coming soon",getTextFromElement(By.xpath("//h1[@id='page-title']")));
    }
    @Test
    public void verifyUserShouldNavigateToContactUsPageSuccessfully(){
        //click on contact us
        clickOnElement(By.xpath("//ul[contains(@class,'nav')]/descendant::span[contains(text(),'Contact us')]"));
        //Verify the text “Contact us”
        verifyText((By.xpath("//h1[@id='page-title']")),
                "Contact us",getTextFromElement(By.xpath("//h1[@id='page-title']")));
    }
}
