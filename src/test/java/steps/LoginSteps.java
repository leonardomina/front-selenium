package steps;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Quando;
import io.cucumber.java.pt.Entao;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;
import utils.DriverFactory;
import org.junit.Assert;

import java.time.Duration;

public class LoginSteps {
    private WebDriver driver;
    private LoginPage loginPage;
    private WebDriverWait wait;

    public LoginSteps() {
        this.driver = DriverFactory.getDriver();
        this.loginPage = new LoginPage(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Dado("que o usuário está na página de login")
    public void usuarioNaPaginaDeLogin() {
        loginPage.acessarPaginaDeLogin();
    }

    @Quando("o usuário insere credenciais válidas")
    public void inserirCredenciaisValidas() {
        loginPage.preencherCredenciais("teste1@test1.com", "123456789");
    }

    @Quando("clica no botão de login")
    public void clicarNoBotaoDeLogin() {
        loginPage.clicarBotaoLogin();
    }

    @Entao("o usuário deve ser redirecionado para a página inicial")
    public void validarPaginaInicial() {
        try {
            WebElement mensagemSucesso = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("swal2-title")));
            Assert.assertTrue("Página inicial não carregou corretamente!", mensagemSucesso.isDisplayed());
            loginPage.clickOkButton();
        } catch (Exception e) {
            Assert.fail("Erro ao validar o redirecionamento para a página inicial: " + e.getMessage());
        }
    }

    @Entao("o usuário deve estar autenticado na aplicação")
    public void verificarUsuarioAutenticado() {
        Assert.assertTrue("Usuário não está autenticado!", loginPage.verificarUsuarioLogado());
    }
}
