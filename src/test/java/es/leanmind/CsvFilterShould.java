package es.leanmind;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CsvFilterShould {

    @Test
    void test_allow_for_correct_lines_only() {
        var headerLine = "Num_factura, Fecha, Bruto, Neto, IVA, IGIC, Concepto, CIF_cliente, NIF_cliente";
        var invoiceLine = "1,02/05/2019,1000,810,19,,ACER Laptop,B76430134,";
        var incorrectLine = "A,line,missing,fields,and,wrong,formats,";

        List<String> correctLines = Csv.filter(List.of(headerLine, invoiceLine, incorrectLine));

        assertThat(correctLines).isEqualTo(List.of(headerLine, invoiceLine));
    }
}
