package features.java10;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class UnmodifiableCollections {

    public static void main(String[] args) {
        // Since Java 9 returns an unmodifiable list
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
        Map<String, Integer> integerMap = numbers.stream()
            .collect(Collectors.toMap(String::valueOf, Function.identity()));

        // In Java 10 java.util.List, java.util.Map and java.util.Set each got a new static method copyOf(Collection).
        // It returns the unmodifiable copy of the given Collection
        // Any attempt to modify such a collection would result in java.lang.UnsupportedOperationException runtime exception.
        List<Integer> copy = List.copyOf(numbers);
        Set<Integer> copySet = Set.copyOf(numbers);
        Map<String, Integer> copyOfIntegerMap = Map.copyOf(integerMap);

        // java.util.stream.Collectors get additional methods to collect a Stream into unmodifiable List, Map or Set
        // Any attempt to modify such a collection would result in java.lang.UnsupportedOperationException runtime exception.
        List<Integer> evenNumbers = numbers.stream()
            .filter(i -> i % 2 == 0)
            .collect(Collectors.toUnmodifiableList());
    }

}
