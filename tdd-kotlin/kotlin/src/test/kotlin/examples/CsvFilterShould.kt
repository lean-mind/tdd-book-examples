package examples

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CsvFilterShould {
    val headerLine = "Num_factura, Fecha, Bruto, Neto, IVA, IGIC, Concepto, CIF_cliente, NIF_cliente"

    @Test
    fun allow_for_correct_lines_only() {
        val invoiceLine = "1,02/05/2019,1000,810,19,,ACER Laptop,B76430134,"

        val result = CsvFilter().filter(listOf(headerLine, invoiceLine))

        assertThat(result).isEqualTo(listOf(headerLine, invoiceLine))
    }

    @Test
    fun exclude_lines_with_both_tax_fields_populated_as_they_are_exclusive() {
        val invoiceLine = "1,02/05/2019,1000,810,19,8,ACER Laptop,B76430134,"

        val result = CsvFilter().filter(listOf(headerLine, invoiceLine))

        assertThat(result).isEqualTo(listOf(headerLine))
    }

    @Test
    fun exclude_lines_with_both_tax_fields_empty_as_one_is_required() {
        val invoiceLine = "1,02/05/2019,1000,810,,,ACER Laptop,B76430134,"

        val result = CsvFilter().filter(listOf(headerLine, invoiceLine))

        assertThat(result).isEqualTo(listOf(headerLine))
    }

    @Test
    fun exclude_lines_with_non_decimal_tax_fields() {
        val invoiceLine = "1,02/05/2019,1000,810,XYZ,,ACER Laptop,B76430134,"

        val result = CsvFilter().filter(listOf(headerLine, invoiceLine))

        assertThat(result).isEqualTo(listOf(headerLine))
    }
}
