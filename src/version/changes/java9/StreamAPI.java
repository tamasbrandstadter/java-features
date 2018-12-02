package version.changes.java9;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamAPI {

    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // takeWhile
        numbers.stream()
            .takeWhile(n -> n <= 5)
            .forEach(System.out::println);

        // dropWhile
        numbers.stream()
            .dropWhile(n -> n <= 5)
            .forEach(System.out::println);

        // new overloaded iterate, just like a for loop
        Stream.iterate(0, x -> x < 100, x -> x + 1)
            .forEach(System.out::println);

        // instead of use filter(), we can use new ofNullable() method
        List<String> comments = Arrays.asList("Awesome", "Nice", null, "Great");
        comments.stream()
            .flatMap(Stream::ofNullable)
            .sorted()
            .forEach(System.out::println);

        Map<String, String> nickNames = new HashMap<>();
        nickNames.put("Frank", "Franky");
        nickNames.put("James", "JC");
        nickNames.put("Oscar", null);
        nickNames.put("Michael", "Mike");
        nickNames.put("Herb", "Herbie");
        nickNames.put("Elizabeth", "Liz");
        nickNames.put("Suzy", null);

        List<String> nick = nickNames.entrySet()
            .stream()
            .flatMap(entry -> Stream.ofNullable(entry.getValue()))
            .collect(Collectors.toList());
        System.out.println(nick);
    }

}
