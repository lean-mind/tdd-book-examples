from hypothesis import given, assume
from hypothesis.strategies import text

from src.hash_text import hash_given


@given(text())
def test_hash_is_always_the_same_given_the_same_input(text):
    assert hash_given(text) == hash_given(text)


@given(text(), text())
def test_hash_is_different_for_each_input(text1, text2):
    assume(text1 != text2)
    assert hash_given(text1) != hash_given(text2)


@given(text())
def test_hash_has_always_the_same_fixed_length(text):
    assert len(hash(text)) == 10
