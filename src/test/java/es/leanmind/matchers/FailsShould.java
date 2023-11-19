package es.leanmind.matchers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class FailsShould {

    private ArchiveFilter filter;
    private File emptyFile = new File("");

    @BeforeEach
    public void setUp() {
        filter = new ArchiveFilter();
    }

    @Test
    void should_fail_if_the_file_is_empty() {
        assertThrows(IllegalFileException.class, () -> {
            filter.apply(emptyFile);
        });
    }

}
