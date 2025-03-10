package pages;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.MoveTargetOutOfBoundsException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CheckoutPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private CheckoutPage checkoutPage;

    private void esperar(int i) {

    }

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Timeout de 20s para evitar falhas
    }

    // Localizadores
    private final By shopButton = By.cssSelector("#root > header > div > div > div > div > div > ul > li:nth-child(2) > a");
    private final By totalValue = By.xpath("//span[@id='cart-total']");
    private final By checkoutButton = By.cssSelector("#offcanvas-add-cart > div.offcanvas-add-cart-wrapper > ul.offcanvas-cart-action-button > li:nth-child(2) > a");
    private final By shopFourGrid = By.xpath("//a[contains(text(), 'Shop Four Grid')]");
    private final By addToCartButton = By.xpath("//button[contains(text(),'Add To Cart')]");
    private final By dropShop = By.xpath("//*[@id=\"root\"]/header/div/div/div/div/div/div[2]/nav/ul/li[2]/a");


    // Formulário de Checkout
    private final By firstName = By.id("fname");
    private final By lastName = By.id("lname");
    private final By companyName = By.id("cname");
    private final By email = By.id("email");
    private final By country = By.id("country");
    private final By stateCity = By.id("city");
    private final By zipCode = By.id("zip");
    private final By fullAddress = By.id("faddress");
    private final By additionalNotes = By.id("messages");
    private final By saveButton = By.cssSelector("#checkout_one > div > div > div:nth-child(1) > div > button");
    private final By placeOrderButton = By.xpath("//button[contains(text(),'Place Order')]");
    private final By orderSuccessMessage = By.xpath("//h2[contains(text(),'Order success!')]");



    /**
     * Acessa a página "Shop Four Grid" garantindo que o menu dropdown seja acionado corretamente.
     */
    public void irParaShopFourGrid() {
        try {
            // Localiza o botão do menu principal "SHOP"
            WebElement menuShop = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//*[@id=\"root\"]/header/div/div/div/div/div/div[2]/nav/ul/li[2]/a")));

            // Passa o mouse sobre o menu para abrir o dropdown
            Actions actions = new Actions(driver);
            actions.moveToElement(menuShop).perform();

            ((JavascriptExecutor) driver).executeScript("arguments[0].style.display='block';", menuShop);

            // Aguarda a opção "Shop Four Grid" ficar visível
            WebElement shopGrid = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//a[contains(text(), 'Shop Four Grid')]")));

            // Clica na opção
            shopGrid.click();

            // Aguarda a página carregar para evitar cliques prematuros
            wait.until(ExpectedConditions.urlContains("shop"));

        } catch (Exception e) {
            throw new RuntimeException("Erro ao acessar Shop Four Grid: " + e.getMessage());
        }
    }

    /**
     * Valida se o valor total do carrinho está correto.
     */
    public void validarTotalCarrinho(double valorEsperado) {
        try {
            WebElement totalElement = wait.until(ExpectedConditions.visibilityOfElementLocated(totalValue));
            String totalTexto = totalElement.getText().replace("$", "").trim();
            totalTexto = totalTexto.replace(",", ".");
            double totalCalculado = Double.parseDouble(totalTexto);

            Assert.assertEquals("O total do carrinho está incorreto!", valorEsperado, totalCalculado, 0.01);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao validar o total do carrinho: " + e.getMessage());
        }
    }

    /**
     * Clica no botão "Proceed to Checkout".
     */
    public void clicarProceedToCheckout() {
        WebElement checkoutBtn = wait.until(ExpectedConditions.elementToBeClickable(checkoutButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkoutBtn);
    }

    /**
     * Preenche os campos do formulário de checkout.
     */
    public void preencherFormulario(String fName, String lName, String company, String emailAddr, String countryOpt, String stateOpt, String zip, String address, String notes) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstName)).sendKeys(fName);
        driver.findElement(lastName).sendKeys(lName);
        driver.findElement(companyName).sendKeys(company);
        driver.findElement(email).sendKeys(emailAddr);

        Select countrySelect = new Select(wait.until(ExpectedConditions.presenceOfElementLocated(country)));
        countrySelect.selectByVisibleText(countryOpt);

        Select stateSelect = new Select(driver.findElement(stateCity));
        stateSelect.selectByVisibleText(stateOpt);

        driver.findElement(zipCode).sendKeys(zip);
        driver.findElement(fullAddress).sendKeys(address);
        driver.findElement(additionalNotes).sendKeys(notes);
    }

    /**
     * Clica no botão "Save".
     */
    public void clicarBotaoSalvar() throws InterruptedException {
        WebElement botaoSalvar = driver.findElement(By.cssSelector("#checkout_one > div > div > div:nth-child(1) > div > button"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", botaoSalvar);
        Thread.sleep(500); // Pequeno tempo para garantir que a rolagem foi feita
        botaoSalvar.click();
    }

    /**
     * Clica no botão "Place Order".
     */
    public void clicarBotaoFinalizarPedido() {
        wait.until(ExpectedConditions.elementToBeClickable(placeOrderButton)).click();
    }

    /**
     * Valida se a mensagem de sucesso "Order success!" aparece após a finalização do pedido.
     */
    public boolean validarMensagemSucesso(String mensagemEsperada) {
        WebElement msgElement = wait.until(ExpectedConditions.visibilityOfElementLocated(orderSuccessMessage));
        return msgElement.getText().equalsIgnoreCase(mensagemEsperada);
    }

    public String capturarTotalCarrinho() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // 🛒 Captura o total do carrinho direto
            WebElement cartTotalElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("#offcanvas-add-cart span.offcanvas-cart-total-price-value")
            ));


            String total = driver.findElement(By.cssSelector("#offcanvas-add-cart span.offcanvas-cart-total-price-value")).getText().trim();
            System.out.println("🛒 Total capturado: " + total);
            return total;

        } catch (TimeoutException e) {
            throw new RuntimeException("⏳ Tempo esgotado ao capturar total do carrinho!");
        } catch (Exception e) {
            throw new RuntimeException("❌ Erro ao capturar total do carrinho: " + e.getMessage());
        }
    }

    public void validarTotalEsperado(double esperado) {
        String totalCarrinho = capturarTotalCarrinho();

        if (!totalCarrinho.contains(String.valueOf(esperado))) {
            throw new RuntimeException("❌ Total inesperado! Esperado: " + esperado + " - Obtido: " + totalCarrinho);
        }

        System.out.println("✅ Total validado corretamente: " + totalCarrinho);
    }


    public void adicionarProdutosAoCarrinho(String... nomesProdutos) {
        try {
            Actions actions = new Actions(driver);
            JavascriptExecutor js = (JavascriptExecutor) driver;

            for (String nomeProduto : nomesProdutos) {
                try {
                    System.out.println("🔄 Tentando adicionar o produto: " + nomeProduto);

                    // **1️⃣ Localiza o produto na página**
                    WebElement produto = wait.until(ExpectedConditions.presenceOfElementLocated(
                            By.xpath("//a[contains(text(), '" + nomeProduto + "')]")));

                    // **2️⃣ Rola até o produto**
                    js.executeScript("arguments[0].scrollIntoView({block: 'center'});", produto);
                    Thread.sleep(1000);

                    // **3️⃣ Simula o movimento do mouse até o produto**
                    actions.moveToElement(produto).perform();
                    Thread.sleep(500);

                    // **4️⃣ Localiza o botão "Add to Cart"**
                    WebElement addToCartBtn = wait.until(ExpectedConditions.presenceOfElementLocated(
                            By.xpath("//a[contains(text(), '" + nomeProduto + "')]/ancestor::div[contains(@class, 'product')]//button[contains(text(), 'Add to cart')]")));

                    if (!addToCartBtn.isDisplayed()) {
                        System.out.println("⚠️ Forçando visibilidade do botão...");
                        js.executeScript("arguments[0].style.display = 'block';", addToCartBtn);
                        Thread.sleep(500);
                    }


                    // **5️⃣ Aguarda até que o botão esteja visível e clicável**
                    wait.until(ExpectedConditions.visibilityOf(addToCartBtn));
                    wait.until(ExpectedConditions.elementToBeClickable(addToCartBtn));

                    // Simula clique realista no botão "Add to Cart"
                    actions.moveToElement(addToCartBtn).click().perform();
                    System.out.println("✅ Produto adicionado ao carrinho: " + nomeProduto);

                    Thread.sleep(2000); // ✅ Aguarda atualização do carrinho antes de capturar total

                } catch (TimeoutException e) {
                    System.out.println("⏳ Tempo esgotado ao adicionar produto " + nomeProduto + ": " + e.getMessage());
                } catch (Exception e) {
                    System.out.println("❌ Erro ao adicionar produto " + nomeProduto + ": " + e.getMessage());
                }
            }

            // **🔹 Agora que todos os produtos foram adicionados, abre o carrinho**
            abrirCarrinho();

        } catch (Exception e) {
            throw new RuntimeException("⚠️ Erro ao adicionar produtos ao carrinho: " + e.getMessage());
        }
    }

    public void abrirCarrinho() {
        try {
            WebElement shopBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#root > header > div > div > div > div > div > ul > li:nth-child(2) > a")));

            // ✅ Garante que o botão está visível antes de clicar
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", shopBtn);
            Thread.sleep(1000);

            shopBtn.click();
            System.out.println("🛒 Carrinho aberto com sucesso.");

            // ✅ Aguarda o carrinho permanecer visível
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#root > header > div > div > div > div > div > ul > li:nth-child(2) > a")));

        } catch (Exception e) {
            throw new RuntimeException("❌ Erro ao abrir o carrinho: " + e.getMessage());
        }
    }

    public void aguardarAtualizacaoCarrinho() {
        int tentativas = 10; // Reduzindo o número de tentativas
        String totalAnterior = "";

        for (int i = 0; i < tentativas; i++) {
            String totalAtual = checkoutPage.capturarTotalCarrinho();
            System.out.println("🛒 Tentativa " + (i + 1) + " - Total do carrinho: " + totalAtual);

            // Se o total for diferente do anterior, assume que foi atualizado
            if (!totalAtual.equals(totalAnterior) && !totalAtual.isEmpty()) {
                System.out.println("✅ Total atualizado detectado: " + totalAtual);
                return;
            }

            totalAnterior = totalAtual;
            esperar(1000); // Espera de 1 segundo antes de tentar novamente
        }

        throw new RuntimeException("⏳ Tempo esgotado ao aguardar atualização do carrinho!");
    }


}
