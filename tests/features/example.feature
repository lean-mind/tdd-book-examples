Feature: A B example

  Scenario: The system
    Given globally shared setup
     When behavior in certain way, we act A
     Then expect A behavior

    Scenario: context X is in place
      Given nested shared setup
       When behaves that way we act B
       Then expect B behavior
      Given nested shared setup
       When behaves that way we act C
       Then expect C behavior