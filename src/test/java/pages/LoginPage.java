package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css="#user-name")
    WebElement username;
    @FindBy(css="#password")
    WebElement password;
    @FindBy(css="#login-button")
    WebElement login;

    /**
     * Use this method to log in to app with valid user and pass
     *
     * @param usernameValue username
     * @param passwordValue password
     */
    public void login(String usernameValue, String passwordValue) {
        enterUsername(usernameValue);
        enterPassword(passwordValue);
        clickLogin();
    }

    public void enterUsername(String usernameValue) {
        typeText(username, usernameValue, "Username Input");
    }

    public void enterPassword(String passwordValue) {
        typeText(password, passwordValue, "Password Input");
    }

    public void clickLogin() {
        clickElement(login, "Login Button");
    }
}