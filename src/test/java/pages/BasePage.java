package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Main app Menu css selector
     */
    By menu = By.cssSelector("#react-burger-menu-btn");

    int waitTime = 30;

    /**
     * use this method to open app menu
     */
    public void openMenu() {
        clickElement(menu, "Menu Icon");
    }

    public void clickElement(WebElement element, String log) {
        try {
            WebDriverWait wdWait = new WebDriverWait(driver, 30);
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

    public void clickElement(By by, String log) {
        try {
            WebDriverWait wdWait = new WebDriverWait(driver, waitTime);
            wdWait.until(ExpectedConditions.visibilityOf(driver.findElement(by)));
            wdWait.until(ExpectedConditions.elementToBeClickable(driver.findElement(by)));

            Actions actions = new Actions(driver);
            actions.moveToElement(driver.findElement(by)).click().build().perform();
            System.out.println("Clicked " + log);
        } catch (StaleElementReferenceException e) {
            Actions actions = new Actions(driver);
            actions.moveToElement(driver.findElement(by)).click().build().perform();
            System.out.println("Clicked " + log);
        }
    }

    public void typeText(By by, String text, String log) {
        try {
            WebDriverWait wdWait = new WebDriverWait(driver, waitTime);
            wdWait.until(ExpectedConditions.visibilityOf(driver.findElement(by)));

            driver.findElement(by).clear();
            driver.findElement(by).sendKeys();

            System.out.println("Typed text " + text + " into " + log);
        } catch (StaleElementReferenceException e) {
            driver.findElement(by).clear();
            driver.findElement(by).sendKeys();
            System.out.println("Typed text " + text + " into " + log);
        }
    }

    public String getText(By by, String log) {
        try {
            WebDriverWait wdWait = new WebDriverWait(driver, waitTime);
            wdWait.until(ExpectedConditions.visibilityOf(driver.findElement(by)));

            System.out.println("Get text from " + log);
            return driver.findElement(by).getText();

        } catch (StaleElementReferenceException e) {
            System.out.println("Get text from " + log);
            return driver.findElement(by).getText();
        }
    }

    public boolean isElementPresent(By by){
        return driver.findElements(by).size()>0;
    }

}

