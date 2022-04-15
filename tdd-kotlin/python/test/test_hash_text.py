from hypothesis import given, assume
from hypothesis.strategies import text
from assertpy import assert_that

from src.hash_text import hash


@given(text())
def test_hash_is_always_the_same_given_the_same_input(text):
    assert_that(hash(text)).is_equal_to(hash(text))


@given(text(), text())
def test_hash_is_different_for_each_input(text1, text2):
    assume(text1 != text2)
    assert_that(hash(text1)).is_not_equal_to(hash(text2))


@given(text())
def test_hash_has_always_the_same_fixed_length(text):
    sha_256_length = 64
    assert_that(len(hash(text))).is_equal_to(sha_256_length)