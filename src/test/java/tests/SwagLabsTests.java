package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;

import java.util.concurrent.TimeUnit;

public class SwagLabsTests {
    WebDriver driver;
    BaseTest baseTest;

    @BeforeMethod
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void tearDown() {
        baseTest.baseTearDown();
    }

    @Test
    public void login() throws InterruptedException {
        driver.get("https://www.saucedemo.com/");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        ProductsPage productsPage = new ProductsPage(driver);

        productsPage.addProducts("Sauce Labs Onesie", "Test.allTheThings() T-Shirt (Red)", "Sauce Labs Bike Light");
        productsPage.removeProducts("Sauce Labs Onesie", "Test.allTheThings() T-Shirt (Red)", "Sauce Labs Bike Light");

        Thread.sleep(1000);

        String[] prices = productsPage.getPrices("Sauce Labs Onesie", "Test.allTheThings() T-Shirt (Red)", "Sauce Labs Bike Light");
        String[] expectedPrices = {"$7.99","$15.99","$9.99"};

        Assert.assertEquals(prices,expectedPrices);

        productsPage.openMenu();
}
}
