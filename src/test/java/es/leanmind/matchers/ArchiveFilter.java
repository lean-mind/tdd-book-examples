package es.leanmind.matchers;

import java.io.File;

public class ArchiveFilter {
    public void apply(File file) throws Exception {
        if (file.length() == 0) {
            throw new IllegalFileException("File is empty");
        }
    }
}
