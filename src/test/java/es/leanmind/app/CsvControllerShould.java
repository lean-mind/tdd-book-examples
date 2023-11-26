package es.leanmind.app;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "admin", password = "admin")
public class CsvControllerShould {
    @Autowired
    MockMvc mvc;

    final String fileName = "invoices.csv";
    final String filepath = System.getProperty("java.io.tmpdir") + File.separator + fileName;
    File csvFile;

    @BeforeEach
    public void setUp() {
        csvFile = new File(filepath);
    }

    @AfterEach
    public void tearDown() {
        csvFile.delete();
    }

    @Test
    public void display_lines_after_filtering_csv_file() throws Exception {
        var lines = List.of(
                "Num_factura, Fecha, Bruto, Neto, IVA, IGIC, Concepto, CIF_cliente, NIF_cliente",
                "1,02/05/2019,1000,810,19,,ACER Laptop,B76430134,",
                "2,03/12/2019,1000,2000,19,8,Lenovo Laptop,,78544372A");

        createCsv(lines);

        var pageSource = mvc.perform(
                        MockMvcRequestBuilders.multipart(Configuration.WEBURL + "/csvform")
                                .file(new MockMultipartFile(
                                        "file", filepath,
                                        "text/plain",
                                        new FileInputStream(csvFile))))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();


        assertThat(pageSource).contains(lines.get(0));
        assertThat(pageSource).contains(lines.get(1));
        assertThat(pageSource).doesNotContain(lines.get(2));
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
}
