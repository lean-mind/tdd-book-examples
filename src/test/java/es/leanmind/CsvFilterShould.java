package es.leanmind;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CsvFilterShould {
    @Test
    void foo() {
        assertThat(Csv.filter(List.of())).isEqualTo(List.of());
    }
}
