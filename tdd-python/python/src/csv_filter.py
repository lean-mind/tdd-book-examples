import re
from typing import List


class CsvFilter:

    def filter(self, lines: List[str]) -> List[str]:
        result = []
        result.append(lines[0])
        invoice = lines[1]
        fields = [field for field in invoice.split(',')]
        ivaFieldIndex = 4
        igicFieldIndex = 5
        ivaField = fields[ivaFieldIndex]
        igicField = fields[igicFieldIndex]
        decimalRegex = re.compile("\\d+(\\.\\d+)?")
        taxFieldsAreMutuallyExclusive = (decimalRegex.match(ivaField)
                                         or decimalRegex.match(igicField))\
            and not (decimalRegex.match(ivaField)
                     and decimalRegex.match(igicField))
        if taxFieldsAreMutuallyExclusive:
            result.append(invoice)
        return result
