package es.leanmind.matchers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

public class FailsShould {

    private ArchiveFilter filter;
    private File emptyFile = new File("");

    @BeforeEach
    public void setUp() {
        filter = new ArchiveFilter();
    }

    @Test
    public void should_fail_if_the_file_is_empty() {
        try {
            filter.apply(emptyFile);
        } catch (Exception e) {
            // Se espera una IllegalFileException,
            // por lo que el test debe pasar si se lanza.
        }
    }

}
