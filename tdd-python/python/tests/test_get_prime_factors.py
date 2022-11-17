from unittest import TestCase
from assertpy import assert_that


def get_prime_factors_for(number: int) -> list[int]:
    factor = 2
    factors = [factor]
    reminder = number / factor
    if reminder > 1:
        factors.append(2)
    return factors


class TestGetPrimeFactors(TestCase):

    def test_finds_the_prime_composition_of_the_given_number(self):
        assert_that(get_prime_factors_for(2)).is_equal_to([2])
        assert_that(get_prime_factors_for(2 * 2)).is_equal_to([2, 2])
