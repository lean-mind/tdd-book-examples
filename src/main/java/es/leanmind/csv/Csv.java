package es.leanmind.csv;

import java.util.ArrayList;
import java.util.List;

public class Csv {
    public static List<String> filter(List<String> lines) {
        var correctLines = new ArrayList<String>();
        var headerLineIndex = 0;
        var headerLine = lines.get(headerLineIndex);
        correctLines.add(headerLine);
        var invoiceLine = lines.get(1);
        // TODO: Filter all lines not just the first one
        var fields = List.of(invoiceLine.split(","));
        var ivaFieldIndex = 4;
        var igicFieldIndex = 5;
        var ivaField = fields.get(ivaFieldIndex);
        var igicField = fields.get(igicFieldIndex);
        var decimalRegex = "\\d+(\\.\\d+)?";
        var oneOfTheTaxIsPopulated = (ivaField.isBlank() || igicField.isBlank());
        var taxesAreDecimalFormat =
                (ivaField.matches(decimalRegex)
                        || igicField.matches(decimalRegex));
        var taxFieldsAreMutuallyExclusive =
                taxesAreDecimalFormat && oneOfTheTaxIsPopulated;
        if (taxFieldsAreMutuallyExclusive) {
            correctLines.add(invoiceLine);
        }
        return correctLines;
    }
}
