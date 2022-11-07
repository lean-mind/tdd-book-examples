from dataclasses import dataclass
from assertpy import assert_that
import pytest


@dataclass(frozen=True)
class File:
    file_name: str
    content: str


@dataclass
class Filter:
    @staticmethod
    def apply(file: File):
        assert_that(file.content).is_not_empty()

class TestFilter:
    empty_file = File("", "")
    filter = Filter()

    def test_should_fail_if_the_file_is_empty(self):
        try:
            self.filter.apply(self.empty_file)
        except Exception as e:
            pass  # Left blank intentionally

    @pytest.mark.xfail(raises=Exception)
    def test_should_fail_if_the_file_is_empty_2(self):
        self.filter.apply(self.empty_file)

    def test_should_fail_if_the_file_is_empty_3(self):
        assert_that(self.filter.apply).raises(
            Exception).when_called_with(self.empty_file)