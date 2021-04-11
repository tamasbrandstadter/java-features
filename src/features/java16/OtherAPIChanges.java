package features.java16;

import java.net.http.HttpRequest;
import java.util.List;
import java.util.stream.Stream;

// The DateTimeFormatterBuilder class has a new method, appendDayPeriodText().
// This provides a way of formatting the time of day as “in the morning”, “in the afternoon”, etc. rather than just AM or PM.
// ByteBuffer, CharBuffer, DoubleBuffer, FloatBuffer, IntBuffer, LongBuffer and ShortBuffer all have a new put() method.
// These methods copy a defined length section of a second buffer, starting at a given offset in that buffer to a specified index
// in the buffer that the method is being called on.  That description seems complicated, but it is a simple replacement for the loop.
// Also:
public class OtherAPIChanges {

    public static void main(String[] args) {
        // Stream.toList as convenience for the most typical collection method (instead of relying on .collect(Collectors.toList()))
        List<String> result = Stream.of("one", "two", "three")
                .filter(s -> s.length() == 3)
                .toList();

        // Stream.mapMulti to replace each element of this stream with zero or more elements, an alternative to flatMap
        Stream.of(1, 2, 3, 4)
                .mapMulti((number, downstream) -> downstream.accept(String.valueOf(number)))
                .forEach(System.out::print);

        // New builder to HTTP client that specifies a header filter
        // ie remove foo-bar header
        HttpRequest.newBuilder(HttpRequest.newBuilder().build(), (name, value) -> !name.equalsIgnoreCase("Foo-Bar"));
    }
}
