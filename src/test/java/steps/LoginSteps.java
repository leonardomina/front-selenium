package steps;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Quando;
import io.cucumber.java.pt.Entao;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;
import pages.HomePage;
import utils.DriverFactory;

import java.time.Duration;

public class LoginSteps {

    WebDriver driver = DriverFactory.getDriver();
    LoginPage loginPage = new LoginPage(driver);
    HomePage homePage = new HomePage(driver);

    @Dado("que o usuário está na página de login")
    public void usuarioNaPaginaDeLogin() {
        driver.get("https://automationpratice.com.br/login"); // URL da página de login
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("user"))); // Espera o campo de login aparecer
    }

    @Quando("o usuário insere credenciais válidas")
    public void inserirCredenciaisValidas() {
        // Preenche os campos de usuário e senha
        loginPage.enterUsername("teste1@teste1.com");
        loginPage.enterPassword("123456789");
    }

    @Dado("clica no botão de login")
    public void clicarNoBotaoDeLogin() {
        // Clica no botão de login e submete o formulário
        loginPage.clickLoginButton();
    }

    @Entao("o usuário deve ser redirecionado para a página inicial")
    public void validarPaginaInicial() {
        // Verifica se a página inicial foi carregada com sucesso
        Assert.assertTrue("Login realizado", homePage.isAt());
        loginPage.clickokButton();
    }
}
