package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutPage {
    WebDriver driver;
    private WebDriverWait wait;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(3)); // Aguarda até 3s

    }

    // Localizadores
    By shopButton = By.xpath("//i[contains(@class, 'fa fa-shopping-bag')]");
    By totalValue = By.className("offcanvas-cart-total-price");
    By lastName = By.id("lname");
    By companyName = By.id("cname");
    By email = By.id("email");
    By stateCity = By.id("city");
    By zipCode = By.id("zip");
    By fullAddress = By.id("faddress");
    By additionalNotes = By.id("messages");

    // Métodos da página
    public void irParaShop() throws InterruptedException {
        Thread.sleep(2500);
        driver.findElement(shopButton).click();
    }

    public void validarTotalCarrinho(String expectedTotal) {
        driver.findElement(totalValue).isDisplayed();
    }

    public void clicarProceedToCheckout() throws InterruptedException {
        Thread.sleep(2500);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement checkoutButton = driver.findElement(By.xpath("//nav//a[contains(text(), 'Checkout')]"));
        js.executeScript("arguments[0].click();", checkoutButton);
    }

    public void preencherFormulario(String fName, String lName, String company, String emailAddr, String countryOpt, String stateOpt, String zip, String address, String notes) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement firstNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("fname")));
        firstNameField.sendKeys(fName);
        driver.findElement(lastName).sendKeys(lName);
        driver.findElement(companyName).sendKeys(company);
        driver.findElement(email).sendKeys(emailAddr);

        WebElement countryDropdown = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("country")));
        Select countrySelect = new Select(countryDropdown);
        countrySelect.selectByVisibleText("usa");
        countrySelect.selectByVisibleText("Afghanistan");

        Select stateSelect = new Select(driver.findElement(stateCity));
        stateSelect.selectByVisibleText(stateOpt);

        driver.findElement(zipCode).sendKeys(zip);
        driver.findElement(fullAddress).sendKeys(address);
        driver.findElement(additionalNotes).sendKeys(notes);
    }

    public void clicarBotaoSalvar() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        WebElement saveButton = driver.findElement(By.xpath("//section[2]//button[contains(text(),'Save')]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", saveButton);

    }

    public void clicarBotaoFinalizarPedido() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        WebElement saveButton = driver.findElement(By.xpath("//section[2]//button[contains(text(),'Place Order')]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", saveButton);
    }

    public boolean validarMensagemSucesso(String mensagem) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        WebElement msgElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[contains(text(),'Order success!')]")));
        return msgElement.getText().equals(mensagem);
    }
}
