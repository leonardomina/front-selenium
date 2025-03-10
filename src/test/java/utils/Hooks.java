package hooks;

import org.openqa.selenium.WebDriver;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import pages.LoginPage;
import utils.DriverFactory;

public class Hooks {
    private WebDriver driver;
    private LoginPage loginPage;

    @Before
    public void setup() {
        driver = DriverFactory.getDriver();
        loginPage = new LoginPage(driver);
        loginPage.acessarPaginaDeLogin();
        loginPage.preencherCredenciais("teste1@teste1.com", "123456789");
        loginPage.clicarBotaoLogin();
        loginPage.clickOkButton();
    }

    @After
    public void teardown() {
        DriverFactory.quitDriver(); // Fecha o navegador ao final
    }
}
