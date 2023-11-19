package es.leanmind;


import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CamelCaseShould {
    public static String toCamelCase(String text) {
        String[] words = text.split("/[ ,_-]/g");
        return String.join("", words);
    }

    @Test
    void converts_the_first_character_of_each_word_to_uppercase() {
        assertThat(toCamelCase("foo")).isEqualTo("Foo");
    }
}
