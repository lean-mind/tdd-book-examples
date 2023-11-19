package es.leanmind.veterinary.fixtures;

import es.leanmind.veterinary.Case;
import es.leanmind.veterinary.Diagnosis;

import java.util.ArrayList;
import java.util.List;

public class CasesWithDiagnoses {
    private int id = 0;
    private final List<Diagnosis> theDiagnoses = new ArrayList<>();
    private final List<Case> theCases = new ArrayList<>();

    private Diagnosis aDiagnosisWith(int id, String location) {
        return new Diagnosis(id, "Irrelevant-name", location, "Irrelevant-system", "Irrelevant-origin", "Irrelevant-specie");
    }

    private Case aCaseWithDiagnosis(String patientName, Diagnosis diagnosis, int id) {
        return new Case(id, patientName, diagnosis.getId(), diagnosis.getName(), new ArrayList<>(), new ArrayList<>());
    }

    private void add(String locationName) {
        ++id;
        Diagnosis aDiagnosis = aDiagnosisWith(id, locationName);
        String randomPatientName = "Patient" + Math.random();
        Case aCase = aCaseWithDiagnosis(randomPatientName, aDiagnosis, id);
        theDiagnoses.add(aDiagnosis);
        theCases.add(aCase);
    }

    public static CasesWithDiagnosesBuilder builder() {
        return new CasesWithDiagnosesBuilder();
    }

    public List<Case> cases() {
        return new ArrayList<>(theCases);
    }

    public List<Diagnosis> diagnoses() {
        return new ArrayList<>(theDiagnoses);
    }

    public String patientNameGivenDiagnosisLocation(String name) {
        int diagnosisId = theDiagnoses.stream().filter(
                d -> d.getLocation().equals(name)
        ).findFirst().map(Diagnosis::getId).orElse(-1);

        return theCases.stream().filter(
                c -> c.getDiagnosisId() == diagnosisId
        ).findFirst().map(Case::patientName).orElse(null);

    }

    public static class CasesWithDiagnosesBuilder {
        private final CasesWithDiagnoses casesWithDiagnoses = new CasesWithDiagnoses();

        public CasesWithDiagnosesBuilder havingDiagnosisWithLocation(String locationName) {
            casesWithDiagnoses.add(locationName);
            return this;
        }

        public CasesWithDiagnoses build() {
            return casesWithDiagnoses;
        }
    }
}