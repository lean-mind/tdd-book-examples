from unittest import TestCase
from assertpy import assert_that
from test.fixtures.diagnoses_builder import CasesWithDiagnoses


class TestDiagnosesExample(TestCase):
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
            CasesWithDiagnoses()
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

    @staticmethod
    def render_component_with(cases, diagnoses) -> None:
        pass

    @staticmethod
    def simulate_click_on_filter_checkbox(checkbox_name) -> None:
        pass

    @staticmethod
    def wait_for_cases_table_to_update_results() -> str:
        table_content = """
        <table>
            <tr>
                <td>
                    Chupito
                </td>
                <td>
                    Juliana
                </td>
            </tr>
        </table>
        """
        return table_content
