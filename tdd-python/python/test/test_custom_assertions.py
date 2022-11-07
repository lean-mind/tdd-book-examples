from dataclasses import dataclass
from unittest import TestCase
from assertpy import assert_that


@dataclass
class ListMatchers:
    _actual: list[float]

    def is_exactly(self, *args) -> None:
        assert_that(args).is_length(len(self._actual))
        assert_that(args).is_subset_of(self._actual)


def assert_that_list(series: list[float]) -> ListMatchers:
    return ListMatchers(series)


class TestListMatchers(TestCase):

    def test_edge_cases(self):
        assert_that_list([1]).is_exactly(1)
        assert_that_list([]).is_exactly()
        assert_that_list([10, 20]).is_exactly(10, 20)
