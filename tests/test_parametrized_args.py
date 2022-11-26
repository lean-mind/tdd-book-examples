import pytest
from assertpy import assert_that


def is_prime(n):
    for i in range(2, n):
        if (n % i) == 0:
            return False
    return True


@pytest.mark.parametrize("number", [1, 3, 5, 7, 11])
def test_recognizes_prime_numbers(number: int):
    assert_that(is_prime(number)).is_true()
