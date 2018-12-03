package version.changes.java11;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileIO {

    public static void main(String[] args) throws IOException {
        /* If you need to read from a large file, you usually use Files::lines to get a lazy stream of its content.
        Likewise, for writing a lot of content that may not be present in memory all at once, you use Files::write by passing it an Iterable<String>.
        But what about the easy case where you can handle the entire content as a simple string?
        That hasn’t been terribly convenient because Files::readAllBytes and the matching overload for Files::write operate with byte arrays.
        If need be, you can also pass a CharSet to readString and OpenOptions to writeString.
        */
        String haiku = Files.readString(Path.of("haiku.txt"));
        String modified = haiku + "foo";
        Files.writeString(Path.of("haiku-mod.txt"), modified);

        // Need an OutputStream that discards input bytes? Need an empty InputStream?
        // What about Reader and Writer that do nothing? Java 11 has got you covered:
        InputStream input = InputStream.nullInputStream();
        OutputStream output = OutputStream.nullOutputStream();
        Reader reader = Reader.nullReader();
        Writer writer = Writer.nullWriter();
    }

}
