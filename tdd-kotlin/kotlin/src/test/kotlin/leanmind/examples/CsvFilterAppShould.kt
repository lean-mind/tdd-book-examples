package leanmind.examples


import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import java.io.File
import java.io.IOException
import java.io.PrintWriter


@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT
)
class CsvFilterAppShould {
    @Value("\${chrome.path}")
    lateinit var chromePath: String
    lateinit var driver: WebDriver

    val filepath = System.getProperty("java.io.tmpdir") + File.separator + "invoices.csv"
    lateinit var csvFile: File

    @BeforeEach
    fun setUp() {
        System.setProperty(
            "webdriver.chrome.driver",
            chromePath
        )
        driver = ChromeDriver()
        csvFile = File(filepath)
    }

    @AfterEach
    fun tearDown() {
        csvFile.delete()
        driver.close()
    }

    @Test
    fun display_lines_after_filtering_csv_file() {
        val lines = listOf(
            "Num_factura, Fecha, Bruto, Neto, IVA, IGIC, Concepto, CIF_cliente, NIF_cliente",
            "1,02/05/2019,1000,810,19,,ACER Laptop,B76430134,",
            "2,03/12/2019,1000,2000,19,8,Lenovo Laptop,,78544372A"
        )
        createCsv(lines)
        login()
        selectFile()
        submitForm()

        assertThat(driver.pageSource).contains(lines[0])
        assertThat(driver.pageSource).contains(lines[1])
        assertThat(driver.pageSource).doesNotContain(lines[2])
    }

    private fun submitForm() {
        driver.findElement(By.id("submit")).click()
    }

    private fun selectFile() {
        driver.get(Configuration.webUrl + "/csvform")
        val input = driver.findElement(By.id("file"))
        input.sendKeys(filepath)
    }

    private fun createCsv(lines: List<String>) {
        try {
            val printWriter = PrintWriter(filepath)
            for (line in lines) {
                printWriter.println(line)
            }
            printWriter.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun login(
        username: String = Configuration.username,
        password: String = Configuration.password
    ) {
        driver.get(Configuration.webUrl + Configuration.loginUrl)
        driver.findElement(By.name("username"))?.sendKeys(username)
        driver.findElement(By.name("password"))?.sendKeys(password)
        driver.findElement(By.cssSelector("button[type='submit']"))?.click()

        assertThat(driver.currentUrl)
            .isNotEqualTo(
                Configuration.webUrl +
                    Configuration.loginUrl + "?error"
            )
    }
}
