package es.leanmind.cryptography;

import net.jqwik.api.Assume;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.PropertyDefaults;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@PropertyDefaults(tries = 100)
public class HashShould {

    // Function under test:
    private static String hash(String text) {
        var fixedLength = 10;
        if (text.length() < fixedLength) {
            var valuesToAdd = Math.max(fixedLength - text.length(), 0);
            var randomValues = "1234567890".substring(0, valuesToAdd);
            return text + randomValues;
        }
        return text.substring(0, fixedLength);
    }

    // Tests:

    @Property
    public void be_always_the_same_given_the_same_input(@ForAll String text) {
        assertThat(hash(text)).isEqualTo(hash(text));
    }

    @Property
    public void be_different_for_each_input(@ForAll String text1, @ForAll String text2) {
        Assume.that(!text1.equals(text2));
        assertThat(hash(text1)).isNotEqualTo(hash(text2));
    }

    @Property
    public void had_always_the_same_fixed_length(@ForAll String text) {
        var fixedLength = 10;
        assertThat(hash(text).length()).isEqualTo(fixedLength);
    }

}