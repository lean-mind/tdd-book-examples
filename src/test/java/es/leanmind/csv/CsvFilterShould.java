package es.leanmind.csv;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CsvFilterShould {

    public final String headerLine = "Num_factura, Fecha, Bruto, Neto, IVA, IGIC, Concepto, CIF_cliente, NIF_cliente";
    public final String emptyField = "";
    public final List<String> noInvoiceLines = List.of(headerLine);

    @Test
    void allow_for_correct_lines_only() {
        var ivaTax = "19";
        var invoiceLines = fileWithOneInvoiceLineHaving(ivaTax, emptyField);
        var incorrectLine = "A,line,missing,fields,and,wrong,formats,";
        invoiceLines.add(incorrectLine);

        var correctLines = Csv.filter(invoiceLines);

        assertThat(correctLines).isEqualTo(
                fileWithOneInvoiceLineHaving(ivaTax, emptyField)
        );
    }

    @Test
    void exclude_lines_with_both_tax_fields_populated() {
        var ivaTax = "19";
        var igicTax = "8";
        var invoiceLines = fileWithOneInvoiceLineHaving(ivaTax, igicTax);

        var correctLines = Csv.filter(invoiceLines);

        assertThat(correctLines).isEqualTo(noInvoiceLines);
    }

    @Test
    void at_least_one_tax_is_required() {
        var invoiceLines = fileWithOneInvoiceLineHaving(emptyField, emptyField);

        var correctLines = Csv.filter(invoiceLines);

        assertThat(correctLines).isEqualTo(noInvoiceLines);
    }

    @Test
    void exclude_lines_with_non_decimal_tax_fields() {
        var wrongTaxType = "XYZ";
        var invoiceLines = fileWithOneInvoiceLineHaving(wrongTaxType, emptyField);

        var correctLines = Csv.filter(invoiceLines);

        assertThat(correctLines).isEqualTo(noInvoiceLines);
    }

    @Test
    void test_exclude_lines_with_both_taxes_even_with_wrong_format() {
        var wrongIvaType = "XYZ";
        var igicTax = "8";
        var invoiceLines = fileWithOneInvoiceLineHaving(wrongIvaType, igicTax);

        var correctLines = Csv.filter(invoiceLines);

        assertThat(correctLines).isEqualTo(noInvoiceLines);
    }

    private ArrayList<String> fileWithOneInvoiceLineHaving(String ivaTax, String igicTax) {
        var invoiceId = "1";
        var invoiceDate = "02/05/2019";
        var grossAmount = "1000";
        var netAmount = "810";
        var concept = "ACER Laptop";
        var cif = "B76430134";
        var nif = "";
        String invoiceLine =
                String.join(",",
                        invoiceId,
                        invoiceDate,
                        grossAmount,
                        netAmount,
                        ivaTax,
                        igicTax,
                        concept,
                        cif,
                        nif
                );
        return new ArrayList<>(List.of(headerLine, invoiceLine));
    }
}
