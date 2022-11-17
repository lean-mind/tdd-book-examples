import re
from unittest import TestCase
from assertpy import assert_that


def is_strong_password(password: str) -> bool:
    has_min_length = len(password) >= 6
    contains_digits = bool(re.search(r'\d', password))
    contains_lower_case = bool(re.search(r'[a-z]', password))
    contains_upper_case = bool(re.search(r'[A-Z]', password))
    contains_under_score = bool(re.search(r'[\_]', password))
    is_strong_password = (
        has_min_length
        and contains_digits
        and contains_lower_case
        and contains_upper_case
        and contains_under_score
    )
    return is_strong_password


class ThePasswordStrengthValidator(TestCase):

    def test_consider_a_password_to_be_strong_when_all_requirements_are_met(self):
        assert_that(is_strong_password("1234abcdABCD_")).is_true()

    def test_fails_when_password_is_too_short(self):
        assert_that(is_strong_password("1aA_")).is_false()

    def test_fails_when_password_is_missing_a_number(self):
        assert_that(is_strong_password("abcdABCD_")).is_false()

    def test_fails_when_password_not_contains_lowercase(self):
        assert_that(is_strong_password("1234ABCD_")).is_false()

    def test_fails_when_password_not_contains_uppercase(self):
        assert_that(is_strong_password("1234abcd")).is_false()

    def test_fails_when_password_not_contains_under_score(self):
        assert_that(is_strong_password("1234abcdABCD")).is_false()
