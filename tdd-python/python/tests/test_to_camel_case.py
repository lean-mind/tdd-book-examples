from assertpy import assert_that

def to_camel_case(text: str) -> str:
    words = text.split(r"[ ,_-]")
    word = words[0]
    word = word[0].upper() + word[1:]
    words[0] = word
    return "".join(words)

def test_converts_the_first_character_of_each_word_to_uppercase():
    assert_that(to_camel_case("foo")).is_equal_to("Foo")