package es.leanmind;


import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CamelCaseShould {
    public static String toCamelCase(String text) {
        var words = text.split("/[ ,_-]/g");
        var word = words[0];
        word = word.substring(0, 1).toUpperCase() + word.substring(1);
        words[0] = word;
        return String.join("", words);
    }

    @Test
    void converts_the_first_character_of_each_word_to_uppercase() {
        assertThat(toCamelCase("foo")).isEqualTo("Foo");
    }
}
