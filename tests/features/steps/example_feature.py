from behave import given, when, then
from assertpy import assert_that


@given("globally shared setup")
def globally_shared_setup(context):
    context.global_setup = globally_shared_setup()


@when("behavior in certain way, we act A")
def behavior_in_certain_way(context):
    act_a()


@then("expect A behavior")
def expects_a_behavior(context):
    assert_that(context.failed).is_false()
    expect_a()


@given("nested shared setup")
def nested_setup(context):
    context.nested_setup = nested_shared_setup()


@when("behaves that way we act B")
def behavior_in_certain_way(context):
    act_b()


@then("expect B behavior")
def expects_a_behavior(context):
    assert_that(context.failed).is_false()
    expect_b()


@when("behaves that way we act C")
def behaves_that_way_we_act_c(context):
    act_c()


@then("expect C behavior")
def expects_c_behavior(context):
    assert_that(context.failed).is_false()
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
