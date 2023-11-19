package es.leanmind;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class PasswordStrengthValidatorShould {
    public static boolean isStrongPassword(String password) {
        return true;
    }

    @Test
    void consider_a_password_to_be_strong_when_all_requirements_are_met() {
        assertThat(isStrongPassword("1234abcdABCD_")).isTrue();
    }
}