from unittest import TestCase


class TheSystem(TestCase):
    def setUp(self) -> None:
        globally_shared_setup()
        return super().setUp()

    def test_behaves_in_certain_way(self):
        act_a()
        expect_a()

    class GivenThatContextXIsInPlace(TestCase):
        def setUp(self) -> None:
            nested_shared_setup()
            return super().setUp()

        def test_behaves_that_way(self) -> None:
            act_b()
            expect_b()

        def test_behaves_in_some_other_way(self) -> None:
            act_c()
            expect_c()


def globally_shared_setup() -> None:
    pass


def nested_shared_setup() -> None:
    pass


def act_a() -> None:
    pass


def expect_a() -> None:
    pass


def act_b() -> None:
    pass


def expect_b() -> None:
    pass


def act_c() -> None:
    pass


def expect_c() -> None:
    pass
