package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BasePage {
    WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Main app Menu css selector
     */
    @FindBy(css = "#react-burger-menu-btn")
    WebElement menu;

    int waitTime = 30;

    /**
     * use this method to open app menu
     */
    public void openMenu() {
        clickElement(menu, "Menu Icon");
    }



    public void clickElement(WebElement element, String log) {
        try {
            WebDriverWait wdWait = new WebDriverWait(driver, waitTime);
            wdWait.until(ExpectedConditions.visibilityOf(element));
            wdWait.until(ExpectedConditions.elementToBeClickable(element));

            Actions actions = new Actions(driver);
            actions.moveToElement(element).click().build().perform();
            System.out.println("Clicked " + log);
        } catch (StaleElementReferenceException e) {
            Actions actions = new Actions(driver);
            actions.moveToElement(element).click().build().perform();
            System.out.println("Clicked " + log);
        }
    }

    public void typeText(WebElement element, String text, String log) {
        try {
            WebDriverWait wdWait = new WebDriverWait(driver, waitTime);
            wdWait.until(ExpectedConditions.visibilityOf(element));

            element.clear();
            element.sendKeys(text);

            System.out.println("Typed text " + text + " into " + log);
        } catch (StaleElementReferenceException e) {
            element.clear();
            element.sendKeys();
            System.out.println("Typed text " + text + " into " + log);
        }
    }

    public String getText(WebElement element, String log) {
        try {
            WebDriverWait wdWait = new WebDriverWait(driver, waitTime);
            wdWait.until(ExpectedConditions.visibilityOf(element));

            System.out.println("Get text from " + log);
            return element.getText();

        } catch (StaleElementReferenceException e) {
            System.out.println("Get text from " + log);
            return element.getText();
        }
    }

    public boolean isElementPresent(List<WebElement> elements){
        return elements.size()>0;
    }

}

