package es.leanmind;

import java.util.ArrayList;
import java.util.List;

public class Csv {
    public static List<String> filter(List<String> lines) {
        var header = lines.get(0);
        var correctLines = new ArrayList<String>();
        correctLines.add(header);
        var invoiceLine = lines.get(1);
        var fields = invoiceLine.split(",");
        var iva = fields[4];
        var igic = fields[5];
        var oneTaxIsPopulated = iva.isEmpty() || igic.isEmpty();
        var bothTaxesAreNotPopulated = !(iva.isEmpty() && igic.isEmpty());
        var taxIsDecimal = iva.matches("\\d+") || igic.matches("\\d+");
        if (oneTaxIsPopulated && bothTaxesAreNotPopulated && taxIsDecimal) {
            correctLines.add(invoiceLine);
        }
        return correctLines;
    }
}
