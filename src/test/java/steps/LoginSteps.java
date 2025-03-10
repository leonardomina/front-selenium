package steps;

import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Hooks;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class LoginSteps {
    private WebDriver driver;

    public LoginSteps() {
        this.driver = Hooks.getDriver();
    }

    @Given("que o usuário está na página de login")
    public void que_o_usuário_está_na_página_de_login() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://automationpratice.com.br/login");
    }

    @When("o usuário insere credenciais válidas")
    public void oUsuárioInsereCredenciaisVálidas() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement emailInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("email")));

        emailInput.sendKeys("usuario@example.com");
        driver.findElement(By.id("password")).sendKeys("senha123");
    }


    @When("clica no botão de login")
    public void clica_no_botão_de_login() {
        WebElement botaoLogin = driver.findElement(By.id("login-button"));
        botaoLogin.click();
    }

    @Then("o usuário deve ser redirecionado para a página inicial")
    public void o_usuário_deve_ser_redirecionado_para_a_página_inicial() {
        assertTrue(driver.getCurrentUrl().contains("home"));
        driver.quit();
    }
}
