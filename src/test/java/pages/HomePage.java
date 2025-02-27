package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Representa a página inicial (Home) da aplicação, que é exibida após o login.
 */
public class HomePage {

    WebDriver driver;

    // Localizador de um elemento característico da HomePage (ex: logo, banner)
    By homeLogo = By.id("swal2-title");

    /**
     * Construtor que inicializa a página com o WebDriver.
     * @param driver Instância do WebDriver utilizada para interagir com os elementos.
     */
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Verifica se o usuário está na página inicial.
     * @return true se o elemento característico da HomePage estiver visível, false caso contrário.
     */
    public boolean isAt() {
        return driver.findElement(homeLogo).isDisplayed();
    }
}
