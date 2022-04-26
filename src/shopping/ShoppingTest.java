package shopping;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import utilities.Utility;

public class ShoppingTest extends Utility {
    String baseUrl = "https://mobile.x-cart.com/";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void verifyThatUserShouldPlaceOrderSuccessfullyForOllieTheAppControlledRobot() throws InterruptedException {
        mouseHover(By.xpath("//div/ul[@class='nav navbar-nav top-main-menu']//span[text()='Hot deals']")); //		1.1 Mouse hover on the “Hot deals” link
        mouseHover(By.xpath("//div/ul[@class='nav navbar-nav top-main-menu']//li[@class='leaf has-sub']//ul/li/a/span[text()='Bestsellers']")); //		1.2 Mouse hover on the “Toys"  link
        clickOnElement(By.xpath("//div/ul[@class='nav navbar-nav top-main-menu']//li[@class='leaf has-sub']//ul/li/a/span[text()='Bestsellers']")); //1.2 and click

        //		1.3 Verify the text “Bestsellers”
        String actualMessage1 = getTextFromElement(By.xpath("//h1[@id='page-title']"));
        String expectedMessage1 = "Bestsellers";
        Assert.assertEquals("Not on Toys Page!", expectedMessage1, actualMessage1);

        Thread.sleep(2000); //Allow page to load
        // 	1.4 Mouse hover on “Sort By” and select “Name A-Z”
        //mouseHover(By.xpath("//span[contains(text(),'Sales')]"));
        mouseHover(By.xpath("//body/div[@id='mm-0']/div[@id='page-wrapper']/div[@id='page']/div[@id='main-wrapper']/div[@id='main']/div[1]/div[3]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]"));
        clickOnElement(By.xpath("//li[@class='list-type-grid ']//a[@data-sort-by='translations.name' and @data-sort-order='asc']"));
        Thread.sleep(1000); //Allow page to load

        //	1.5 Click on “Add to cart” button of the product “Ollie - The App Controlled Robot” -- ANOTHER PRODUCT SELECTED
        clickOnElement(By.xpath("//button[@class='btn  regular-button add-to-cart product-add2cart productid-42']"));

        //  1.6 Verify the message “Product has been added to your cart” display in  green bar
        String actualMessage2 = getTextFromElement(By.xpath("//li[contains(text(),'Product has been added to your cart')]"));
        String expectedMessage2 = "Product has been added to your cart";
        Assert.assertEquals("Failed to add items", expectedMessage2, actualMessage2);

        clickOnElement(By.xpath("//div/a[@class='close']")); //1.7 Click on X sign to close the message
        clickOnElement(By.xpath("//div[@class='lc-minicart lc-minicart-horizontal collapsed recently-updated']")); //1.8 Click on “Your cart” icon
        clickOnElement(By.xpath("//span[contains(text(),'View cart')]")); //1.8 and Click on “View cart” button

        // 1.9 Verify the text “Your shopping cart - 1 item”
        String actualMessage3 = getTextFromElement(By.xpath("//h1[@id='page-title']"));
        String expectedMessage3 = "Your shopping cart - 1 item";
        Assert.assertEquals("Item is not added", expectedMessage3, actualMessage3);
        Thread.sleep(4000); // Allow page to load

        //1.10 Verify the Subtotal $299.00 -- Taken using Chropath -- CUSTOMISED XPATH FAILED --- TO BE CHECKED
        //String actualMessage5_1 = getTextFromElement(By.xpath("//div[@class='item-price']//span[@class='surcharge-cell']/child::span[1]"));
        //String actualMessage5_2 = getTextFromElement(By.xpath("//div[@class='item-price']//span/span[@class='surcharge-cell']/span[2]"));
        //String actualMessage5_3 = getTextFromElement(By.xpath("//div[@class='item-price']//span/span[@class='surcharge-cell']/span[3]"));
        //String actualMessage5_4 = getTextFromElement(By.xpath("//div[@class='item-price']//span/span[@class='surcharge-cell']/span[4]"));
        String actualMessage5_1 = getTextFromElement(By.xpath("//body/div[@id='mm-0']/div[@id='page-wrapper']/div[@id='page']/div[@id='main-wrapper']/div[@id='main']/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/ul[1]/li[1]/span[1]/span[1]/span[1]/span[1]"));
        String actualMessage5_3 = getTextFromElement(By.xpath("//body/div[@id='mm-0']/div[@id='page-wrapper']/div[@id='page']/div[@id='main-wrapper']/div[@id='main']/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/ul[1]/li[1]/span[1]/span[1]/span[1]/span[3]"));
        String actualMessage5_2 = getTextFromElement(By.xpath("//body/div[@id='mm-0']/div[@id='page-wrapper']/div[@id='page']/div[@id='main-wrapper']/div[@id='main']/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/ul[1]/li[1]/span[1]/span[1]/span[1]/span[2]"));
        String actualMessage5_4 = getTextFromElement(By.xpath("//body/div[@id='mm-0']/div[@id='page-wrapper']/div[@id='page']/div[@id='main-wrapper']/div[@id='main']/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/ul[1]/li[1]/span[1]/span[1]/span[1]/span[4]"));
        String actualMessage5 = actualMessage5_1 + actualMessage5_2 + actualMessage5_3 + actualMessage5_4; // Combining price to single string
        String expectedMessage5 = "$299.00";
        Assert.assertEquals("Wrong subtotal", expectedMessage5, actualMessage5);

        // 1.11 Verify the total $309.73
        String actualMessage6_1 = getTextFromElement(By.xpath("//li[@class='total']//span/span[@class='surcharge-cell']/span[@class ='part-prefix']"));
        String actualMessage6_2 = getTextFromElement(By.xpath("//li[@class='total']//span/span[@class='surcharge-cell']/span[@class ='part-integer']"));
        String actualMessage6_3 = getTextFromElement(By.xpath("//li[@class='total']//span/span[@class='surcharge-cell']/span[@class ='part-decimalDelimiter']"));
        String actualMessage6_4 = getTextFromElement(By.xpath("//li[@class='total']//span/span[@class='surcharge-cell']/span[@class ='part-decimal']"));
        String actualMessage6 = actualMessage6_1 + actualMessage6_2 + actualMessage6_3 + actualMessage6_4;// Combining price to single string
        String expectedMessage6 = "$309.73";
        Assert.assertEquals("Wrong total", expectedMessage6, actualMessage6);

        clickOnElement(By.xpath("//span[contains(text(),'Go to checkout')]")); // 1.12 Click on “Go to checkout” button

        // 1.13 Verify the text “Log in to your account”
        String actualMessage7 = getTextFromElement(By.xpath("//h3[contains(text(),'Log in to your account')]"));
        String expectedMessage7 = "Log in to your account";
        Assert.assertEquals("Not diverted to login Page", expectedMessage7, actualMessage7);

        sendingKeyToElement(By.xpath("//input[@id='email']"), "ab@yahoo.com");   // 1.14 Enter Email address
        clickOnElement(By.xpath("//button[@class='btn  regular-button anonymous-continue-button submit']")); //1.15 Click on “Continue” Button

        //1.16 Verify the text “Secure Checkout”
        String actualMessage8 = getTextFromElement(By.xpath("//h1[@class='title']"));
        String expectedMessage8 = "Secure Checkout";
        Assert.assertEquals("Not diverted to checkuout Page", expectedMessage8, actualMessage8);

        //1.17 Fill all the mandatory fields
        sendingKeyToElement(By.xpath("//input[@id='shippingaddress-firstname']"), "lola");
        sendingKeyToElement(By.xpath("//input[@id='shippingaddress-lastname']"), "Carlie");
        sendingKeyToElement(By.xpath("//input[@id='shippingaddress-street']"), "3 avenue");
        sendingKeyToElement(By.xpath("//input[@id='shippingaddress-city']"), "London");
        selectByVisibleTextFromDropDown(By.xpath("//select[@id='shippingaddress-country-code']"), "United Kingdom");
        sendingKeyToElement(By.xpath("//input[@id='shippingaddress-custom-state']"), "London");
        sendingKeyToElement(By.xpath("//input[@id='shippingaddress-zipcode']"), "675678");
        //sendingKeyToElement(By.xpath("//input[@id='email']"),"ab@yahoo.com");
        clickOnElement(By.xpath("//input[@id='create_profile']")); // 1.18 Check the check box “Create an account for later use”
        sendingKeyToElement(By.xpath("//input[@id='password']"), "Password123"); //1.19 Enter the password
        Thread.sleep(1500); // Allow page to load
        clickOnElement(By.xpath("//input[@id='method128']")); // 1.20 Select the Delivery Method to “Local Shipping”
        clickOnElement(By.xpath("//input[@id='pmethod6']")); // 1.21 Select Payment Method “COD”
        Thread.sleep(1500); // Allow page to load

        // 1.22 Verify the total $311.03
        String actualMessage9_1 = getTextFromElement(By.xpath("//div[@class='total clearfix']//span/span[@class='surcharge-cell']/span[@class ='part-prefix']"));
        String actualMessage9_2 = getTextFromElement(By.xpath("//div[@class='total clearfix']//span/span[@class='surcharge-cell']/span[@class ='part-integer']"));
        String actualMessage9_3 = getTextFromElement(By.xpath("//div[@class='total clearfix']//span/span[@class='surcharge-cell']/span[@class ='part-decimalDelimiter']"));
        String actualMessage9_4 = getTextFromElement(By.xpath("//div[@class='total clearfix']//span/span[@class='surcharge-cell']/span[@class ='part-decimal']"));
        String actualMessage9 = actualMessage9_1 + actualMessage9_2 + actualMessage9_3 + actualMessage9_4;
        String expectedMessage9 = "$311.03";
        Assert.assertEquals("Wrong total", expectedMessage9, actualMessage9);

        clickOnElement(By.xpath("//span[contains(text(),'Place order:')]")); // 1.23 Click on “Place Order” Button
        Thread.sleep(2000);// Allow page to load

        //1.24 Verify the text “Thank you for your order”
        String actualMessage10 = getTextFromElement(By.xpath("//h1[@id='page-title']"));
        String expectedMessage10 = "Thank you for your order";
        Assert.assertEquals("Not diverted to login Page", expectedMessage10, actualMessage10);
    }

    @Test
    public void verifyThatUserShouldClearShoppingCartSuccessfully() throws InterruptedException {
        mouseHover(By.xpath("//div/ul[@class='nav navbar-nav top-main-menu']//span[text()='Hot deals']")); // 		1.1 Mouse hover on the “Hot deals” link
        mouseHover(By.xpath("//div/ul[@class='nav navbar-nav top-main-menu']//li[@class='leaf has-sub']//ul/li/a/span[text()='Bestsellers']")); //		1.2 Mouse hover on the “Bestseller”  link
        clickOnElement(By.xpath("//div/ul[@class='nav navbar-nav top-main-menu']//li[@class='leaf has-sub']//ul/li/a/span[text()='Bestsellers']")); // 1.2 and click

        // 		1.3 Verify the text “Bestsellers”
        String actualMessage1 = getTextFromElement(By.xpath("//h1[@id='page-title']"));
        String expectedMessage1 = "Bestsellers";
        Assert.assertEquals("Not on Bestsellers Page!", expectedMessage1, actualMessage1);

        Thread.sleep(1000); // Allow page to load
        //mouseHover(By.xpath("//span[contains(text(),'Sales')]"));

        // 		1.4 Mouse hover on “Sort By” and select “Name A-Z”
        mouseHover(By.xpath("//body/div[@id='mm-0']/div[@id='page-wrapper']/div[@id='page']/div[@id='main-wrapper']/div[@id='main']/div[1]/div[3]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]"));
        clickOnElement(By.xpath("//li[@class='list-type-grid ']//a[@data-sort-by='translations.name' and @data-sort-order='asc']"));
        Thread.sleep(1000);// Allow page to load

        // 		1.5 Click on “Add to cart” button of the product “Vinyl Idolz: Ghostbusters” -- ANOTHER PRODUCT SELECTED ---
        //clickOnElement(By.xpath("//a[contains(text(),'Apple Watch Sport 42mm with Sport Band')]"));
        clickOnElement(By.xpath("//button[@class='btn  regular-button add-to-cart product-add2cart productid-39']"));

        //1.6 Verify the message “Product has been added to your cart” display in  green bar
        String actualMessage2 = getTextFromElement(By.xpath("//li[contains(text(),'Product has been added to your cart')]"));
        String expectedMessage2 = "Product has been added to your cart";
        Assert.assertEquals("Failed to add item", expectedMessage2, actualMessage2);

        clickOnElement(By.xpath("//a[@class='close']")); //1.7 Click on X sign to close the message
        clickOnElement(By.xpath("//div[@class='lc-minicart lc-minicart-horizontal collapsed recently-updated']")); //1.8 Click on “Your cart” icon
        clickOnElement(By.xpath("//span[contains(text(),'View cart')]")); // 1.8 and Click on “View cart” button

        //1.9 Verify the text “Your shopping cart - 1 item”
        String actualMessage3 = getTextFromElement(By.xpath(" //h1[@id='page-title']"));
        String expectedMessage3 = "Your shopping cart - 1 item";
        Assert.assertEquals("not on checkout page", expectedMessage3, actualMessage3);

        clickOnElement(By.xpath("//a[contains(text(),'Empty your cart')]"));  // 1.10 Click on “Empty your cart” link

        // 1.11 Verify the text “Are you sure you want to clear your cart?” on alert
        String alertMessage4 = alertText();
        String expectedMessage4 = "Are you sure you want to clear your cart?";
        Assert.assertEquals("Alert nor pop-up", expectedMessage4, alertMessage4);

        //1.12 Click “Ok” on alert

        alertHandle();
        Thread.sleep(1000); // Allow page to load

        //1.13 Verify the message “Item(s) deleted from your cart”
        String actualMessage5 = getTextFromElement(By.xpath("//li[contains(text(),'Item(s) deleted from your cart')]"));
        String expectedMessage5 = "Item(s) deleted from your cart";
        Assert.assertEquals("failed to delete items", expectedMessage5, actualMessage5);
        Thread.sleep(1000); //Allow page to load

        //1.15 Verify the text “Your cart is empty”
        String actualMessage6 = getTextFromElement(By.xpath("//h1[@id='page-title']"));
        String expectedMessage6 = "Your cart is empty";
        Assert.assertEquals("failed to load empty cart page", expectedMessage6, actualMessage6);

    }

    @After
    public void tearDown() {
        closeBrowser();
    }
}