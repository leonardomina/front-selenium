package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.CheckoutPage;
import utils.DriverFactory;

import java.util.Map;

public class CheckoutSteps {
    WebDriver driver = DriverFactory.getDriver(); // Obtém uma instância do WebDriver
    CheckoutPage checkoutPage = new CheckoutPage(driver);

    @Given("que o usuário acessa a página Shop")
    public void acessarPaginaShop() throws InterruptedException {
        checkoutPage.irParaShop();
    }


    @And("valida o total {string}")
    public void validarItensNoCarrinho(String expectedTotal) {
        checkoutPage.validarTotalCarrinho(expectedTotal);
    }

    @When("clica no botão Proceed to Checkout")
    public void clicarProceedToCheckout() throws InterruptedException {
        checkoutPage.clicarProceedToCheckout();
    }

    @And("preenche os campos do formulário de checkout")
    public void preencherFormularioCheckout(DataTable dataTable) throws InterruptedException {
        Map<String, String> dados = dataTable.asMap(String.class, String.class);

        checkoutPage.preencherFormulario(
                dados.get("First Name"),
                dados.get("Last Name"),
                dados.get("Company Name"),
                dados.get("Email Address"),
                dados.get("Country"),
                dados.get("State/City"),
                dados.get("Zip Code"),
                dados.get("Full Address"),
                dados.get("Additional Notes")
        );

    }


    @And("clica no botão Save")
    public void clicarBotaoSalvar() {
        checkoutPage.clicarBotaoSalvar();
    }

    @And("clica no botão Place Order")
    public void clicarBotaoFinalizarPedido() {
        checkoutPage.clicarBotaoFinalizarPedido();
    }

    @Then("a mensagem {string} deve ser exibida")
    public void validarMensagemSucesso(String mensagemEsperada) {
        Assert.assertTrue("Mensagem de sucesso não encontrada!", checkoutPage.validarMensagemSucesso(mensagemEsperada));
    }
}
