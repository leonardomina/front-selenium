package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;
import java.io.IOException;

public class DriverFactory {
    private static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public static WebDriver getDriver() {
        if (driverThreadLocal.get() == null) {
            WebDriverManager.chromedriver().clearResolutionCache().setup();

            ChromeOptions options = new ChromeOptions();
options.addArguments("--remote-allow-origins=*");
options.addArguments("--disable-dev-shm-usage");
options.addArguments("--no-sandbox");
options.addArguments("--disable-gpu");
options.addArguments("--disable-extensions");

// Adiciona um diretório único para cada sessão
options.addArguments("--user-data-dir=/tmp/chrome-profile-" + System.currentTimeMillis());

WebDriver driver = new ChromeDriver(options);

            driver.manage().window().maximize();
            driverThreadLocal.set(driver);
        }
        return driverThreadLocal.get();
    }

    public static void quitDriver() {
        if (driverThreadLocal.get() != null) {
            driverThreadLocal.get().quit();
            driverThreadLocal.remove();
        }
    }

    public static void initDriver() {
        getDriver(); // Apenas chama `getDriver()` para inicializar o WebDriver
    }


    public static void killDriverProcess() {
        try {
            if (System.getProperty("os.name").toLowerCase().contains("win")) {
                Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe /T");
            } else {
                Runtime.getRuntime().exec("pkill -f chromedriver");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
