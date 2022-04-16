package leanmind.examples

import org.assertj.core.api.AbstractAssert
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.AssertionError

// The object we want to compare
class Archive(var filename: String, var content: String)

fun assertThat(actual: Archive): ArchiveAssert {
    return ArchiveAssert(actual)
}

class ArchiveAssert(actual: Archive?) : AbstractAssert<ArchiveAssert, Archive>
    (actual, ArchiveAssert::class.java) {

    fun isEquivalentTo(archive: Archive): ArchiveAssert {
        if (archive.filename != actual.filename) {
            failWithMessage(
                "Archive names are different. Expected %s but was %s",
                archive.filename, actual.filename
            )
        }
        if (archive.content != actual.content) {
            failWithMessage(
                "Archive content is different. Expected %s but was %s",
                archive.content, actual.content
            )
        }
        return this
    }
}

class ArchiveAssertShould {
    @Test
    fun compare_both_archives() {
        assertThat(
            Archive(
                filename = "file1",
                content = "text"
            )
        ).isEquivalentTo(
            Archive(
                filename = "file1",
                content = "text"
            )
        )

        assertThrows<AssertionError> {
            assertThat(
                Archive(
                    filename = "file1",
                    content = "text"
                )
            ).isEquivalentTo(
                Archive(
                    filename = "different_name",
                    content = "different_content"
                )

            )
        }
    }
}
