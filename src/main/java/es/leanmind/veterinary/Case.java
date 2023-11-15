package es.leanmind.veterinary;

import java.util.List;

public class Case {
    private final String patientName;

    public Case(int id, String patientName, int diagnosisId, String diagnosisName, List<Note> publicNotes, List<Note> privateNotes) {
        this.patientName = patientName;
    }

    public String getPatientName() {
        return this.patientName;
    }
}
