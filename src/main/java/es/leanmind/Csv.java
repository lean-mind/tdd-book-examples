package es.leanmind;

import java.util.ArrayList;
import java.util.List;

public class Csv {
    public static List<String> filter(List<String> lines) {
        var result = new ArrayList<String>();
        result.add(lines.get(0));
        var invoiceLine = lines.get(1);
        var fields = invoiceLine.split(",");
        int ivaFieldIndex = 4;
        var ivaField = fields[ivaFieldIndex];
        int igicFieldIndex = 5;
        var igicField = fields[igicFieldIndex];
        var decimalRegex = "\\d+(\\.\\d+)?";
        var taxFieldsAreMutuallyExclusive =
                (ivaField.matches(decimalRegex) ||
                        igicField.matches(decimalRegex)) &&
                        (!(ivaField.matches(decimalRegex)
                                && igicField.matches(decimalRegex)));
        if (taxFieldsAreMutuallyExclusive) {
            result.add(invoiceLine);
        }
        return result;
    }
}
