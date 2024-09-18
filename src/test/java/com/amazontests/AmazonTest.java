package com.amazontests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class AmazonTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
//        System.setProperty("webdriver.chrome.driver","C:\\driver\\chromedriver-win64\\chromedriver.exe");
//        driver = new ChromeDriver();
//        // Maximize the browser window
//        driver.manage().window().maximize();
    	 System.setProperty("webdriver.chrome.driver","C:\\driver\\chromedriver-win64\\chromedriver.exe");

         // Initialize ChromeDriver
       ChromeOptions options=new ChromeOptions();
       options.addArguments("--start-maximized");
         WebDriver driver = new ChromeDriver(options);
 System.out.print("ss");
         // Maximize the browser window
         driver.manage().window().maximize();
    }

    @Test
    public void testAddTwoToysToCartAndValidatePrices() {
        // Step 1: Navigate to Amazon.com
        driver.get("https://www.amazon.com/");

        // Step 2: Search for 'toys'
        WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
        searchBox.sendKeys("toys");
        WebElement searchButton = driver.findElement(By.id("nav-search-submit-button"));
        searchButton.click();

        // Step 3: Select any 2 products and Add to Cart
        List<WebElement> productPrices = driver.findElements(By.xpath("//span[@class='a-price-whole']"));
        List<WebElement> addToCartButtons = driver.findElements(By.xpath("//button[text()='Add to cart']"));

        // Select the first product
        String firstProductPrice = productPrices.get(0).getText();
        addToCartButtons.get(0).click();

        // Select the second product
        String secondProductPrice = productPrices.get(1).getText();
        addToCartButtons.get(1).click();

        // Navigate to cart
        WebElement cartButton = driver.findElement(By.id("nav-cart"));
        cartButton.click();

        // Validate prices in cart (just checking that prices are displayed in the cart)
        List<WebElement> cartPrices = driver.findElements(By.xpath("//span[@class='a-price-whole']"));
        String cartFirstProductPrice = cartPrices.get(0).getText();
        String cartSecondProductPrice = cartPrices.get(1).getText();

        // Step 4: Validate price on Search Results page and Cart Summary page
        Assert.assertEquals(cartFirstProductPrice, firstProductPrice, "First product price mismatch!");
        Assert.assertEquals(cartSecondProductPrice, secondProductPrice, "Second product price mismatch!");
    }

    @AfterMethod
    public void tearDown() {
        // Close the browser
        driver.quit();
    }
}
