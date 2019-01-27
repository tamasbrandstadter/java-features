package features.java11;

import java.util.List;

public class Optionals {

    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);

        // in Java 11 you can do !isPresent() like:
        boolean isEmpty = numbers.stream()
            .filter(x -> x > 6)
            .findFirst()
            .isEmpty();

        System.out.println(isEmpty);
    }

}
