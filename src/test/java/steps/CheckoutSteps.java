package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.pt.*;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.CheckoutPage;
import utils.DriverFactory;
import org.junit.Assert;
import java.time.Duration;

public class CheckoutSteps {
    private WebDriver driver;
    private CheckoutPage checkoutPage;
    private WebDriverWait wait;

    public CheckoutSteps() {
        this.driver = DriverFactory.getDriver();
        this.checkoutPage = new CheckoutPage(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @Dado("que o usuário acessa Shop Four Grid e adiciona produtos ao carrinho")
    public void acessarShopEAdicionarProdutos() {
        checkoutPage.irParaShopFourGrid();
        checkoutPage.adicionarProdutosAoCarrinho("T-Shirt For Men", "Maxi Dress");
    }

    @And("que o usuário aguarda a atualização do carrinho")
    public void aguardarAtualizacaoCarrinho() {
        try {
            System.out.println("⏳ Aguardando atualização do total do carrinho...");
            String valorAnterior = checkoutPage.capturarTotalCarrinho();

            wait.until(driver -> {
                String novoValor = checkoutPage.capturarTotalCarrinho();
                return !novoValor.equals(valorAnterior) && !novoValor.isEmpty();
            });

            System.out.println("✅ Total do carrinho atualizado: " + checkoutPage.capturarTotalCarrinho());
        } catch (TimeoutException e) {
            throw new RuntimeException("⏳ Tempo esgotado ao aguardar atualização do carrinho!");
        }
    }


    @And("o total do carrinho deve ser {double}")
    public void validarTotalEsperado(double esperado) {
        String totalCarrinho = checkoutPage.capturarTotalCarrinho();

        if (!totalCarrinho.contains(String.valueOf(esperado))) {
            throw new RuntimeException("❌ Total inesperado! Esperado: " + esperado + " - Obtido: " + totalCarrinho);
        }

        System.out.println("✅ Total validado corretamente: " + totalCarrinho);
    }


    @Quando("clica no botão Proceed to Checkout")
    public void clicarProceedToCheckout() {
        checkoutPage.clicarProceedToCheckout();
    }

    @E("preenche os campos do formulário de checkout")
    public void preencherFormularioCheckout(io.cucumber.datatable.DataTable dados) {
        var info = dados.asMap(String.class, String.class);
        checkoutPage.preencherFormulario(
                info.get("First Name"), info.get("Last Name"), info.get("Company Name"),
                info.get("Email Address"), info.get("Country"), info.get("State/City"),
                info.get("Zip Code"), info.get("Full Address"), info.get("Additional Notes"));
    }

    @E("clica no botão Save")
    public void clicarBotaoSalvar() throws InterruptedException {
        checkoutPage.clicarBotaoSalvar();
    }

    @E("clica no botão Place Order")
    public void clicarBotaoFinalizarPedido() {
        checkoutPage.clicarBotaoFinalizarPedido();
    }

    @Entao("a mensagem {string} deve ser exibida")
    public void validarMensagemSucesso(String mensagemEsperada) {
        Assert.assertTrue(checkoutPage.validarMensagemSucesso(mensagemEsperada));
    }
}
