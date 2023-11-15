package es.leanmind;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CsvFilterShould {
    @Test
    void allow_for_correct_lines_only() {
        var headerLine = "Num_factura, Fecha, Bruto, Neto, IVA, IGIC, Concepto, CIF_cliente, NIF_cliente";
        var invoiceLine = "1,02/05/2019,1000,810,19,,ACER Laptop,B76430134,";
        var incorrectLine = "A,line,missing,fields,and,wrong,formats,";

        var correctLines = Csv.filter(List.of(headerLine, invoiceLine, incorrectLine));

        assertThat(correctLines).isEqualTo(List.of(headerLine, invoiceLine));
    }

    @Test
    void exclude_lines_with_both_tax_fields_populated() {
        var headerLine = "Num_factura, Fecha, Bruto, Neto, IVA, IGIC, Concepto, CIF_cliente, NIF_cliente";
        var iva = "19";
        var igic = "8";
        var invoiceLine = "1,02/05/2019,1000,810," + iva + "," + igic + ",ACER Laptop,B76430134,";

        var correctLines = Csv.filter(List.of(headerLine, invoiceLine));

        assertThat(correctLines).isEqualTo(List.of(headerLine));
    }
}
