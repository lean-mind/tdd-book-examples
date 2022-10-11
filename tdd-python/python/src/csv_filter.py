from typing import List


class CsvFilter:

    def filter(self, lines: List[str]) -> List[str]:
        result = []
        result.append(lines[0])
        invoice = lines[1]
        ivaFieldIndex = 4
        igicFieldIndex = 5
        fields = [field for field in invoice.split(',')]
        if (not fields[ivaFieldIndex] or not fields[igicFieldIndex])\
                and not (not fields[ivaFieldIndex]
                         and not fields[igicFieldIndex]):
            result.append(invoice)
        return result
