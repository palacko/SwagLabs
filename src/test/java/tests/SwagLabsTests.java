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

        productsPage.addProduct("Sauce Labs Onesie");
        productsPage.addProduct("Test.allTheThings() T-Shirt (Red)");
        productsPage.removeProduct("Sauce Labs Onesie");
        productsPage.removeProduct("Test.allTheThings() T-Shirt (Red)");

        Thread.sleep(1000);

        Assert.assertEquals(productsPage.getPrice("Proizvod 1"), "0");
        Assert.assertEquals(productsPage.getPrice("Sauce Labs Onesie"), "$7.99");

        productsPage.openMenu();
    }
}
