package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductsPage extends BasePage {
    public ProductsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".shopping_cart_link")
    WebElement shoppingCart;
    String productNameXpath = "//div[@class='inventory_item_name ' and contains(text(),'$$')]";
    String productPriceXpath = "//div[@class='inventory_item_name ' and contains(text(),'$$')]/../../..//div[@class='inventory_item_price']";
    String productAddBtnXpath = "//div[@class='inventory_item_name ' and contains(text(),'$$')]/../../..//button[text()='Add to cart']";
    String productRemoveBtnXpath = "//div[@class='inventory_item_name ' and contains(text(),'$$')]/../../..//button[text()='Remove']";

    public void addProduct(String productName) {
        clickElement(driver.findElement(By.xpath(productAddBtnXpath.replace("$$", productName))), "Add Product - " + productName);
    }

    public void addProducts(String... products) {
        for (String product : products) {
            clickElement(driver.findElement(By.xpath(productAddBtnXpath.replace("$$", product))), "Add Product - " + product);
        }
    }

    public void removeProduct(String productName) {
        clickElement(driver.findElement(By.xpath(productRemoveBtnXpath.replace("$$", productName))), "Remove Product - " + productName);
    }

    public void removeProducts(String... products) {
        for (String product : products) {
            clickElement(driver.findElement(By.xpath(productRemoveBtnXpath.replace("$$", product))), "Remove Product - " + product);
        }
    }

    public String getPrice(String productName) {
        if (isElementPresent(driver.findElements(By.xpath(productPriceXpath.replace("$$", productName))))) {
            return getText(driver.findElement(By.xpath(productPriceXpath.replace("$$", productName))), "Price for " + productName);
        } else {
            //Click next pages until element found or until there are no more pages
            return "0";
        }
    }

    public String[] getPrices(String... products) {
        String prices[] = new String[products.length];

        for (int i = 0; i < products.length; i++) {
            prices[i] = getText(driver.findElement(By.xpath(productPriceXpath.replace("$$", products[i]))), "Price for " + products[i]);
        }
        return prices;


//        if (isElementPresent(driver.findElements(By.xpath(productPriceXpath.replace("$$", product))))) {
//            return getText(driver.findElement(By.xpath(productPriceXpath.replace("$$", product))), "Price for " + product);
//        } else {
//            //Click next pages until element found or until there are no more pages
//            return "0";
//        }
    }

    public void clickShoppingCart() {
        clickElement(shoppingCart, "Shopping Cart");
    }
}
