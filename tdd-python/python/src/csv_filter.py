from typing import List


class CsvFilter:

    def filter(self, lines: List[str]) -> List[str]:
        result = []
        result.append(lines[0])
        invoice = lines[1]
        fields = [field for field in invoice.split(',')]
        if not fields[4] or not fields[5]:
            result.append(invoice)
        return result

