from assertpy import assert_that


def get_prime_factors_for(number: int) -> list[int]:
    factor = 2
    while number % factor != 0:
        factor += 1
    reminder = number / factor
    if reminder <= 1:
        return [factor]
    return [*[factor], *get_prime_factors_for(reminder)]


def test_knows_what_is_a_primer_number():
    assert_that(get_prime_factors_for(2)).is_equal_to([2])
    assert_that(get_prime_factors_for(3)).is_equal_to([3])


def test_produces_the_same_result_to_multiply_the_numbers_in_the_output_list():
    assert_that(get_prime_factors_for(2 * 2 * 2)).is_equal_to([2, 2, 2])


def test_orders_the_prime_factors_from_the_smallest_to_the_biggest():
    assert_that(get_prime_factors_for(5 * 7 * 11 * 3)).is_equal_to([3, 5, 7, 11])
