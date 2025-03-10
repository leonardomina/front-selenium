package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Representa a página inicial (Home) da aplicação, que é exibida após o login.
 */
public class HomePage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // Localizador de um elemento característico da HomePage (ex: logo, banner)
    private final By homeLogo = By.id("swal2-title");

    /**
     * Construtor que inicializa a página com o WebDriver.
     * @param driver Instância do WebDriver utilizada para interagir com os elementos.
     */
    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Timeout de 10s
    }

    /**
     * Verifica se o usuário está na página inicial.
     * @return true se o elemento característico da HomePage estiver visível, false caso contrário.
     */
    public boolean isAt() {
        try {
            WebElement logo = wait.until(ExpectedConditions.visibilityOfElementLocated(homeLogo));
            return logo.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
