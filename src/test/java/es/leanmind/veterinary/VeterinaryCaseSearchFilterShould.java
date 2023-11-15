package es.leanmind.veterinary;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


class VeterinaryCaseSearchFilterShould {
    private List<Case> cases;
    private List<Diagnosis> diagnoses;

    @BeforeEach
    void setUp() {
        /* some other initialization code goes around here...*/
        cases = new ArrayList<>();
        cases.add(new Case(
                1,               // id
                "Chupito",          // patientName
                1,                  // diagnosisId
                "Calicivirus",      // diagnosisName
                List.of(new Note(   // publicNotes
                        1,          // id
                        "public"    // content
                )),
                List.of(new Note(   // privateNotes
                        2,          // id
                        "private"   // content
                ))
        ));
        cases.add(new Case(
                2,               // id
                "Juliana",          // patientName
                2,                  // diagnosisId
                "Epilepsia",        // diagnosisName
                new ArrayList<>(),  // publicNotes
                new ArrayList<>()   // privateNotes
        ));
        cases.add(new Case(
                3,               // id
                "Dinwell",          // patientName
                3,                  // diagnosisId
                "Otitis",           // diagnosisName
                new ArrayList<>(),  // publicNotes
                new ArrayList<>()   // privateNotes
        ));
        diagnoses = new ArrayList<>();
        diagnoses.add(new Diagnosis(
                1,               // id
                "Calicivirus",      // name
                "Vías Respiratorias Altas", // location
                "Respiratorio",     // system
                "Virus",            // origin
                "Gato"              // specie
        ));
        diagnoses.add(new Diagnosis(
                2,               // id
                "Epilepsia",        // name
                "Cerebro",          // location
                "Neurológico",      // system
                "Idiopatico",       // origin
                "Perro, Gato"       // specie
        ));
        diagnoses.add(new Diagnosis(
                3,               // id
                "Otitis",           // name
                "Oídos",            // location
                "Auditivo",         // system
                "Bacteria",         // origin
                "Perro, Gato"       // specie
        ));
        renderComponentWith(cases, diagnoses);
    }

    /*
      ...
      some other tests around here
      ...
    */
    @Test
    void filter_cases_when_several_diagnosis_filters_are_applied_together() {
        simulateClickOnFilterCheckbox("Cerebro");
        simulateClickOnFilterCheckbox("Vías Respiratorias Altas");

        var table = waitForCasesTableToUpdateResults();
        assertThat(table).extracting(Case::getPatientName).doesNotContain("Dinwell");
        assertThat(table).extracting(Case::getPatientName).contains("Chupito");
        assertThat(table).extracting(Case::getPatientName).contains("Juliana");
    }

    void renderComponentWith(List<Case> cases, List<Diagnosis> diagnoses) {
        // ...
    }

    void simulateClickOnFilterCheckbox(String filterName) {
        // ...
    }

    List<Case> waitForCasesTableToUpdateResults() {
        return cases.stream().filter(c -> !c.getPatientName().equals("Dinwell")).toList();
    }


}