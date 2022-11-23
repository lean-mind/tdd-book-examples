import re
from assertpy import assert_that


def to_camel_case(text: str) -> str:
    words = re.split(r"[ ,_-]", text)
    words = [w[0].upper() + w[1:] for w in words]
    return "".join(words)


def test_converts_the_first_character_of_each_word_to_uppercase():
    assert_that(to_camel_case("foo")).is_equal_to("Foo")
    assert_that(to_camel_case("foo bar")).is_equal_to("FooBar")
