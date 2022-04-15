package examples

class CsvFilter {
    fun filter(lines: List<String>): List<String> {
        val result = mutableListOf<String>()
        result.add(lines[0])
        val invoice = lines[1]
        val fields = invoice.split(',')
        val ivaFieldIndex = 4
        val igicFieldIndex = 5
        val ivaField = fields[ivaFieldIndex]
        val igicField = fields[igicFieldIndex]
        val decimalRegex = "\\d+(\\.\\d+)?".toRegex()
        val taxFieldsAreMutuallyExclusive =
            (ivaField.matches(decimalRegex) || igicField.matches(decimalRegex)) &&
                (ivaField.isEmpty() || igicField.isEmpty())
        if (taxFieldsAreMutuallyExclusive){
            result.add(lines[1])
        }
        return result.toList()
    }
}
