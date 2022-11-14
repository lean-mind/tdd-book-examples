from unittest import TestCase


class TestSuiteWhenContext1(TestCase):

    def setUp(self) -> None:
        arrange_block_1()
        return super().setUp()

    def test_a(self):
        act_a()
        assert_a()

    def test_b(self):
        act_b()
        assert_b


class TestSuiteWhenContext2(TestCase):

    def setUp(self) -> None:
        arrange_block_2()
        return super().setUp()

    def test_c(self):
        act_c()
        assert_c

    def test_d(self):
        act_d()
        assert_d


def arrange_block_1() -> None:
    pass


def arrange_block_2() -> None:
    pass


def act_a() -> None:
    pass


def act_b() -> None:
    pass


def act_c() -> None:
    pass


def act_d() -> None:
    pass


def assert_a() -> None:
    pass


def assert_b() -> None:
    pass


def assert_c() -> None:
    pass


def assert_d() -> None:
    pass
