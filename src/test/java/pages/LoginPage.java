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

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private By inputEmail = By.id("user");
    private By inputPassword = By.id("password");
    private By btnLogin = By.id("btnLogin");
    private By okButton = By.xpath("//button[text()='OK']");
    private By userProfileIcon = By.id("userLogged");

    public void acessarPaginaDeLogin() {
        driver.get("https://automationpratice.com.br/login");
        wait.until(ExpectedConditions.visibilityOfElementLocated(inputEmail)); // Espera o campo de e-mail aparecer
    }

    public void preencherCredenciais(String email, String senha) {
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(inputEmail));
        emailField.clear();
        emailField.sendKeys(email);

        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(inputPassword));
        passwordField.clear();
        passwordField.sendKeys(senha);
    }

    public void clicarBotaoLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(btnLogin)).click();
    }

    public void clickOkButton() {
        wait.until(ExpectedConditions.elementToBeClickable(okButton)).click();
    }

    public boolean verificarUsuarioLogado() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(userProfileIcon)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
