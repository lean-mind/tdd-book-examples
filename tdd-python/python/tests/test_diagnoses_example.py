from unittest import TestCase
from assertpy import assert_that
from tests.fixtures.diagnoses_builder import CasesWithDiagnosesBuilder


class TestDiagnosesExample(TestCase):
    _component_content = ''
    _table_content = ''
    cases = []
    diagnoses = []

    def setUp(self) -> None:
        # some other initialization code goes around here...*
        return super().setUp()

    # ...
    # some other tests around here
    # ...

    def test_filters_cases_when_several_diagnosis_filters_are_applied_together(self):
        search_criterion_1 = "Cerebro"
        search_criterion_2 = "Vías Respiratorias Altas"
        discarded_location = "irrelevant"
        fixtures = (
            CasesWithDiagnosesBuilder()
            .having_diagnosis_with_location(search_criterion_1)
            .having_diagnosis_with_location(search_criterion_2)
            .having_diagnosis_with_location(discarded_location)
            .build()
        )
        self.render_component_with(fixtures.cases(), fixtures.diagnoses())

        self.simulate_click_on_filter_checkbox(search_criterion_1)
        self.simulate_click_on_filter_checkbox(search_criterion_2)

        table = self.wait_for_cases_table_to_update_results()
        assert_that(table).does_not_contain(
            fixtures.patient_name_given_diagnosis_location(discarded_location)
        )
        assert_that(table).contains(
            fixtures.patient_name_given_diagnosis_location(search_criterion_1)
        )
        assert_that(table).contains(
            fixtures.patient_name_given_diagnosis_location(search_criterion_2)
        )

    def render_component_with(self, cases: list[dict], diagnoses: list[dict]) -> None:
        table_content = []
        for c, d in zip(cases, diagnoses):
            table_content.append(f"{d.get('location')}:{c.get('patient_name')}")
        self._component_content = ",".join(table_content)

    def simulate_click_on_filter_checkbox(self, search_criteria: str) -> None:
        component_values = self._component_content.split(",")
        self._table_content += "".join(
            [c for c in component_values if search_criteria in c])

    def wait_for_cases_table_to_update_results(self) -> str:
        return self._table_content
