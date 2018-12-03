package version.changes.java11;

import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class PatternAsPredicate {

    public static void main(String[] args) {
        String message = "I am so so happy today, and I am happy every day";
        Pattern pattern = Pattern.compile("\\s*[^\\p{IsAlphabetic}]+\\s*");

        // since Java 8 we could test as predicate if the pattern is found in a given input string
        Predicate<String> predicate = pattern.asPredicate();

        // reminder: splitAsStream() also introduced to Java 8
        Map<String, Long> wordCount = pattern.splitAsStream(message)
            .map(String::toLowerCase)
            .collect(Collectors.toUnmodifiableMap(Function.identity(), word -> 1L, Long::sum));

        wordCount.forEach((k, v) -> System.out.println("Key: " + k + " Value: " + v));

        // in Java 11 asMatchPredicate() was added which tests if the pattern matches a given input string
        Predicate<String> matchPredicate = pattern.asMatchPredicate();
    }

}
