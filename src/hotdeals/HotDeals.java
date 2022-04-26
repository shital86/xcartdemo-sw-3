package hotdeals;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HotDeals extends Utility {
    String baseUrl = " https://mobile.x-cart.com";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void verifySaleProductsArrangeAlphabetically() {
        actionMethod(By.xpath("//ul[contains(@class,'nav')]/descendant::span[contains(text(),'Hot deals')]"),
                (By.linkText("Sale")));
        //Verify the text “Sale”
        verifyText(By.linkText("Sale"), "Sale", getTextFromElement(By.linkText("Sale")));
        //Mouse hover on “Sort By” and select “Name A-Z”
        actionMethod(By.xpath("//span[contains(text(),'Recommended')]"),
                (By.xpath("//a[@data-sort-by='translations.name' and @data-sort-order='asc']")));
        //to verify product start name from a to z
        verifyText(By.xpath("//a[contains(text(),'Avengers: ')]"), "A",
                getTextFromElement(By.xpath("//a[contains(text(),'Avengers: ')]")).substring(0, 1));
    }
    @Test
    public void verifySaleProductsPriceArrangeLowToHigh() {
        actionMethod(By.xpath("//ul[contains(@class,'nav')]/descendant::span[contains(text(),'Hot deals')]"),
                (By.linkText("Sale")));
        //Verify the text “Sale”
        verifyText(By.linkText("Sale"), "Sale", getTextFromElement(By.linkText("Sale")));
        // Mouse hover on “Sort By” and select “Price Low-High
        actionMethod(By.xpath("//span[contains(text(),'Recommended')]"),
                (By.xpath("//a[@class='fn url next-previous-assigned']")));
        // Verify that the product’s price arrange Low to High
    }
    @Test
    public void verifySaleProductsArrangeByRates() {
        actionMethod(By.xpath("//ul[contains(@class,'nav')]/descendant::span[contains(text(),'Hot deals')]"),
                (By.linkText("Sale")));
        //Verify the text “Sale”
        verifyText(By.linkText("Sale"), "Sale", getTextFromElement(By.linkText("Sale")));
        // Mouse hover on “Sort By” and select rate
        actionMethod(By.xpath("//span[contains(text(),'Recommended')]"), (By.xpath("//div[@class='product-average-rating']")));
        // Verify that the product’s sort by rate

    }

    @Test
    public void verifyBestSellersProductsArrangeByZToA() throws InterruptedException {
        actionMethod(By.xpath("//ul[@class='nav navbar-nav top-main-menu']/descendant::span[@class='primary-title']"), (By.xpath("//ul[@class='nav navbar-nav top-main-menu']/descendant::span[@class='primary-title']//following::ul/descendant::span[contains(text()," +
                "'Bestse')]")));
        //Verify the text "Bestsellers"
        verifyText(By.xpath("//h1[@id='page-title']"), "Bestsellers", getTextFromElement(By.xpath("//h1[@id='page-title']")));
        //getting before filter product list
        List<WebElement> beforelists = driver.findElements(By.xpath("//ul[contains(@class,'products')]/descendant::h5"));
        //converting in string
        List<String>beforFilterprodcutList=new ArrayList<>();
        for (WebElement value : beforelists) {
            String beforeFilter = value.getText();
            System.out.println(beforeFilter);
        }
        actionMethod(By.xpath("//span[contains(text(),'Sort by:')]"), (By.xpath("//a[@data-sort-by='translations.name'and@data-sort-order='desc']")));
        //gettting after filter product list
        Thread.sleep(5000);
        List<WebElement> afterlists1 = driver.findElements(By.xpath("//ul[contains(@class,'products')]/descendant::h5"));
        //converting in string
        List<String>afterFilterProductList=new ArrayList<>();
        for (WebElement value1 : afterlists1) {
            String afterFilter = value1.getText();
            System.out.println(afterFilter);
        }

        //sorting in collection class
        Collections.sort(beforFilterprodcutList,Collections.reverseOrder());
        // Verify that the product’s sort by z-a
        Assert.assertEquals("verify list starting from z-a ", beforFilterprodcutList,afterFilterProductList);
    }
    @Test
    public void verifyBestSellersProductsPriceArrangeHighToLow() throws InterruptedException {
        actionMethod(By.xpath("//ul[@class='nav navbar-nav top-main-menu']/descendant::span[@class='primary-title']"), (By.xpath("//ul[@class='nav navbar-nav top-main-menu']/descendant::span[@class='primary-title']//following::ul/descendant::span[contains(text()," +
                "'Bestse')]")));
        //Verify the text "Bestsellers"
        verifyText(By.xpath("//h1[@id='page-title']"), "Bestsellers", getTextFromElement(By.xpath("//h1[@id='page-title']")));

        // Mouse hover on “Sort By” and select “Price High-low and getting pricelist  before filter
        List<WebElement> beforepricelists = driver.findElements(By.xpath("//li[contains(@class,'product-price-base')]"));
        //converting in double for price 19.00 example
        List<Double>beforFilterprodcutprice=new ArrayList<>();
        //removing$ from price and storing in double datatype
        for (WebElement value : beforepricelists) {
            beforFilterprodcutprice.add(Double.valueOf(value.getText().replace("$","")));
        }
        //clicking on high to low price filter
        actionMethod(By.xpath("//span[contains(text(),'Sort by:')]"),
                (By.xpath("//a[@data-sort-by='p.price' and @data-sort-order='desc']")));
        // store the all product price

        List<WebElement> afterpicelists = driver.findElements(By.xpath("//li[contains(@class,'product-price-base')]"));
        //converting in double
        List<Double> afterFilterPriceList=new ArrayList<>();
        //removing $ from price
        Thread.sleep(1000);
        for (WebElement value : afterpicelists) {
            afterFilterPriceList.add(Double.valueOf(value.getText().replace("$","")));
        }
        //sorting data
        Collections.sort(beforFilterprodcutprice,Collections.reverseOrder());
        Assert.assertEquals("verify pricelist starting high to low ", beforFilterprodcutprice,afterFilterPriceList);
        Thread.sleep(1000);
    }

    @Test
    public void verifyBestSellersProductsArrangeByRates() {
        actionMethod(By.xpath("//ul[@class='nav navbar-nav top-main-menu']/descendant::span[@class='primary-title']"), (By.xpath("//ul[@class='nav navbar-nav top-main-menu']/descendant::span[@class='primary-title']//following::ul/descendant::span[contains(text()," +
                "'Bestse')]")));
        //Verify the text "Bestsellers"
        verifyText(By.xpath("//h1[@id='page-title']"), "Bestsellers", getTextFromElement(By.xpath("//h1[@id='page-title']")));

    }
}
