class CasesWithDiagnoses:
    cases_to_use = []
    diagnoses_to_use = []
    _matched_diagnoses = []
    _matched_cases = []

    def __init__(self) -> None:
        self.cases_to_use = [
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
        self.diagnoses_to_use = [
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

    def having_diagnosis_with_location(self, search_criteria: str):
        matched_diagnoses = [
            d for d in self.diagnoses_to_use if d['location'] == search_criteria]
        if len(matched_diagnoses) > 0:
            self._matched_diagnoses.append(matched_diagnoses[0])
            id_of_diagnostic = matched_diagnoses[0]['id']
            matched_cases = [
                c for c in self.cases_to_use if c['id'] == id_of_diagnostic]
            self._matched_cases.append(matched_cases[0])
        return self

    def build(self):
        self.cases_to_use = self._matched_cases
        self.diagnoses_to_use = self._matched_diagnoses
        return self

    def cases(self) -> list:
        return self.cases_to_use

    def diagnoses(self) -> list:
        return self.diagnoses_to_use

    def patient_name_given_diagnosis_location(self, search_location: str) -> str:
        matched_diagnoses = [
            d for d in self.diagnoses_to_use if d['location'] == search_location]
        if len(matched_diagnoses) > 0:
            client_names = [c
                           for c in self.cases_to_use if c['id'] == matched_diagnoses[0]['id']]
            return client_names[0]['patientName']
        return "unknown_client_name"
