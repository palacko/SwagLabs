package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage extends BasePage{
    public ProductsPage(WebDriver driver) {
        super(driver);
    }
    By shoppingCart = By.cssSelector(".shopping_cart_link");
    String productNameXpath = "//div[@class='inventory_item_name ' and contains(text(),'$$')]";
    String productPriceXpath = "//div[@class='inventory_item_name ' and contains(text(),'$$')]/../../..//div[@class='inventory_item_price']";
    String productAddBtnXpath = "//div[@class='inventory_item_name ' and contains(text(),'$$')]/../../..//button[text()='Add to cart']";
    String productRemoveBtnXpath ="//div[@class='inventory_item_name ' and contains(text(),'$$')]/../../..//button[text()='Remove']";

    public void addProduct(String productName){
        clickElement(By.xpath(productAddBtnXpath.replace("$$", productName)), "Add Product - "+productName);
    }

    public void removeProduct(String productName){
        clickElement(By.xpath(productRemoveBtnXpath.replace("$$", productName)), "Remove Product - "+productName);
    }

    public String getPrice(String productName) {
        if(isElementPresent(By.xpath(productPriceXpath.replace("$$", productName)))){
           return getText(By.xpath(productPriceXpath.replace("$$", productName)), "Price for "+productName);
        } else {
            //Click next pages until element found or until there are no more pages
            return "0";
        }
    }

    public void clickShoppingCart(){
        clickElement(shoppingCart, "Shopping Cart");
    }
}
