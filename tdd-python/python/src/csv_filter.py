import re
from typing import List


class CsvFilter:

    def filter(self, lines: List[str]) -> List[str]:
        result = []
        result.append(lines[0])
        invoice = lines[1]
        fields = [field for field in invoice.split(',')]
        iva_field_index = 4
        igic_field_index = 5
        iva_field = fields[iva_field_index]
        igic_field = fields[igic_field_index]
        decimal_regex = re.compile("\\d+(\\.\\d+)?")
        tax_fields_are_mutually_exclusive = (
            decimal_regex.match(iva_field)
            or decimal_regex.match(igic_field)
        ) and (not iva_field or not igic_field)
        if tax_fields_are_mutually_exclusive:
            result.append(invoice)
        return result
