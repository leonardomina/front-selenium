package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;


    // Locators
    By usernameField = By.id("user");
    By passwordField = By.id("password");
    By loginButton = By.id("btnLogin");
    By okButton = By.cssSelector("body > div.swal2-container.swal2-center.swal2-backdrop-show > div > div.swal2-actions > button.swal2-confirm.swal2-styled");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Espera até 10 segundos
    }

    /**
     * Insere o nome de usuário no campo apropriado.
     * @param username valor do nome de usuário
     */

    public void enterUsername(String username) {
        WebElement userField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user")));
        userField.sendKeys(username);
    }


    /**
     * Insere a senha no campo apropriado.
     * @param password valor da senha
     */
    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    /**
     * Clica no botão de login para submeter o formulário.
     */
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }
    public void clickokButton() {
        driver.findElement(okButton).click();
    }
}
