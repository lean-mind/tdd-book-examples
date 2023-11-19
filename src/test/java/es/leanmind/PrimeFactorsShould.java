package es.leanmind;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PrimeFactorsShould {
    public static List<Integer> getPrimeFactorsFor(int number) {
        var factor = 2;
        while (number % factor != 0) {
            factor++;
        }
        var factors = new ArrayList<Integer>();
        factors.add(factor);
        var remainder = number / factor;
        if (remainder > 1) {
            factors.addAll(getPrimeFactorsFor(remainder));
        }
        return factors;
    }

    @Test
    void finds_the_prime_composition_of_the_given_number() {
        assertThat(getPrimeFactorsFor(2)).isEqualTo(List.of(2));
        assertThat(getPrimeFactorsFor(2 * 2)).isEqualTo(List.of(2, 2));
        assertThat(getPrimeFactorsFor(2 * 2 * 2)).isEqualTo(List.of(2, 2, 2));
        assertThat(getPrimeFactorsFor(3)).isEqualTo(List.of(3));
        assertThat(getPrimeFactorsFor(5 * 5)).isEqualTo(List.of(5, 5));
        assertThat(getPrimeFactorsFor(5 * 7 * 11 * 3)).isEqualTo(List.of(3, 5, 7, 11));
    }
}
