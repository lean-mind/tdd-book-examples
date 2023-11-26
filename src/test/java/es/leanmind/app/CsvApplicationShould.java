package es.leanmind.app;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        properties = {"server.port=8080"}
)
public class CsvApplicationShould {

    WebDriver driver = null;
    String fileName = "invoices.csv";
    String filepath = System.getProperty("java.io.tmpdir") + File.separator + fileName;
    File csvFile;

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
        csvFile = new File(filepath);
    }

    @AfterEach
    public void tearDown() {
        csvFile.delete();
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void display_lines_after_filtering_csv_file() {
        var lines = List.of(
                "Num_factura, Fecha, Bruto, Neto, IVA, IGIC, Concepto, CIF_cliente, NIF_cliente",
                "1,02/05/2019,1000,810,19,,ACER Laptop,B76430134,",
                "2,03/12/2019,1000,2000,19,8,Lenovo Laptop,,78544372A");

        createCsv(lines);
        login();
        selectFile();
        submitForm();

        assertThat(driver.getPageSource()).contains(lines.get(0));
        assertThat(driver.getPageSource()).contains(lines.get(1));
        assertThat(driver.getPageSource()).doesNotContain(lines.get(2));
    }


    private void submitForm() {
        driver.findElement(By.id("submit")).click();
    }

    private void selectFile() {
        driver.get(Configuration.WEBURL + "/csvform");
        var input = driver.findElement(By.id("file"));
        input.sendKeys(filepath);
    }

    private void createCsv(List<String> lines) {
        try {
            var printWriter = new PrintWriter(filepath);
            for (String line : lines) {
                printWriter.println(line);
            }
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void login() {
        driver.get(Configuration.WEBURL + Configuration.LOGINURL);

        var usernameTextField = driver.findElement(By.name("username"));
        assertThat(usernameTextField).isNotNull();
        usernameTextField.sendKeys(Configuration.USERNAME);

        var passwordTextField = driver.findElement(By.name("password"));
        assertThat(passwordTextField).isNotNull();
        passwordTextField.sendKeys(Configuration.PASSWORD);

        var submitButton = driver.findElement(By.cssSelector("button[type='submit']"));
        assertThat(submitButton).isNotNull();
        submitButton.click();

        assertThat(
                driver.getCurrentUrl()
        ).isNotEqualTo(
                Configuration.WEBURL + Configuration.LOGINURL + "?error"
        );
    }
}
