package es.leanmind;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PrimeFactorsShould {
    public static List<Integer> getPrimeFactorsFor(int number) {
        return List.of(2);
    }

    @Test
    void finds_the_prime_composition_of_the_given_number() {
        assertThat(getPrimeFactorsFor(2)).isEqualTo(List.of(2));
        assertThat(getPrimeFactorsFor(2 * 2)).isEqualTo(List.of(2, 2));
    }
}
