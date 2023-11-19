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
    void knows_what_is_a_prime_number() {
        assertThat(getPrimeFactorsFor(2)).isEqualTo(List.of(2));
        assertThat(getPrimeFactorsFor(3)).isEqualTo(List.of(3));
    }

    @Test
    void produces_the_same_result_to_multiply_the_numbers_in_the_output_list() {
        assertThat(getPrimeFactorsFor(2 * 2 * 2)).isEqualTo(List.of(2, 2, 2));
    }

    @Test
    void orders_the_prime_factors_from_the_smallest_to_the_biggest() {
        assertThat(getPrimeFactorsFor(5 * 7 * 11 * 3)).isEqualTo(List.of(3, 5, 7, 11));
    }
}
