package es.leanmind;


import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CamelCaseShould {
    public static String toCamelCase(String text) {
        return Stream
                .of(text.split("/[ ,_-]/g"))
                .map(word -> word.substring(0, 1).toUpperCase() + word.substring(1))
                .collect(Collectors.joining(""));
    }

    @Test
    void converts_the_first_character_of_each_word_to_uppercase() {
        assertThat(toCamelCase("foo")).isEqualTo("Foo");
    }
}
