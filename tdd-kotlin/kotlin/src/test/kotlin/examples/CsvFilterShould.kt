package examples

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CsvFilterShould {
    private val headerLine = "Num_factura, Fecha, Bruto, Neto, IVA, IGIC, Concepto, CIF_cliente, NIF_cliente"
    private lateinit var filter: CsvFilter
    private val emptyDataFile = listOf(headerLine)
    private val emptyField = ""

    @BeforeEach
    fun setup() {
        filter = CsvFilter()
    }

    @Test
    fun allow_for_correct_lines_only() {
        val lines = fileWithOneInvoiceLineHaving(concept = "a correct line with irrelevant data")
        val result = filter.apply(lines)

        assertThat(result).isEqualTo(lines)
    }

    @Test
    fun exclude_lines_with_both_tax_fields_populated_as_they_are_exclusive() {
        val result = filter.apply(
            fileWithOneInvoiceLineHaving(ivaTax = "19", igicTax = "8")
        )

        assertThat(result).isEqualTo(emptyDataFile)
    }

    @Test
    fun exclude_lines_with_both_tax_fields_empty_as_one_is_required() {
        val result = filter.apply(
            fileWithOneInvoiceLineHaving(ivaTax = emptyField, igicTax = emptyField)
        )

        assertThat(result).isEqualTo(emptyDataFile)
    }

    @Test
    fun exclude_lines_with_non_decimal_tax_fields() {
        val result = filter.apply(
            fileWithOneInvoiceLineHaving(ivaTax = "XYZ")
        )

        assertThat(result).isEqualTo(emptyDataFile)
    }

    @Test
    fun exclude_lines_with_both_tax_fields_populated_even_if_non_decimal() {
        val result = filter.apply(
            fileWithOneInvoiceLineHaving(ivaTax = "XYZ", igicTax = "12")
        )

        assertThat(result).isEqualTo(emptyDataFile)
    }

    private fun fileWithOneInvoiceLineHaving(
        ivaTax: String = "19",
        igicTax: String = emptyField,
        concept: String = "irrelevant"
    ): List<String> {
        val invoiceId = "1"
        val invoiceDate = "02/05/2019"
        val grossAmount = "1000"
        val netAmount = "810"
        val cif = "B76430134"
        val nif = emptyField
        val formattedLine = listOf(
            invoiceId,
            invoiceDate,
            grossAmount,
            netAmount,
            ivaTax,
            igicTax,
            concept,
            cif,
            nif
        ).joinToString(",")
        return listOf(headerLine, formattedLine)
    }
}
