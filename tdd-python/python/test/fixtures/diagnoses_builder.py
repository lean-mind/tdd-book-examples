import random
from dataclasses import dataclass


@dataclass(frozen=True)
class CasesWithDiagnoses:
    _diagnoses: list[dict]
    _cases: list[dict]

    def patient_name_given_diagnosis_location(self, location_name: str) -> str:
        diagnostic_id = [d for d in self._diagnoses if d.get(
            'location') == location_name][0].get('id')
        associated_case = [c for c in self._cases if c.get(
            'diagnostic_id') == diagnostic_id][0]
        return associated_case.get('patient_name', 'name_not_found')

    def cases(self) -> list[dict]:
        return self._cases

    def diagnoses(self) -> list[dict]:
        return self._diagnoses


class CasesWithDiagnosesBuilder:
    _id = 0
    _cases = []
    _diagnoses = []

    def having_diagnosis_with_location(self, location_name: str):
        self._add(location_name)
        return self

    def build(self) -> CasesWithDiagnoses:
        return CasesWithDiagnoses(self._diagnoses, self._cases)

    def _add(self, location_name: str) -> None:
        self._id += 1
        diagnostic = self._diagnostic_with_location(self._id, location_name)
        random_patient_name = f"Patient {random.randint(0, 10)}"
        case = self._case_with_diagnostic(random_patient_name, diagnostic, self._id)
        self._cases.append(case)
        self._diagnoses.append(diagnostic)

    @staticmethod
    def _diagnostic_with_location(id: int, location_name: str) -> dict:
        return {
            "id": id,
            "name": "irrelevant_name",
            "location": location_name,
            "system": "irrelevant_system",
            "origin": "irrelevant_origin",
            "specie": "irrelevant_specie"
        }

    @staticmethod
    def _case_with_diagnostic(patient_name: str, diagnostic: dict, id: int) -> dict:
        return {
            "id": id,
            "patient_name": patient_name,
            "diagnostic_id": diagnostic.get('id'),
            "diagnostic_name": diagnostic.get('name'),
            "public_notes": [],
            "private_notes": []
        }
