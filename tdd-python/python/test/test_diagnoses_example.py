from unittest import TestCase
from assertpy import assert_that


class TestDiagnosesExample(TestCase):
    cases = []
    diagnoses = []

    def setUp(self) -> None:
        # some other initialization code goes around here...*
        cases = [
            {
                "id": 1,
                "patientName": "Chupito",
                "diagnosisId": 1,
                "diagnosisName": "Calicivirus",
                "publicNotes": [{"id": 1, "content": "public"}],
                "privateNotes": [{"id": 2, "content": "private"}],
            },
            {
                "id": 2,
                "patientName": "Juliana",
                "diagnosisId": 2,
                "diagnosisName": "Epilepsia",
                "publicNotes": [],
                "privateNotes": [],
            },
            {
                "id": 3,
                "patientName": "Dinwell",
                "diagnosisId": 3,
                "diagnosisName": "Otitis",
                "publicNotes": [],
                "privateNotes": [],
            }
        ]
        diagnoses = [
            {
                "id": 1,
                "name": "Calicivirus",
                "location": "Vías Respiratorias Altas",
                "system": "Respiratorio",
                "origin": "Virus",
                "specie": "Gato",
            },
            {
                "id": 2,
                "name": "Epilepsia",
                "location": "Cerebro",
                "system": "Neurológico",
                "origin": "Idiopatico",
                "specie": "Perro, Gato",
            },
            {
                "id": 3,
                "name": "Otitis",
                "location": "Oídos",
                "system": "Auditivo",
                "origin": "Bacteria",
                "specie": "Perro, Gato",
            },
        ]
        self.renderComponentWith(cases, diagnoses)
        return super().setUp()

    # ...
    # some other tests around here
    # ...

    def test_filters_cases_when_several_diagnosis_filters_are_applied_together(self):
        self.simulateClickOnFilterCheckbox("Cerebro")
        self.simulateClickOnFilterCheckbox("Vías Respiratorias Altas")

        table = self.waitForCasesTableToUpdateResults()
        assert_that(table).does_not_contain("Dinwell")
        assert_that(table).contains("Chupito")
        assert_that(table).contains("Juliana")

    @staticmethod
    def renderComponentWith(cases, diagnoses) -> None:
        pass

    @staticmethod
    def simulateClickOnFilterCheckbox(checkbox_name) -> None:
        pass

    @staticmethod
    def waitForCasesTableToUpdateResults() -> str:
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
