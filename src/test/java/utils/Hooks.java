package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Hooks {
    private static WebDriver driver;

    @Before
    public void setUp() {
        if (driver == null) { // Evita abrir múltiplas instâncias do navegador
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            throw new IllegalStateException("O WebDriver não foi inicializado corretamente");
        }
        return driver;
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null; // Reseta para evitar manter sessões abertas
        }
    }
}
