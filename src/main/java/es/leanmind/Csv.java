package es.leanmind;

import java.util.List;

public class Csv {
    public static List<String> filter(List<String> lines) {
        return List.of(lines.get(0), lines.get(1));
    }
}
