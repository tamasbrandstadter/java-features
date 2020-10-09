package features.java10;

import java.util.Set;

public class Optionals {

    public static void main(String[] args) {
        Set<Integer> numbers = Set.of(1, 2, 3, 4, 5);

        // java.util.Optional, java.util.OptionalDouble, java.util.OptionalInt and java.util.OptionalLong each got a new method orElseThrow()
        // which doesn’t take any argument and throws NoSuchElementException if no value is present.
        // It’s synonymous with and is now the preferred alternative to the existing get() method.
        Integer firstEven = numbers.stream()
                .filter(i -> i % 2 == 0)
                .findFirst()
                .orElseThrow();
    }

}
