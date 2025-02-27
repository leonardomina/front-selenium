package runners;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import net.masterthought.cucumber.ReportBuilder;
import utils.DriverFactory;

import java.io.File;
import java.util.Collections;
import java.util.List;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "steps",
        plugin = {
                "pretty",
                "json:target/cucumber-reports/cucumber.json", // JSON necessário para o relatório
                "html:target/cucumber-reports/html-report.html" // Relatório HTML básico
        },
        monochrome = true
)
public class TestRunner {

        @AfterClass
        public static void gerarRelatorio() {
                File reportOutputDirectory = new File("target/cucumber-reports");
                List<String> jsonFiles = Collections.singletonList("target/cucumber-reports/cucumber.json");

                ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, new net.masterthought.cucumber.Configuration(
                        reportOutputDirectory, "Projeto Selenium Cucumber"
                ));

                reportBuilder.generateReports();
        }
        @AfterClass
        public static void tearDownAfterAllTests() {
                if (DriverFactory.getDriver() != null) {
                        DriverFactory.quitDriver(); // Fecha o navegador depois de todos os testes
                }
        }
}
