package utilities;

import browserfactory.BaseTest;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Set;

public class Utility extends BaseTest {
    String actualText;

    // click method
    public void clickOnElement(By by) {
        driver.findElement(by).click();
    }
    public String getTextFromElement(By by) {
        return driver.findElement(by).getText();

    }
    public void sendingKeyToElement(By by ,String value ){
        driver.findElement(by).sendKeys(value);
    }

    //verify  method
    public void verifyText(By by, String expactedText, String actualtext) {
        this.actualText = getTextFromElement(by);
        Assert.assertEquals("verify text", expactedText, actualtext);

    }
    //This method will select the option by visible text
    public void selectByVisibleTextFromDropDown(By by, String text) {
        WebElement dropDown = driver.findElement(by);
        Select select = new Select(dropDown);
        select.selectByVisibleText(text);
    }
    // Mouse Hover -- Just Hover No click
    public void mouseHover(By by) {
        Actions actions = new Actions(driver);
        WebElement menu = driver.findElement(by);
        actions.moveToElement(menu).build().perform();
    }

    //action class method
    public void actionMethod(By by1, By by2) {
        Actions actions = new Actions(driver);
        WebElement element1 = driver.findElement(by1);
        WebElement element2 = driver.findElement(by2);
        actions.moveToElement(element1).build().perform();
        actions.moveToElement(element2).click().build().perform();

    }

    //get the listsize of element method and get the size
    public void getListsSizeOfElement(By by) {
        List<WebElement> lists = driver.findElements(by);
        System.out.println(lists.size());
    }

    public void getTheTextOfAllListAfterFilter(By by) {
        List<WebElement> lists = driver.findElements(by);
        System.out.println(lists.size());
        for (WebElement value : lists) {
            System.out.println(value.getText());
        }
    }

    //alert method
    public String alertPopUpHandling() {
        Alert alert = driver.switchTo().alert();
        return alert.getText();
    }

    //switching window method
    public void switchingWindowMethod(By by1, By by2) {
        String parentWindow = driver.getWindowHandle();
        System.out.println(parentWindow);
        clickOnElement(by1);
        Set<String> handles = driver.getWindowHandles();
        System.out.println(handles);
        for (String handle : handles) {
            if (!handle.equals(parentWindow)) {
                driver.switchTo().window(handle);
                clickOnElement(by2);
                driver.close();
                break;
            }
        }
    }
        public String alertText(){
            Alert alert = driver.switchTo().alert(); //Creating Alert object reference and switch to alert
            return alert.getText();
        }
        public void alertHandle(){
            Alert alert = driver.switchTo().alert(); //Creating Alert object reference and switch to alert
            alert.accept();
        }
    }

