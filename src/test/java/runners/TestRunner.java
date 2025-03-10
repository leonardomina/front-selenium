package runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import utils.DriverFactory;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"steps", "hooks"},
        plugin = {"pretty", "html:target/cucumber-reports.html"},
        monochrome = true
)
public class TestRunner {

        @AfterClass
        public static void tearDownAfterAllTests() {
                System.out.println("⚡ Finalizando testes e fechando navegador...");

                if (DriverFactory.getDriver() != null) {
                        DriverFactory.quitDriver(); // Fecha o navegador apenas se ainda estiver ativo
                }
        }
}
