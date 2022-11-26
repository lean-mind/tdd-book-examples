from dataclasses import dataclass
from unittest import TestCase
from assertpy import assert_that


@dataclass
class Triangle:
    _base: int
    _altitude: int

    @staticmethod
    def create(base: int, altitude: int):
        return Triangle(base, altitude)

    def altitude(self) -> int:
        return self._altitude

    def base(self) -> int:
        return self._base


class TestTriangle(TestCase):

    def test_a_triangle_has_base_and_altitude(self):
        triangle = Triangle.create(10, 20)
        assert_that(triangle.base()).is_equal_to(10)
        assert_that(triangle.altitude()).is_equal_to(20)
