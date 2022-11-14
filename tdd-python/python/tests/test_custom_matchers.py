from assertpy import add_extension
from dataclasses import dataclass
from assertpy import assert_that, fail

# The object we want to compare


@dataclass
class Archive:
    file_name: str
    content: str

# Assertion implementation


def is_equivalent_to(self, actual: Archive):
    if self.val.file_name != actual.file_name:
        raise Exception(
            f"Archive names are different. Expected {self.val.file_name} but was {actual.file_name}")
    if self.val.content != actual.content:
        raise Exception(
            f"Archive content is different. Expected {self.val.file_name} but was {actual.file_name}")
    return self


add_extension(is_equivalent_to)


# Assertion usage
assert_that(Archive("1", "")).is_equivalent_to(Archive("1", ""))


def test_different_contents():
    try:
        assert_that(Archive("1", "")).is_equivalent_to(Archive("1", "1"))
        fail('should have raised error')
    except Exception as e:
        assert_that(str(e)).contains("Archive content is different")


def test_different_file_names():
    try:
        assert_that(Archive("1", "")).is_equivalent_to(Archive("not_equal", ""))
        fail('should have raised error')
    except Exception as e:
        assert_that(str(e)).contains("Archive names are different")
