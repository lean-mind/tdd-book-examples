package leanmind.examples


import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.mock.web.MockMultipartFile
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.io.File
import java.io.IOException
import java.io.PrintWriter


@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT
)
@AutoConfigureMockMvc
class CsvFilterAppShould {
    @Autowired
    lateinit var mvc: MockMvc

    val filepath = System.getProperty("java.io.tmpdir") + File.separator + "invoices.csv"
    lateinit var csvFile: File


    @BeforeEach
    fun setUp() {
        csvFile = File(filepath)
    }

    @AfterEach
    fun tearDown() {
        csvFile.delete()
    }

    @Test
    fun display_lines_after_filtering_csv_file() {
        val lines = listOf(
            "Num_factura, Fecha, Bruto, Neto, IVA, IGIC, Concepto, CIF_cliente, NIF_cliente",
            "1,02/05/2019,1000,810,19,,ACER Laptop,B76430134,",
            "2,03/12/2019,1000,2000,19,8,Lenovo Laptop,,78544372A"
        )
        createCsv(lines)
        val pageSource = mvc.perform(
            MockMvcRequestBuilders.multipart(
                Configuration.webUrl + "/csvform"
            )
                .file(
                    MockMultipartFile(
                        "file", filepath,
                        "text/plain",
                        csvFile.inputStream()
                    )
                )
        ).andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn().response.contentAsString
        assertThat(pageSource).contains(lines[0])
        assertThat(pageSource).contains(lines[1])
        assertThat(pageSource).doesNotContain(lines[2])
    }


    private fun createCsv(lines: List<String>) {
        csvFile.printWriter().use { out ->
            lines.forEach {
                out.println(it)
            }
        }
    }
}
