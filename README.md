# 🏆 Projeto de Automação de Testes E2E - Selenium + Cucumber + Java

Este repositório contém um projeto de **testes automatizados End-to-End (E2E)** utilizando **Java, Selenium WebDriver, Cucumber, JUnit e ExtentReports**. O objetivo é automatizar cenários de testes web e gerar relatórios detalhados.

---

## 📌 **1. Pré-requisitos**
Antes de começar, certifique-se de ter os seguintes programas instalados:

### 🖥️ **Softwares necessários**
| Programa         | Versão Requerida       | Link de Download |
|-----------------|----------------------|------------------|
| **Java JDK**    | 11+                   | [Baixar JDK](https://adoptium.net/) |
| **IntelliJ IDEA** | Última versão (Community ou Ultimate) | [Baixar IntelliJ IDEA](https://www.jetbrains.com/idea/download/) |
| **Maven**       | 3.8+                   | [Baixar Maven](https://maven.apache.org/download.cgi) |
| **ChromeDriver** | Versão compatível com o Chrome | [Baixar ChromeDriver](https://chromedriver.chromium.org/downloads) |
| **Git**         | Última versão          | [Baixar Git](https://git-scm.com/downloads) |

---

## 📌 **2. Configuração do Ambiente**
### 🔹 **2.1. Instalar e Configurar o Java**
1. Baixe e instale o **JDK 11 ou superior**.
2. Configure a variável de ambiente `JAVA_HOME`:
    - No Windows, vá para **Configurações do Sistema → Variáveis de Ambiente** e adicione:
      ```
      JAVA_HOME=C:\Program Files\Java\jdk-11
      ```
    - Adicione o diretório `bin` do JDK ao **PATH**:
      ```
      C:\Program Files\Java\jdk-11\bin
      ```
    - No Linux/Mac, adicione ao `~/.bashrc` ou `~/.zshrc`:
      ```sh
      export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk-11/Contents/Home
      export PATH=$JAVA_HOME/bin:$PATH
      ```
3. Para verificar a instalação, execute no terminal:
   ```sh
   java -version


### 🔹 2.2. Instalar e Configurar o Maven
1. Baixe o Maven e extraia o arquivo zip.
2. Configure a variável de ambiente MAVEN_HOME e adicione bin ao PATH:

MAVEN_HOME=C:\apache-maven-3.8.6
PATH=%MAVEN_HOME%\bin

3. Para testar, execute:

mvn -version

### 🔹 2.3. Clonar o Projeto

git clone https://github.com/seu-usuario/seu-repositorio.git
cd seu-repositorio

## 📌 3. Estrutura do Projeto

📂 meu-projeto-automation/
├── 📂 src
│   ├── 📂 main
│   │   └── 📂 java (se necessário, classes de apoio)
│   ├── 📂 test
│   │   ├── 📂 java
│   │   │   ├── 📂 runners          # Arquivo para executar os testes
│   │   │   ├── 📂 steps            # Step Definitions
│   │   │   ├── 📂 pages            # Page Objects
│   │   │   ├── 📂 utils            # Configuração do WebDriver
│   │   ├── 📂 resources
│   │   │   ├── 📂 features         # Arquivos .feature do Cucumber
├── 📜 pom.xml                      # Configuração do Maven
├── 📜 README.md                    # Documentação

## 📌 4. Configuração do IntelliJ IDEA
1. Abrir o Projeto:
Abra o IntelliJ IDEA e selecione "Open" → Escolha a pasta do projeto.

2. Configurar o Maven no IntelliJ:
Vá em File → Settings → Build, Execution, Deployment → Maven
Em "Maven home directory", selecione o caminho do Maven instalado.

3. Instalar Plugins no IntelliJ (Opcional):

- ✅ Cucumber for Java
- ✅ Gherkin
## 📌 5. Dependências no pom.xml
Certifique-se de que o arquivo pom.xml contém as dependências necessárias:
```
<dependencies>
<!-- Selenium -->
<dependency>
<groupId>org.seleniumhq.selenium</groupId>
<artifactId>selenium-java</artifactId>
<version>4.8.0</version>
</dependency>

    <!-- Cucumber -->
    <dependency>
        <groupId>io.cucumber</groupId>
        <artifactId>cucumber-java</artifactId>
        <version>7.14.0</version>
    </dependency>

    <dependency>
        <groupId>io.cucumber</groupId>
        <artifactId>cucumber-junit</artifactId>
        <version>7.14.0</version>
        <scope>test</scope>
    </dependency>

    <!-- ExtentReports (Relatórios) -->
    <dependency>
        <groupId>com.aventstack</groupId>
        <artifactId>extentreports</artifactId>
        <version>5.0.9</version>
    </dependency>
</dependencies>
Após modificar o pom.xml, execute no terminal:
```

mvn clean install
## 📌 6. Executando os Testes
Para rodar os testes, use um dos métodos abaixo:

### 🔹 Via Terminal

mvn test
### 🔹 Executando no IntelliJ
- Vá até a classe TestRunner.java
- Clique com o botão direito e selecione Run 'TestRunner'.
## 📌 7. Geração de Relatórios
Após a execução dos testes, os relatórios são gerados em:

- target/cucumber-reports/cucumber.json
- target/cucumber-reports/html-report.html
Abra o arquivo target/cucumber-reports/html-report.html no navegador para visualizar os resultados.

## 📌 8. Captura de Evidências (Screenshots)
Os prints são capturados automaticamente ao final de cada passo do teste e adicionados ao relatório.

Os arquivos de screenshot são salvos em:
```
📂 screenshots/
```
## 📌 9. Problemas Comuns e Soluções
### ❌ Erro: "chromedriver executable needs to be in PATH"
### ✅ Solução: Certifique-se de que o ChromeDriver correto está instalado e configurado no PATH.

### ❌ Erro: "Cannot find cucumber.json"
### ✅ Solução: Verifique se o json:target/cucumber-reports/cucumber.json está corretamente configurado no @CucumberOptions.

### ❌ Erro: ElementClickInterceptedException
### ✅ Solução: Se um elemento não for clicável, use JavaScriptExecutor ou role até ele antes de clicar:
```
WebElement botaoSalvar = driver.findElement(By.cssSelector("#checkout_one > div > div > div:nth-child(1) > div > button"));
JavascriptExecutor executor = (JavascriptExecutor) driver;
executor.executeScript("arguments[0].click();", botaoSalvar);
```
## 📌 10. Contribuição
1. Fork este repositório
2. Crie um branch com sua feature:
```
git checkout -b minha-feature
```
3. Faça o commit das mudanças:
```
git commit -m "Adicionei uma nova feature"
```
4. Faça um push para o branch:
```
git push origin minha-feature
```
5. Abra um Pull Request
## 🚀 Agora é só rodar os testes e garantir a qualidade do seu projeto! 🏆
