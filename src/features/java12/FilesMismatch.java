package features.java12;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

// https://docs.oracle.com/en/java/javase/12/docs/api/java.base/java/nio/file/Files.html
public class FilesMismatch {

    // Find the first mismatched byte in the content of two files
    public static long findFirstMismatch() throws IOException {
        return Files.mismatch(Path.of("haiku.txt"), Path.of("haiku2.txt"));
    }

}
