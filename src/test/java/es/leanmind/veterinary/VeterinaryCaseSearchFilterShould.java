package es.leanmind.veterinary;

import es.leanmind.veterinary.fixtures.CasesWithDiagnoses;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


class VeterinaryCaseSearchFilterShould {

    private List<Case> cases;
    private List<Diagnosis> diagnoses;

    @BeforeEach
    void setUp() {
    }

    @Test
    void filter_cases_when_several_diagnosis_filters_are_applied_together() {
        var searchCriterion1 = "Cerebro";
        var searchCriterion2 = "Vías Respiratorias Altas";
        var discardedLocation = "irrelevant";

        var fixtures = casesWithDiagnoses()
                .havingDiagnosisWithLocation(searchCriterion1)
                .havingDiagnosisWithLocation(searchCriterion2)
                .havingDiagnosisWithLocation(discardedLocation)
                .build();

        renderComponentWith(fixtures.cases(), fixtures.diagnoses());

        simulateClickOnFilterCheckbox(searchCriterion1);
        simulateClickOnFilterCheckbox(searchCriterion2);

        var table = waitForCasesTableToUpdateResults();
        assertThat(table)
                .extracting(Case::patientName)
                .doesNotContain(fixtures.patientNameGivenDiagnosisLocation(discardedLocation));
        assertThat(table)
                .extracting(Case::patientName)
                .contains(fixtures.patientNameGivenDiagnosisLocation(searchCriterion1));
        assertThat(table).
                extracting(Case::patientName)
                .contains(fixtures.patientNameGivenDiagnosisLocation(searchCriterion2));
    }

    private CasesWithDiagnoses.CasesWithDiagnosesBuilder casesWithDiagnoses() {
        return CasesWithDiagnoses.builder();
    }

    void renderComponentWith(List<Case> cases, List<Diagnosis> diagnoses) {
        this.cases = cases;
        this.diagnoses = diagnoses;
    }

    void simulateClickOnFilterCheckbox(String filterName) {
        // ...
    }

    List<Case> waitForCasesTableToUpdateResults() {
        var diagnosesIds = diagnoses.stream().filter(
                d -> !d.getLocation().contains("irrelevant")
        ).map(Diagnosis::getId).toList();

        return cases.stream().filter(
                c -> diagnosesIds.contains(c.getDiagnosisId())
        ).toList();
    }


}