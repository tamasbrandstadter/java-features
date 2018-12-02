package version.changes.java10;

import java.util.List;
import java.util.stream.Collectors;

public class UnmodifiableCollections {

    public static void main(String[] args) {
        // Since Java 9 returns an unmodifiable list
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);

        // In Java 10 java.util.List, java.util.Map and java.util.Set each got a new static method copyOf(Collection).
        // It returns the unmodifiable copy of the given Collection
        // Any attempt to modify such a collection would result in java.lang.UnsupportedOperationException runtime exception.
        List<Integer> copy = List.copyOf(numbers);

        // java.util.stream.Collectors get additional methods to collect a Stream into unmodifiable List, Map or Set
        // Any attempt to modify such a collection would result in java.lang.UnsupportedOperationException runtime exception.
        List<Integer> evenNumbers = numbers.stream()
            .filter(i -> i % 2 == 0)
            .collect(Collectors.toUnmodifiableList());

        // java.util.Optional, java.util.OptionalDouble, java.util.OptionalInt and java.util.OptionalLong each got a new method orElseThrow()
        // which doesn’t take any argument and throws NoSuchElementException if no value is present.
        // It’s synonymous with and is now the preferred alternative to the existing get()method.
        Integer firstEven = numbers.stream()
            .filter(i -> i % 2 == 0)
            .findFirst()
            .orElseThrow();
    }

}
