package examples

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CsvFilterShould {
    @Test
    fun allow_for_correct_lines_only() {
        val headerLine = "Num_factura, Fecha, Bruto, Neto, IVA, IGIC, Concepto, CIF_cliente, NIF_cliente"
        val invoiceLine = "1,02/05/2019,1000,810,19,,ACER Laptop,B76430134,"

        val result = CsvFilter().filter(listOf(headerLine, invoiceLine))

        assertThat(result).isEqualTo(listOf(headerLine, invoiceLine))
    }
}
