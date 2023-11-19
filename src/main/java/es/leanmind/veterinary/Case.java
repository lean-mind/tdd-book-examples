package es.leanmind.veterinary;

import java.util.List;

public class Case {
    private final String patientName;
    private final int diagnosisId;

    public Case(int id, String patientName, int diagnosisId, String diagnosisName, List<Note> publicNotes, List<Note> privateNotes) {
        this.patientName = patientName;
        this.diagnosisId = diagnosisId;
    }

    public String patientName() {
        return this.patientName;
    }

    public int getDiagnosisId() {
        return this.diagnosisId;
    }

}
