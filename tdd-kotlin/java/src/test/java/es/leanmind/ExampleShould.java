package es.leanmind;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ExampleShould {
    @Test
    void assert_my_first_test() {
        assertThat(new Example().foo()).isTrue();
    }
}
