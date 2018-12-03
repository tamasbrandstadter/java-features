package version.changes.java9;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class CollectionsFactoryMethods {

    public static void main(String[] args) {
        List<String> trees = List.of("Oak", "Pine", "Teak");
        trees.forEach(System.out::println);

        Set<Integer> nums = Set.of(13,7,19,21);
        nums.forEach(System.out::println);

        Map<String, String> states = Map.of(
            "FL", "Florida",
            "CA", "California",
            "TX", "Texas");
        states.forEach((k,v) -> System.out.printf("Key = %s, Value = %s\n", k, v));

        Map<String, Integer> integerMap = Map.ofEntries(
            Map.entry("one", 1),
            Map.entry("two", 2),
            Map.entry("three", 3),
            Map.entry("four", 4),
            Map.entry("five", 5),
            Map.entry("six", 6),
            Map.entry("seven", 7),
            Map.entry("eight", 8),
            Map.entry("nine", 9),
            Map.entry("ten", 10),
            Map.entry("eleven", 11),
            Map.entry("twelve", 12)
        );
        integerMap.forEach((k,v) -> System.out.println(k + " " + v));

        // trees.add("Maple"); -> Trying to modify an immutable list, this would be UnsupportedOperationException
        // Set<Integer> dupElement = Set.of(1,2,3,1); -> Trying to create a set with duplicate elements, this would be IllegalArgumentException
        // List<String> nullElement = List.of("Maple", null); -> Trying to create a list will a null element, NullPointerException
    }

}
