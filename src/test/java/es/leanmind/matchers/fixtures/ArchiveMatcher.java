package es.leanmind.matchers.fixtures;

import org.assertj.core.api.AbstractAssert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ArchiveMatcher {
    // The object we want to compare
    public record Archive(String filename, String content) {
    }

    @Test
    public void custom_matcher() {
        var actualArchive = new Archive("A name", "Some content");
        var expectedArchive = new Archive("A name", "Some content");
        // Assertion usage:
        assertThat(actualArchive).isEquivalentTo(expectedArchive);
    }

    @Test
    public void custom_matcher_fails() {
        var actualArchive = new Archive("A name", "Some content");
        var expectedArchive = new Archive("A n2ame", "Some other content");
        // Assertion usage:
        assertThrows(AssertionError.class, () -> {
            assertThat(actualArchive).isEquivalentTo(expectedArchive);
        });
    }

    // Assertion implementation:
    private ArchiveAssert assertThat(Archive actual) {
        return new ArchiveAssert(actual);
    }

    public static class ArchiveAssert extends AbstractAssert<ArchiveAssert, Archive> {

        public ArchiveAssert(Archive actual) {
            super(actual, ArchiveAssert.class);
        }

        public ArchiveAssert isEquivalentTo(Archive archive) {
            if (!archive.filename.equals(actual.filename)) {
                failWithMessage("Archive names are different. Expected %s but was %s",
                        archive.filename, actual.filename);
            }
            if (!archive.content.equals(actual.content)) {
                failWithMessage("Archive content is different. Expected %s but was %s",
                        archive.content, actual.content);
            }
            return this;
        }
    }
}