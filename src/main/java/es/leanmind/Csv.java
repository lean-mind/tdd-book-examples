package es.leanmind;

import java.util.ArrayList;
import java.util.List;

public class Csv {
    public static List<String> filter(List<String> lines) {
        var result = new ArrayList<String>();
        result.add(lines.get(0));
        var invoiceLine = lines.get(1);
        var fields = invoiceLine.split(",");
        var iva = fields[4];
        var igic = fields[5];
        if (iva.isBlank() || igic.isBlank()) {
            result.add(invoiceLine);
        }
        return result;
    }
}
