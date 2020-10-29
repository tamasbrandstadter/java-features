package features.java12;

import java.util.stream.Collectors;
import java.util.stream.Stream;

// https://docs.oracle.com/en/java/javase/12/docs/api/java.base/java/util/stream/Collectors.html#teeing(java.util.stream.Collector,java.util.stream.Collector,java.util.function.BiFunction)
public class Streams {

    // Teeing is used for creating a Collector that is a composite of two downstream collectors
    public Double avg() {
        return Stream.of(1, 4, 2, 7, 4, 6, 5)
                .collect(Collectors.teeing(Collectors.summingDouble(i -> i), Collectors.counting(), (sum, n) -> sum / n));
    }

}
